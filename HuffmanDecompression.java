import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;


public class HuffmanDecompression {
	//string of the name of the .txt file (encoded text file)
	private String tFName;
	
	public HuffmanDecompression(String name) {
		this.tFName = name;
	}

	public String toByteArray() {
		//method that returns a sequence of bits corresponding to the text file
		String txtByte="";
		try {
			File file=new File(tFName);
			//we get the length of the filer
			long length = file.length();
		    byte[] array = new byte[(int)length];
			//we get the bytes in the form of integers stored in array
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			in.readFully(array);
			in.close();
			//converting these integers to binary
			for (int i=0; i<array.length ; i++) {
				int q=array[i];
				//if the number is negative we take its absolute value
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
	
	public String treePath(String txtByte, HuffmanNode node) {
		//method which traverses the huffman tree and which translates the txtByte bit string into a character string
		String txt="";
		int i=0;
		//we save the root node
		HuffmanNode root=node;
		//we go through the whole sequence of bits
		while (i<txtByte.length()) {
			//if the node is not a leaf
			if (!(node.is_leaf()) ) {
				//if the selected character is a 0 we go to the left
				if (txtByte.charAt(i)=='0') {
					node=node.getlChild();
					i++;
				}
				//if the selected character is a 1 we go to the right
				else if (txtByte.charAt(i)=='1') {
					node=node.getrChild();
					i++;
				}
			}
			//if it is a leaf we add its label to the text and we return to the root node
			else {
				txt+=node.getLabel();
				node=root;
			}
		}
		return txt;
	}
	
	public ArrayList<String> treePathBis(String txtByte, HuffmanNode node, int i) {
		//same goal as treePath () using recursive, only return one character (requires while loop in Test to get all characters)
		String txt="";
		ArrayList<String> l= new ArrayList<String>();
		//if the node is not a leaf of the huffman tree
		if (!(node.is_leaf())) {
			//if the ith character of txtByte is a 0 we go to the left in the tree looking at the next character of txtByte
			if (txtByte.charAt(i)=='0') {
				l=treePathBis(txtByte, node.getlChild(),i+1);
			}
			//if the ith character of txtByte is a 1 we go to the right in the tree looking at the next character of txtByte
			if (txtByte.charAt(i)=='1') {
				l=treePathBis(txtByte, node.getrChild(),i+1);
			}
		}
		//when we arrive on a leaf we add the label of the leaf to the list l as well as the index at which we stopped in order to be able to resume in the right place during the next call of the fct
		else {
			txt=node.getLabel();
			l.add(txt);
			l.add(String.valueOf(i));
		}
		//System.out.println(l);
		return l;
	}
	
	
	public void fileCreation(String txt) {
		//method which takes a character string as argument and creates a txt file containing this string
		try {
			//creation of the unzipped file
			// for the name I have considered that all the names of the unzipped files were of the form "name_comp.txt" so I get "name_" and add "decomp.txt"
		    FileWriter myWriter = new FileWriter(getNomFt().substring(0,getNomFt().length()-8)+"decomp.txt");
		    //we write the txt in the file
		    myWriter.write(txt);
		    //we close the file
		    myWriter.close();
		    } 
		    catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

	public float compression_rate(HuffmanAlphabet alpha) {
	    //method which takes as argument an HuffmanAlphabet object and which returns the compression rate
		//(from the alphabet enables to calculate the rate even if the decoded text is false)
	    //calculation of the initial volume (unzipped txt)
		float init_volume=0;
		ArrayList<ArrayList<String>> alphaB=alpha.readAlpha();
		for (int i=0; i<alphaB.size();i++) {
			init_volume+=Integer.parseInt(alphaB.get(i).get(1));
			}
		init_volume=init_volume*8;
		//System.out.println(volume_init);
		//calculation of the final volume (compressed txt)
		float final_volume=getNomFt().length();
		//System.out.println(volume_final);
		//rate calculation
		float rate=1-final_volume/init_volume;
		return(rate);
		}
	
	public float compression_rateBis(HuffmanNode root) {
		//method which takes as argument the root of the huffman tree and which returns the compression rate (using the decompressed file)
	    //calculation of the initial volume (unzipped txt)
		String unzipTxt=treePath(toByteArray(), root);
		float init_volume=unzipTxt.length()*8;
		//System.out.println(volume_init);
		//calculation of the final volume (compressed txt)
		float final_volume=getNomFt().length();
		//System.out.println(volume_final);
		//rate calculation
		float rate=1-final_volume/init_volume;
		return(rate);
	}
	
	
	public String label2code(HuffmanNode node,String charac){
	    //method which returns the code associated with a node / character of the tree
		String list="" ;
		String left="";
		String right="";
		//if we find the node whose label is the character we are looking for, we return its code
	    if (node.getLabel()!=null &&node.getLabel().equals(charac)) {
	    	return node.getCode();
	    }
	    else {
	        if (node.getlChild()!=null) {
	        	left=label2code(node.getlChild(),charac);
	        }
	        if (node.getrChild()!=null) {
	        	right=label2code(node.getrChild(),charac);
	        }
	        if ((node.is_leaf()) || (left.equals("") && right.equals(""))){
	            list="";
	        }
	        if (!(left.equals(""))) {
	        	list=left;
	        }
	        if (!(right.equals(""))) {        	
	        	list=right;
	        }
	    }
	    return list;
	}
	
	public float nb_bit(HuffmanAlphabet alpha, HuffmanNode root) {
		//method which takes as parameter the list of the alphabet and the root of the huffman tree and returns the average number of storage bits of a character of the compressed text
		//number of bits
	    float nbB=0;
	    //sum of number of elements
	    float divisor=0;
	    ArrayList<ArrayList<String>> alphaB=alpha.readAlpha();
	    //for each character of the text
	    for (int i=0; i<alphaB.size();i++) {
	        //the numerator corresponds to the sum of the bit numbers corresponding to a character multiplied by its frequency of appearance
	        nbB+=label2code(root,alphaB.get(i).get(0)).length()*Integer.parseInt(alphaB.get(i).get(1));
	        //the denominator corresponds to the sum of the number of elements therefore, as each character appears only once in the alphabet, it is the sum of the frequencies
	        divisor+=Integer.parseInt(alphaB.get(i).get(1));
	        //System.out.println(diviseur);
	        //System.out.println(nbB);
	    }
	    float average=nbB/divisor;
	    return(average);
	    
	}

	public String getNomFt() {
		return tFName;
	}

	public void setNomFt(String nomFt) {
		this.tFName = nomFt;
	}
	
	
}
