package p1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import p1.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	Connection con;
	
	public EmployeeDAOImpl() {
		
		con = MySQLConnection.mySql;
	}
	
	@Override
	public boolean saveEmployee(Employee e) throws SQLException{
		
		/* code should be responsible to 
		 * 1) insert employee into DB 
		 * 2) return the status value*/
		
		String query = "insert into ncs.employee values(?,?,?,?,?,?,?,?);";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, e.getEmpId());
		ps.setString(2,e.getName());
		ps.setInt(3, e.getProjectInfo());
		ps.setString(4, e.getEmail());
		ps.setInt(5, e.getBankAccount());
		ps.setString(6, e.getAddress());
		ps.setString(7, e.getDesignation());
		ps.setInt(8, e.getSalary());
		
		int rowsEffected = ps.executeUpdate();
		
		boolean status = (rowsEffected == 1)?true:false;
		
		return status;
	}

	@Override
	public Employee getEmployeeByID(int id) throws SQLException {
		
		Employee output = null;
		
		String query = "select * from ncs.employee where empId = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs!=null)
		{
			while(rs.next()) 
			{
				int empId = rs.getInt(1);
				String name = rs.getString(2);
				int projectId = rs.getInt(3);
				String email = rs.getString("email");
				int bankAccount = rs.getInt("bankaccount");
				String address= rs.getString("address");
				String designation = rs.getString("designation");
				int salary = rs.getInt("salary");
				
				
				output = new Employee(empId, name, projectId, email, bankAccount, address, designation, salary);
			}
		}
		
		return output;
	}

	@Override
	public List<Employee> getAllEmployee() throws SQLException {
		
		List<Employee> employees = new ArrayList<Employee>();
		Employee output = null;
		
		String query = "select * from ncs.employee";
		PreparedStatement ps = con.prepareStatement(query);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs!=null)
		{
			while(rs.next()) 
			{
				int empId = rs.getInt(1);
				String name = rs.getString(2);
				int projectId = rs.getInt(3);
				String email = rs.getString("email");
				int bankAccount = rs.getInt("bankaccount");
				String address= rs.getString("address");
				String designation = rs.getString("designation");
				int salary = rs.getInt("salary");
				
				
				output = new Employee(empId, name, projectId, email, bankAccount, address, designation, salary);
				employees.add(output);
			}
		}
		
		return employees;
	}

	@Override
	public Employee assignProjectToEmployee(int empId, int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
