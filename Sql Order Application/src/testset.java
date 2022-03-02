import java.sql.*;

public class testset {
	public static void main(String[] args) throws SQLException {
		SQL_order testorder = new SQL_order();
		testorder.connect();
		testorder.stmt = testorder.conn.createStatement();
		ResultSet rs = testorder.stmt.executeQuery("SELECT OrderID, CustomerID, EmployeeID, OrderDate FROM ORDERS");	
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println(rs);
		for(int i = 1;i<rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnName(i) + ", ");
		}
		
		while(rs.next()) {
			int orderID = rs.getInt("OrderID");
			String CustomerID = rs.getString("CustomerID");
			int EmployeeID = rs.getInt("EmployeeID");
			String orderdate = rs.getDate("OrderDate").toString();
			
			System.out.println(orderID + " " + CustomerID + " " + EmployeeID + " " + orderdate);
		}
		
		System.out.println(" ");
		testorder.CloseConnection();
	}
}
