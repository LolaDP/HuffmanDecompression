import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Alphabet {
	//chaine de caractere nom du fichier.txt (fichier des frequences)
	private String nomFf;
	
	public Alphabet(String nom) {
		this.nomFf = nom;
	}

	public ArrayList<ArrayList<String>> readAlpha(){
		//methode qui lit l'alphabet et renvoit une liste de liste contenant les label et frequence [[label1,frequence1],...]
		ArrayList<ArrayList<String>> alpha=new ArrayList<ArrayList<String>>();
		try {
			File file=new File(nomFf);
			// Creation l'objet File Reader
			FileReader fr=new FileReader(file);
			// Creation l'objet BufferedReader        
			BufferedReader br = new BufferedReader(fr); 
			//je ne veux pas la premiere ligne
			br.readLine();
			String line= null;
			//la boucle va lire ligne par ligne a partir de la 2e ligne
			while((line = br.readLine())!= null){
				//sous liste de la liste alpha de forme [caractere,frequence]
				ArrayList<String> array=new ArrayList<String>();
				//on recupere le caractere 
				//substring(int debut, int fin) retourne un nouveau string qui debute au caractere a la position debut et va jusqu'au caractere a la position fin -1
				array.add(line.substring(0,1));
				//on recupere la frequence
				//si on ne donne pas de caractere de fin on recupere le string qui debute a debut et fini a la fin de la ligne 
				array.add(line.substring(2));
				//on ajoute la sous liste a la liste principale decrivant l'alphabet
				alpha.add(array);
			}
			fr.close();  
		}
		catch(IOException e){
			System.out.println("An error occurred.");
		    e.printStackTrace();
	    }
		return alpha;
	}
	
	

	public String getNomF() {
		return nomFf;
	}

	//creation de la liste des noeuds associe a chaque caractere
	public ArrayList<Node> creaLN(ArrayList<ArrayList<String>> alpha){
		ArrayList<Node> listenoeud= new ArrayList<Node>();
		//pour chaque caractere on cree le noeud correspondant et on l'ajoute a la liste des feuilles de l'arbre d'huffman
		for (int i=0; i<alpha.size();i++) {
			Node n=new Node(alpha.get(i).get(0),Integer.parseInt(alpha.get(i).get(1)),"");
		    listenoeud.add(n);
		}
		return listenoeud;
	}

	
}
