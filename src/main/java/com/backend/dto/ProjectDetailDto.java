package com.backend.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.backend.model.CommonFormDetail;
import com.backend.model.ProjectDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDetailDto {

	private Integer id;
	private String projectName;
	private String singleWindowNumber;

	private Integer mainDistrict;

	private Integer mainState;

	private List<CommonFormDetail> commonFormDetails = new ArrayList<>();

	public ProjectDetailDto(ProjectDetails details) {
		this.projectName = details.getName();
		this.singleWindowNumber = details.getSw_no();
		this.mainDistrict= details.getMain_district();
		this.mainState= details.getMain_state();
		this.commonFormDetails.addAll(details.getCommonFormDetails());
	}

	public ProjectDetailDto(Integer id, String ProjectName) {
		this.id = id;
		this.projectName = ProjectName;
	}
}
