package com.backend.dto;

import com.backend.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatedUser {

	private Integer userId;
	private String name;
	
	public UpdatedUser(User user) {
		this.userId = user.getEntityid();
		this.name = user.getName_of_Entity();
	}
	
}
