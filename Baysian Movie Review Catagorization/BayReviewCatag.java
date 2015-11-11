import java.io.*;

public class BayReviewCatag
{

	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.out.println("Sample program usage: \'java BayReviewCatag path/to/reviews/pos/'");
			return;
		}
	
		File folder = new File(args[0]);
		File[] listOfFiles = folder.listFiles();
		MovieVec[] results = new MovieVec[listOfFiles.length];

		for (int i = 0; i < listOfFiles.length; i++)
		{
			File file = listOfFiles[i];
			
			if (file.isFile() && file.getName().endsWith(".txt"))
			{
				results[i] = ReadMovieReview(file);
				System.out.println("Results: " + results[i].ToString());
			} 
		}
		
		System.out.println("Odds of 'awful': " + CalculateP(results, MovieVec.EField.AW));
		System.out.println("Odds of 'bad': " + CalculateP(results, MovieVec.EField.BA));
		System.out.println("Odds of 'boring': " + CalculateP(results, MovieVec.EField.BO));
		System.out.println("Odds of 'dull': " + CalculateP(results, MovieVec.EField.DU));
		System.out.println("Odds of 'effective': " + CalculateP(results, MovieVec.EField.EF));
		System.out.println("Odds of 'enjoyable': " + CalculateP(results, MovieVec.EField.EN));
		System.out.println("Odds of 'great': " + CalculateP(results, MovieVec.EField.GR));
		System.out.println("Odds of 'hilarious': " + CalculateP(results, MovieVec.EField.HI));
	}
	
	public static float CalculateP(MovieVec[] results, MovieVec.EField field)
	{
		int tCount = 0;
		for (MovieVec result : results)
		{
			if (result.Get(field))
				tCount++;
		}
		return ((float)tCount) / ((float)results.length);
	}
	
	public static float CalculatePGiven(MovieVec[] results, MovieVec.EField field, MovieVec.EField given)
	{
		int tCount = 0;
		for (MovieVec result : results)
		{
			if (result.Get(given))
			{
				gCount++;
				
				if (result.Get(field))
					tCount++;
			}
		}
		return ((float)tCount) / ((float)gCount);
	}
	
	public static MovieVec ReadMovieReview(File file)
	{
		BufferedReader reader = null;
		
		boolean[] vec = new boolean[8];
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line     = null;
			String[] tokens = null;
			
			while ((line = reader.readLine()) != null) {
				tokens = line.split(" ");
				for (String token : tokens)
				{
					if (token.equals("awful"))
						vec[0] = true;
					else if (token.equals("bad"))
						vec[1] = true;
					else if (token.equals("boring"))
						vec[2] = true;
					else if (token.equals("dull"))
						vec[3] = true;
					else if (token.equals("effective"))
						vec[4] = true;
					else if (token.equals("enjoyable"))
						vec[5] = true;
					else if (token.equals("great"))
						vec[6] = true;
					else if (token.equals("hilarious"))
						vec[7] = true;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: IOException.");
			e.printStackTrace();
		}
		return new MovieVec(vec);
	}
}