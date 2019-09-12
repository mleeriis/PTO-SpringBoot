package com.riis.io.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.riis.io.entity.CombinedEntity;
import com.riis.io.entity.PTOEntity;

interface CombinedOutput{
	String getFirstname();
	String getLastname();
	
}

@Repository
public interface PTORepository extends PagingAndSortingRepository<PTOEntity, Integer> {
	PTOEntity findById(int id);
	
	@Query(value=
			"SELECT R.Id, R.EmployeeID, CONCAT(E.Firstname, ' ', E.Lastname) AS FullName, R.StartDate, R.EndDate, R.Status FROM Requests AS R "+
	"LEFT JOIN Employees AS E ON E.id = R.EmployeeID WHERE R.EmployeeID = :empID", 
			nativeQuery=true)
	Page<PTOEntity> findAllPtoByEmpID(@Param("empID") int empId, Pageable pageableRequest);
	
}
