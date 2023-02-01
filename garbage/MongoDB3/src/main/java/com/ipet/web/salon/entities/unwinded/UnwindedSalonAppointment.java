package com.ipet.web.salon.entities.unwinded;

import com.ipet.web.member.entities.Member;
import com.ipet.web.member.entities.Pet;
import com.ipet.web.salon.entities.SalonSchedule;
import com.ipet.web.salon.entities.sub_entities.SalonAppointDetail;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-下午 03:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UnwindedSalonAppointment {
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private String id;
    @Field("APM_BUILD_TIME")
    private Date apmBuildTime;
    @Field("APM_STATUS")
    private Integer apmStatus;
    @Field("CUSTOMER_NOTE")
    private String customerNote;
    @Field("MEM")
    private List<Member> mem;
    @Field("PET")
    private List<Pet> pet;
    @Field("SCH")
    private List<SalonSchedule> sch;
    @Field("ADDITIONAL_PRICE")
    private Integer additionalPrice;
    @Field("TOTAL_PRICE")
    private Integer totalPrice;
    @Field("SALON_APPOINTMENT_DETAIL")
    private List<SalonAppointDetail> salonAppointDetail;


}
