public class TransportSpace
{
	public int start;
	public int end;
	
	public TransportSpace(int st, int nd)
	{
		start = st;
		end = nd;
	}
	
	public boolean IsLadder()
	{
		return (start < end)? true : false;
	}
}