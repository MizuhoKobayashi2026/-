
public class InvalidGradeException extends StudentManagementException {
	public InvalidGradeException(String message) {
		super("InvalidGrade" + message);
	}
}
