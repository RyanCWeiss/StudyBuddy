package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.CourseInfo;
import model.CourseSelection;
import model.SceneChangeUtil;

public class CourseListEditorController implements Initializable{

    @FXML
    private BorderPane createCourseBP;

    @FXML
    private TextField courseNameTF;

    @FXML
    private Button addCourseBTN;
    
    @FXML 
	private Button editCourseListBTN;
	
	@FXML 
	private Button courseSelectionBTN;
	
	/* additions */
	
	private static CourseSelection courseSelection = CourseSelection.getCourseSelectionInstance("");
	private static ObservableList<CourseInfo> courses = FXCollections.observableArrayList(sqliteUtil.DataBaseUtil.getAllTables(JfxApp.App.MY_DATABASE));
	
	@FXML 
	private Button removeCourseBTN;
	
	@FXML 
	private Button updateCourseBTN;
	
	@FXML
    private TextField updateCourseNameTF;
	
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
        	courseNameTF.setText(courseName);
        	System.out.println("Selection: " + courseSelection.getCourseName());
        	updateCourseNameTF.setText(courseName);
    	}
    	
    }

    
    public void updateTable() {
    	courses = FXCollections.observableArrayList(sqliteUtil.DataBaseUtil.getAllTables(JfxApp.App.MY_DATABASE));
    	courseNameTC.setCellValueFactory(new PropertyValueFactory<CourseInfo,String>("courseName"));
    	numOfTermsTC.setCellValueFactory(new PropertyValueFactory<CourseInfo,Integer>("numOfTerms"));
    		
    	courseSelectionTV.setItems(courses);
    }
    
    @FXML
    void onUpdateCourseButtonClicked(ActionEvent event) {
    	String newName = updateCourseNameTF.getText();
    	try{
    		sqliteUtil.DataBaseUtil.updateTableName(JfxApp.App.MY_DATABASE, CourseSelection.getCourseSelectionInstance("").getCourseName(), newName);
    	
    		if (courseSelection != null) {
//    			String courseName = courseSelection.getCourseName();
    			CourseSelection.getCourseSelectionInstance("").setCourseName(newName);
    			courseNameTF.setText(newName);
    			System.out.println("Selection: " + courseSelection.getCourseName());
    			updateCourseNameTF.setText(newName);
    		} else {
    			courseNameTF.setText("");
    			updateCourseNameTF.setText("");
    		}
    	} finally {
    		updateTable();
    	}
    }
    
    @FXML
    void onRemoveCourseButtonClicked(ActionEvent event) {
    	
    	sqliteUtil.DataBaseUtil.dropTable(JfxApp.App.MY_DATABASE, CourseSelection.getCourseSelectionInstance("").getCourseName());
    	CourseSelection.getCourseSelectionInstance("").setNull();
    	updateTable();
    	courseSelection = null;
    	courseNameTF.setText("");
		updateCourseNameTF.setText("");
    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		updateTable();
	}
	
	
	
	
	/* end additions */
	
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
    		updateTable();
    		
    	}
    }

}

