package JfxApp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
	
	private static ObservableList<Node> stackPaneContent;
	
	
	public static String MY_DATABASE = "src/DataBases/CoursesDB";
	public static String  TABLE_NAME = "Courses";
	public static String COURSE_FORMAT = "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "TERM VARCHAR(100) NOT NULL UNIQUE, "
			+ "TEXT_CONTENT VARCHAR(100) NOT NULL, "
			+ "PDF_PATH VARCHAR(50)"
			+ ")";
	
	public static String COLUMN_NAMES = "(TERM, TEXT_CONTENT, PDF_PATH)";
	public static String NEW_INSERT = "\'Biology\', \'intro course\', \'\'";

	//	public static String TABLE_FORMAT =  "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
//			+ "FirstName VARCHAR(50), "
//			+ "UserName VARCHAR(50) NOT NULL UNIQUE, "
//			+ "CustomerOrder VARCHAR(50) NOT NULL, "
//			+ "Price REAL NOT NULL"
//			+ ")";
	
	public static void main(String[] args) {
		launch(args);
		
//		String NEW_QUERY = "FirstName=\'Ryan\' AND UserName=\'Ryan21\'";
//		String ORDER_COL = "customerOrder";
//		String UPDATED_ORDER = "Tacos, Nachos, Margharita";
//		String PRICE_COL = "Price";
//		String UPDATED_PRICE = "20.0";
		
		
		
		
//		System.out.println(sqliteUtil.DataBaseUtil.createTableIfAbsent(MY_DATABASE, TABLE_NAME, TABLE_FORMAT));

//		System.out.println("add to table: " + sqliteUtil.DataBaseUtil.addToTable(MY_DATABASE, "BIO101", COLUMN_NAMES, NEW_INSERT));

//		System.out.println("table exists: " + sqliteUtil.DataBaseUtil.tableExists(MY_DATABASE, TABLE_NAME));
//		System.out.println("search for Ryans Order: "); sqliteUtil.DataBaseUtil.search(MY_DATABASE, TABLE_NAME, NEW_QUERY);
//		System.out.println("update  Ryans Order: "); sqliteUtil.DataBaseUtil.updateItem(MY_DATABASE, TABLE_NAME, NEW_QUERY, ORDER_COL, UPDATED_ORDER);
//		System.out.println("update  Ryans Price: "); sqliteUtil.DataBaseUtil.updateItem(MY_DATABASE, TABLE_NAME, NEW_QUERY, PRICE_COL, UPDATED_PRICE);
//		System.out.println("Ryans Updated Order: "); sqliteUtil.DataBaseUtil.search(MY_DATABASE, TABLE_NAME, NEW_QUERY);
//		
//		System.out.println("dropDB(): " + sqliteUtil.DataBaseUtil.dropTable(MY_DATABASE, "BIO101"));
//
//		System.out.println("table exists: " + sqliteUtil.DataBaseUtil.tableExists(MY_DATABASE, TABLE_NAME));
//		System.out.print("TABLES: "); sqliteUtil.DataBaseUtil.getAllTables(MY_DATABASE).forEach(table -> {System.out.print(table + " ");});
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/view/root.fxml"));
		StackPane sp = new StackPane();
		BorderPane courseSelection = (BorderPane)FXMLLoader.load(getClass().getResource("/view/CourseSelection.fxml"));
		BorderPane courseEditor = (BorderPane)FXMLLoader.load(getClass().getResource("/view/CourseEditor.fxml"));
		BorderPane card = (BorderPane)FXMLLoader.load(getClass().getResource("/view/Card.fxml"));
		BorderPane createCourse = (BorderPane)FXMLLoader.load(getClass().getResource("/view/CreateCourse.fxml"));
		
		sp.getChildren().addAll(courseSelection, courseEditor, card, createCourse);
		root.setCenter(sp);
		
//		courseSelection.
//		.updateTable();
		
		Scene scene = new Scene(root);
		
		stackPaneContent = sp.getChildren();
		controller.RootController.setStackPaneContent(stackPaneContent);
		stackPaneContent.get(0).setVisible(true);
    	stackPaneContent.get(1).setVisible(false);
    	stackPaneContent.get(2).setVisible(false);
    	stackPaneContent.get(3).setVisible(false);
//    	stackPaneContent.get(4).setVisible(false);
//    	stackPaneContent.get(5).setVisible(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	@Override
	public void stop() {
		
	}
	@Override
	public void init() {
		
	}
	public static ObservableList<Node> getStackPaneContent(){
		return stackPaneContent;
	}

}
