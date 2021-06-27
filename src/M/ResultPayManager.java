package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import C.GlobalData;

public class ResultPayManager
{
	public static ArrayList<ResultPayDB> getResultPay()
	{
		ArrayList<ResultPayDB> list = new ArrayList<ResultPayDB>();
		try
	    {
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      String query = "Select a.nickname, b.payment_price, c.nickname AS toWho FROM users AS a INNER JOIN billing_details_result AS b "
	      		+ "ON a.users_id = b.users_id INNER JOIN users AS c ON b.payment_to_who_id = c.users_id "
	      		+ "WHERE b.billing_id = '"+GlobalData.CurrentResult_billing_id+"'";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);

	      while (rs.next())
	      {
	        String users_nickname = rs.getString("nickname");
	        double payment_price = rs.getDouble("payment_price");
	        String payment_to_who = rs.getString("toWho");
	        
	        ResultPayDB cc = new ResultPayDB(users_nickname,payment_price,payment_to_who);
	        list.add(cc);
	        
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		return list;
	}
	
}
