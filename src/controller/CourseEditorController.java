package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

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

}
