package part1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderDirectoriesAndSubdirectoriesWithFiles {
	
	private static ArrayList<Double> maxValues = new ArrayList<Double>();
	
	public static void showAbsoluteResultFromDirAndSubdir(String directoryName) {
		List<File> lf = listf(directoryName);
		
		for(File f: lf) {
			new ReaderCSVFiles(f.getAbsolutePath());
			double maxVal = ReaderCSVFiles.readFromFile();
			System.out.println(f+" - max value: "+maxVal);
			maxValues.add(maxVal);
		}
		
		double absoluteMaxValue=0;
		System.out.println("Result from list: ");
		for(double res: maxValues) {
			System.out.print(res+", ");
			if(res>absoluteMaxValue) absoluteMaxValue = res;
				
		}
		System.out.println();
		System.out.println("Absolutemax value: "+absoluteMaxValue);
		
	}
	
	public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
            	//System.out.println(file.getAbsolutePath());
            	//new ReaderCSVFiles(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
                //System.out.println(file.getAbsolutePath());
            }
        }
        //System.out.println(fList);
        return resultList;
    }

}
