package com.ipet.web.member.entities;


import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serial;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@Document("PET")
@AllArgsConstructor
@NoArgsConstructor
public class Pet extends Core {

  @Serial
  private static final long serialVersionUID = 1010628508164416263L;
  @Id
  @NonNull
  private String id;
  @Field("PET_BIRTH")
  @NonNull
  private Date petBirth;
  @Field("PET_GEN")
  @NonNull
  private String petGen;
  @Field("PET_NAME")
  @NonNull
  private String petName;
  @Field("PET_SIZE")
  @NonNull
  private String petSize;
  @Field("PET_STATUS")
  @NonNull
  private Integer petStatus;
  @Field("PET_VAR")
  @NonNull
  private String petVar;
}
