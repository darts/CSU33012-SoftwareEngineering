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

	
	//returns key of the LCA
	public static int getDAG_LCA(DAG theDAG, int keyA, int keyB) {
		//2 cases: one of the nodes is LCA || the nodes have a common LCA
		DAG.treeNode curNode = theDAG.head;
		
		int theNodeKey = bothInSubtreeOf(curNode, keyA, keyB);
		
		return theNodeKey;
	}
	
	private static int bothInSubtreeOf(DAG.treeNode curNode, int keyA, int keyB) {
		int curNodeVal = curNode.key;
		for(int i = 0; i < curNode.children.size(); i++) {
			int subTreeOf = bothInSubtreeOf(curNode.children.get(i), keyA, keyB);
			if(subTreeOf >= 0) {
				return subTreeOf;
			}else {
				switch(subTreeOf) {
				case -1: return -1;
				case -2: return (curNodeVal == keyB?curNodeVal:-2);
				case -3: return (curNodeVal == keyA?curNodeVal:-3);
				}
			}
		}
		if(curNodeVal == keyA)
			return keyA;
		if(curNodeVal == keyB)
			return keyB;
		
		return -1;
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
