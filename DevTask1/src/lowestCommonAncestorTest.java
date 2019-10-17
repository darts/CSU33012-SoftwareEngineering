import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class lowestCommonAncestorTest {
	
	//The tree being used:
	/*
	 * 	 0
	 *     /   \
	 *    1	    2
	 *  /  \   /  \
	 * 3	4 5    6
	 */

	//sanity check to make sure tree functions work as intended
	@Test
	public void treeTest() {
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.binaryTree theTree = theLCA.new binaryTree();
		
		//check for failure with tree size 0
		assert(theTree.getKey(theTree.head) == -1);
		
		//check head is added correctly
		theTree.add(0, null, true);
		assert(theTree.getKey(theTree.head) == 0);
		
		//check left and right are correctly located on head
		theTree.add(1, theTree.head, true);
		assert(theTree.getKey(theTree.head.lChild) == 1);
		theTree.add(2, theTree.head, false);
		assert(theTree.getKey(theTree.head.rChild) == 2);
		
		//check left and right are located correctly tree head left child
		theTree.add(3, theTree.head.lChild, true);
		theTree.add(4, theTree.head.lChild, false);
		assert(theTree.getKey(theTree.head.lChild.lChild) == 3);
		assert(theTree.getKey(theTree.head.lChild.rChild) == 4);
		
		//check left and right are located correctly tree head left child
		theTree.add(5, theTree.head.rChild, true);
		theTree.add(6, theTree.head.rChild, false);
		assert(theTree.getKey(theTree.head.rChild.lChild) == 5);
		assert(theTree.getKey(theTree.head.rChild.rChild) == 6);
	}
	
	
	//test LCA is correct when the two nodes are at same depth
	@Test
	public void testLCAAtSameDepth() {
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.binaryTree theTree = theLCA.new binaryTree();
		theTree.add(0, null, true);
		theTree.add(1, theTree.head, true);
		theTree.add(2, theTree.head, false);
		theTree.add(3, theTree.head.lChild, true);
		theTree.add(4, theTree.head.lChild, false);
		theTree.add(5, theTree.head.rChild, true);
		theTree.add(6, theTree.head.rChild, false);
		
		
		//LCA is head, nodes are l&r child
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild, theTree.head.rChild) == 0);
		
		//LCA is parent of both nodes
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild.lChild, theTree.head.lChild.rChild) == 1);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.rChild.lChild, theTree.head.rChild.rChild) == 2);

		//LCA is head, nodes are 2 down
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild.lChild, theTree.head.rChild.rChild) == 0);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild.lChild, theTree.head.rChild.lChild) == 0);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild.rChild, theTree.head.rChild.rChild) == 0);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild.rChild, theTree.head.rChild.lChild) == 0);
	}
	
	@Test
	public void testLCAAtVariedDepth() {
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.binaryTree theTree = theLCA.new binaryTree();
		theTree.add(0, null, true);
		theTree.add(1, theTree.head, true);
		theTree.add(2, theTree.head, false);
		theTree.add(3, theTree.head.lChild, true);
		theTree.add(4, theTree.head.lChild, false);
		theTree.add(5, theTree.head.rChild, true);
		theTree.add(6, theTree.head.rChild, false);
		
		//LCA is head, nodes are depth 1 and 2
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild, theTree.head.rChild.rChild) == 0);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild, theTree.head.rChild.lChild) == 0);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild.lChild, theTree.head.rChild) == 0);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild.rChild, theTree.head.rChild) == 0);
		
	}
	
	//check for cases that should not pass
	@Test
	public void testInvalid() {
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.binaryTree theTree = theLCA.new binaryTree();
		theTree.add(0, null, true);
		theTree.add(1, theTree.head, true);
		theTree.add(2, theTree.head, false);
		theTree.add(3, theTree.head.lChild, true);
		theTree.add(4, theTree.head.lChild, false);
		theTree.add(5, theTree.head.rChild, true);
		theTree.add(6, theTree.head.rChild, false);
		
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head, null) == -1);
		assert(lowestCommonAncestor.getLCA(theTree, null, null) == -1);
		lowestCommonAncestor.binaryTree.treeNode errNode = theTree.new treeNode(200, null, null, null);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head, errNode) == -1);
		
	}
	
	//check that nothing bad happens when both nodes are the same
	@Test
	public void testSameSame() {
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.binaryTree theTree = theLCA.new binaryTree();
		theTree.add(0, null, true);
		theTree.add(1, theTree.head, true);
		theTree.add(2, theTree.head, false);
		theTree.add(3, theTree.head.lChild, true);
		theTree.add(4, theTree.head.lChild, false);
		theTree.add(5, theTree.head.rChild, true);
		theTree.add(6, theTree.head.rChild, false);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head, theTree.head) == 0);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.lChild, theTree.head.lChild) == 1);
		assert(lowestCommonAncestor.getLCA(theTree, theTree.head.rChild, theTree.head.rChild) == 2);
	}
	
	
	//sanity check to make sure the graph works correctly
	@Test
	public void testDAG() {
		/* 
		 * 	 0
		 *     ↙   ↘
		 *    1	    2
		 *  ↙  ↘   ↙  ↘
		 * 3	4 5    6
		 * 
		 */
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.DAG theDAG = theLCA.new DAG();
		theDAG.addNode(0, null);
		theDAG.addNode(1, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(2, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(3, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(1))));
		theDAG.addNode(4, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(1))));
		theDAG.addNode(5, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(2))));
		theDAG.addNode(6, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(2))));
	
		assert(theDAG.printChildren(theDAG.head).equals("1  2  "));
		assert(theDAG.printChildren(theDAG.getNodeWithKey(1)).equals("3  4  "));
		assert(theDAG.printChildren(theDAG.getNodeWithKey(2)).equals("5  6  "));
		
		/*
		 *    0
		 *  ↙   ↘
		 * 1	 2
		 * ↓ ↘  ↙ ↘
		 * ↓  3 → 4
		 * ↓ ↙   ↙
		 * 5   ↙
		 * ↓ ↙
		 * 6
		 * 
		 */
		
		theDAG = theLCA.new DAG();
		theDAG.addNode(0, null);
		theDAG.addNode(1, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(2, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(3, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(1),theDAG.getNodeWithKey(2))));
		theDAG.addNode(4, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(3),theDAG.getNodeWithKey(2))));
		theDAG.addNode(5, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(3),theDAG.getNodeWithKey(1))));
		theDAG.addNode(6, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(4),theDAG.getNodeWithKey(5))));

		assert(theDAG.printChildren(theDAG.head).equals("1  2  "));
		assert(theDAG.printChildren(theDAG.getNodeWithKey(1)).equals("3  5  "));
		assert(theDAG.printChildren(theDAG.getNodeWithKey(2)).equals("3  4  "));
		assert(theDAG.printChildren(theDAG.getNodeWithKey(3)).equals("4  5  "));
		assert(theDAG.printChildren(theDAG.getNodeWithKey(4)).equals("6  "));
		assert(theDAG.printChildren(theDAG.getNodeWithKey(5)).equals("6  "));
		assert(theDAG.printChildren(theDAG.getNodeWithKey(6)).equals(""));
	}
	
	//test on normal binary tree
	@Test
	public void testDAG_LCAonBinaryTree() {
		/* 
		 * 	 0
		 *     ↙   ↘
		 *    1	    2
		 *  ↙  ↘   ↙  ↘
		 * 3	4 5    6
		 * 
		 */
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.DAG theDAG = theLCA.new DAG();
		theDAG.addNode(0, null);
		theDAG.addNode(1, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(2, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(3, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(1))));
		theDAG.addNode(4, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(1))));
		theDAG.addNode(5, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(2))));
		theDAG.addNode(6, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(2))));
		
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 1, 2) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 3, 4) == 1);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 5, 6) == 2);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 3, 5) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 3, 6) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 4, 5) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 3, 2) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 1, 5) == 0);
	}
	
	//test on DAG previously shown
	@Test
	public void testDAG_LCAonDAG() {
		/*
		 *    0
		 *  ↙   ↘
		 * 1	 2
		 * ↓ ↘  ↙ ↘
		 * ↓  3 → 4
		 * ↓ ↙   ↙
		 * 5   ↙
		 * ↓ ↙
		 * 6
		 * 
		 */
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.DAG theDAG = theLCA.new DAG();
		theDAG.addNode(0, null);
		theDAG.addNode(1, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(2, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(3, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(1),theDAG.getNodeWithKey(2))));
		theDAG.addNode(4, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(3),theDAG.getNodeWithKey(2))));
		theDAG.addNode(5, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(3),theDAG.getNodeWithKey(1))));
		theDAG.addNode(6, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(4),theDAG.getNodeWithKey(5))));

		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 1, 2) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 3, 5) == 3);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 5, 6) == 5);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 4, 5) == 3);
		
		
		/*
		 *    0
		 *  ↙   ↘
		 * 1	 2
		 * ↓ ↘  ↙ ↘
		 * ↓  3   4
		 * ↓ ↙   ↙
		 * 5   ↙
		 * ↓ ↙
		 * 6
		 * 
		 */
		theDAG = theLCA.new DAG();
		theDAG.addNode(0, null);
		theDAG.addNode(1, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(2, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(3, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(1),theDAG.getNodeWithKey(2))));
		theDAG.addNode(4, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(2))));
		theDAG.addNode(5, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(3),theDAG.getNodeWithKey(1))));
		theDAG.addNode(6, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(4),theDAG.getNodeWithKey(5))));
		
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 0, 0) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 0, 4) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 1, 2) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 3, 4) == 2);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 3, 5) == 3);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 4, 5) == 2);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 5, 6) == 5);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 6, 3) == 3);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 6, 4) == 4);
	}
	
	//test error cases, things that should not return real data
	@Test
	public void testErrDAG_LCA() {
		/*
		 *    0
		 *  ↙   ↘
		 * 1	 2
		 * ↓ ↘  ↙ ↘
		 * ↓  3   4
		 * ↓ ↙   ↙
		 * 5   ↙
		 * ↓ ↙
		 * 6
		 * 
		 */
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.DAG theDAG = theLCA.new DAG();
		theDAG.addNode(0, null);
		theDAG.addNode(1, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(2, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(3, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(1),theDAG.getNodeWithKey(2))));
		theDAG.addNode(4, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(2))));
		theDAG.addNode(5, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(3),theDAG.getNodeWithKey(1))));
		theDAG.addNode(6, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(4),theDAG.getNodeWithKey(5))));
	
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 0, -1) == -1);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 2, lowestCommonAncestor.FOUND_A) == -1);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 2, lowestCommonAncestor.FOUND_B) == -1);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, lowestCommonAncestor.FOUND_B, lowestCommonAncestor.FOUND_A) == -1);
	}
	
	//test with a larger graph
	@Test 
	public void testDAG_LCAtheBigGraph() {
		/*
		 *       0
		 *    ↙ ↓   ↘
		 *  ↙   ↓     ↘
		 * 1    2      3
		 * ↓   ↙ ↘    ↙ ↘
		 * 4  5   6  7   8
		 * ↓  ↓   ↓ ↙     ↓
		 * 9  10  11      12
		 * ↓  ↓   ↓ ↘    ↙ ↘
		 * 13 ↓   14 15 16 17 
		 * ↓ ↙    ↓  ↙   ↘ ↙
		 * 18     19      20
		 * ↓      ↓       ↓
		 * ↳ → → →21← ← ← ↲
		 */
		lowestCommonAncestor theLCA = new lowestCommonAncestor();
		lowestCommonAncestor.DAG theDAG = theLCA.new DAG();
		theDAG.addNode(0, null);
		theDAG.addNode(1, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(2, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(3, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.head)));
		theDAG.addNode(4, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(1))));
		theDAG.addNode(5, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(2))));
		theDAG.addNode(6, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(2))));
		theDAG.addNode(7, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(3))));
		theDAG.addNode(8, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(3))));
		theDAG.addNode(9, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(4))));
		theDAG.addNode(10, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(5))));
		theDAG.addNode(11, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(6),theDAG.getNodeWithKey(7))));
		theDAG.addNode(12, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(8))));
		theDAG.addNode(13, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(9))));
		theDAG.addNode(14, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(11))));
		theDAG.addNode(15, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(11))));
		theDAG.addNode(16, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(12))));
		theDAG.addNode(17, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(12))));
		theDAG.addNode(18, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(13),theDAG.getNodeWithKey(10))));
		theDAG.addNode(19, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(14),theDAG.getNodeWithKey(15))));
		theDAG.addNode(20, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(16),theDAG.getNodeWithKey(17))));
		theDAG.addNode(21, new ArrayList<lowestCommonAncestor.DAG.treeNode>(Arrays.asList(theDAG.getNodeWithKey(18),theDAG.getNodeWithKey(19),theDAG.getNodeWithKey(20))));

		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 1, 2) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 1, 0) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 4, 6) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 13,15) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 18, 8) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 18, 19) == 2);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 21, 7) == 7);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 6, 10) == 2);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 10, 11) == 2);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 17, 16) == 12);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 16, 10) == 0);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 19, 20) == 3);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 21, 4) == 4);
		assert(lowestCommonAncestor.getDAG_LCA(theDAG, 20, 21) == 20);
	}
	
}
