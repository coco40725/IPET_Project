package com.ipet.web.salon.entities;

import com.ipet.core.entities.Core;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serial;
import java.util.Date;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Document("SALON_SALE")
@AllArgsConstructor
@NoArgsConstructor
public class SalonSale extends Core {

  @Serial
  private static final long serialVersionUID = -7635226219222789436L;
  @Id
  @NonNull
  private String id;
  @Field("END_TIME")
  @NonNull
  private Date endTime;
  @Field("SALE_CONTENT")
  @NonNull
  private String saleContent;
  @Field("SALE_NAME")
  @NonNull
  private String saleName;
  @Field("START_TIME")
  @NonNull
  private Date startTime;
  @Field("SVC_DETAIL")
  @NonNull
  private List<Map<String,String>> svcDetail;


}
