package p1.exceptions;

public class InvalidProjectIdException extends Exception {
	
	String projectId;

	public InvalidProjectIdException(String projectId) {
		super();
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "Project Id Should be Numeric and less than 50 , entered value is "+projectId;
	}
	
	

}