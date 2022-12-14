package com.control.model.dto;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.control.model.validation.ExistsUserEmailUpdate;
import com.control.model.validation.ExistsUserId;
import com.control.model.validation.ExistsUserNameUpdate;

import lombok.Data;

@Data
@ExistsUserNameUpdate
@ExistsUserEmailUpdate
public class UserUpdateDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;
	
	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsUserId
	private String userId;

	@NotNull(message = "It cannot be null")
	private Boolean userIsEnabled;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonExpired;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonLocked;

	@NotNull(message = "It cannot be null")
	private Boolean userIsCredentialsNonDiscredited;

	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String userName;

	@Email(message = "Incorrect format")
	@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters")
	private String userEmail;
	
	private String userPasswordToken;

	private String userRememberToken;

	private Long userPinCode;
		
}
