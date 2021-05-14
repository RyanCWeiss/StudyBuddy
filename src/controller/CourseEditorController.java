package controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class CourseEditorController {

    @FXML
    private BorderPane courseSelectionBP;

    @FXML
    private TableView<?> questionSelectotTV;

    @FXML
    private TableColumn<?, ?> termTC;

    @FXML
    private TableColumn<?, ?> contentTC;

    @FXML
    private TableColumn<?, ?> attachmentTC;

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
