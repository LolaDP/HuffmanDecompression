# HuffmanDecompression

This is a school project where I have to create a Huffman algorithm to decompress a text file knowing the original alphabet as well as how often the characters in the alphabet appear. 

## To operate the program needs :
  - the alphabet file : exemple_freq.txt
  - the compressed file : exemple_comp.bin
  
## The program must :
  - Read the file containing the alphabet and associated character frequencies
  - Build the code tree
  - Decode the compressed text and create the decompressed file
  - Determine the compression rate associated with the initial text
  - Determine the average number of storage bits for a character in the encoded text

## Language used: 
  - Java

## How it works :
You have to create a new Java Project whose classes are files named "Huffman ..." (for example "HuffmanTest") and save the alphabet and compressed file in the same folder.  
In the HuffmanTest class we foremost create an HuffmanAlphabet object whith the exemple_freq.txt file to create the double list [[character, frequency]] and then the list of leaves of the tree.   
Next, we create the Huffman tree and we associate its binary code with each node of the tree.   
An HuffmanDecompression object is cerated with the exemple_comp.bin file. We first convert the text to a bit sequence with toByteArray() and we translate this sequence into a character string with treePath(). With this character string we create the new file with fileCreation().   
Finally, the methods compressions_rate() and compression_rateBis() return the compression ratio from different arguments and the nb_bit() method returns the average number of storage bits for a character of the compressed text.
    
So, you just have to start the program which will create the unzipped file in the same folder and show you in the console :
  - the list [[character, frequency]] of the alphabet
  - the sequence of bits corresponding to the compressed test
  - the unzipped text
  - the compression ratio
  - the average number of storage bits for a character of the compressed text
