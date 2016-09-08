public class Fibonacci
{
	
	public static double CalculateFibonacciInExponential(int n)
	{
		if(n == 0)
		{
			return 0;
		}
		else if(n == 1)
		{
			return 1;
		}
		else
		{
			return CalculateFibonacciInExponential(n-1) + CalculateFibonacciInExponential(n-2);
		}
	}
	
	public static double CalculateFibonacciInQuadratic(int n, double[] resultArray)
	{
		if(resultArray.length == (n + 1))
		{
			if(n == 0)
			{
				return 0;
			}
		    resultArray[0] = 0;
		    resultArray[1] = 1;
		    
		    for(int i = 2; i <= n; i++)
		    {
		    	//nth fibonacci number is about 0.694*n bits long, so this addition takes linear time,
		    	//not constant time
		    	resultArray[i] = resultArray[i-1] + resultArray[i-2];
		    }
		    return resultArray[n];
		}

		return -1;		//error
	}
	
	public static double CalculateFibonacciUsingMatrix(int n)
	{
		return 0;
	}
	
	public static void main(String[] args)
	{
		
	}
}