package controller;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.CourseInfo;
import model.CourseSelection;
import model.SceneChangeUtil;

public class CourseSelectionController implements Initializable{
	private static CourseSelection courseSelection = CourseSelection.getCourseSelectionInstance("");
	private static ObservableList<CourseInfo> courses = FXCollections.observableArrayList(sqliteUtil.DataBaseUtil.getAllTables(JfxApp.App.MY_DATABASE));
	
	@FXML 
	private Button editCourseListBTN;
	
	@FXML 
	private Button courseSelectionBTN;
	
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
    	if (courseSelection != null) {
    		String courseName = courseSelection.getCourseName();
        	CourseSelection.getCourseSelectionInstance("").setCourseName(courseName);
        	System.out.println("Selection: " + courseSelection.getCourseName());
        	 SceneChangeUtil scu = new SceneChangeUtil();
     	    scu.switchScenes("CourseEditor.fxml", event);
    	}
    }
    
    @FXML
	void onEditCourseListBTNClicked(ActionEvent event) {
	    SceneChangeUtil scu = new SceneChangeUtil();
	    scu.switchScenes("CourseListEditor.fxml", event);
	}
	    
	@FXML
	void onCourseSelectionBTNClicked(ActionEvent event) {
	    SceneChangeUtil scu = new SceneChangeUtil();
	    scu.switchScenes("CourseSelection.fxml", event);
	}
    
    
    

    public void updateTable() {
    	courses = FXCollections.observableArrayList(sqliteUtil.DataBaseUtil.getAllTables(JfxApp.App.MY_DATABASE));
    	courseNameTC.setCellValueFactory(new PropertyValueFactory<CourseInfo,String>("courseName"));
    	numOfTermsTC.setCellValueFactory(new PropertyValueFactory<CourseInfo,Integer>("numOfTerms"));
    		
    	courseSelectionTV.setItems(courses);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		courseSelection = CourseSelection.getCourseSelectionInstance("");
		updateTable();
	}
	
}
