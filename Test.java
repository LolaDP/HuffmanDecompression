
public class Test {
	
	public static void main (String[] arg) {
		Alphabet alpha=new Alphabet("exemple_freq.txt");
		System.out.println(alpha.readAlpha());
		Tree arbre= new Tree(alpha.creaLN(alpha.readAlpha()));
		arbre.creaTree();
		arbre.codage(arbre.creaTree());
		//System.out.println(arbre.creaTree().getlChild().getrChild().getCode());
		Decompression fichier=new Decompression("exemple_comp.bin");
		System.out.println(fichier.toByteArray());
		System.out.println(fichier.parcourtTree(fichier.toByteArray(),arbre.creaTree()));
	}
}

