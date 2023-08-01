package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
	private Integer id;
	private String name;
	private boolean is_active;
	private boolean is_deleted;
	private String description;
	private String item_no;
	
	
}
