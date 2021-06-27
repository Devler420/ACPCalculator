package M;

public class ItemDB
{
	public String db_name;
	public int billing_details_item_id;
	public double billing_details_item_price;
	public String users_nickname;
	
	public ItemDB()
	{
		
	}
	public ItemDB(
			String xdb_name,
			int xbilling_details_item_id,
			double xbilling_details_item_price,
			String xusers_nickname)
	{
		db_name = xdb_name;
		billing_details_item_id = xbilling_details_item_id;
		billing_details_item_price = xbilling_details_item_price;
		users_nickname = xusers_nickname;
	}
}
