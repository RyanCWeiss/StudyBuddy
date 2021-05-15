package controller;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.CourseInfo;
import model.CourseSelection;
import model.SceneChangeUtil;
import model.TermInfo;

public class CourseEditorController implements Initializable {
	
	private static CourseSelection courseSelection = CourseSelection.getCourseSelectionInstance("");
	private static ObservableList<TermInfo> terms = FXCollections.observableArrayList(sqliteUtil.DataBaseUtil.getAllTerms(JfxApp.App.MY_DATABASE, CourseSelection.getCourseSelectionInstance("").getCourseName()));
	private static String pathChosen = null;
    @FXML
    private BorderPane bp;

    @FXML
    private TableView<TermInfo> termTV;

    @FXML
    private TableColumn<TermInfo, String> termTC;

    @FXML
    private TableColumn<TermInfo, String> contentTC;

    @FXML
    private TableColumn<TermInfo, String> attachmentTC;

    @FXML
    private Button selectAttachmentBTN;

    @FXML
    private TextArea textContentTF;

    @FXML
    private TextField termTF;

    @FXML
    private ImageView attachmentIV;

    @FXML
    private Text courseTXT;
    
    @FXML 
	private Button createCourseBTN;
	
	@FXML 
	private Button courseSelectionBTN;
	
	@FXML 
	private Button updateTermBTN;
		
	@FXML 
	private Button deleteTermBTN;
	
	@FXML 
	private Button addTermBTN;

	@FXML
	void onUpdateTermBTNClicked(ActionEvent event) {
	   System.out.println("Update Term");
	}
	@FXML
	void onAddTermBTNClicked(ActionEvent event) {
		
		String insert = termTF.getText() + ", " + textContentTF.getText() + ", " +pathChosen;
		sqliteUtil.DataBaseUtil.addToTable(JfxApp.App.MY_DATABASE, courseSelection.getCourseName(), JfxApp.App.COLUMN_NAMES, insert);
		System.out.println("Add Term");
		pathChosen = null;
		 
	}
	@FXML
	void onDeleteTermBTNClicked(ActionEvent event) {
		 System.out.println("Delete Term");
	}
	
	@FXML
	void onSelectAttachmentBTNClicked(ActionEvent event) {
	    FileChooser chooser = new FileChooser();
	    chooser.setTitle("Open File");
	    chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
	    Stage stage = (Stage)bp.getScene().getWindow();
	    File file = chooser.showOpenDialog(stage);
	    if (file != null) {
	        String cwd = System. getProperty("user.dir");
	        String s = new File(cwd).toURI().relativize(file.toURI()).getPath();
	        s = s.substring(4,s.length());
	        
	    }
	        System.out.println("FILE CHOOSER INCOMPLETE");
	}
	
	@FXML
	void onCreateCourseBTNClicked(ActionEvent event) {
	    SceneChangeUtil scu = new SceneChangeUtil();
	    scu.switchScenes("CreateCourse.fxml", event);
	}
	    
	@FXML
	void onCourseSelectionBTNClicked(ActionEvent event) {
	    SceneChangeUtil scu = new SceneChangeUtil();
	    scu.switchScenes("CourseSelection.fxml", event);
	}

	
	public void updateTable() {
		terms = FXCollections.observableArrayList(sqliteUtil.DataBaseUtil.getAllTerms(JfxApp.App.MY_DATABASE, CourseSelection.getCourseSelectionInstance("").getCourseName()));
    	termTC.setCellValueFactory(new PropertyValueFactory<TermInfo,String>("term"));
    	contentTC.setCellValueFactory(new PropertyValueFactory<TermInfo,String>("content"));
    	attachmentTC.setCellValueFactory(new PropertyValueFactory<TermInfo,String>("pdfPath"));
    		
    	termTV.setItems(terms);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		courseTXT.setText(courseSelection.getCourseName());
		LinkedList<TermInfo> termList = sqliteUtil.DataBaseUtil.getAllTerms(JfxApp.App.MY_DATABASE, CourseSelection.getCourseSelectionInstance("").getCourseName());
		terms = FXCollections.observableArrayList(termList);
    	termTC.setCellValueFactory(new PropertyValueFactory<TermInfo,String>("term"));
    	contentTC.setCellValueFactory(new PropertyValueFactory<TermInfo,String>("content"));
    	attachmentTC.setCellValueFactory(new PropertyValueFactory<TermInfo,String>("pdfPath"));
    		
    	termTV.setItems(terms);
	}
	
	
	@FXML
	public void onTermTVSelected(ActionEvent event) {
		System.out.println("term selected");
		pathChosen = null;
	}
	
	
    
//    FileChooser chooser = new FileChooser();
//    chooser.setTitle("Open File");
//    chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
//    Stage stage = (Stage)anchorpane.getScene().getWindow();
//    File file = chooser.showOpenDialog(stage);
//    if (file != null) {
//        String cwd = System. getProperty("user.dir");
//        String s = new File(cwd).toURI().relativize(file.toURI()).getPath();
//        s = s.substring(4,s.length());
//        lib.getLoggedInUser().setProPic(s);
//        System.out.println( lib.getLoggedInUser().getProPic());
//    	profileIV.setImage(new Image(lib.getLoggedInUser().getProPic()));  
    
}
