package com.wavefront.repository;

import com.wavefront.entity.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Basically this is a Spring Data repository
 *
 * This class is used to extend JpaRepository to store and get
 * shape entities. No need of adding any methods now.
 * The Spring Boot will provide the implementation for the CRUD operations
 * automatically
 *
 */
public interface ShapeRepository extends JpaRepository<Shape, Long> {
}
