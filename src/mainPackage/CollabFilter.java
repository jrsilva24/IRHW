package mainPackage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CollabFilter {
	static List<User> TrainingUsers;
	static List<User> TestUsers;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println("Jose Silva CS 599");
			TrainingUsers = new ArrayList<User>();
			TestUsers = new ArrayList<User>();
			try {
				readFiles(System.getProperty("user.dir") + "/src/mainPackage/test.dat");
				readFilesForTestUsers(System.getProperty("user.dir") + "/src/mainPackage/training.dat");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public static void readFiles(String path) throws FileNotFoundException, IOException{
		String line;
		try (
		    InputStream fis = new FileInputStream(path);
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) 
		{
		    while ((line = br.readLine()) != null) {
		        // Deal with the line
		    	 String[] data = line.split(",");
		    	 String name = data[0];
		    	 
		    	 double [] values = getDoubleArrayFromFile(data);
		    	 
		    	 TrainingUsers.add(new User(name,values));
		    	 
		    	 
		    }
		    for (User u : TrainingUsers) {
		    	   System.out.println(Arrays.toString(u.ratings));
		    	   System.out.println(IRFunctions.averageOfRow(u.ratings));
		    	}
		}
	}
	
	public static void readFilesForTestUsers(String path) throws FileNotFoundException, IOException{
		String line;
		try (
		    InputStream fis = new FileInputStream(path);
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) 
		{
		    while ((line = br.readLine()) != null) {
		        // Deal with the line
		    	 String[] data = line.split(",");
		    	 String name = data[0];
		    	 
		    	 double [] values = getDoubleArrayFromFile(data);
		    	 
		    	 TestUsers.add(new User(name,values));
		    }
		    
		    for (User u : TestUsers) {
		    	   System.out.println(Arrays.toString(u.ratings));
		    	   System.out.println(IRFunctions.averageOfRow(u.ratings));
		    	}
		}
	}
	
	public static double[] getDoubleArrayFromFile(String[] data){
		ArrayList<Double> list = new ArrayList<Double>();
		for(int i = 1; i< data.length; i++){
			try{
				Double value = Double.parseDouble(data[i]);
				list.add(value);
			}catch(Exception e){
				System.out.println("Not a parsable double");
				list.add(null);
			}
			
		}
		return convertDoubles(list);
	}
	
	public static double[] convertDoubles(List<Double> doubles)
	{
	    double[] ret = new double[doubles.size()];
	    Iterator<Double> iterator = doubles.iterator();
	    int i = 0;
	    while(iterator.hasNext())
	    {
	        ret[i] = iterator.next();
	        i++;
	    }
	    return ret;
	}

}

