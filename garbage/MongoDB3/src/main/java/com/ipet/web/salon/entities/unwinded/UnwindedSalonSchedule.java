package com.ipet.web.salon.entities.unwinded;

import com.ipet.web.staff.entities.Staff;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-29-下午 03:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UnwindedSalonSchedule {
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private String id;
    @Field("GROOMER")
    private List<Staff> groomer;
    @Field("ASST1")
    private List<Staff> asst1;
    @Field("ASST2")
    private List<Staff> asst2;
    @Field("EMPLOYEE_NOTE")
    private String employeeNote;
    @Field("SCH_DATE")
    private Date schDate;
    @Field("SCH_PERIOD")
    private String schPeriod;
    @Field("APPPOINT_ID")
    private String appointId;
}
