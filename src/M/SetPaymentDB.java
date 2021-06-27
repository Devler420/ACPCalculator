package M;

public class SetPaymentDB
{
	public int billing_details_result_id;
	public int billing_id;
	public String users_nickname;
	public double payment_price;
	public String payment_to_who_id;
	
	public SetPaymentDB()
	{
		
	}
	public SetPaymentDB(
			int xbilling_details_result_id,
			int xbilling_id,
			String xusers_nickname,
			double xpayment_price,
			String xpayment_to_who_id)
	{
		billing_details_result_id = xbilling_details_result_id;
		billing_id = xbilling_id;
		users_nickname = xusers_nickname;
		payment_price = xpayment_price;
		payment_to_who_id = xpayment_to_who_id;
	}
}
