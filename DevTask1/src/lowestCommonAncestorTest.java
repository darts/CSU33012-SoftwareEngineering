import static org.junit.Assert.*;

import org.junit.Test;

public class lowestCommonAncestorTest {

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
	}
	
}
