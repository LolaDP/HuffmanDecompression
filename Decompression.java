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
		String txtByte="";
		try {
			File file=new File(nomFt);
			long length = file.length();
		    byte[] array = new byte[(int)length];
			//on recupere les octets sous forme d'entier stockes dans array
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			in.readFully(array);
			in.close();
			//convertion de ces entiers en binaire
			for (int i=0; i<array.length ; i++) {
				int q=array[i];
				//si le nombre est negatig on prend sa valeur absolue
				if (q<0) {
					q=Math.abs(q);
				}
				String rep="";
				while (q!=0) {
					int r=q%2;
					rep=String.valueOf(r)+rep;
					q=q/2;
				}
				txtByte+=rep;
			}
		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return txtByte;
	}
	
	public String parcourtTree(String txtByte, Node node) {
		String txt="";
		
		//System.out.println(txtByte.charAt(0));
		while (txtByte!="") {
			if (!(node.is_leaf()) && txtByte.length()!=1) {
				if (txtByte.charAt(0)=='0') {
					txtByte=txtByte.substring(1);
					System.out.println(txtByte);
					System.out.println("b");
					txt+=parcourtTree(txtByte, node.getlChild());
					
				}
				if (txtByte.charAt(0)=='1') {
					txtByte=txtByte.substring(1);
					System.out.println(txtByte);
					System.out.println("a");
					txt+=parcourtTree(txtByte, node.getrChild());
					
				}
			}
			else {
				txt+=node.getLabel();
				txtByte=txtByte.substring(1);
			}
		
		}
		
		return txt;
	}
	
	
}
