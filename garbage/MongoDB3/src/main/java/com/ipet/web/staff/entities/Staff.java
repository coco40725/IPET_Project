package com.ipet.web.staff.entities;


import com.ipet.core.entities.Core;
import com.ipet.core.entities.sub_entities.RefEntity;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Document("STAFF")
@AllArgsConstructor
@NoArgsConstructor
public class Staff extends Core {

  @Serial
  private static final long serialVersionUID = -1082868287105447578L;
  @Id
  @NonNull
  private String id;

  @Field("STAFF_AC")
  @NonNull
  private String staffAc;
  @Field("STAFF_ADD")
  @NonNull
  private String staffAdd;
  @Field("STAFF_BIRTH")
  @NonNull
  private Date staffBirth;
  @Field("STAFF_EMAIL")
  @NonNull
  private String staffEmail;
  @Field("STAFF_NAME")
  @NonNull
  private String staffName;
  @Field("STAFF_PHONE")
  @NonNull
  private String staffPhone;
  @Field("STAFF_POSI")
  @NonNull
  private String staffPosi;
  @Field("STAFF_PW")
  @NonNull
  private String staffPw;
  @Field("STAFF_ROLE_IDS")
  @NonNull
  private List<RefEntity> staffRoleIds;
  @Field("STAFF_SEX")
  @NonNull
  private String staffSex;
  @Field("STAFF_STATUS")
  @NonNull
  private Integer staffStatus;
  @Field("STAFF_TEL")
  private String staffTel;
  @Field("STAFF_UID")
  @NonNull
  private String staffUid;


}
