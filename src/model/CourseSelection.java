package model;

public class CourseSelection {

	private static CourseSelection courseSelection = null;
	
	private String courseName;
	
	
	private CourseSelection(String courseName) {
		this.courseName = courseName;
	}
	
	public static CourseSelection getCourseSelectionInstance(String courseName) {
		if (CourseSelection.courseSelection != null) {
			return CourseSelection.courseSelection;
		} else {
			CourseSelection.courseSelection = new CourseSelection(courseName);
			return CourseSelection.courseSelection;
		}
		
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setNull() {
		CourseSelection.courseSelection = null;
	}

	
}
