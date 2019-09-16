package com.riis.io.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.riis.io.entity.BalanceEntity;

public interface BalanceRepository extends CrudRepository<BalanceEntity, Integer> {
	@Query(value=
			"SELECT * FROM CurrentBalance WHERE EmployeeID=:empID", 
			nativeQuery=true)
	BalanceEntity findByEmployeeID(@Param("empID") int empId);
	
	@Transactional
	@Modifying
	@Query(value=
			"UPDATE CurrentBalance SET HoursBalance=:newBalance WHERE EmployeeID=:empID", 
			nativeQuery=true)
	void updateHoursBalance(@Param("empID") int empId, @Param("newBalance") int newBalance);
	
}
