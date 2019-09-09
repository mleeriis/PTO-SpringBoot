package com.riis.io.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.riis.io.entity.PTOEntity;

@Repository
public interface PTORepository extends PagingAndSortingRepository<PTOEntity, Integer> {
	PTOEntity findById(int id);
}
