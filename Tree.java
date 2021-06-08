import java.util.ArrayList;

public class Tree {

	private ArrayList<Node> listeNoeud= new ArrayList<Node>();

	public Tree(ArrayList<Node> listeNoeud) {
		this.listeNoeud = listeNoeud;
	}
		
	public Node creaTree() {
		//methode pour creer l'arbre d'huffman
		while (listeNoeud.size()!=1) {
	        //recherche des f min
	        Node min1=listeNoeud.get(0);
	        Node min2=listeNoeud.get(1);
	        for (int k=0; k<listeNoeud.size();k++) {
	            if (listeNoeud.get(k).getFreq()<min1.getFreq() & listeNoeud.get(k)!=min2){
	                min1=listeNoeud.get(k);
	            }
	            for(int j=0; j<listeNoeud.size();j++) {
	                if (listeNoeud.get(j).getFreq()<min2.getFreq() & listeNoeud.get(j)!=min1) {
	                    min2=listeNoeud.get(j);
	                }
	            }
	        }
	        //on les enleve de la liste 
	        listeNoeud.remove(min1);
	        listeNoeud.remove(min2);
	        //creation d'un nouveau noeud avec T1 et T2 en ss arbre gauche et droit
	        Node T=new Node(null,min1.getFreq()+min2.getFreq(),null);
	        T.setlChild(min1);
	        T.setrChild(min2);
	        // on ajoute l'arbre constitue des sous arbres dans la liste
	        listeNoeud.add(T);
		}
	    return(listeNoeud.get(0));
	}
	
	public void codage(Node arbre) {
	    //methode qui prend en argument la racine de l'arbre et associe le code binaire associe a chaque noeud de l'arbre 
	    //initialisation sur le noeud racine
	    if (arbre.getCode()==null) {
	        arbre.setCode("");
	    }
	    //a chaque fois qu'on va a gauche dans l'arbre on rajoute un 0 au code du caractere
	    if (arbre.getlChild()!=null) {
	        arbre.getlChild().setCode(arbre.getCode()+"0");
	        codage(arbre.getlChild());
	    }
	    //a chaque fois qu'on va a droite dans l'arbre on rajoute un 1 au code du caractere
	    if (arbre.getrChild()!=null) {
	        arbre.getrChild().setCode(arbre.getCode()+"1");
	        codage(arbre.getrChild());
	    }
	}
	
}
