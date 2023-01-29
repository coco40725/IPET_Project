package com.ipet.web.salon.utils;

import com.ipet.web.member.repositories.MemberRepository;
import com.ipet.web.member.repositories.PetRepository;
import com.ipet.web.salon.entities.*;
import com.ipet.web.salon.repositories.*;
import com.ipet.web.staff.repositories.StaffRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
    private final SalonServiceCategoryRepository salonServiceCategoryRepository;
    private final SalonServicePetTypeRepository salonServicePetTypeRepository;

    public SalonUtils(SalonServiceCategoryRepository salonServiceCategoryRepository,
                      SalonServicePetTypeRepository salonServicePetTypeRepository) {
        this.salonServiceCategoryRepository = salonServiceCategoryRepository;
        this.salonServicePetTypeRepository = salonServicePetTypeRepository;
    }


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

    public  List<SalonAppointment> joinAppointment (List<SalonAppointment> appointments){
        for (SalonAppointment appointment : appointments){
            appointment.setMem(memberRepository.findById(appointment.getMemId()).orElse(null));
            appointment.setPet(petRepository.findById(appointment.getPetId()).orElse(null));
            appointment.setSch(salonScheduleRepository.findById(appointment.getSchId()).orElse(null));
            appointment.setSalonAppointmentDetails(salonAppointmentDetailRepository.findAllByApmId(appointment.getApmId()));
        }
        return appointments;
    }

    public List<SalonAppointmentDetail> joinSalonAppointmentDetail(List<SalonAppointmentDetail> salonAppointmentDetails){
        for (SalonAppointmentDetail detail : salonAppointmentDetails){
            detail.setSalonSale(salonSaleRepository.findById(detail.getSaleId()).orElse(null));
            detail.setSalonService(salonServiceRepository.findById(detail.getSvcId()).orElse(null));
        }
        return salonAppointmentDetails;
    }
    public List<SalonSaleDetail> joinSaleDetail(List<SalonSaleDetail> salonSaleDetails){
        for (SalonSaleDetail detail : salonSaleDetails){
            detail.setSalonService(salonServiceRepository.findById(detail.getSvcId()).orElse(null));
            detail.setSalonSale(salonSaleRepository.findById(detail.getSaleId()).orElse(null));
        }
        return salonSaleDetails;
    }

    public  List<SalonSchedule> joinSchedule (List<SalonSchedule> salonSchedules){
        for (SalonSchedule salonSchedule : salonSchedules){
            salonSchedule.setGroomer(staffRepository.findById(salonSchedule.getGroomerId()).orElse(null));
            salonSchedule.setAsst1(staffRepository.findById(salonSchedule.getAsstId1()).orElse(null));
            salonSchedule.setAsst2(staffRepository.findById(salonSchedule.getAsstId2()).orElse(null));
            salonSchedule.setSalonAppointment(salonAppointmentRepository.findBySchId(salonSchedule.getSchId()));
        }

        return salonSchedules;
    }

    public List<SalonService> joinService (List<SalonService> salonServices){
        for (SalonService salonService : salonServices){
            salonService.setServiceCategory(salonServiceCategoryRepository.findById(salonService.getSvcCategoryId()).orElse(null));
            salonService.setSalonServicePetType(salonServicePetTypeRepository.findById(salonService.getTypeId()).orElse(null));
        }
        return salonServices;
    }
    public List<SalonServiceCategory> joinServiceCategory (List<SalonServiceCategory> salonServiceCategories){
        for (SalonServiceCategory serviceCategory : salonServiceCategories){
            Byte[] img = serviceCategory.getSvcCategoryImg();
            if (img != null && img.length != 0){
                serviceCategory.setSvcCategoryImgBase64(Base64.getEncoder().encodeToString(ArrayUtils.toPrimitive(img)));
            }
        }
        return salonServiceCategories;
    }
}
