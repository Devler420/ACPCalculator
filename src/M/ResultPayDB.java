package M;

public class ResultPayDB
{
	public String users_nickname;
	public double payment_price;
	public String payment_to_who_id;
//	public String status;
	
	public ResultPayDB()
	{
		
	}
	public ResultPayDB(
			String xusers_nickname,
			double xpayment_price,
			String xpayment_to_who_id)
		//  String xstatus)
	{
		users_nickname = xusers_nickname;
		payment_price = xpayment_price;
		payment_to_who_id = xpayment_to_who_id;
//		status = xstatus;
	}
}
