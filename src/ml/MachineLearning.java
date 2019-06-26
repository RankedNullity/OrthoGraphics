package ml;
import java.util.function.*;

public class MachineLearning {
	
	double gamma = 0.01;
	double precision = 0.001;
	
	/**
	 * Creates a new matrix with the number of rows and number of columns. (Initially all 0)
	 * @param df The derivative of the differentiable function
	 */
	public double gradientDescend(Function<Double, Double> df) {
		
		double curlX = 6.0;
		double previousStepSize = 1.0;
		
		while (previousStepSize > precision) {
			double prevX = curlX;
			curlX -= gamma * df.apply(prevX);
			previousStepSize = Math.abs(curlX - prevX);
		}
		
		return curlX;
		
		
	}

}
