package p1.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import p1.dao.ProjectDAO;
import p1.dao.ProjectDAOImpl;
import p1.exceptions.InvalidProjectIdException;
import p1.model.Employee;
import p1.model.Project;

public class ProjectServiceImpl implements ProjectService {

	ProjectDAO projectDAO;
	
	public ProjectServiceImpl() {
		projectDAO = new ProjectDAOImpl();
	}

	@Override
	public Project getProjectById(int projectNumber) throws SQLException {
		
		return projectDAO.getProjectById(projectNumber);
	}

	@Override
	public boolean addProject(Project p) throws SQLException, InvalidProjectIdException {
		
		return projectDAO.addProject(p);
	}

	@Override
	public boolean assignProject(int empId, int projectInfo) throws SQLException {
		
		return projectDAO.assignProject(empId, projectInfo);
	}
	
	@Override
	public boolean deleteProject(int projectId) throws SQLException {
		
		return projectDAO.deleteProject(projectId);
	}

	@Override
	public Map<Project, List<Employee>> showProjectDetails(int projectId) throws SQLException {
		
		return projectDAO.showProjectDetails(projectId);
	}

	@Override
	public Map<Project, Employee> showProjectSalary(int projectId) throws SQLException {
		
		return projectDAO.showProjectSalary(projectId);
	}

}