package com.backend.dto.EcPartA;

import com.backend.dto.ActivityDto;
import com.backend.dto.SubActivityDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EcForm12ProjectActivityDetailsDto {

    private Integer id;
    private ActivityDto activityDto;
    private SubActivityDto subActivityDto;
    private String activity_type;
    private String threshold;
    private String proposalNo;
    private boolean isDelete;

    public EcForm12ProjectActivityDetailsDto(Integer id, Integer ac_id, String name, boolean is_active,
                                            boolean is_deleted, String description, String item_no, Integer sub_id, String sub_name,
                                            boolean sub_is_active, boolean sub_is_deleted, String sub_desc, String activity_type, String threshold,
                                            String proposalNo, boolean isDelete) {
        super();
        this.id = id;
        this.activityDto = new ActivityDto(ac_id, name, is_active, is_deleted, description, item_no);
        this.subActivityDto = new SubActivityDto(sub_id, sub_name, sub_is_active, sub_is_deleted, sub_desc);
        this.activity_type = activity_type;
        this.threshold = threshold;
        this.proposalNo = proposalNo;
        this.isDelete = isDelete;
    }
}
