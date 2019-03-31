package part1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Part1 {
	static ArrayList<String> genes = new ArrayList<String>();
		
	public Part1() {}
	
	public static void gene(String s) {
		int indexStart=0;
		int indexStop=0;
		int reply = 0;
		s = s.toUpperCase();
			
			while(indexStop<s.length()-6 && s.length()>=6) {
				
				indexStart = s.indexOf("ATG", indexStop);
				int taa = findStopCodon(s, indexStart, "TAA");
				int tag = findStopCodon(s, indexStart, "TAG");
				int agt = findStopCodon(s, indexStart, "TGA");
				//if there is any gene and %3 = 0 in any case
				//reply = Math.min(taa, Math.min(agt, tag));
				if(taa!=-1 || tag!=-1 || agt!=-1) {
					if(taa%3 == 0) reply = taa;
					if(tag<reply && tag%3 == 0) reply = tag;
					if(agt<reply && agt%3 == 0) reply = agt;
					
					if(reply == -1) indexStop = s.length();
					else if(indexStop == 0 ) {
						int max = Math.max(taa, Math.max(tag,  agt));
						indexStop = indexStop+max;
					}
					else indexStop = indexStop + reply;
				
					}
				
			}
			for(String g: genes) System.out.println(g);
	}
	
	public static int findStopCodon(String s, int startIndex, String searchCodon) {
		 int indexStop = s.indexOf(searchCodon, startIndex);
		 String gene ="";
		 if(indexStop !=-1) {			
			gene = findSimpleGene(s, startIndex, indexStop);
			System.out.println(gene);			
			System.out.println(gene.length());
			System.out.println("======");
			
			//if(gene.length()-indexStop>3) indexStop = indexStop +3;
		}
		 else return -1;
		 
		 return gene.length();
			
	}
	
	private static boolean twoOccurrences(String stringB, String stringA) {
		
		boolean reply = false;
		if(stringB.contains(stringA)); 
		{
			int startIndex = stringB.indexOf(stringA);
			int stopIndex = stringB.indexOf(stringA) + stringA.length();
			
			if( (stringB.substring(0, startIndex).length()>stringA.length() && stringB.substring(0, startIndex).contains(stringA)) || (stringB.substring(stopIndex).length()>stringA.length() && stringB.substring(stopIndex).contains(stringA)) ) {
				//System.out.println("there are more than one occurence of substring "+ stringA);
				reply = true;
			}
			
			//System.out.println(stringB.substring(0, startIndex));
			//System.out.println(stringB.substring(0, startIndex).length());
			//System.out.println(stringB.substring(stopIndex));
		}
		
		return reply;
	}
	
	
	public static String findSimpleGene(String s, int indexStart, int indexStop){// If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
			
			if(indexStart==-1 || indexStop==-1) return "";
			else{ 
				String substring = s.substring(indexStart, indexStop);
				if(substring.length()% 3 == 0) genes.add(substring);
				
				return substring;
			}
			

	}
	
	public static String readFileAsString(String fileName)throws Exception 
	  { 
	    String data = ""; 
	    data = new String(Files.readAllBytes(Paths.get(fileName))); 
	   // System.out.println(data);
	      return data; 
	  } 


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*System.out.println(findSimpleGene("ATGSDFAKS"));
		System.out.println(findSimpleGene("DSFSFHATGSDFAKS"));
		*/
		//gene("DSATGTAAFSFHATGSDFAKNTAAAGDDATGHHHAATAAHHAATGGGGGGGGGG");
		
		
		//gene("dsjakfnATGjfhmnbvtaalltaglndatgklmtaaatgatgagtATGkkkTAA");
		//boolean rep = twoOccurrences("abrakadabrrrrafggrstunek", "ra");
		//String data = readFileAsString("C:\\Users\\Monika\\Desktop\\DNA.txt"); 
		//System.out.println(data);
		/*FileReader fr = new FileReader("C:\\Users\\Monika\\Desktop\\DNA.txt"); 
			  
			    int i; 
			    while ((i=fr.read()) != -1) 
			      System.out.print((char) i); 
		  */
	    
	}

}
