/**
 * @author yusuf.sirin
 *
 */

public class Node {

	public int item;
	private int result;
	private int height;
	private String op;
	private String rop;

	private Node parent;
	private Node left;
	private Node right;
	private Node up;
	private Node down;

	public Node(int item, Node parent, int result, String op, int height) {
		this.item = item;
		this.parent = parent;
		this.result = result;
		this.setOp(op);
		this.setHeight(height);
	}

	/**
	 * @return the left
	 */
	public Node getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Node getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(Node right) {
		this.right = right;
	}

	/**
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {

		return String.format("(result: %1$s, item: %2$s, op: %3$s) ", this.result, this.item, this.getOp());
	}

	/**
	 * @return the op
	 */
	public String getOp() {
		return op;
	}

	/**
	 * @param op the op to set
	 */
	public void setOp(String op) {
		this.op = op;
		if (op == "+")
			this.setRop("-");
		else if (op == "-")
			this.setRop("+");
		else if (op == "*")
			this.setRop("/");
		else if (op == "/")
			this.setRop("*");
	}

	/**
	 * @return the up
	 */
	public Node getUp() {
		return up;
	}

	/**
	 * @param up the up to set
	 */
	public void setUp(Node up) {
		this.up = up;
	}

	/**
	 * @return the down
	 */
	public Node getDown() {
		return down;
	}

	/**
	 * @param down the down to set
	 */
	public void setDown(Node down) {
		this.down = down;
	}

	/**
	 * @return the rop
	 */
	public String getRop() {
		return rop;
	}

	/**
	 * @param rop the rop to set
	 */
	public void setRop(String rop) {
		this.rop = rop;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
