package mainPackage;

public class IRFunctions {

	public static double averageOfRow(double[] values){
		double sum = 0;
		for (double item : values) {
			if(Double.isNaN(item)) continue;
		    sum += item;
		}
		return sum/values.length;
	}
	
	public static double PearsonCorrelation(double average1, double[] values1, double average2, double [] values2){
		double top = PearsonCorrelationTop(average1, values1, average2, values2);
		double bottom =  PearsonCorrelationBottom(average1, values1,average2, values2);
		return top/bottom;
	}
	
	public static double PearsonCorrelationTop (double average1, double[] values1, double average2, double [] values2){
		double sum = 0;
		for(int i =0; i< values1.length; i++){
			sum += (values1[i] - average1) * (values2[i] - average2);
		}
		return sum;
	}
	
	public static double PearsonCorrelationBottom(double average1, double[] values1, double average2, double [] values2){
		int sum1 = 0;
		int sum2 = 0;
		for(int i =0; i< values1.length; i++){
			sum1 += Math.pow((values1[i] - average1),2); 
			sum2 += Math.pow((values2[i] - average2),2);
		}
		double total = Math.pow(sum1,1/2) * Math.pow(sum2,1/2);
		return total;
	}
	
	public static double AggregateRatings (double [] listOfPearsonsCorrelations, double [] verticalValues,
			double [] averagesOfEachRow, double averageOfMissingRow ){
	
		double topOfAR = 0.0;
		double bottomOfAR = 0.0;
		for(int i =0; i< listOfPearsonsCorrelations.length; i++){
			topOfAR+= listOfPearsonsCorrelations[i] * (verticalValues[i] - averagesOfEachRow[i]);
			bottomOfAR+=listOfPearsonsCorrelations[i];
		}
		//Estimated Relative Rating
		double err = topOfAR / bottomOfAR;
		
		return err + averageOfMissingRow;
	}
	
	public static double MAE (double[] trueRatings, double[] predictedRating){
		double sum = 0;
		for(int i =0; i< trueRatings.length; i++){
			sum += Math.abs(trueRatings[i] - predictedRating[i]);
		}
		return sum/ trueRatings.length;
		
	}
	
	public static double RMSE (double[] trueRatings, double[] predictedRating){
		double sum = 0;
		for(int i =0; i< trueRatings.length; i++){
			sum += Math.pow(trueRatings[i] - predictedRating[i], 2);
		}
		double x = sum/ trueRatings.length;
		
		return Math.pow(x, .5);
		
	}
	
}
