package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class RootController{
	
	private static ObservableList<Node> stackPaneContent;

    @FXML
    private BorderPane navBP;

    @FXML
    private Button courseSelectionButton;

    @FXML
    private Button editCourseListButton;

    @FXML
    private Button createCourseButton;

    @FXML
    private Button editCourseButton;

    @FXML
    private Button returnToCourseButton;

    @FXML
    void onCourseSelectionButtonClicked(ActionEvent event) {
//    	stackPane.se
    }

    @FXML
    void onCreateCourseButtonClicked(ActionEvent event) {

    }

    @FXML
    void onEditCourseButtonClicked(ActionEvent event) {

    }

    @FXML
    void onEditCourseListButtonClicked(ActionEvent event) {

    }

    @FXML
    void onReturnToCourseButtonClicked(ActionEvent event) {

    }

    public static void setStackPaneContent(ObservableList<Node> spChildren) {
    	stackPaneContent = JfxApp.App.getStackPaneContent();
    }
    public static ObservableList<Node> getStackPaneContent() {
    	return stackPaneContent;
    }

}
