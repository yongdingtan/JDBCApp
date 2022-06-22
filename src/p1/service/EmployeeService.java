package p1.service;

import java.sql.SQLException;
import java.util.List;

import p1.model.Employee;

public interface EmployeeService {

	public boolean saveEmployee(Employee e) throws SQLException;
	public boolean deleteEmployee(int empId) throws SQLException;
	public Employee getEmployeeByID(int id) throws SQLException;
	public List<Employee> getAllEmployee() throws SQLException;
	public Employee doLinkProjectWithEmployee(int empId,int projetId);
	
	public boolean validateSalary(int salary,String designation);
	
}
