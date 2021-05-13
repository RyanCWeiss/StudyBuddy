package JfxApp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
	
	private static ObservableList<Node> stackPaneContent;
	
	
	public static String MY_DATABASE = "src/DataBases/OrdersDB";
	public static String  TABLE_NAME = "Orders";
	public static String COLUMN_NAMES = "(FirstName, UserName, CustomerOrder, Price)";
	public static String TABLE_FORMAT =  "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "FirstName VARCHAR(50), "
			+ "UserName VARCHAR(50) NOT NULL UNIQUE, "
			+ "CustomerOrder VARCHAR(50) NOT NULL, "
			+ "Price REAL NOT NULL"
			+ ")";
//	public static String COURSE_FORMAT =  "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
//			+ "TERM VARCHAR(100) NOT NULL UNIQUE, "
//			+ "DEFINITION VARCHAR(100) NOT NULL UNIQUE, "
//			+ "PDF BLOB, "
//			+ ")";
	
	public static void main(String[] args) {
		launch(args);
		
		String NEW_INSERT = "\'Ryan\', \'Ryan21\', \'Tacos and Nachos\', 10.0";
		String NEW_QUERY = "FirstName=\'Ryan\' AND UserName=\'Ryan21\'";
		String ORDER_COL = "customerOrder";
		String UPDATED_ORDER = "Tacos, Nachos, Margharita";
		String PRICE_COL = "Price";
		String UPDATED_PRICE = "20.0";
		
		
		
		
//		System.out.println(sqliteUtil.DataBaseUtil.createTableIfAbsent(MY_DATABASE, TABLE_NAME, TABLE_FORMAT));
//		System.out.println("add to table: " + sqliteUtil.DataBaseUtil.addToTable(MY_DATABASE, TABLE_NAME, COLUMN_NAMES, NEW_INSERT));
//		System.out.println("table exists: " + sqliteUtil.DataBaseUtil.tableExists(MY_DATABASE, TABLE_NAME));
//		System.out.println("search for Ryans Order: "); sqliteUtil.DataBaseUtil.search(MY_DATABASE, TABLE_NAME, NEW_QUERY);
//		System.out.println("update  Ryans Order: "); sqliteUtil.DataBaseUtil.updateItem(MY_DATABASE, TABLE_NAME, NEW_QUERY, ORDER_COL, UPDATED_ORDER);
//		System.out.println("update  Ryans Price: "); sqliteUtil.DataBaseUtil.updateItem(MY_DATABASE, TABLE_NAME, NEW_QUERY, PRICE_COL, UPDATED_PRICE);
//		System.out.println("Ryans Updated Order: "); sqliteUtil.DataBaseUtil.search(MY_DATABASE, TABLE_NAME, NEW_QUERY);
//		
//		System.out.println("dropDB(): " + sqliteUtil.DataBaseUtil.dropTable(MY_DATABASE, TABLE_NAME));
//
//		System.out.println("table exists: " + sqliteUtil.DataBaseUtil.tableExists(MY_DATABASE, TABLE_NAME));
//		System.out.print("TABLES: "); sqliteUtil.DataBaseUtil.getAllTables(MY_DATABASE).forEach(table -> {System.out.print(table + " ");});
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/view/root.fxml"));
		
		// trying to get the panes to be centered
		StackPane sp = new StackPane();
		
		
		BorderPane courseSelection = (BorderPane)FXMLLoader.load(getClass().getResource("/view/CourseSelection.fxml"));
		BorderPane courseEditor = (BorderPane)FXMLLoader.load(getClass().getResource("/view/CourseEditor.fxml"));
		
		BorderPane card = (BorderPane)FXMLLoader.load(getClass().getResource("/view/Card.fxml"));
		
//		sp.setAlignment(Pos.BASELINE_CENTER);
		
		sp.getChildren().addAll(card, courseEditor, courseSelection);
		root.setCenter(sp);
		
		Scene scene = new Scene(root);
		
		stackPaneContent = sp.getChildren();
		controller.RootController.setStackPaneContent(stackPaneContent);
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
