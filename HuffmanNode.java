
public class HuffmanNode {
	
	private String label;
	private int freq;
	private HuffmanNode lChild;
	private HuffmanNode rChild;
	private String code;
	
	public HuffmanNode(String l, int f, String c) {
		//a node has a label, a frequency, a right and left child initialized to null and a code consisting of 0 and 1
		this.label = l;
		this.freq = f;
		this.lChild = this.rChild=null;
		this.code = c;
	}
	
	public Boolean is_leaf() {
        //method which takes a node as a parameter and returns true if it is a leaf
        if (lChild==null && rChild==null) {
            return true;
        }
        else {
            return false;
        }
	}
	
	public String getLabel() {
		return label;
	}


	public int getFreq() {
		return freq;
	}


	public void setlChild(HuffmanNode lChild) {
		this.lChild = lChild;
	}


	public void setrChild(HuffmanNode rChild) {
		this.rChild = rChild;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public HuffmanNode getlChild() {
		return lChild;
	}


	public HuffmanNode getrChild() {
		return rChild;
	}
	

	public String getCode() {
		return code;
	}


}
