package com.control.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RoleInsertDTO implements Serializable {

	private static final long serialVersionUID = -363668585366767814L;

	@NotNull(message = "It cannot be null")
	private Boolean roleIsEnabled;

	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String roleName;

}
