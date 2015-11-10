public class MedicalStats
{
	public final boolean fl;
	public final boolean sm;
	public final boolean st;
	public final boolean fe;
	public final boolean br;
	public final boolean wh;
	public final boolean co;
	
	public MedicalStats(boolean[] results)
	{
		assert results != null && results.length == 7;
		
		fl = results[0];
		sm = results[1];
		st = results[2];
		fe = results[3];
		br = results[4];
		wh = results[5];
		co = results[6];
	}
	
	public String ToString()
	{
		return "(" + 
			fl + ", " +
			sm + ", " +
			st + ", " +
			fe + ", " +
			br + ", " +
			wh + ", " +
			co +
		")";
	}
}