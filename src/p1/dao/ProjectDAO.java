package p1.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import p1.exceptions.InvalidProjectIdException;
import p1.model.Employee;
import p1.model.Project;

public interface ProjectDAO {
	
	public boolean addProject(Project p) throws SQLException, InvalidProjectIdException;
	public Project getProjectById(int projectId) throws SQLException;
	public boolean assignProject(int empId, int ProjectInfo) throws SQLException;
	public boolean deleteProject(int projectId) throws SQLException;
	public Map<Project, List<Employee>> showProjectDetails(int projectId) throws SQLException;
	public Map<Project, Employee> showProjectSalary(int projectId) throws SQLException;

}
