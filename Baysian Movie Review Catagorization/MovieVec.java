public class MovieVec {
	public enum EField {
		AW,
		BA,
		BO,
		DU,
		EF,
		EN,
		GR,
		HI
	};

	public final boolean aw;
	public final boolean ba;
	public final boolean bo;
	public final boolean du;
	public final boolean ef;
	public final boolean en;
	public final boolean gr;
	public final boolean hi;
	
	public MovieVec(boolean[] results)
	{
		assert results != null && results.length == 8;
		
		aw = results[0];
		ba = results[1];
		bo = results[2];
		du = results[3];
		ef = results[4];
		en = results[5];
		gr = results[6];
		hi = results[7];
	}
	
	public boolean Get(EField field)
	{
		switch(field)
		{
			case AW:
				return aw;
			case BA:
				return ba;
			case BO:
				return bo;
			case DU:
				return du;
			case EF:
				return ef;
			case EN:
				return en;
			case GR:
				return gr;
			case HI:
				return hi;
			default:
				System.out.println("Error in movieVec switch");
				return false;
		}
	}
	
	public String ToString()
	{
		return "(" + 
			aw + ", " +
			ba + ", " +
			bo + ", " +
			du + ", " +
			ef + ", " +
			en + ", " +
			gr + ", " +
			hi +
		")";
	}
}