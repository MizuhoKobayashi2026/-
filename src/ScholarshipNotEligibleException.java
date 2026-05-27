
public class ScholarshipNotEligibleException extends StudentManagementException {
	public ScholarshipNotEligibleException(String message) {
		super("Scholarship Not Eligible" + message);
	}
}
