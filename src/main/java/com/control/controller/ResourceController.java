package com.control.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.model.dto.ResourceDTO;
import com.control.model.dto.ResourceInsertDTO;
import com.control.model.dto.ResourceUpdadeDTO;
import com.control.service.ResourceService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/resource")
@Slf4j
public class ResourceController {

	private final ResourceService resourceService;

	public ResourceController(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(Pageable pageable) {
		log.info("Pagination: {}", pageable);

		final var entity = resourceService.findAll(pageable);
		return !entity.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(entity)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/{resourceId}")
	public ResponseEntity<?> findById(@PathVariable("resourceId") String resourceId) {
		log.info("Parameter: applicationId={}", resourceId);

		final var entity = resourceService.findById(resourceId);
		return entity.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(entity)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ResourceInsertDTO resourceInsertDTO) {
		log.info("Parameter: object={}", resourceInsertDTO.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(resourceService.save(resourceInsertDTO));
	}

	@PutMapping
	public ResponseEntity<?> save(@Valid @RequestBody ResourceUpdadeDTO resourceUpdateDTO) {
		log.info("Parameter: object={}", resourceUpdateDTO);
		resourceService.save(resourceUpdateDTO);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping()
	public ResponseEntity<?> delete(@Valid @RequestBody ResourceDTO resourceDTO) {
		log.info("Parameter: resourceId={}", resourceDTO.getResourceId());
		resourceService.delete(resourceDTO);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
