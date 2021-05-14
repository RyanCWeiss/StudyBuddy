package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.TermInfo;
import model.TermSelection;

public class TermSelectionController {

    @FXML
    private BorderPane termSelectionBP;

    @FXML
    private TableView<TermInfo> termSelectionTV;

    @FXML
    private TableColumn<TermInfo, String> TermTC;

    @FXML
    private TableColumn<TermInfo, String> contentTC;

    @FXML
    void onSelectionMade(MouseEvent event) {
    	TermInfo termSelection = termSelectionTV.getSelectionModel().getSelectedItem();
    	String term = termSelection.getTerm();
    	String content = termSelection.getContent();
    	String pdfPath = termSelection.getPdfPath();
    	
    	TermSelection.changeTermSelection(term, content, pdfPath);
    	System.out.println("Selection: " + termSelection.getTerm());
    }

}
