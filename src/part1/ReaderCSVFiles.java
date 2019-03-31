/*This Class reads from file values in row 1(you can change it byc column[1]) and finding max value in this row*/

package part1;

import java.io.FileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReaderCSVFiles {
	public static String file;
	public final int TimeEST = 0; 
	
	public ReaderCSVFiles(String file) {
		this.file = file;
		//readFromFile();
	}
	
		
		protected static double readFromFile() {
		String csvFile = file;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        double maxVal = 0;
        double minVal = 100;
        try {
        	
            br = new BufferedReader(new FileReader(csvFile)); 
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                
                String vmax = country[1];
                String vmin = country[3];
                if(vmax.equals(null)) System.out.println("null");
                else if(!vmax.equals(null)) { 
	                double val = 0;
	                try{
	                	val = Double.parseDouble(vmax);
	                	//System.out.print(val+", ");
	                	
	                }catch(NumberFormatException e) {continue;}
	                
	                if(maxVal<val) maxVal=val;
                }
	            //System.out.println("maxVal: "+maxVal);
                //System.out.println("Country [ " + country[1] + " , name=" + country[2]+ " , name=" + country[3]+ "]");
                
            }
            //System.out.println("maxVal = "+maxVal);
            

        } catch (FileNotFoundException e) {
           //System.out.println(br);
           //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return maxVal;
	}
	

   /* public static void main(String[] args) {

    	readFromFile("FL_insurance_sample.csv");

    }
*/
}
