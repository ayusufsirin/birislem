/**
 * @author yusuf.sirin
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class SolutionSequenceFinder {

	private static Scanner sc;

	/**
	 * Starting from the target number generates operation sequences and push them
	 * into a tree to print the sequence easily
	 * 
	 * @param numbers
	 * @param i
	 * @param target
	 * @param root
	 */
	static void findOpSequences(Vector<Integer> numbers, int i, int target, Node root) {

		if (target == 0 || i >= numbers.size())
			return;

		root.setLeft(new Node(numbers.get(i), root, target - numbers.get(i), "-", i + 1));
		root.setRight(new Node(numbers.get(i), root, target + numbers.get(i), "+", i + 1));
		root.setUp(new Node(numbers.get(i), root, target * numbers.get(i), "*", i + 1));
		if (target % numbers.get(i) == 0)
			root.setDown(new Node(numbers.get(i), root, target / numbers.get(i), "/", i + 1));

		findOpSequences(numbers, i + 1, target - numbers.get(i), root.getLeft());
		findOpSequences(numbers, i + 1, target + numbers.get(i), root.getRight());
		findOpSequences(numbers, i + 1, target * numbers.get(i), root.getUp());
		if (target % numbers.get(i) == 0)
			findOpSequences(numbers, i + 1, target / numbers.get(i), root.getDown());
	}

	/**
	 * Traverse the tree created recursively and looks for a node or leaf that
	 * having item = 0
	 * 
	 * @param node
	 */
	static void printIfSolution(Node node) {
		if (node == null)
			return;

		boolean isDivOrMult = node.getResult() == 1 && (node.getOp().equals("*") || node.getOp().equals("/"));
		boolean isSumOrDiff = node.getResult() == 0;

		if (isSumOrDiff || isDivOrMult) {
			String paranthesis = "";
			for (int i = 0; i < node.getHeight(); i++)
				paranthesis += "(";

			System.out.print("An operation sequence is: ");
			int index = 1;
			if (node.getOp() == "+")
				index = 0;
			System.out.println(paranthesis + printOpSequence(node, 0).substring(index));

			System.exit(0);
		}

		printIfSolution(node.getLeft());
		printIfSolution(node.getRight());
		printIfSolution(node.getUp());
		printIfSolution(node.getDown());
	}

	/**
	 * An auxiliary method for "printIfSolution" method. Returns the operation
	 * sequence from given node to root node reversely
	 * 
	 * @param node
	 * @param parCount
	 * @return
	 */
	static String printOpSequence(Node node, int parCount) {
		if (node.getParent() == null)
			return "";
		String format = "%1$s%2$d)";

		return String.format(format, node.getRop(), node.item) + printOpSequence(node.getParent(), parCount + 1);
	}

	/**
	 * Generates all permutations of the input number set
	 * 
	 * @param arr
	 * @param k
	 * @param permutations
	 */
	static void permute(List<Integer> arr, int k, List<List<Integer>> permutations) {
		for (int i = k; i < arr.size(); i++) {
			Collections.swap(arr, i, k);
			permute(arr, k + 1, permutations);
			Collections.swap(arr, k, i);
		}
		if (k == arr.size() - 1) {
			permutations.add(new ArrayList<Integer>(arr));
		}
	}

	/**
	 * Driver function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		sc = new Scanner(System.in);

		int size;
		System.out.print("Enter the number of given numbers you want to: ");
		size = sc.nextInt();

		int[] numbers = new int[size];
		System.out.println("Enter the numbers of the set: ");
		for (int i = 0; i < size; i++) {
			numbers[i] = sc.nextInt();
		}

		int target;
		System.out.print("Enter the target number you want to obtain: ");
		target = sc.nextInt();

		List<Integer> arr = new ArrayList<Integer>();
		for (int i : numbers) {
			arr.add(i);
		}

		List<List<Integer>> permutations = new ArrayList<List<Integer>>();

		permute(arr, 0, permutations);

		for (List<Integer> integer : permutations) {

			Vector<Integer> numberVector = new Vector<Integer>();
			for (int a : integer) {
				numberVector.add(a);
			}

			Node root = new Node(-1, null, -1, "=" + target, 1);

			findOpSequences(numberVector, 0, target, root);
			printIfSolution(root);
		}

		System.out.println("No solution exists for this target with givens!");
	}
}
