import java.util.ArrayList;

public class HuffmanTree {

	private ArrayList<HuffmanNode> listNodes= new ArrayList<HuffmanNode>();

	public HuffmanTree(ArrayList<HuffmanNode> listN) {
		this.listNodes = listN;
	}
		
	public HuffmanNode treeCreation() {
		//method to create the huffman tree
		while (listNodes.size()!=1) {
	        //search for minimum frequencies
	        HuffmanNode min1=listNodes.get(0);
	        HuffmanNode min2=listNodes.get(1);
	        for (int k=0; k<listNodes.size();k++) {
	            if (listNodes.get(k).getFreq()<min1.getFreq() & listNodes.get(k)!=min2){
	                min1=listNodes.get(k);
	            }
	            for(int j=0; j<listNodes.size();j++) {
	                if (listNodes.get(j).getFreq()<min2.getFreq() & listNodes.get(j)!=min1) {
	                    min2=listNodes.get(j);
	                }
	            }
	        }
	        //we remove them from the list 
	        listNodes.remove(min1);
	        listNodes.remove(min2);
	        //creation of a new node with T1 and T2 in left and right subtree and whose frequency is the sum of the frequencies of T1 and T2
	        HuffmanNode T=new HuffmanNode(null,min1.getFreq()+min2.getFreq(),null);
	        T.setlChild(min1);
	        T.setrChild(min2);
	        //we add the tree constitutes sub-trees in the list
	        listNodes.add(T);
		}
	    return(listNodes.get(0));
	}
	
	public void coding(HuffmanNode arbre) {
	    //method which takes the root of the tree as an argument and associates the binary code associated with each node of the tree
	    //initialization on the root node
	    if (arbre.getCode()==null) {
	        arbre.setCode("");
	    }
	    //each time we go to the left in the tree we add a 0 to the character code
	    if (arbre.getlChild()!=null) {
	        arbre.getlChild().setCode(arbre.getCode()+"0");
	        coding(arbre.getlChild()); 
	    }
	    //each time we go to the right in the tree we add a 1 to the character code
	    if (arbre.getrChild()!=null) {
	        arbre.getrChild().setCode(arbre.getCode()+"1");
	        coding(arbre.getrChild());
	    }
	}
	
}
