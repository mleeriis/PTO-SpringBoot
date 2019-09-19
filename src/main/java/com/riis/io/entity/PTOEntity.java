package com.riis.io.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//@SqlResultSetMapping(
//name = "displayWithFullName", 
//entities= {
//		@EntityResult(
//				entityClass=PTOEntity.class,
//				fields= {
//						@FieldResult(name = "EXAMPLEId", column="Requests.Id"), 
//						@FieldResult(name = "EmployeeID", column="Requests.EmployeeID"), 
//						@FieldResult(name = "StartDate", column="Requests.StartDate"), 
//						@FieldResult(name = "EndDate", column="Requests.EndDate"), 
//						@FieldResult(name = "Status", column="Requests.Status")
//				}
//				),
//		@EntityResult(
//				entityClass=BalanceEntity.class,
//				fields= {
//						@FieldResult(name = "FullName", column="Employees.Firstname"),
//				})
//}
////classes = {
////		@ConstructorResult(
////		targetClass = PTOEntity.class, 
////		columns = {
////			@ColumnResult(name = "Id", type=Integer.class), 
////			@ColumnResult(name = "EmployeeID", type=Integer.class), 
////			@ColumnResult(name = "FullName", type=String.class),
////			@ColumnResult(name = "StartDate", type=Date.class), 
////			@ColumnResult(name = "EndDate", type=Date.class), 
////			@ColumnResult(name = "Status", type=Integer.class) }
////	)
////}
//)
//
//@NamedNativeQuery(name = "findAllWithFullName", 
//resultClass = PTOEntity.class, 
////resultSetMapping = "displayWithFullName", 
//query = "SELECT Requests.Id, Requests.EmployeeID, Employees.Firstname AS FullName, Requests.StartDate, Requests.EndDate, Requests.Status FROM Requests LEFT JOIN Employees ON Employees.id = Requests.EmployeeID")

@Entity
@Table(name="Requests")
@SecondaryTable(name="Employees")
public class PTOEntity implements Serializable {

	private static final long serialVersionUID = -5961917022589138570L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(name="EmployeeID")
	private int EmployeeID;
	
	@Column(name="StartDate")
	private Date StartDate;
	
	@Column(name="EndDate")
	private Date EndDate;
	
	@Column(name="Status")
	private int Status;
	
	@Column(insertable = false, updatable = false, table="Employees")
	private String Firstname;
	
	@Column(insertable = false, updatable = false, table="Employees")
	private String Lastname;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}

}
