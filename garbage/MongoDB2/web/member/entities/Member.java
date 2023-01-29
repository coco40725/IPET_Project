package com.ipet.web.member.entities;

import com.ipet.core.entities.Core;
import com.ipet.core.entities.RefEntity;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document("MEMBER")
@AllArgsConstructor
@NoArgsConstructor
public class Member extends Core {

  @Serial
  private static final long serialVersionUID = -3097974212224652934L;
  @Id
  @NonNull
  private String id;
  @Field("MEM_AC")
  @NonNull
  private String memAc;
  @Field("MEM_ADD")
  @NonNull
  private String memAdd;
  @Field("MEM_BTH")
  @NonNull
  private Date memBth;
  @Field("MEM_EMAIL")
  @NonNull
  private String memEmail;
  @Field("MEM_NAME")
  @NonNull
  private String memName;
  @Field("MEM_PHONE")
  @NonNull
  private String memPhone;
  @Field("MEM_PW")
  @NonNull
  private String memPw;
  @Field("MEM_SEX")
  @NonNull
  private String memSex;
  @Field("MEM_STATUS")
  @NonNull
  private Integer memStatus;
  @Field("MEM_TEL")
  private String memTel;
  @Field("MEM_UID")
  @NonNull
  private String memUid;
  @Field("PET")
  private List<RefEntity> pet;
}
