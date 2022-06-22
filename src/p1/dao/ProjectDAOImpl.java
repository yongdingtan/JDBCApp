package p1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import p1.exceptions.InvalidProjectIdException;
import p1.model.Employee;
import p1.model.Project;

public class ProjectDAOImpl implements ProjectDAO {

	Connection con;

	public ProjectDAOImpl() {
		
		con = MySQLConnection.mySql;
	}
	@Override
	public Map<Project, List<Employee>> showProjectDetails(int projectId) throws SQLException
	{
		Map<Project, List<Employee>> mapProject = new HashMap<>();
		List<Employee> employeeList = new ArrayList<Employee>();
		
		Employee employee = null;
		
		String employeeQuery = "select empId, email from ncs.employee where projectInfo = ?"; // DQL 
		PreparedStatement ps = con.prepareStatement(employeeQuery);
		ps.setInt(1, projectId);
		
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			int empId = rs.getInt("empId");
			String email = rs.getString("email");
		
			employee = new Employee();
			employee.setEmpId(empId);
			employee.setEmail(email);
			
			employeeList.add(employee);
		}
		
		Project project = null;
		
		String projectQuery = "select projectNumber, projectName from ncs.project where projectNumber = ?";
		PreparedStatement psOne = con.prepareCall(projectQuery);
		psOne.setInt(1, projectId);
		
		ResultSet rsOne = psOne.executeQuery();
		
		while(rsOne.next())
		{
			int projectNumber = rsOne.getInt("projectNumber");
			String projectName = rsOne.getString("projectName");
			
			project = new Project();
			project.setProjectNumber(projectNumber);
			project.setProjectName(projectName);
		}
		
		mapProject.put(project, employeeList);
		
		
		return mapProject;
	}
	
	
	@Override
	public Map<Project, Employee> showProjectSalary(int projectId) throws SQLException
	{
		Map<Project, Employee> mapProject = new HashMap<>();
		
		Project project = null;
		
		String query = "select projectNumber from ncs.project where projectNumber = ?"; // DQL 
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, projectId);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			int projectNumber = rs.getInt("projectNumber");
		
			project = new Project();
			project.setProjectNumber(projectNumber);
		}
		
		Employee employee = null;
		
		String queryOne = "select sum(salary) AS salary from ncs.employee where projectInfo = ?";
		PreparedStatement psOne = con.prepareStatement(queryOne);
		psOne.setInt(1,  projectId);
		
		ResultSet rsOne = psOne.executeQuery();
		
		while(rsOne.next())
		{
			int salary = rsOne.getInt("salary");
			
			employee = new Employee();
			employee.setSalary(salary);
		}
		
		mapProject.put(project, employee);
		
		return mapProject;
	}

	@Override
	public boolean addProject(Project p) throws SQLException, InvalidProjectIdException {
		
		boolean status = false;
		
		if(p.getProjectNumber() > 50)
		{
			throw new InvalidProjectIdException(p.getProjectNumber()+"");
		}
		else
		{
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
		status = (rowsAffected == 1)?true:false;
		}
		return status;
	}
	
	@Override
	public boolean deleteProject(int projectId) throws SQLException {
		boolean isDeleted = false;
		
		String updateEmployeeQuery = "update ncs.employee set projectInfo = null where projectInfo = ?";
		String deleteProjectQuery = "delete from  ncs.project where projectNumber = ?";
		try {
			
			con.setAutoCommit(false);
			
			// update employee table
			PreparedStatement  psUpdate = con.prepareStatement(updateEmployeeQuery);
			psUpdate.setInt(1, projectId);
			
			int rowsEffected = psUpdate.executeUpdate();
			System.err.println("INFO : "+LocalTime.now()+" rows effected after update :- "+rowsEffected);
			
			
			// delete project table
			PreparedStatement  psDelete = con.prepareStatement(deleteProjectQuery);
			psDelete.setInt(1, projectId);
			
			int deleteRowsEffected = psDelete.executeUpdate();
			System.err.println("INFO : "+LocalTime.now()+" rows effected after delete :- "+rowsEffected);
			
			if(rowsEffected != 0 && deleteRowsEffected !=0)
			{
				con.commit();

				System.err.println("INFO : "+LocalTime.now()+" Database commited !!!");
				isDeleted = true;
			}
			
		} catch (Exception e) {
			try {
				System.err.println("Inside catch Block :- "+e);
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Exception during roll back "+e);
			}
		}

		
		return isDeleted;
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
