package com.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.control.model.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String> {

}
