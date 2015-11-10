import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
		ConstructNetwork();
		
		for (int i = 0; i < 10000; i++) {
			MedicalStats results = RandomSample();
			
			if (results.co)
				System.out.println("Results: " + results.ToString());
		}
		
	}
	
	public static void ConstructNetwork()
	{
		// CONSTRUCT NODES:
		flu        = new BaysianNode("Flu", null, Arrays.asList(0.08f));
		smokes     = new BaysianNode("Smokes", null, Arrays.asList(0.15f));
		soreThroat = new BaysianNode("Sore Throat", Arrays.asList(flu), Arrays.asList(0.002f, 0.6f));
		fever      = new BaysianNode("Fever", Arrays.asList(flu), Arrays.asList(0.001f, 0.4f));
		bronchitis = new BaysianNode("Bronchitis", Arrays.asList(flu, smokes), Arrays.asList(0.001f, 0.6f, 0.01f, 0.95f));
		wheezing   = new BaysianNode("Wheezing", Arrays.asList(bronchitis), Arrays.asList(0.005f, 0.95f));
		coughing   = new BaysianNode("Coughing", Arrays.asList(bronchitis), Arrays.asList(0.002f, 0.45f));
	}
	
	public static MedicalStats RandomSample()
	{
		Random randGen = new Random();
		boolean fl = (randGen.nextFloat() < flu.getProbability(null));
		boolean sm = (randGen.nextFloat() < smokes.getProbability(null));
		
		boolean st = ( randGen.nextFloat() < soreThroat.getProbability(new boolean[]{fl})     );
		boolean fe = ( randGen.nextFloat() < fever.getProbability(new boolean[]{fl})          );
		boolean br = ( randGen.nextFloat() < bronchitis.getProbability(new boolean[]{fl, sm}) );
		
		boolean wh = ( randGen.nextFloat() < wheezing.getProbability(new boolean[]{br})       );
		boolean co = ( randGen.nextFloat() < coughing.getProbability(new boolean[]{br})       );
		
		return new MedicalStats(new boolean[]{fl, sm, st, fe, br, wh, co});
	}
}