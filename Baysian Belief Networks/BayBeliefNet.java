import java.util.Arrays;
import java.util.List;

public class BayBeliefNet
{
	static BaysianNode flu;
	static BaysianNode smokes;
	static BaysianNode soreThroat;
	static BaysianNode fever;
	static BaysianNode bronchitis;
	static BaysianNode wheezing;
	static BaysianNode coughing;
	
	public static void main(String[] args)
	{
		// CONSTRUCT NODES:
		flu        = new BaysianNode("Flu", null, Arrays.asList(0f));
		smokes     = new BaysianNode("Smokes", null, Arrays.asList(0f));
		soreThroat = new BaysianNode("Sore Throat", Arrays.asList(flu), Arrays.asList(0f, 0f));
		fever      = new BaysianNode("Fever", Arrays.asList(flu), Arrays.asList(0f, 0f));
		bronchitis = new BaysianNode("Bronchitis", Arrays.asList(flu, smokes), Arrays.asList(0f, 0f, 0f, 0f));
		wheezing   = new BaysianNode("Wheezing", Arrays.asList(bronchitis), Arrays.asList(0f, 0f));
		coughing   = new BaysianNode("Coughing", Arrays.asList(bronchitis), Arrays.asList(0f, 0f));
	}
}