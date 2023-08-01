package com.backend.dto;

import java.util.Objects;

public class VillageDTO {

	private Integer s_no;
	
	private String village_name;

	public Integer getS_no() {
		return s_no;
	}

	public void setS_no(Integer s_no) {
		this.s_no = s_no;
	}

	public String getVillage_name() {
		return village_name;
	}

	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}

	@Override
	public String toString() {
		return "VillageDTO [s_no=" + s_no + ", village_name=" + village_name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(s_no, village_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VillageDTO other = (VillageDTO) obj;
		return Objects.equals(s_no, other.s_no) && Objects.equals(village_name, other.village_name);
	}
	
	
}
