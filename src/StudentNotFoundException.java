
public class StudentNotFoundException extends StudentManagementException {
	public StudentNotFoundException(String message) {
		super("Student Not Found" + message);
	}
}
