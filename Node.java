
public class Node {
	private String label;
	private int freq;
	private Node lChild;
	private Node rChild;
	private String code;
	
	public Node(String l, int f, String c) {
		super();
		this.label = l;
		this.freq = f;
		this.lChild = this.rChild=null;
		this.code = c;
	}
	
	public Boolean is_leaf() {
        //methode qui prend en parametre un noeud et retourne vrai si c'est une feuille
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


	public void setlChild(Node lChild) {
		this.lChild = lChild;
	}


	public void setrChild(Node rChild) {
		this.rChild = rChild;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Node getlChild() {
		return lChild;
	}


	public Node getrChild() {
		return rChild;
	}
	

	public String getCode() {
		return code;
	}


}
