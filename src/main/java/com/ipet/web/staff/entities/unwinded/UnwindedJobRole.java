package com.ipet.web.staff.entities.unwinded;

import com.ipet.core.entities.sub_entities.RefEntity;
import com.ipet.web.staff.entities.JobPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-31-上午 11:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UnwindedJobRole {
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;
    @Field("ROLE_NAME")
    private String roleName;
    @Field("ROLE_DESC")
    private String roleDesc;
    @Field("JOB_PERMISSION")
    private List<JobPermission> jobPermissions;
}
