package com.control.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.service.UserRolesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/userRoles")
@Slf4j
public class UserRolesController {

	private UserRolesService userRolesService;

	public UserRolesController(UserRolesService userRolesService) {
		this.userRolesService = userRolesService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var userRoles = userRolesService.findAll(pageable);
		return userRoles.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(userRoles);
	}

	@GetMapping("/{user_roles_id}")
	public ResponseEntity<?> findById(@PathVariable("user_roles_id") String user_roles_id) {
		log.info("Parameter: userRolesId={}", user_roles_id);

		final var userRoles = userRolesService.findById(user_roles_id);
		return userRoles == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
				: ResponseEntity.status(HttpStatus.FOUND).body(userRoles);
	}

}