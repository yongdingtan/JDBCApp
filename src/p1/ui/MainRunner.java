package p1.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import p1.dto.EmployeeDTO;
import p1.model.Employee;
import p1.model.Project;
import p1.service.EmployeeService;
import p1.service.EmployeeServiceImpl;
import p1.service.ProjectService;
import p1.service.ProjectServiceImpl;

public class MainRunner {

	Scanner sc = new Scanner(System.in);
	Scanner stringSc = new Scanner(System.in);
	
	EmployeeService empService;
	ProjectService projectService;
	
	public MainRunner() {
		empService = new EmployeeServiceImpl();
		projectService = new ProjectServiceImpl();
	}
	
	public static void main(String[] args) {
		
		MainRunner app = new MainRunner();
		
		while(true)
		{
			System.out.println("\n\n ============== Link Panel ==============");
			System.out.println("1. Insert Employee ");
			System.out.println("2. Add Project");
			System.out.println("3. Assign Project to Employee");
			System.out.println("4. View All Employee");
			System.out.println("5. View Employee By ID ");
			System.out.println("6. Delete Project");
			System.out.println("7. Project Details");
			System.out.println("8. Project Salary");
			System.out.println("9. Delete Employee");
			System.out.println("10. Add multiple employees");
			System.out.println("0. EXIT");
			
			System.out.println("Enter your choice : ");
			int userOption = new Scanner(System.in).nextInt();
			
			switch(userOption)
			{
				case 1:
						app.saveEmployee();
						break;
				case 2:
						app.addProject();
						break;
				case 3:
						app.assignProjectToEmployee();
						break;
				case 4:
						app.viewAllEmployees();
						break;
				case 5:
						app.viewEmployeeByID();
						break;
				case 6:
						app.deleteProject();
						break;
				case 7:
						app.showProjectDetails();
						break;
				case 8:
						app.showProjectSalary();
						break;
				case 9:
						app.deleteEmployee();
						break;
				case 10:
						app.saveMultipleEmployee();
						break;
				case 0:
					System.exit(0);
			}
			
		}//end of while
	}//end of main
	
	public void saveMultipleEmployee()
	{
		System.out.println("Enter number of employees to add: ");
		int count = sc.nextInt();
		
		while(count>0) {
			
		
		System.out.println("Enter id: ");
		int empId = sc.nextInt();
		System.out.println("Enter name: ");
		String name = stringSc.nextLine();
		System.out.println("Enter projectInfo: ");
		int projectInfo = sc.nextInt();
		System.out.println("Enter email: ");
		String email = stringSc.nextLine();
		System.out.println("Enter bank account: ");
		int bankAccount = sc.nextInt();
		System.out.println("Enter address: ");
		String address = stringSc.nextLine();
		System.out.println("Enter designation: ");
		String designation = stringSc.nextLine();
		System.out.println("Enter salary: ");
		int salary = sc.nextInt();
		
		Employee e = new Employee(empId, name, projectInfo, email, bankAccount, address, designation, salary);
		try {
			boolean status = empService.saveEmployee(e);
			if(status == true)
			{
				System.out.println(e.getEmpId()+" Saved in the Database "+e);
			}
			else
			{
				throw new Exception("Unknown SQL Exception ");
			}
		}
		catch (Exception e2) {
			System.out.println(e2.getMessage()+"\n contact to customer care");
			System.out.println("\n\n care@ncshr.com");
		}
		count--;
		}
	}
	
	public void deleteEmployee()
	{
		System.out.println("Enter employee ID: ");
		int empId = sc.nextInt();
		
		try {
			empService.deleteEmployee(empId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showProjectSalary()
	{
		System.out.println("Enter project id: ");
		int projectId = sc.nextInt();
		try {
			
			Map<Project, Employee> projectDetails = projectService.showProjectSalary(projectId);
			projectDetails.forEach((k, v) -> {
				System.out.println("==============Project Detail==============");
				System.out.println("Project ID: "+k.getProjectNumber()+" Total Salary: $"+v.getSalary());		
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showProjectDetails()
	{
		System.out.println("Enter project id: ");
		int projectId = sc.nextInt();
		try {
			
			Map<Project, List<Employee>> projectDetails = projectService.showProjectDetails(projectId);
			projectDetails.forEach((k, v) -> {
				System.out.println("==============Project Detail==============");
				System.out.println("Project ID: "+k.getProjectNumber()+" Project Name: "+k.getProjectName());
				System.out.println("=============Employee Details=============");
				for (Employee employee : v) {
					System.out.println("Employee ID: "+ employee.getEmpId()+" Email: "+employee.getEmail());
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProject() {
		System.out.println("Enter project id: ");
		int projectId = sc.nextInt();
		try {
			
			projectService.deleteProject(projectId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void saveEmployee()
	{
		System.out.println("Enter id: ");
		int empId = sc.nextInt();
		System.out.println("Enter name: ");
		String name = stringSc.nextLine();
		System.out.println("Enter projectInfo: ");
		int projectInfo = sc.nextInt();
		System.out.println("Enter email: ");
		String email = stringSc.nextLine();
		System.out.println("Enter bank account: ");
		int bankAccount = sc.nextInt();
		System.out.println("Enter address: ");
		String address = stringSc.nextLine();
		System.out.println("Enter designation: ");
		String designation = stringSc.nextLine();
		System.out.println("Enter salary: ");
		int salary = sc.nextInt();
		
		Employee e = new Employee(empId, name, projectInfo, email, bankAccount, address, designation, salary);
		try {
			boolean status = empService.saveEmployee(e);
			if(status == true)
			{
				System.out.println(e.getEmpId()+" Saved in the Database "+e);
			}
			else
			{
				throw new Exception("Unknown SQL Exception ");
			}
		}
		catch (Exception e2) {
			System.out.println(e2.getMessage()+"\n contact to customer care");
			System.out.println("\n\n care@ncshr.com");
		}
		
	}
	
	public void addProject()
	{
		System.out.println("Enter project number: ");
		int projectNumber = sc.nextInt();
		System.out.println("Enter project name: ");
		String projectName = stringSc.nextLine();
		System.out.println("Enter cost: ");
		int cost = sc.nextInt();
		System.out.println("Enter project start date(YYYY-MM-dd): ");
		String startdate = stringSc.nextLine();
		System.out.println("Enter project end date(YYYY-MM-dd): ");
		String enddate = stringSc.nextLine();
		System.out.println("Enter comments: ");
		String comments = stringSc.nextLine();
		System.out.println("Enter project head email: ");
		String projectHeadEmail = stringSc.nextLine();
		
		Project p = new Project(projectNumber, projectName, cost, startdate, enddate, comments, projectHeadEmail);
		
		try {
			
			boolean status = projectService.addProject(p);
			if(status == true)
			{
				System.out.println(p.getProjectNumber()+" saved in database ");
			}
			else
			{
				throw new Exception("Unknown SQL Exception");
			}
			
		}
		catch(Exception e)
		{ 
			System.out.println(e.getMessage()+"\n contact to customer care");
			System.out.println("\n\n care@ncshr.com");
		}
		
		
	}
	
	public void assignProjectToEmployee()
	{
		System.out.println("Enter employee ID: ");
		int empId = sc.nextInt();
		System.out.println("Enter project ID to be assigned: ");
		int projectInfo = sc.nextInt();
		
		try {
			
			boolean status = projectService.assignProject(empId, projectInfo);
			if(status == true)
			{
				System.out.println("Project "+projectInfo+" successfully assigned to Employee "+empId);
			}
			else
			{
				throw new Exception("unknown SQL Exception");
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n contact to customer care");
			System.out.println("\n\n care@ncshr.com");
		}
	}
	
	public void viewAllEmployees()
	{
		try {
			List<Employee> employees = empService.getAllEmployee();
			for (Employee employee : employees) {
				
			
			if(employee != null)
			{
				Project p = projectService.getProjectById(employee.getProjectInfo());
				if(p != null)
				{
					EmployeeDTO dtoObject = new EmployeeDTO();
					dtoObject.setEmpId(employee.getEmpId());
					dtoObject.setEmpName(employee.getName());
					dtoObject.setEmailId(employee.getEmail());
					dtoObject.setProjectName(p.getProjectName());
					dtoObject.setProjectHeadEmail(p.getProjectHeadEmail());
					
					//System.out.println(dtoObject);
					printEmployeeInfo(dtoObject);
				}
				else
				{
					EmployeeDTO dtoObject = new EmployeeDTO();
					dtoObject.setEmpId(employee.getEmpId());
					dtoObject.setEmpName(employee.getName());
					dtoObject.setEmailId(employee.getEmail());
					dtoObject.setProjectName("No Project Allocated");
					dtoObject.setProjectHeadEmail("No Project Allocated");
					
					//System.out.println(dtoObject);
					printEmployeeInfo(dtoObject);
				}
			}
			else
			{
				System.out.println(" Wrong Employee ID !!!");
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception "+e.getMessage());
		}
		
	}
	
	public void viewEmployeeByID()
	{
		System.out.println("Enter the Employee ID: ");
		int searchID = sc.nextInt();
		
		try {
			Employee e = empService.getEmployeeByID(searchID);
			if(e != null)
			{
				Project p = projectService.getProjectById(e.getProjectInfo());
				if(p != null)
				{
					EmployeeDTO dtoObject = new EmployeeDTO();
					dtoObject.setEmpId(e.getEmpId());
					dtoObject.setEmpName(e.getName());
					dtoObject.setEmailId(e.getEmail());
					dtoObject.setProjectName(p.getProjectName());
					dtoObject.setProjectHeadEmail(p.getProjectHeadEmail());
					
					//System.out.println(dtoObject);
					printEmployeeInfo(dtoObject);
				}
				else
				{
					EmployeeDTO dtoObject = new EmployeeDTO();
					dtoObject.setEmpId(e.getEmpId());
					dtoObject.setEmpName(e.getName());
					dtoObject.setEmailId(e.getEmail());
					dtoObject.setProjectName("No Project Allocated");
					dtoObject.setProjectHeadEmail("No Project Allocated");
					
					//System.out.println(dtoObject);
					printEmployeeInfo(dtoObject);
				}
			}
			else
			{
				System.out.println(" Wrong Employee ID !!!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception "+e.getMessage());
		}
		
	}
	

	public void printEmployeeInfo(EmployeeDTO dto)
	{
		System.out.println("______________________________________");
		System.out.println("Employee Id :- "+dto.getEmpId());
		System.out.println("Employee Name :- "+dto.getEmpName());
		System.out.println("Employee Email :- "+dto.getEmailId());
		System.out.println("Project Name :- "+dto.getProjectName());
		System.out.println("Project Head :- "+dto.getProjectHeadEmail());
		System.out.println("************************************************");
	}
	
	
}//end of class
