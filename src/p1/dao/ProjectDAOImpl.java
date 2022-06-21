package p1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import p1.model.Project;

public class ProjectDAOImpl implements ProjectDAO {

	Connection con;

	public ProjectDAOImpl() {
		
		con = MySQLConnection.mySql;
	}

	@Override
	public boolean addProject(Project p) throws SQLException {
		
		String query = "insert into ncs.project values (?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, p.getProjectNumber());
		ps.setString(2, p.getProjectName());
		ps.setInt(3, p.getCost());
		ps.setString(4, p.getStartdate());
		ps.setString(5, p.getEnddate());
		ps.setString(6, p.getComments());
		ps.setString(7, p.getProjectHeadEmail());
		
		int rowsAffected = ps.executeUpdate();
		boolean status = (rowsAffected == 1)?true:false;
		
		return status;
	}


	@Override
	public Project getProjectById(int projectId) throws SQLException {
		Project outputProject = null;
		
		String query = "select * from ncs.project where projectNumber = ?"; // DQL 
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, projectId);
		
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			String projectName = rs.getString(2);
			String projectHeadEmail = rs.getString("projectHeadEmail");
		
			outputProject = new Project();
			outputProject.setProjectNumber(projectId);
			outputProject.setProjectName(projectName);
			outputProject.setProjectHeadEmail(projectHeadEmail);
		}
		
		
		return outputProject;
	}

	@Override
	public boolean assignProject(int empId, int projectInfo) throws SQLException {
		
		String query = "update ncs.employee set projectInfo = ? where empId = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, projectInfo);
		ps.setInt(2, empId);
		
		int rowsAffected = ps.executeUpdate();
		boolean status = (rowsAffected == 1)?true:false;
		
		return status;
	}

}
