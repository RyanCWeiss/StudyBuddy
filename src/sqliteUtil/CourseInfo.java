package sqliteUtil;

public class CourseInfo {

	
	private String courseName;
	private int numOfTerms;
	
	public CourseInfo(String courseName, int numOfTerms) {
		this.courseName = courseName;
		this.numOfTerms = numOfTerms;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getNumOfTerms() {
		return numOfTerms;
	}
	
	
}
