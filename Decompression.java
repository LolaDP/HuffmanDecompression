import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;


public class Decompression {
	//chaine de caractere nom du fichier.txt (fichier du texte code)
	private String nomFt;
	
	public Decompression(String nomF) {
		this.nomFt = nomF;
	}

	public String toByteArray() {
		//methode qui renvoit une chaine de bits correspondant au fichier texte
		String txtByte="";
		try {
			File file=new File(nomFt);
			//on recupere la longueur du fichier
			long length = file.length();
		    byte[] array = new byte[(int)length];
			//on recupere les octets sous forme d'entier stockes dans array
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			in.readFully(array);
			in.close();
			//convertion de ces entiers en binaire
			for (int i=0; i<array.length ; i++) {
				int q=array[i];
				//si le nombre est negatif on prend sa valeur absolue
				if (q<0) {
					q=Math.abs(q);
				}
				String rep="";
				while (q!=0) {
					int r=q%2;
					rep=String.valueOf(r)+rep;
					q=q/2;
				}
				//System.out.println(array[i]);
				//System.out.println(rep);
				txtByte+=rep;
			}
		
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		return txtByte;
	}
	
	public String parcourtTree(String txtByte, Node node) {
		//methode qui parcourt l'arbre de huffman et qui traduit la chaine de bits txtByte en chaine de caractere
		String txt="";
		int i=0;
		//on sauvegarde le noeud racine
		Node root=node;
		//on parcourt toute la sequence de bits
		while (i<txtByte.length()) {
			//si le noeud n'est pas une feuille 
			if (!(node.is_leaf()) ) {
				//si le caractere selectionne est un 0 on va a gauche
				if (txtByte.charAt(i)=='0') {
					node=node.getlChild();
					i++;
				}
				//si le caractere selectionne est un 1 on va a droite
				else if (txtByte.charAt(i)=='1') {
					node=node.getrChild();
					i++;
				}
			}
			//si c'est une feuille on ajoute son label au texte et on revient au noeud racine
			else {
				txt+=node.getLabel();
				node=root;
			}
		}
		return txt;
	}
	
	public ArrayList<String> parcourtTreeBis(String txtByte, Node node, int i) {
		//meme but que parcourtTree en utilisant le recursif renvoit qu'un ca(necessite la boucle while dans Test pour recuperer tous les carateres) 
		String txt="";
		ArrayList<String> l= new ArrayList<String>();
		//si le noeud n'est pas une feuille de l'arbre d'huffman
		if (!(node.is_leaf())) {
			// si le ie caractere de txtByte est un 0 on va a gauche dans l'arbre en regardant le caractere de txtByte suivant
			if (txtByte.charAt(i)=='0') {
				l=parcourtTreeBis(txtByte, node.getlChild(),i+1);
			}
			// si le ie caractere de txtByte est un 0 on va a droite dans l'arbre en regardant le caractere de txtByte suivant
			if (txtByte.charAt(i)=='1') {
				l=parcourtTreeBis(txtByte, node.getrChild(),i+1);
			}
		}
		//quand on arrive sur une feuille on ajoute l'etiquette de la feuille a la liste l ainsi que l'indice auquel on s'est arrete 
		//pour pouvoir reprendre au bon endroit lors du prochain appel de la fct
		else {
			txt=node.getLabel();
			l.add(txt);
			l.add(String.valueOf(i));
		}
		//System.out.println(l);
		return l;
	}
	
	
	public void creaFichier(String txt) {
		//methode qui prend en argument une chaine de caractere et cree un fichier txt contenant cette chaine
		try {
			//creation du fichier decompresse 
			// pour le nom j'ai considere que tous les noms des fichiers a decompresse etait de la forme "nom_comp.txt" donc je recupere "nom_" et j'ajoute "decomp.txt"
		    FileWriter myWriter = new FileWriter(getNomFt().substring(0,getNomFt().length()-8)+"decomp.txt");
		    //on ecrit le txt dans le fichier
		    myWriter.write(txt);
		    //on ferme le fichier
		    myWriter.close();
		    } 
		    catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public float taux_compression(Node root) {
		//methode qui prend en argument le nom d'un fichier texte et qui renvoit le taux de compression (en utilisant le fichier decompresse)
	    //calcul du volume initial (txt decomp)
		String txtDecomp=parcourtTree(toByteArray(), root);
		float volume_init=txtDecomp.length()*8;
		//System.out.println(volume_init);
		//calcul du volume final (txt comp)
		float volume_final=getNomFt().length();
		//System.out.println(volume_final);
		//calcul du taux
		float tx=1-volume_final/volume_init;
		return(tx);
	}
	
	public float taux_compressionBis(Alphabet alpha) {
	    //methode qui prend en argument le nom d'un fichier texte et qui renvoit le taux de compression 
		//(a partir de l'alphabet permet de calculer le tx même si le texte decode est faux)
	    //calcul du volume initial (txt decomp)
		float volume_init=0;
		ArrayList<ArrayList<String>> alphaB=alpha.readAlpha();
		for (int i=0; i<alphaB.size();i++) {
			volume_init+=Integer.parseInt(alphaB.get(i).get(1));
			}
		volume_init=volume_init*8;
		//System.out.println(volume_init);
		//calcul du volume final (txt comp)
		float volume_final=getNomFt().length();
		//System.out.println(volume_final);
		//calcul du taux
		float tx=1-volume_final/volume_init;
		return(tx);
		}
	
	public String label2code(Node node,String caract){
	    //methode qui renvoit le code associe a un noeud/caractere de l'arbre
	    //si la racine est l'etiquette on retourne son code
		String liste="" ;
		String left="";
		String right="";
		//si on trouve le noeud dont le label est le caractere recherche on renvoi son code
	    if (node.getLabel()!=null &&node.getLabel().equals(caract)) {
	    	return node.getCode();
	    }
	    else {
	        if (node.getlChild()!=null) {
	        	left=label2code(node.getlChild(),caract);
	        }
	        if (node.getrChild()!=null) {
	        	right=label2code(node.getrChild(),caract);
	        }
	        if ((node.is_leaf()) || (left.equals("") && right.equals(""))){
	            liste="";
	        }
	        if (!(left.equals(""))) {
	        	liste=left;
	        }
	        if (!(right.equals(""))) {        	
	        	liste=right;
	        }
	    }
	    return liste;
	}
	
	public float nb_bit(Alphabet alpha, Node root) {
		//nombre de bit
	    float nbB=0;
	    //somme du nombre d'elements
	    float diviseur=0;
	    ArrayList<ArrayList<String>> alphaB=alpha.readAlpha();
	    //pour chaque caractere du texte
	    for (int i=0; i<alphaB.size();i++) {
	        //le numerateur correspond a la somme des nombres de bit correspondant a un caractere multiplie par sa frequence d'apparition
	        nbB+=label2code(root,alphaB.get(i).get(0)).length()*Integer.parseInt(alphaB.get(i).get(1));
	        //le denominateur correspond a la somme du nombre d'element donc, comme chaque caractere n'apparait qu'une fois dans l'alphabet, c'est la somme des frequences 
	        diviseur+=Integer.parseInt(alphaB.get(i).get(1));
	        //System.out.println(diviseur);
	        //System.out.println(nbB);
	    }
	    float moyenne=nbB/diviseur;
	    return(moyenne);
	    
	}

	public String getNomFt() {
		return nomFt;
	}

	public void setNomFt(String nomFt) {
		this.nomFt = nomFt;
	}
	
	
}

