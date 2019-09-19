package com.riis.io.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.riis.io.entity.PTOEntity;

@Repository
public interface PTORepository extends PagingAndSortingRepository<PTOEntity, Integer> {
	PTOEntity findById(int id);
	
	@Query(value=
			"SELECT R.Id, R.EmployeeID, CONCAT(E.Firstname, ' ', E.Lastname) AS FullName, R.StartDate, R.EndDate, R.Status FROM Requests AS R "+
	"LEFT JOIN Employees AS E ON E.id = R.EmployeeID WHERE R.EmployeeID = :empID", 
			nativeQuery=true)
	Page<PTOEntity> findAllPtoByEmpID(@Param("empID") int empId, Pageable pageableRequest);
	
	@Query(value=
			"SELECT R.Id, R.EmployeeID, E.Firstname, E.Lastname, R.StartDate, R.EndDate, R.Status FROM Requests AS R "+
	"LEFT JOIN Employees AS E ON E.id = R.EmployeeID", 
	countQuery="SELECT COUNT(*) FROM Requests AS R " + 
			"LEFT JOIN Employees AS E ON E.id = R.EmployeeID",
			nativeQuery=true)
	Page<PTOEntity> findAllPtoWithFullName(Pageable pageableRequest);
	
	//Update Balance table based on calculations from Add PTO
	
}
