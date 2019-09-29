
public class lowestCommonAncestor {

	public lowestCommonAncestor() {

	}

	class binaryTree {
		treeNode head;
		
		binaryTree(){
			head = null;
		}
		
		private void add(int key, treeNode parent, boolean isLeft) {
			treeNode newNode = new treeNode(key, parent, null, null);
			if(head == null) {
				head = newNode;
				return;
			}
			if(isLeft)
				parent.lChild = newNode;
			else
				parent.rChild = newNode;
		}
		
		private void genTreeFromArr(int[] keys) {
			if(keys == null || keys.length <= 0) {
				System.out.print("ERROR when creating tree, input array invalid");
				return;
			}
			
		}
		
		class treeNode {
			treeNode parent, lChild, rChild;
			int key;
			treeNode(int key, treeNode parent, treeNode lChild, treeNode rChild){
				this.key = key;
				this.parent = parent;
				this.lChild = lChild;
				this.rChild = rChild;
			}
		}
	}

}
