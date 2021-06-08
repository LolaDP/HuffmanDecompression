import java.util.ArrayList;

public class Test {
	
	public static void main (String[] arg) {
		Alphabet alpha=new Alphabet("exemple_freq.txt");
		//affiche la double liste [carctere,frequence]
		System.out.println(alpha.readAlpha());
		Tree arbre= new Tree(alpha.creaLN(alpha.readAlpha()));
		//racine de l'arbre d'huffman
		Node root=arbre.creaTree();
		arbre.codage(root);
		Decompression fichier=new Decompression("exemple_comp.bin");
		//affiche le texte converti en sequence de bits
		System.out.println(fichier.toByteArray());
		
////POUR AFFICHER LE TEXTE DECOMPRESSE AVEC pourcourtTreeBis()
//		String a="";
//		int i=0;
//		ArrayList<String> l= new ArrayList<String>();
//		while (i<fichier.toByteArray().length()-1) {
//			l=fichier.parcourtTreeBis(fichier.toByteArray(),arbre.creaTree(),i);
//			a=a+l.get(0);
//			i=Integer.parseInt(l.get(1));
//		}
//		System.out.println(a);
		
		//System.out.println(fichier.label2code(root,"r"));
		
		//System.out.println(fichier.parcourtTree(fichier.toByteArray(), root));
		//obtiention du texte decompresse
		String txtDecomp=fichier.parcourtTree(fichier.toByteArray(), root);
		//creation du fichier decompresse
		fichier.creaFichier(txtDecomp);
		//affichage du taux de compression
		System.out.println(fichier.taux_compression(root));
		//affichage du nombre moyen de bits de stockage d’un caractere du texte compresse
		System.out.println(fichier.nb_bit(alpha,root));
	}
}

