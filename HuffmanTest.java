import java.util.ArrayList;

public class HuffmanTest {
	
	public static void main (String[] arg) {
		//creation of the HuffmanAlphabet object taking as argument the text file containing the alphabet
		HuffmanAlphabet alpha=new HuffmanAlphabet("exemple_freq.txt");
		//display the double list [[character, frequency]]
		System.out.println("the list [[character, frequency]] is : "+ alpha.readAlpha());
		
		//creation of the Huffman Tree object taking as argument the list of leaves of the tree
		HuffmanTree tree= new HuffmanTree(alpha.lNCreation(alpha.readAlpha()));
		//huffman tree root
		HuffmanNode root=tree.treeCreation();
		//association of its binary code to each node of the tree
		tree.coding(root);
		
		//creation of the HuffmanDecompression object which takes as argument the bin file to decompress
		HuffmanDecompression file=new HuffmanDecompression("exemple_comp.bin");
		//displays text converted to bit sequence
		System.out.println("the sequence of bits corresponding to the compressed test is : "+file.toBitString());
		
////TO DISPLAY THE UNCOMPRESSED TEXT WITH treePathBis()
//		String a="";
//		int i=0;
//		ArrayList<String> l= new ArrayList<String>();
//		while (i<file.toBitString().length()-1) {
//			l=file.treePathBis(file.toBitString(),tree.treeCreation(),i);
//			a=a+l.get(0);
//			i=Integer.parseInt(l.get(1));
//		}
//		System.out.println("the unzipped text is : "+a);
		
		//display of the code corresponding to the character "r" in the Huffman tree
		//System.out.println(file.label2code(root,"r"));
		
		//display of unzipped text
		System.out.println("the unzipped text is : "+file.treePath(file.toBitString(), root));
		//storing unzipped text
		String txtDecomp=file.treePath(file.toBitString(), root);
		//creation of the unzipped file
		file.fileCreation(txtDecomp);
		
		//compression ratio display
		System.out.println("the compression ratio is : "+file.compression_rate(alpha));
		//display of the average number of storage bits of a character of the compressed text
		System.out.println("the average number of storage bits for a character of the compressed text is : "+file.nb_bit(alpha,root));
	}
}

