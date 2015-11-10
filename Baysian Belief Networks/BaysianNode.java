import java.util.ArrayList;
import java.util.List;

public class BaysianNode
{
	String nodeName;
	ArrayList<BaysianNode> parents;
	ArrayList<Float> probability;
	public BaysianNode(String n, List<BaysianNode> parentNodes, List<Float> probabilities)
	{
		// assert probabilities is the right length: 2^(num_parents)
		// probability index is a binary sum of parent indecies.
		// ex. TFTF -> probability[10], FFFF = [0], TTTT = [15]
		
		assert probabilities != null && (
			(parentNodes == null && probabilities.size() == 1) ||
			probabilities.size() == (int)Math.pow(2, parentNodes.size())
		);
		
		if (parentNodes != null) parents = new ArrayList<BaysianNode>(parentNodes);
		probability = new ArrayList<Float>(probabilities);
		nodeName = n;
	}
	
	public float getProbability(boolean[] parentStates)
	{
		assert (parentStates == null && parents == null) || parentStates.length == parents.size();
		
		if (parentStates == null)
			return probability.get(0);
			
		else
		{
			int index = 0;
			for (int i = 0; i < parentStates.length; i++)
			{
				if (parentStates[i])
					index += Math.pow(2, i);
			}
			return probability.get(index);
		}
	}
}