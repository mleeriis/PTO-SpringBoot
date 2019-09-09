package com.riis.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.riis.io.entity.PTOEntity;

@Repository
public interface PTORepository extends CrudRepository<PTOEntity, Integer> {
	PTOEntity findById(int id);
}
