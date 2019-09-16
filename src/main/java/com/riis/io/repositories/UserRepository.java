package com.riis.io.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.riis.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
	UserEntity findByEmail(String email);

	
	@Query(value=
			"SELECT E.Id, E.Firstname, E.Lastname, E.email, E.RoleID, E.Password, C.HoursBalance FROM Employees AS E " + 
			"LEFT JOIN CurrentBalance AS C ON E.Id = C.EmployeeID", 
	countQuery="SELECT COUNT(*) FROM Employees AS E " + 
			"LEFT JOIN CurrentBalance AS C ON E.Id = C.EmployeeID",
			nativeQuery=true)
	Iterable<UserEntity> findAllEmployeesWithHoursBalance();
}
