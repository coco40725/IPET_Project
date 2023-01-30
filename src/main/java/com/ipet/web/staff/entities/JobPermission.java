package com.ipet.web.staff.entities;

import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;

@EqualsAndHashCode(callSuper = false)
@Data
@Document("JOB_PERMISSION")
@AllArgsConstructor
@NoArgsConstructor
public class JobPermission extends Core {
  @Serial
  private static final long serialVersionUID = -5747037407041896482L;
  @Id
  @NonNull
  private String id;
  @Field("PERMISSION_NAME")
  @NonNull
  private String permissionName;


}
