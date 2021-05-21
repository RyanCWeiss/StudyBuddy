package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.CourseInfo;
import model.CourseSelection;
import model.SceneChangeUtil;
import model.TermInfo;
import model.TermSelection;

public class CourseEditorController implements Initializable {
	
	private static CourseSelection courseSelection = CourseSelection.getCourseSelectionInstance("");
	private static ObservableList<TermInfo> terms = FXCollections.observableArrayList(sqliteUtil.DataBaseUtil.getAllTerms(JfxApp.App.MY_DATABASE, CourseSelection.getCourseSelectionInstance("").getCourseName()));
	private TermSelection termSelection = TermSelection.getTermSelectionInstance("","","");
	
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
	private Button studyBTN;
	
	
	@FXML
	void onStudyBTNClicked(ActionEvent event) {
	    SceneChangeUtil scu = new SceneChangeUtil();
	    scu.switchScenes("Carousel.fxml", event);
	}
	@FXML
	void onUpdateTermBTNClicked(ActionEvent event) throws MalformedURLException {
	   System.out.println("Update Term: inc");
	   
	   String term = termTF.getText();
	   String content = textContentTF.getText();
	   String path = pathChosen;
	   
	   sqliteUtil.DataBaseUtil.updateItemValues(JfxApp.App.MY_DATABASE, courseSelection.getCourseName(), "TERM = \'" + termSelection.getTerm() + "\'", term, content, path);
	   
	   textContentTF.setText("");
	   termTF.setText("");
	   
	   updateTable();
	}
	@FXML
	void onAddTermBTNClicked(ActionEvent event) {
		String insert;
		if (pathChosen != null) {

			insert = "\'" + termTF.getText() + "\', \'" + textContentTF.getText() + "\', \'"  + pathChosen + "\'";
		} else {

			insert = "\'" + termTF.getText() + "\', \'" + textContentTF.getText() + "\', \'\'";
		}
		sqliteUtil.DataBaseUtil.addToTable(JfxApp.App.MY_DATABASE, courseSelection.getCourseName(), JfxApp.App.COLUMN_NAMES, insert);
		System.out.println("Add Term: inc: " +insert);
		textContentTF.setText("");
		termTF.setText("");
		pathChosen = null;
		updateTable();
		
		 
	}
	@FXML
	void onDeleteTermBTNClicked(ActionEvent event) {
		 String term = termSelection.getTerm();
		 
		 System.out.println("Delete Term" + term);
		 
		 sqliteUtil.DataBaseUtil.deleteItem(JfxApp.App.MY_DATABASE, courseSelection.getCourseName(), "TERM = \'" + term +"\'");
		 
		 textContentTF.setText("");
		 termTF.setText("");
		 pathChosen = null;
		 updateTable();
	}
	
	@FXML
	void onSelectAttachmentBTNClicked(ActionEvent event) throws IOException {
	    FileChooser chooser = new FileChooser();
	    chooser.setTitle("Open File");
	    chooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
	    Stage stage = (Stage)bp.getScene().getWindow();
	    File file = chooser.showOpenDialog(stage);
	    if (file != null) {
	        String path1 = file.toString();
	        System.out.println("FILE CHOOSER INCOMPLETE: NEED TO CLONE THE FILE AND PUT INTO PROPER LOCAL FOLDER");
	        
	        InputStream is = null;
	        OutputStream os = null;
	        String [] parts = path1.split("/");
	        String name = parts[parts.length-1];
	        String path2 = "src/DataBases/Attachments/"+ name;
	        try {
	        	
	            is = new FileInputStream(new File(path1));
	            os = new FileOutputStream(new File(path2));
	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = is.read(buffer)) > 0) {
	                os.write(buffer, 0, length);
	            }
	        } finally {
	            is.close();
	            os.close();
	        }
	        pathChosen = name;
	    }
	}
	
	@FXML
	void onCreateCourseBTNClicked(ActionEvent event) {
	    SceneChangeUtil scu = new SceneChangeUtil();
	    scu.switchScenes("CourseListEditor.fxml", event);
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
		courseSelection = CourseSelection.getCourseSelectionInstance("");
		courseTXT.setText(courseSelection.getCourseName());
		LinkedList<TermInfo> termList = sqliteUtil.DataBaseUtil.getAllTerms(JfxApp.App.MY_DATABASE, CourseSelection.getCourseSelectionInstance("").getCourseName());
		terms = FXCollections.observableArrayList(termList);//TERM, TEXT_CONTENT, PDF_PATH
    	termTC.setCellValueFactory(new PropertyValueFactory<TermInfo,String>("term"));
    	contentTC.setCellValueFactory(new PropertyValueFactory<TermInfo,String>("content"));
    	attachmentTC.setCellValueFactory(new PropertyValueFactory<TermInfo,String>("pdfPath"));
    	
    	termTV.setItems(terms);
    	pathChosen = null;
	}
		
	@FXML
	public void onRemoveAttachmentBTNClicked(ActionEvent event) {
		pathChosen = null;
		attachmentIV.setImage(null);
	}
	
	@FXML
	public void onTermTVSelected(MouseEvent event) throws MalformedURLException {
		System.out.println("term selected");
		
		TermInfo selection = termTV.getSelectionModel().getSelectedItem();
    	if (selection != null) {
    		String term = selection.getTerm();
    		String content = selection.getContent();
    		String path = selection.getPdfPath();
        	
    		TermSelection.getTermSelectionInstance("","","").changeTermSelection(term, content, path);
        	System.out.println("Selection: " + termSelection.getTerm());
    	
		textContentTF.setText(content);
		termTF.setText(term);
		
		
		pathChosen = path;
		
		Path imagePath = Paths.get("./src/DataBases/Attachments/"+ path).normalize().toAbsolutePath();
		Image image = new Image(imagePath.toUri().toURL().toString());
		attachmentIV.setImage(image);
		attachmentIV.setVisible(false);
		attachmentIV.setVisible(true);
    	}
	}
	
    
}
