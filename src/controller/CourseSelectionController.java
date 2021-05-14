package controller;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.CourseSelection;
import sqliteUtil.CourseInfo;

public class CourseSelectionController implements Initializable{
	private static CourseSelection courseSelection = CourseSelection.getCourseSelectionInstance("");
	private static ObservableList<CourseInfo> courses = FXCollections.observableArrayList(sqliteUtil.DataBaseUtil.getAllTables(JfxApp.App.MY_DATABASE));
	
	
    @FXML
    private BorderPane courseSelectionBP;

    @FXML
    private TableView<CourseInfo> courseSelectionTV;

    @FXML
    private TableColumn<CourseInfo, String> courseNameTC;

    @FXML
    private TableColumn<CourseInfo, Integer> numOfTermsTC;

    @FXML
    void onSelectionMade(MouseEvent event) {
    	
    	CourseInfo courseSelection = courseSelectionTV.getSelectionModel().getSelectedItem();
    	String courseName = courseSelection.getCourseName();
    	CourseSelection.getCourseSelectionInstance(courseName);
    	System.out.println("Selection: " + courseSelection.getCourseName());
    	
    	
    }

    public void updateTable() {
    	courses = FXCollections.observableArrayList(sqliteUtil.DataBaseUtil.getAllTables(JfxApp.App.MY_DATABASE));
    	courseNameTC.setCellValueFactory(new PropertyValueFactory<CourseInfo,String>("courseName"));
    	numOfTermsTC.setCellValueFactory(new PropertyValueFactory<CourseInfo,Integer>("numOfTerms"));
    		
    	courseSelectionTV.setItems(courses);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		updateTable();
		System.out.println("Incomplete Class: Missing Ability to update on switching of scenes");
		System.out.println("Incomplete Class: Missing Ability navigave to different Scene");
	}
}
