import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HuffmanAlphabet {
	//string of the name of the .txt file (alphabet and frequencies file)
	private String fFName;
	
	public HuffmanAlphabet(String name) {
		this.fFName = name;
	}

	public ArrayList<ArrayList<String>> readAlpha(){
		//method which reads the alphabet and returns a list containing the label and frequency [[label1, frequency1], ...]
		ArrayList<ArrayList<String>> alpha=new ArrayList<ArrayList<String>>();
		try {
			File file=new File(fFName);
			// Creating the File Reader object
			FileReader fr=new FileReader(file);
			// Creating the BufferedReader object 
			BufferedReader br = new BufferedReader(fr); 
			//we don't take the first line
			br.readLine();
			String line= null;
			//the loop will read line by line from the 2nd line
			while((line = br.readLine())!= null){
				//sub-list of the alpha list of the form [character, frequency]
				ArrayList<String> array=new ArrayList<String>();
				//we get the character
				//substring (int start, int end) returns a new string that starts at the character at position start and goes up to the character at position end -1
				array.add(line.substring(0,1));
				//we get the frequency
				//if we don't give an end character we get the string that starts at "start" and ends at the end of the line
				array.add(line.substring(2));
				//we add the sublist to the main list describing the alphabet
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
	
	

	public String getFFName() {
		return fFName;
	}

	public ArrayList<HuffmanNode> lNCreation(ArrayList<ArrayList<String>> alpha){
		//creation of the list of nodes associated with each character
		ArrayList<HuffmanNode> listNodes= new ArrayList<HuffmanNode>();
		//for each character we create the corresponding node and add it to the list of leaves of the huffman tree
		for (int i=0; i<alpha.size();i++) {
			HuffmanNode n=new HuffmanNode(alpha.get(i).get(0),Integer.parseInt(alpha.get(i).get(1)),"");
		    listNodes.add(n);
		}
		return listNodes;
	}

	
}
