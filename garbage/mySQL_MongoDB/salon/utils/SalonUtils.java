package temp.salon.utils;

import com.ipet.web.member.repositories.MemberRepository;
import com.ipet.web.member.repositories.PetRepository;
import com.ipet.web.staff.repositories.StaffRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Yu-Jing
 * @create 2023-01-23-下午 08:51
 */
@Component
public class SalonUtils {
    private static SalonAppointmentRepository salonAppointmentRepository;
    private static SalonAppointmentDetailRepository salonAppointmentDetailRepository;
    private static SalonServiceRepository salonServiceRepository;
    private static SalonSaleRepository salonSaleRepository;
    private static SalonSaleDetailRepository salonSaleDetailRepository;
    private static MemberRepository memberRepository;
    private static SalonScheduleRepository salonScheduleRepository;
    private static PetRepository petRepository;
    private static StaffRepository staffRepository;
    private static SalonAppointmentQueryRepository appointmentQueryRepository;
    private static SalonScheduleQueryRepository salonScheduleQueryRepository;

    @Autowired
    public  void setSalonAppointmentRepository(SalonAppointmentRepository salonAppointmentRepository) {
        SalonUtils.salonAppointmentRepository = salonAppointmentRepository;
    }

    @Autowired
    public  void setSalonAppointmentDetailRepository(SalonAppointmentDetailRepository salonAppointmentDetailRepository) {
        SalonUtils.salonAppointmentDetailRepository = salonAppointmentDetailRepository;
    }
    @Autowired
    public  void setSalonServiceRepository(SalonServiceRepository salonServiceRepository) {
        SalonUtils.salonServiceRepository = salonServiceRepository;
    }
    @Autowired
    public  void setSalonSaleRepository(SalonSaleRepository salonSaleRepository) {
        SalonUtils.salonSaleRepository = salonSaleRepository;
    }
    @Autowired
    public  void setSalonSaleDetailRepository(SalonSaleDetailRepository salonSaleDetailRepository) {
        SalonUtils.salonSaleDetailRepository = salonSaleDetailRepository;
    }
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        SalonUtils.memberRepository = memberRepository;
    }
    @Autowired
    public  void setSalonScheduleRepository(SalonScheduleRepository salonScheduleRepository) {
        SalonUtils.salonScheduleRepository = salonScheduleRepository;
    }
    @Autowired
    public  void setPetRepository(PetRepository petRepository) {
        SalonUtils.petRepository = petRepository;
    }
    @Autowired
    public  void setStaffRepository(StaffRepository staffRepository) {
        SalonUtils.staffRepository = staffRepository;
    }
    @Autowired
    public  void setAppointmentQueryRepository(SalonAppointmentQueryRepository appointmentQueryRepository) {
        SalonUtils.appointmentQueryRepository = appointmentQueryRepository;
    }
    @Autowired
    public  void setSalonScheduleQueryRepository(SalonScheduleQueryRepository salonScheduleQueryRepository) {
        SalonUtils.salonScheduleQueryRepository = salonScheduleQueryRepository;
    }

    public static SalonAppointmentQuery integrateSalonAppointmentQuery(SalonAppointment appointment){
        SalonAppointmentQuery salonAppointmentQuery = new SalonAppointmentQuery();

        salonAppointmentQuery.setId(appointment.getApmId());
        salonAppointmentQuery.setApmBuildTime(new Date(appointment.getApmBuildTime().getTime()));
        salonAppointmentQuery.setApmStatus(appointment.getApmStatus());
        salonAppointmentQuery.setCustomerNote(appointment.getCustomerNote());
        salonAppointmentQuery.setTotalPrice(appointment.getTotalPrice());

        // {"id": new NumberInt("1"), "name": "連大戰"}
        Document mem = new Document();
        mem.append("id", appointment.getMemId());
        mem.append("name", Objects.requireNonNull(memberRepository.findById(appointment.getMemId()).orElse(null)).getMemName());
        salonAppointmentQuery.setMem(mem);

        // {"id": new NumberInt("1"), "name": "Micky"}
        Document pet = new Document();
        pet.append("id", appointment.getPetId());
        pet.append("name", Objects.requireNonNull(petRepository.findById(appointment.getPetId()).orElse(null)).getPetName());
        salonAppointmentQuery.setPet(pet);

        // {"id": new NumberInt("1"), "date": "2022-12-26", "period": "上午"}
        Document sch = new Document();
        sch.append("id", appointment.getSchId());
        sch.append("date", Objects.requireNonNull(salonScheduleRepository.findById(appointment.getSchId()).orElse(null)).getSchDate());
        sch.append("period", Objects.requireNonNull(salonScheduleRepository.findById(appointment.getSchId()).orElse(null)).getSchPeriod());
        salonAppointmentQuery.setSch(sch);

        //[{"id": new NumberInt("5"), "svcName": , "svcContent": ,  "svcPrice": new NumberInt("2400"),
        // "svcStatus": new NumberInt("1"),
        // "sale": {"id": new NumberInt("2"), "saleName": , "startTime": "2022-12-25 00:00:00", "endTime": "2023-03-31 23:59:59",
        // "salePrice": new NumberInt("2350")}}]
        List<Document> detail = new ArrayList<>();
        for (SalonAppointmentDetail salonAppointmentDetail : salonAppointmentDetailRepository.findAllByApmId(appointment.getApmId())) {
            Document doc = new Document();
            Integer svcId = salonAppointmentDetail.getSvcId();
            Integer saleId = salonAppointmentDetail.getSaleId();
            doc.append("id", salonAppointmentDetail.getSvcId());
            doc.append("svcName", Objects.requireNonNull(salonServiceRepository.findById(svcId).orElse(null)).getSvcName());
            doc.append("svcContent", Objects.requireNonNull(salonServiceRepository.findById(svcId).orElse(null)).getSvcContent());
            doc.append("svcPrice", Objects.requireNonNull(salonServiceRepository.findById(svcId).orElse(null)).getSvcPrice());
            doc.append("svcStatus", Objects.requireNonNull(salonServiceRepository.findById(svcId).orElse(null)).getSvcStatus());

            if ( saleId != null) {
                Document docSale = new Document();
                docSale.append("id", saleId);
                docSale.append("saleName", Objects.requireNonNull(salonSaleRepository.findById(saleId).orElse(null)).getSaleName());
                docSale.append("startTime", Objects.requireNonNull(salonSaleRepository.findById(saleId).orElse(null)).getStartTime());
                docSale.append("endTime", Objects.requireNonNull(salonSaleRepository.findById(saleId).orElse(null)).getEndTime());
                docSale.append("salePrice", Objects.requireNonNull(salonSaleDetailRepository.findBySaleIdAndAndSvcId(saleId, svcId).getSalePrice()));
                doc.append("sale", docSale);
            }
            detail.add(doc);
        }
        salonAppointmentQuery.setAppointDetail(detail);
        return salonAppointmentQuery;
    }

    public static SalonScheduleQuery integrateSalonScheduleQuery(SalonSchedule salonSchedule){
        SalonScheduleQuery salonScheduleQuery = new SalonScheduleQuery();
        Date  utilDate = new Date(salonSchedule.getSchDate().getTime());
        salonScheduleQuery.setId(salonSchedule.getSchId());
        salonScheduleQuery.setAppointId(salonSchedule.getAppointId());
        salonScheduleQuery.setEmployeeNote(salonSchedule.getEmployeeNote());
        salonScheduleQuery.setPeriod(salonSchedule.getSchPeriod());
        salonScheduleQuery.setDate(utilDate);


        // {"_id": new NumberInt("3"), "name": "陳大壯"}
        Document groomer = new Document();
        groomer.append("id", salonSchedule.getGroomerId());
        groomer.append("name", Objects.requireNonNull(staffRepository.findById(salonSchedule.getGroomerId()).orElse(null)).getStaffName());
        salonScheduleQuery.setGroomer(groomer);

        Document asst1 = new Document();
        asst1.append("id", salonSchedule.getAsstId1());
        asst1.append("name", Objects.requireNonNull(staffRepository.findById(salonSchedule.getAsstId1()).orElse(null)).getStaffName());
        salonScheduleQuery.setAsst1(asst1);

        Document asst2 = new Document();
        asst2.append("id", salonSchedule.getAsstId2());
        asst2.append("name", Objects.requireNonNull(staffRepository.findById(salonSchedule.getAsstId2()).orElse(null)).getStaffName());
        salonScheduleQuery.setAsst2(asst2);

        return salonScheduleQuery;
    }
}
