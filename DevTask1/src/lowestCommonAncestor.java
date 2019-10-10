import java.util.ArrayList;

public class lowestCommonAncestor {

	public lowestCommonAncestor() {

	}

	// return the key of the LCA of the two nodes
	public static int getLCA(binaryTree theTree, binaryTree.treeNode val1, binaryTree.treeNode val2) {
		if (val1 == null || val2 == null)
			return -1;

		ArrayList<Integer> route1 = new ArrayList<>();
		ArrayList<Integer> route2 = new ArrayList<>();
		binaryTree.treeNode node1 = val1;
		binaryTree.treeNode node2 = val2;

		do {
			route1.add(node1.key);
			node1 = node1.parent;
		} while (node1 != null);

		do {
			route2.add(node2.key);
			node2 = node2.parent;
		} while (node2 != null);

		for (Integer theNode : route1) {
			for (Integer searchNode : route2)
				if (theNode.equals(searchNode))
					return theNode.intValue();
		}

		return -1;
	}

	// the binary tree that we will be traversing
	public class binaryTree {
		treeNode head;

		binaryTree() {
			head = null;
		}

		public int getKey(treeNode theNode) {
			if (theNode != null)
				return theNode.key;
			return -1;
		}

		public void add(int key, treeNode parent, boolean isLeft) {
			treeNode newNode = new treeNode(key, parent, null, null);
			if (head == null) { // check for first node
				head = newNode;
				return;
			}
			if (isLeft)
				parent.lChild = newNode;
			else
				parent.rChild = newNode;
		}

		public class treeNode {
			treeNode parent, lChild, rChild;
			int key;

			treeNode(int key, treeNode parent, treeNode lChild, treeNode rChild) {
				this.key = key;
				this.parent = parent;
				this.lChild = lChild;
				this.rChild = rChild;
			}
		}
	}

	public class DAG {
		treeNode head = null;

		public void addNode(int key, ArrayList<treeNode> parents) {
			treeNode theNode = new treeNode(key);
			if (parents != null)
				for (treeNode parentNode : parents)
					if (parentNode != null)
						parentNode.children.add(theNode);
		}

		public int getKey(treeNode node) {
			if(node != null)
				return node.key;
			return -1;
		}
		
		public ArrayList<treeNode> getChildren(treeNode node) {
			if(node != null)
				return node.children;
			return null;
		}
		
		class treeNode {
			private ArrayList<treeNode> children;
			private int key;

			treeNode(int key) {
				children = new ArrayList<treeNode>();
				this.key = key;
			}
		}
	}

}
