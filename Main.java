import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
//initialize global variables for use
	static int samples = 0;
	static int inputs = 0;
	static final double weightZero = -1.0;
	static final double correctionFactor = 0.1;
	static final int MAXITER = 100;
	static final double STARTINGWEIGHT = 0.0;
	static final int BIAS = 1;
	static Scanner argString = new Scanner(System.in);
	
	public static void main(String[] args) {
		Perceptron perc = new Perceptron();
		//System.out.println(File.separator);
		//System.out.println(System.getProperty("user.dir"));
	
//Let user decide to enter own file or use built in file			
		String userFile;
		
		System.out.print("Enter 1 to give directory of test file, enter 2 for built in testfile");
		int choice = argString.nextInt();
		if(choice == 1) {
			System.out.println("Enter full directory string of file");
			userFile = argString.next();
		} else if (choice == 2) {
			userFile = "./5by6.txt";
		} else {
			userFile = "./src/test2.txt";
		}
		//System.out.println(userFile);
		//argString.close();
		
//Open given file or built in file and read input		
		File inputFile = new File(userFile);
		Scanner input = null;
		try {
			input = new Scanner(inputFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
//Pull first line for samples and inputs		
		Boolean splitFlag = true;
		String str = input.nextLine();
		for (String str2: str.split(" ")) {
			if(splitFlag) {
				inputs = Integer.parseInt(str2);
				splitFlag = false;
				continue;
			}else samples = Integer.parseInt(str2);
		}
//Set inputs, samples, and weightzero within Perceptron		
		perc.setInputs(inputs);
		perc.setSamples(samples);
		perc.setWeightZero(weightZero);
		perc.setCorrectionFactor(correctionFactor);
		perc.setMaxIter(MAXITER);
		perc.setStartingWeights(STARTINGWEIGHT);
		perc.setBias(BIAS);
		perc.setInitialWeights();
		//System.out.println(perc.getWeightZero());
		//System.out.println(str);
		
//Convert lines from file to rows of the table
		ArrayList<Integer> postConvert = new ArrayList<Integer>();
		while(input.hasNextLine()) {
			str = input.nextLine();
			postConvert = convertInput(str);
			perc.addRow(postConvert);
			//System.out.println(postConvert.toString());
		}
		input.close();
		
//print first grid, with Bias added in as first element in each sample		
		perc.printGrid();
		perc.trainPerc();
		//Scanner ip2 = new Scanner(System.in);
		String userSample;
		System.out.println("");
		//userSample = ip2.nextLine();
		boolean valid = true;
		while(valid) {
			//System.out.println("Enter exit to exit loop");
			//System.out.println("Please enter your " + inputs +" variable space-delimited sample, leaving out bias and classification entries");
			//userSample = input.nextLine();
			userSample = gatherString();
			if(userSample.equalsIgnoreCase("exit")) {
				System.out.println("");
				System.out.println("Exiting...");
				break; 
			} else {
				perc.calcSample(userSample);
			}
		} 
		//boolean test = perc.processSamples();
		//System.out.println(test);
		//perc.trainPerc();
		//ip2.close();
		//input.close();
		argString.close();
	}//end main
	
	public static ArrayList<Integer> convertInput(String str){
		//int bias = 1;
		ArrayList<Integer> postConvert = new ArrayList<Integer>();
		ArrayList<String> test = new ArrayList<String>(Arrays.asList(str.split(" ")));
		postConvert.add(BIAS);
		for(String str3 : test) {
			postConvert.add(Integer.parseInt(str3));
		}
		
		//System.out.println(postConvert.toString());
		return postConvert;
	}
	
	public static String gatherString() {
		Scanner ip2 = new Scanner(System.in);
		System.out.println("Enter exit to exit loop");
		System.out.println("Please enter your " + inputs +" variable space-delimited sample, leaving out bias and classification entries");
		String userSample = ip2.nextLine();
		//System.out.println(userSample);
		//ip2.close();
		return userSample;
		
	}
}
