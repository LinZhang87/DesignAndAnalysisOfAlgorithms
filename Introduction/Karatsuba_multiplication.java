// This is a recursive algorithm to calculate the multiplication
// of two integers x and y, n is the larger number of digits of x and y
// x = a * 10^(ceil(n/2)) + b
// y = c * 10^(ceil(n/2)) + d
// x * y = 10^n * a * c + 10^(ceil(n/2)) * (a * d + b * c) + b * d
// When n is odd, round up n / 2 to n / 2 + 1. Not doing this round up
// makes the following implementation not working correctly

// Q: Is this algorithm better than the basic multiplication?
// A: The basic multiplication : n^2
// 	  Karatsuba: T(n) = 3 * T(ceil(n/2)) + c0 * n + c1, where c0 and c1 are constants
//    Applying master theorem, T(n) ~ asymptotic bound n^(log 2   3), 2 is the base of the log

// Q: Does this algorithm work on two integers that do not have 
//	  the same digit numbers?
// A: Yes

// Q: Does this algorithm work on both positive and negative integers?
// A: Yes.

public class Karatsuba_multiplication
{
	// n is the larger number of x's digits and y's digits
	// e.g., if x = 1234, y = 56, then n = 4
	public static double karatsuba_mul(int x, int y, int n) throws IllegalArgumentException
	{
		if(n <= 0)
		{
			throw new IllegalArgumentException("the digit number must be bigger than 0!\n");
		}
		else if(n == 1)
		{
			return x * y;
		}
		else
		{
			// This recursive algorithm is most efficient when 
			int temp = (int)Math.ceil((double)n/2);
			int a = x / ((int) Math.pow(10, temp));
			int b = x % ((int) Math.pow(10, temp));
			int c = y / ((int) Math.pow(10, temp));
			int d = y % ((int) Math.pow(10, temp));
			double ac = karatsuba_mul(a, c, temp);
			double bd = karatsuba_mul(b, d, temp);
			double ad_bc;

			if((a + b) >= (int) Math.pow(10, temp) || (c + d) >= (int) Math.pow(10, temp))
			{
				ad_bc = karatsuba_mul(a+b, c+d, temp + 1) - ac -bd;
			}
			else
			{
				ad_bc = karatsuba_mul(a+b, c+d, temp) - ac -bd;
			}

		    return Math.pow(10, n) * ac + Math.pow(10, temp) * ad_bc + bd;

		}		
	}
	public static void main(String args[])
	{

		System.out.println("5678 * 1234 == " + (int)karatsuba_mul(5678, 1234, 4));		
		 
		System.out.println("5678 * 0 == " + (int)karatsuba_mul(5678, 0, 4));		
		 
		System.out.println("5678 * 12 == " + (int)karatsuba_mul(5678, 12, 4));
			 
		System.out.println("3 * 4 == " + (int)karatsuba_mul(3, 4, 1));
				 
		System.out.println("3 * 12 == " + (int)karatsuba_mul(3, 12, 2));
		 
		System.out.println("-5678 * (-1234) == " + (int)karatsuba_mul(-5678, -1234, 4));
	 
		System.out.println("-5678 * 1234 == " + (int)karatsuba_mul(-5678, 1234, 4));
	}
}