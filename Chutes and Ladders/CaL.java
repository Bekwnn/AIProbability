import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class CaL
{
	public static int NUM_OF_SQUARES;
	
	public static ArrayList<TransportSpace> portSpaces;
	
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.out.println("Sample program usage: \'java CaL input.txt\'");
			return;
		}
		
		ReadInputFile(args[0]);
		
		ArrayList<GameResults> resultsList = new ArrayList<GameResults>();
		for (int i = 0; i < 1000; i++)
		{
			GameResults gameResults = PlayGame();
			resultsList.add(gameResults);
		}
		
		//TODO: analyze results and compute averages/histogram
	}
	
	public static GameResults PlayGame()
	{
		Random randomGenerator = new Random();
		int moves = 0;
		int transportsCaught = 0;
		int transportBalance = 0;
		for (int playerPos = 1; playerPos < NUM_OF_SQUARES;)
		{
			moves++;
			playerPos += randomGenerator.nextInt(6) + 1;
			for (TransportSpace space : portSpaces)
			{
				if (playerPos == space.start)
				{
					System.out.println("Caught a transport from " + playerPos + " to " + space.end);
					
					transportsCaught++;
					transportBalance += space.end - space.start;
					
					playerPos = space.end;
				}
			}
		}
		System.out.println("Game Completed in " + moves + " moves.");
		
		return new GameResults(moves, transportsCaught, transportBalance);
	}
	
	public static void ReadInputFile(String filename)
	{
		File file = new File(filename);
		BufferedReader reader = null;
		portSpaces = new ArrayList<TransportSpace>();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line     = null;
			String[] tokens = null;
			
			NUM_OF_SQUARES = Integer.parseInt(reader.readLine());
			
			while ((line = reader.readLine()) != null) {
				tokens = line.split(" ");
				
				//exit the program if the reader can't read it
				if (tokens.length != 2)
				{
					System.out.println("Incorrect file format");
					System.exit(0);
				}
				
				portSpaces.add(new TransportSpace(
					Integer.parseInt(tokens[0]),
					Integer.parseInt(tokens[1])
				));
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: IOException.");
			e.printStackTrace();
		}
	}
}