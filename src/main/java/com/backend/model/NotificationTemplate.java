package com.backend.model;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationTemplate {

	private Integer Id;

	private String TemplateId;

	private String message_type;

	private String message;

	private Date created_on;

	private Date updated_on;

	private Boolean is_active;

	private Boolean is_deleted;

}
