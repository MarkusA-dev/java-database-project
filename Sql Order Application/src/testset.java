public class testset {
	public static void main(String[] args) {
		SQL_order testorder = new SQL_order();
		testorder.connect();
		testorder.CloseConnection();
	}
}
