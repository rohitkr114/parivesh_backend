package com.backend.response;

import lombok.Data;

@Data
public class GlobalResponse {

	private int code;
	private String message = "No data found";
	private Object data;
}
