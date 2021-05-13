package sqliteUtil;

public class QueryFormatter {

	private String query;
	private String tableName;
	
	public QueryFormatter(String tableName, String query) {
		this.query = query;
		this.tableName = tableName;
	}
	
	public String sqlQueryAllString() {
		return "SELECT * FROM "+ tableName + " WHERE " + query;
	}
	
	public String sqlQueryValuesString(String columnName) {
		return "SELECT " + columnName + " FROM "+ tableName + " WHERE " + query;
		
	}
	
}
