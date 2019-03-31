package part1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Genes {
	
	private static ArrayList<String> genes = new ArrayList<String>();
	
	final static String startCodonATG = "ATG";
	final static String stopCodonTAA = "TAA";
	final static String stopCodonTAG = "TAG";
	final static String stopCodonTGA = "TGA";
	
	private static void findGenes(String s) throws Exception {
		int indexStart = s.indexOf(startCodonATG);
		
		int minCodon;
		int howMany = 0;
		if(!s.isEmpty()) {
			s = s.toUpperCase();
			
			while(indexStart != -1){
				int indexStop = s.indexOf(startCodonATG, indexStart+3);
				String substring;
				if(indexStop!=-1) substring = s.substring(indexStart, indexStop+3);
				else substring =s.substring(indexStart);
				
				howMany++;
				minCodon = -1;
				int taa = findStopCodon(s, indexStart, stopCodonTAA);
				int tag = findStopCodon(s, indexStart, stopCodonTAG);
				int tga = findStopCodon(s, indexStart, stopCodonTGA);
				/*
				int taa = findStopCodon(substring, 0, stopCodonTAA);
				int tag = findStopCodon(substring, 0, stopCodonTAG);
				int tga = findStopCodon(substring, 0, stopCodonTGA);*/
				int maxCodon = Math.max(taa, Math.max(tag, tga));
				
				//int maxCodon = Math.max(taa, Math.max(tag, tga))+indexStart;
			
				if((maxCodon-indexStart)%3 == 0) minCodon=maxCodon;
				
				//if exists any gene to check
				if(maxCodon !=-1) {
					
					/*if(taa<maxCodon && taa!=-1 && (taa-indexStart)%3 == 0)minCodon = taa+indexStart;
					if(tga<maxCodon && tga!=-1 && (tga-indexStart)%3 == 0)minCodon = tga+indexStart;
					if(tag<maxCodon && tag!=-1 && (tag-indexStart)%3 == 0)minCodon = tag+indexStart;
					*/
					if(taa<maxCodon && taa!=-1 && (taa-indexStart)%3 == 0)minCodon = taa;
					if(tga<maxCodon && tga!=-1 && (tga-indexStart)%3 == 0)minCodon = tga;
					if(tag<maxCodon && tag!=-1 && (tag-indexStart)%3 == 0)minCodon = tag;
					
					
					//if there is gene here(gene%3=0)
					if(minCodon !=-1) {
						findSimpleGene(s, indexStart, minCodon);
						indexStart = s.indexOf(startCodonATG, minCodon);					
					}//if there's no exist any gene%3=0 -then we start at the pos startIndex+3 as we don't know if 
					//there is not a situation atgxatgblabla
					else if(minCodon==-1) indexStart = s.indexOf(startCodonATG, indexStart+3);
				}
				
				else if(maxCodon == -1) indexStart = -1;
				
			}
		}
		
		System.out.println("genes:");
		for(String g: genes) {
			System.out.println(g);
		}
		System.out.println("\nThere is/are "+howMany+" genes.\n");
		
		//ratio of T and G in whole string..
		processGenes(genes, 'C');
		
		
	}
	
	public static String readFileAsString(String fileName)throws Exception 
	  { 
	    String data = ""; 
	    data = new String(Files.readAllBytes(Paths.get(fileName))); 
	   // System.out.println(data);
	      return data; 
	  } 
	
	private static void processGenes(ArrayList<String> list, char c) throws Exception {
		int howMany = 0;
		System.out.println("Genes longer than 60: ");
		for(String g: list) {
			if(g.length()>60) {
				howMany ++;
				System.out.println(g);
			}
		}System.out.println("There is "+ howMany+" genes longer than 60 character ");
		
		System.out.println("Genes equal 9: ");
		for(String g: list) {
			if(g.length()==9) System.out.println(g);
		}
		
		System.out.println("Genes shorter than 9: ");
		for(String g: list) {
			if(g.length()<9) System.out.println(g);
		}System.out.println();
		
		int num =0;
		int theLongest=0;
		for(String g: list) {
			if(g.length()>theLongest) theLongest = g.length();
			if(cgRatio(g, c)>0.35) {
				num++;
				System.out.println("in gene: "+g+", ratio "+c+ " is>0.35 and is equal: "+cgRatio(g, c));
				}
			}
		System.out.println("The number of genes whose "+c +" ratio is higher than 0.35 is: "+num);
		System.out.println("The longest gene lengt is: "+theLongest);
		
	}
	
	private static float cgRatio(String str, char c) {
		int i = -1;
		int num = -1;
		do {
			num++;
			i = str.indexOf(c, i+1);	
		}while(i!=-1);
		
		return (float)num/str.length();
	}
	
	private static void cgTheSame(String str, String rep) {
		int i = -1;
		int num = -1;
		do {
			num++;
			i = str.indexOf(rep, i+1);	
		}while(i!=-1);
		
		System.out.println(num);;
	}
	//it returns indexStop if thefe is found stop codon, and -1 if there is not
	public static int findStopCodon(String s, int startIndex, String searchCodon) {
		 int indexStop = s.indexOf(searchCodon, startIndex);
		  //finds stopIndex and adds a gene to the list if there exists one
		 if(indexStop !=-1 ){
			 indexStop = indexStop+3;
			 //findSimpleGene(s, startIndex, indexStop);	
		 }
			
		return indexStop;
	}
	
	public static void findSimpleGene(String s, int start, int stop){// If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
		
			String substring = s.substring(start, stop);
			genes.add(substring);
			
			
	}
	
	private static void showMeResult(String path) throws Exception {
		String data = readFileAsString(path); 
		findGenes(data);
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//findGenes("aATGjahTTTTAaATGTGasATGjksdjTAAmTGAATGhhhTAGjjjsTAAmATGnnnTAGATGJJJJJJJJjkjJTAAKatgkkkjjjhhhtaammmatghahtaa");
		//findGenes("");
		//---------------6---------m18/
		//showMeResult("dna_quiz.txt");
		
		new ReaderDirectoriesAndSubdirectoriesWithFiles();
		//new ReaderCSVFiles("weather-2012-01-01.csv");
		//ReaderCSVFiles.listf("nc_weather");
		ReaderDirectoriesAndSubdirectoriesWithFiles.showAbsoluteResultFromDirAndSubdir("nc_weather");
		
		

	}

}
