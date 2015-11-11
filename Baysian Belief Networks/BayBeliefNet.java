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
		int part1MatchCount = 0;
		int part2NonRejectCount = 0;
		int part2MatchCount = 0;
		
		for (int i = 0; i < 1000000; i++) {
			MedicalStats results = RandomSample();
			
			if (IsPart1(results))
				part1MatchCount++;
			
			if (results.co)
			{
				part2NonRejectCount++;
				if (IsPart2(results))
					part2MatchCount++;
			}
			
			if (i % 100000 == 0)
			{
				System.out.println((i/100000) + " hundred thousand samples:");
				System.out.println("Part 1 probability: " + ((float)part1MatchCount/(float)i));
				System.out.println("Part 2 matches: " + ((float)part2MatchCount/(float)part2NonRejectCount));
			}
		}		
	}
	
	public static boolean IsPart1(MedicalStats results)
	{
		return (
			results.fl &&
			results.st &&
			results.fe &&
			!results.br &&
			!results.sm && 
			results.co &&
			!results.wh
		);
	}
	
	public static boolean IsPart2(MedicalStats results)
	{
		return (results.fl && results.co && !results.wh);
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