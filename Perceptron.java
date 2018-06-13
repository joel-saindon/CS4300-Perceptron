import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;


public class Perceptron {
	private int samples;
	private int inputs;
	private double weightZero;
	private double correctionFactor;
	private int maxIter;
	private double startingWeights;
	private int bias;
	private boolean confirmFlag = false;
	private ArrayList<ArrayList<Integer>> table = new ArrayList<>();
	private ArrayList<Double> weights = new ArrayList<>();

	public Perceptron() {
		this.table = new ArrayList<ArrayList<Integer>>();
	}
	
	public void addRow (ArrayList<Integer> listToAdd) {
		this.table.add(listToAdd);
	}
	
	public void printGrid() {
		System.out.print("X0 ");
		for (int i = 1; i< samples; i++) {
			System.out.print("X" + i + " ");
		}
		System.out.println("C");
		for(ArrayList<Integer> row : this.table) {
			System.out.println(row.toString());
		}
		//DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("Weights:");
		System.out.print("[");
		for(int j = 0; j< this.weights.size(); j++) {
			System.out.print(new DecimalFormat("0.00").format(this.weights.get(j)));
			if(j == this.weights.size() -1) {
				System.out.println("]");
			} else {
				System.out.print(", ");
			}
		}
		//System.out.println(weights.toString());
	}
	
	public void setInitialWeights() {
		for(int i = 1; i<this.samples; i++) {
			this.weights.add(0.0);
		}
	}
	
	public void trainPerc() {
		for(int i =1; i <=this.maxIter; i++) {
			if(processSamples() == true) {
				this.confirmFlag = true;
				System.out.println("Acceptable Classification Weights found after " + i + " iterations" );
				System.out.println("Final Weights: ");
				System.out.print("[");
				for(int j = 0; j< this.weights.size(); j++) {
					System.out.print(new DecimalFormat("0.00").format(this.weights.get(j)));
					if(j == this.weights.size() -1) {
						System.out.println("]");
					} else {
						System.out.print(", ");
					}
				}
				break;
			} 
			
		}
		if(this.confirmFlag == false) {
			System.out.println("No Acceptable Classification weights found after " + this.maxIter + " iterations");
		}
	}
	
	public int threshold(double given) {
		if(given > 0) {
			return 1;
		}else { //given sum is <= 0
			return -1;
		}
	}
	
	public boolean processSamples() {
		double sum = 0.0;
		int i = 1;
		//int count = 0;
		int result = 0;
		int numFalse = 0;
		for(ArrayList<Integer> ip : this.table) {
			//System.out.println(ip.toString());
			sum = 0.0;
			for(i=0; i<(ip.size() -2); i++) {//1 for 0 indexing, 1 for leaving out C,
				//System.out.println(i);
				sum = sum + (ip.get(i) * this.weights.get(i));
			}
			//System.out.println("sum for sample " + count + ": " + sum);
			//count++;
			//System.out.println(threshold((int)sum));
			//System.out.println(ip.size() -1);
			result = threshold(sum);
			//System.out.println("Result: " + result);
			
//if threshold result matches classification, no need to recalculate weights
			//System.out.println("DEBUG: result: " + result + " ip.get(ip.size()-1)): " + ip.get((ip.size()-1)));
			if((Integer)result == (Integer)ip.get(ip.size() -1)) {
				continue;
			} else {//if sample is misclassified
				recalcWeights(ip, result);
				numFalse++;
			}
		}
		
		System.out.println("Weights after processing: ");
		System.out.print("[");
		for(int j = 0; j< this.weights.size(); j++) {
			System.out.print(new DecimalFormat("0.00").format(this.weights.get(j)));
			if(j == this.weights.size() -1) {
				System.out.println("]");
			} else {
				System.out.print(", ");
			}
		}
		if (numFalse == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void recalcWeights(ArrayList<Integer> ip, int result) {
		for(int i =0; i <= ip.size() -2; i++) {
			double newWeight = this.weights.get(i) + ((this.correctionFactor*(ip.get(ip.size()-1) - result))*ip.get(i));
			//System.out.println("newWeight calculated: " + newWeight);
			this.weights.set(i, newWeight);
			//double temp = this.weights.get(i);
			//temp = newWeight;
		}
		
	}
	
	public void calcSample(String userSample) {
		ArrayList<Integer> sample = new ArrayList<Integer>();
		ArrayList<String> test = new ArrayList<String>(Arrays.asList(userSample.split(" ")));
//add beginning bias to sample
		sample.add(this.bias);
		for (String str2 : test) {
			sample.add(Integer.parseInt(str2));
		}
		//System.out.println(sample.toString());
		System.out.println("Classification: " + processSingle(sample));
	}
	
	public int processSingle(ArrayList<Integer> sample) {
		double sum = 0.0;
		int i = 1;
		//int count = 0;
		//int result = 0;
		//int numFalse = 0;
		for(i=0; i<(sample.size() -1); i++) {//1 for 0 indexing, 1 for leaving out C,
			//System.out.println(i);
			sum = sum + (sample.get(i) * this.weights.get(i));
		}
		return threshold(sum);
	}
//***************************Getters/Setters*************************
	public int getSamples() {
		return samples;
	}

	public void setSamples(int samples) {
		this.samples = samples;
	}

	public int getInputs() {
		return inputs;
	}

	public void setInputs(int inputs) {
		this.inputs = inputs;
	}

	public ArrayList<ArrayList<Integer>> getTable() {
		return table;
	}

	public void setTable(ArrayList<ArrayList<Integer>> table) {
		this.table = table;
	}

	public ArrayList<Double> getWeights() {
		return weights;
	}

	public void setWeights(ArrayList<Double> weights) {
		this.weights = weights;
	}

	public double getWeightZero() {
		return weightZero;
	}

	public void setWeightZero(double weightZero) {
		this.weightZero = weightZero;
		this.weights.add(this.weightZero);
		}

	public double getCorrectionFactor() {
		return correctionFactor;
	}

	public void setCorrectionFactor(double correctionFactor) {
		this.correctionFactor = correctionFactor;
	}

	public int getMaxIter() {
		return maxIter;
	}

	public void setMaxIter(int maxIter) {
		this.maxIter = maxIter;
	}

	public double getStartingWeights() {
		return startingWeights;
	}

	public void setStartingWeights(double startingWeights) {
		this.startingWeights = startingWeights;
	}

	public int getBias() {
		return bias;
	}

	public void setBias(int bias) {
		this.bias = bias;
	}

	public boolean isConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(boolean confirmFlag) {
		this.confirmFlag = confirmFlag;
	}
	
	
}
