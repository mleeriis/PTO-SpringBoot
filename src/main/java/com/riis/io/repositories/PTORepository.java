package com.riis.io.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.riis.io.entity.PTOEntity;

@Repository
public interface PTORepository extends PagingAndSortingRepository<PTOEntity, Integer> {
	PTOEntity findById(int id);
	
	@Query(value=
				"SELECT R.Id, R.EmployeeID, E.Firstname, E.Lastname, R.StartDate, R.EndDate, R.Status, C.HoursBalance FROM Requests AS R "+
				"LEFT JOIN Employees AS E ON E.id = R.EmployeeID " +
				"LEFT JOIN CurrentBalance AS C ON C.EmployeeID = R.EmployeeID WHERE R.EmployeeID = :empID", 
			countQuery="SELECT COUNT(*) FROM Requests AS R",
			nativeQuery=true)
	Page<PTOEntity> findAllPtoByEmpID(@Param("empID") int empId, Pageable pageableRequest);
	
	@Query(value=
				"SELECT R.Id, R.EmployeeID, E.Firstname, E.Lastname, R.StartDate, R.EndDate, R.Status, C.HoursBalance FROM Requests AS R "+
				"LEFT JOIN Employees AS E ON E.id = R.EmployeeID " +
				"LEFT JOIN CurrentBalance AS C ON C.EmployeeID = R.EmployeeID", 
			countQuery="SELECT COUNT(*) FROM Requests AS R",
			nativeQuery=true)
	Page<PTOEntity> findAllPtoWithFullName(Pageable pageableRequest);
	
	// TODO: Update Balance table based on calculations from Add PTO
	@Query(value="SELECT HoursBalance FROM CurrentBalance WHERE EmployeeID = :empID", nativeQuery=true)
	Integer getCurrentHoursBalanceForEmployee(@Param("empID") int empId);
	
	@Transactional
	@Modifying
	@Query(value=
			"UPDATE CurrentBalance SET HoursBalance=:newBalance WHERE EmployeeID=:empID", 
			nativeQuery=true)
	void updateHoursBalance(@Param("newBalance") int newBalance, @Param("empID") int empId);
}
