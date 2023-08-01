package com.backend.dto;

import lombok.Data;

@Data
public class Clearance {
	private boolean EnvironmentClearance;
	public boolean isEnvironmentClearance() {
		return EnvironmentClearance;
	}
	public void setEnvironmentClearance(boolean environmentClearance) {
		EnvironmentClearance = environmentClearance;
	}
	public boolean isWildlifeClearance() {
		return WildlifeClearance;
	}
	public void setWildlifeClearance(boolean wildlifeClearance) {
		WildlifeClearance = wildlifeClearance;
	}
	public boolean isForestClearance() {
		return ForestClearance;
	}
	public void setForestClearance(boolean forestClearance) {
		ForestClearance = forestClearance;
	}
	public boolean isCRZClearance() {
		return CRZClearance;
	}
	public void setCRZClearance(boolean cRZClearance) {
		CRZClearance = cRZClearance;
	}
	private boolean WildlifeClearance;
	private boolean ForestClearance;
	private boolean CRZClearance;
}
