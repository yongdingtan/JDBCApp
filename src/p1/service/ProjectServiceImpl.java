package p1.service;

import java.sql.SQLException;

import p1.dao.ProjectDAO;
import p1.dao.ProjectDAOImpl;
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
	public boolean addProject(Project p) throws SQLException {
		
		boolean status = projectDAO.addProject(p);
		
		return status;
	}



	@Override
	public boolean assignProject(int empId, int projectInfo) throws SQLException {
		
		boolean status = projectDAO.assignProject(empId, projectInfo);
		
		return status;
	}

}