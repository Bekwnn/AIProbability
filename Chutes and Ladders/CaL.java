import java.io.*;
import java.util.ArrayList;

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