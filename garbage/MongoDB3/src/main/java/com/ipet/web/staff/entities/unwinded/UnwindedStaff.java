package com.ipet.web.staff.entities.unwinded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-31-上午 10:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UnwindedStaff {
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private String id;
    @Field("STAFF_AC")
    private String staffAc;
    @Field("STAFF_ADD")
    private String staffAdd;
    @Field("STAFF_BIRTH")
    private Date staffBirth;
    @Field("STAFF_EMAIL")
    private String staffEmail;
    @Field("STAFF_NAME")
    private String staffName;
    @Field("STAFF_PHONE")
    private String staffPhone;
    @Field("STAFF_POSI")
    private String staffPosi;
    @Field("STAFF_PW")
    private String staffPw;
    @Field("JOB_ROLES")
    private List<UnwindedJobRole> unwindedJobRoles;
    @Field("STAFF_SEX")
    private String staffSex;
    @Field("STAFF_STATUS")
    private Integer staffStatus;
    @Field("STAFF_TEL")
    private String staffTel;
    @Field("STAFF_UID")
    private String staffUid;

}
