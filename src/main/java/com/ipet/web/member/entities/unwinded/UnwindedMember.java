package com.ipet.web.member.entities.unwinded;

import com.ipet.web.member.entities.Pet;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * @author Yu-Jing
 * @create 2023-01-29-下午 12:40
 */

/*
https://stackoverflow.com/questions/46139856/spring-data-mongo-unable-to-instantiate-java-util-list-using-constructor
https://blog.csdn.net/qq_31729917/article/details/105289877
unwinded 前後數據結構是不同的，因此需要建立兩個 class 來對應

When you $unwind reviews field, query's return json structure does not match with your Hotel class anymore.
Because $unwindoperation makes reviews a sub object instead of a list.
If you try your query in robomongo or some other tools, you can see your return object is like that
{
  "_id" : ObjectId("59b519d72f9e340bcc830cb3"),
  "id" : "59b23c39c70ff63135f76b14",
  "name" : "Signature",
  "reviews" : {
    "id" : 1,
    "userName" : "Salman",
    "rating" : 8,
    "approved" : true
  }
}
**/

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UnwindedMember {
    @Field (name = "_id", targetType = FieldType.OBJECT_ID)
    private String id;
    @Field("MEM_AC")
    private String memAc;
    @Field("MEM_ADD")
    private String memAdd;
    @Field("MEM_BTH")
    private Date memBth;
    @Field("MEM_EMAIL")
    private String memEmail;
    @Field("MEM_NAME")
    private String memName;
    @Field("MEM_PHONE")
    private String memPhone;
    @Field("MEM_PW")
    private String memPw;
    @Field("MEM_SEX")
    private String memSex;
    @Field("MEM_STATUS")
    private Integer memStatus;
    @Field("MEM_TEL")
    private String memTel;
    @Field("MEM_UID")
    private String memUid;
    @Field("PET_DETAIL")
    private List<Pet> petDetail;
}
