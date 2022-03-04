package application;
import java.sql.*;
import java.util.List;




public class testset {
	
	public class object_sample {
		
		int OrderID;
		String ProductName;
		String ContactName;
		Float cost;
		String Date;
		
		public object_sample() {
			
		}
		
		public List<Object> getList(List<Object> list) {
			return list;
		}
	}
	
	public static void main(String[] args) throws SQLException {
		SQL_order testorder = new SQL_order();
		testorder.connect();
		testorder.stmt = testorder.conn.createStatement();
		ResultSet rs = testorder.stmt.executeQuery("SELECT * FROM [java database view]");	
		ResultSetMetaData rsmd = rs.getMetaData();
		for(int i = 1;i<=rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnName(i) + ", ");
		}
		
		System.out.println(" ");
		
		while(rs.next()) {
			int OrderID = rs.getInt(1);
			String ProductName = rs.getString(2);
			String ContactName = rs.getString(3);
			Float cost = rs.getFloat(4);
			String date = rs.getDate(5).toString();
			System.out.println(OrderID +" ["+ ProductName + "] [" + ContactName + "] [" + cost + "€] [" + date + "]");
			
		}
		
		List<String> resultStr = testorder.stmtToString();
		
		for(int i = 0; i<resultStr.size(); i++)
		{
			System.out.println(resultStr.get(i));
		}
		System.out.println(resultStr.size());
		testorder.CloseConnection();
	}
	
	
}
