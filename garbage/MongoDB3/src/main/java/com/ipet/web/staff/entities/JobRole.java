package com.ipet.web.staff.entities;

import com.ipet.core.entities.Core;
import com.ipet.core.entities.sub_entities.RefEntity;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Document("JOB_ROLE")
@AllArgsConstructor
@NoArgsConstructor
public class JobRole extends Core {
  @Serial
  private static final long serialVersionUID = 8468183186642431161L;
  @Id
  @NonNull
  private String id;
  @Field("ROLE_NAME")
  @NonNull
  private String roleName;
  @Field("ROLE_DESC")
  @NonNull
  private String roleDesc;
  @Field("STAFF_PERMISSION_IDS")
  private List<RefEntity> staffPermissionIds;

}
