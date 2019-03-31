package imgConverter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class GrayImageConverter{
	
	
	public static void makeGray(String inImage) {
		BufferedImage img = null, img1 = null;
		File f=null, g = null;
		
		Random generator = new Random();
		 try{
			//reading from old img and creating gray new one
			 img = ImageIO.read(new File(inImage));
			 img1 = ImageIO.read(new File(inImage));
			 
			 //looping thorougth all pixels in old image and set new walue to a new one 
			 for (int x = 0; x < img.getWidth(); x++) {
		            for (int y = 0; y < img.getHeight(); y++) {
		                final int clr = img.getRGB(x, y);
		                final int red = (clr & 0x00ff0000) >> 16;
		                final int green = (clr & 0x0000ff00) >> 8;
		                final int blue = clr & 0x000000ff;
		                int color = (red + green + blue)/3;
				        img.setRGB(x, y, new Color(color, color, color, 127).getRGB());
				        
				        
				        //changing color, experimet with random values
				        int i=0, j=0, k=0;
						try {
				    	i = 255-red;
				        j = 255-green;
				        k = 255-blue;
				        img1.setRGB(x, y, new Color(i, j, k, 127).getRGB());
						}catch(IllegalArgumentException e) {System.out.println("Error: "+e);}
				        /*int i = generator.nextInt(color);
				        int j = generator.nextInt(color-i);
				        int k = color-j;
				        img1.setRGB(x, y, new Color(i,j,k, 127).getRGB());
				        img2.setRGB(x, y, new Color(color, color, color, 127).getRGB());
				        img3.setRGB(x, y, new Color(color, color, color, 127).getRGB());
		           */    
		            }
		        }
			//writing new one in gray
			 f = new File(inImage+"GRAY.jpg");
			 ImageIO.write(img, "jpg", f);
			 
			 g = new File(inImage+"1GRAY.jpg");
			 ImageIO.write(img1, "jpg", g);
		     
		      System.out.println("Writing complete.");
		    }catch(IOException e){
		      System.out.println("Error: "+e);
		    }		
	}
	
	public static void convertAllInDirectory(String directoryName) {
		File directory = new File(directoryName);

	        //List<File> resultList = new ArrayList<File>();

	        // get all the files from a directory
	        File[] fList = directory.listFiles();
	        //resultList.addAll(Arrays.asList(fList));
	        if (fList != null) {
		        for (File file : fList) {
		            if (file.isFile()) {
		            	System.out.println("file: "+file.getAbsolutePath());
		            	makeGray(file.toString());
		            	//new ReaderCSVFiles(file.getAbsolutePath());
		            } else if (file.isDirectory()) {
		            	convertAllInDirectory(file.toString());
		                System.out.println(file.getAbsolutePath());
		            }
		        } 
	        }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//makeGray("girl.jpg");
		convertAllInDirectory("img");
	}

}
