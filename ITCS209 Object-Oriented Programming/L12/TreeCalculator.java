
public class TreeCalculator {

		
	public static int findMax(Node root)
	{	//****YOUR CODE HERE**
		if(root == null){
			return -1;
		}
		return findMax(root, root.id);
		//*********************
	}

	private static int findMax(Node node, int max){
		if(max < node.id){
			max = node.id;
		}
		if(node.left == null && node.right == null){
			return max;
		}
		if(node.right == null){
			return findMax(node.left, max);
		}
		if(node.left == null){
			return findMax(node.right, max);
		}
		return findMax(node.right, findMax(node.left, max));
	}
	
	public static int findMin(Node root)
	{	//****YOUR CODE HERE**
		if(root == null){
			return -1;
		}
		return findMin(root, root.id);
		//*********************
	}
	private static int findMin(Node node, int min){
		if(min > node.id){
			min = node.id;
		}
		if(node.left == null && node.right == null){
			return min;
		}
		if(node.right == null){
			return findMin(node.left, min);
		}
		if(node.left == null){
			return findMin(node.right, min);
		}
		return findMin(node.right, findMin(node.left, min));
	}
	
	//************* BONUS ****************//
	public static double sumTree(Node root)
	{	
		//****YOUR CODE HERE**
		if(root == null){
			return 0.0;
		}
		return sumTree(root, 0.0);
		//*********************
	}

	private static double sumTree(Node node, double s){
		if(node.left == null && node.right == null){
			return s + node.id;
		}
		if(node.right == null){
			return sumTree(node.left, s+node.id);
		}
		if(node.left == null){
			return sumTree(node.right, s+node.id);
		}
		return sumTree(node.left, sumTree(node.right, s+node.id));
	}
	
	public static double avgTree(Node root)
	{
		//****YOUR CODE HERE**
		if(root == null){
			return 0.0;
		}
		double sum = sumTree(root);
		int n = countNode(root);
		return sum/(double)n;
		//*********************
	}
	
	public static int countNode(Node node){
		if(node == null){
			return 0;
		}
		return countNode(node, 1);
	}

	private static int countNode(Node node, int n){
		if(node.right == null && node.left == null){
			return n;
		}
		if(node.right == null){
			return countNode(node.left, n+1);
		}
		if(node.left == null){
			return countNode(node.right, n+1);
		}
		return countNode(node.right, countNode(node.left, n+2));
	}

}
