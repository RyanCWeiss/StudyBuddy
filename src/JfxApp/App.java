package JfxApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	
	public static String MY_DATABASE = "src/DataBases/CoursesDB";
	public static String  TABLE_NAME = "Courses";
	public static String COURSE_FORMAT = "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "TERM VARCHAR(100) NOT NULL UNIQUE, "
			+ "TEXT_CONTENT VARCHAR(100) NOT NULL, "
			+ "PDF_PATH VARCHAR(50)"
			+ ")";
	
	public static String COLUMN_NAMES = "(TERM, TEXT_CONTENT, PDF_PATH)";
	public static String NEW_INSERT = "\'Biology\', \'intro course\', \'\'";


	public static void main(String[] args) {
		launch(args);
		
	
		
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
		BorderPane courseSelection = (BorderPane)FXMLLoader.load(getClass().getResource("/view/CourseSelection.fxml"));
		Scene scene = new Scene(courseSelection);
		scene.getStylesheets().add("/view/style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	@Override
	public void stop() {
		
	}
	@Override
	public void init() {
		
	}


}
