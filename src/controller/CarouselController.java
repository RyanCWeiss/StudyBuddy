package controller;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.CourseSelection;
import model.SceneChangeUtil;
import model.TermInfo;

public class CarouselController implements Initializable {
	
	LinkedList<TermInfo> terms = sqliteUtil.DataBaseUtil.getAllTerms(JfxApp.App.MY_DATABASE, CourseSelection.getCourseSelectionInstance("").getCourseName());
	int index = 0;
	Text t = new Text("");
    @FXML
    private BorderPane cardBP;

    @FXML
    private Button courseSelectionBTN;

    @FXML
    private Button createCourseBTN;

    @FXML
    private Text termTXT;

    @FXML
    private ImageView attachmentIV;

    @FXML
    private TextFlow contentTF;

    @FXML
    private Button contentBTN;

    @FXML
    private Button attachmentBTN;

    @FXML
    private Button previoiusBTN;

    @FXML
    private Button nextBTN;

    @FXML
    void onNextBTNClicked(ActionEvent event) {
    	updateCard(1);
    }

    @FXML
    void onPreviousBTNClicked(ActionEvent event) {
    	updateCard(-1);
    }
    
    @FXML
    void onAttachmentBTNClicked(ActionEvent event) {
    	contentTF.setVisible(false);
    	attachmentIV.setVisible(true);
    }
    @FXML
    void onContentBTNClicked(ActionEvent event) {
    	contentTF.setVisible(true);
    	attachmentIV.setVisible(false);	
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

	
   	public void updateCard(int i) {
   		long start = System.currentTimeMillis();
   		index+=i;
   		if (index < 0) {
   			index = terms.size()-1;
   		}
   		if (index > terms.size()-1) {
   			index = 0;
   		}
   		
   		TermInfo termInfo = terms.get(index);
   		String term = termInfo.getTerm();
   		String content = termInfo.getContent();
   		String path = termInfo.getPdfPath();
   		if (!path.isBlank()) {
   			attachmentIV.setImage(new Image(path));
   		}
   		termTXT.setText(term);
   		t.setText(content);
   		contentTF.getChildren().set(0, t);
   		
   		
   		contentTF.setVisible(false);
   		attachmentIV.setVisible(true);
   		contentTF.setVisible(true);
   		attachmentIV.setVisible(false);
   	}
   	
   	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
   		t.setFont(Font.font("verdana",FontWeight.NORMAL, FontPosture.REGULAR,24));
   		contentTF.getChildren().add(t);
		
   		updateCard(0);
		
		
	}

}
