package M;

public class DrinkerDB
{
	public int billing_details_drinker_id;
	public String users_nickname;
	public double cup_drank;
	
	public DrinkerDB()
	{
		
	}
	public DrinkerDB(
			int xbilling_details_drinker_id,
			String xusers_nickname,
			double xcup_drank)
	{
		billing_details_drinker_id = xbilling_details_drinker_id;
		users_nickname = xusers_nickname;
		cup_drank = xcup_drank;
	}
	
}
