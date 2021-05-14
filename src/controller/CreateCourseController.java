package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CreateCourseController {

    @FXML
    private BorderPane createCourseBP;

    @FXML
    private TextField courseNameTF;

    @FXML
    private Button addCourseBTN;

    @FXML
    void onAddCourseButtonClicked(ActionEvent event) {
    	System.out.println("onAddCourseButtonClicked: " + courseNameTF.getText());
    	if (!courseNameTF.getText().isBlank()) {
    		String courseName = courseNameTF.getText().toUpperCase();
    		System.out.println(courseName);
    		/**
    		 * Assuming Uniqueness
    		 */
    		sqliteUtil.DataBaseUtil.createTableIfAbsent(JfxApp.App.MY_DATABASE, courseName, JfxApp.App.COURSE_FORMAT);
    		courseNameTF.setText("");
    		
    	}
    }

}

