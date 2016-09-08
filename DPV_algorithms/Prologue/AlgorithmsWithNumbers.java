public class AlgorithmsWithNumbers
{
	//Basic arithmetic for 2 n-bit numbers
	
	//Addition: O(n), n is the number of bits of input	
	//Multiplication: O(n^2)
	//Division: O(n^2)
	
	//Input: 2 n-bit integers x and y, where y >= 0
	//Output: Their product
	public static int Multiply(int x, int y)
	{
		if(y == 0)
		{
			return 0;
		}
		int z = Multiply(x, y/2);
		if(y % 2 == 0)
		{
			return 2*z;
		}
		else
		{
			return x + 2*z;
		}	
	}
	
	//Input: 2 n-bit integers x and y, where y >= 1
	//Output: The quotient and remainder of x divided by y
	public static int[] Divide(int x, int y)
	{
		int[] result = new int[2];
		if(x == 0)
		{
			result[0] = 0;	//quotient
			result[1] = 0;	//remainder
			return result;
		}
		result = Divide(x/2, y);
		result[0] = result[0] * 2;
		result[1] = result[1] * 2;
		
		if(x % 2 != 0)
		{
			result[1]++;
		}
		
		if(result[1] >= y)
		{
			result[1] -= y;
			result[0]++;
		}
		return result;
	}
	
	//Modular arithmetic for 2 n-bit numbers
	//Modular addition: O(n)
	//Modular multiplication: O(n^2)
	//Euclid's gcd algorithm: O(n^3)
	//Modular division: O(n^3)
	//Modular exponentiation: O(n^3)
	
	public static int ModularAddition(int x, int y, int N)
	{
		//Assume x and y are modular N
	    int z = x + y;	//O(n)
	    if(z >= N)
	    {
	    	z -= N;		//O(n)
	    }
	    return z;
	}
	
	public static int ModularMultiplication(int x, int y, int N)
	{
		int[] r = new int[2];
		int z = Multiply(x,y);	//O(n^2), z <= (N-1)^2; z at most 2n bits, n = ceil(log(N))
		if(z >= N)
		{
			r = Divide(z,N);    //z at most 2n bits, N n bits, O(n^2)
			z = r[1];
		}
		return z;
	}
	
	//input: 2 n-bit integers x and N, an integer exponent y
	//output: x^y % N
	public static int ModularExponentiation(int x, int y, int N)
	{
		if(y == 0)
		{
			return 1;
		}
		//z is always in range[0, N-1], never gets too big
		//
		int z = ModularExponentiation(x, y/2, N);//n recursive calls, n is the number of y's bits	
		if(y % 2 == 0)
		{
			return (z*z) % N;	//O(n^2)
		}
		else
		{
			return (x*z*z) % N;	//O(n^2)
		}
	}
	
	//Lemma: if a >= b, then a % b < a/2. This means after any two consecutive recursive calls,
	//both a and b are at the very least halved in value -- the length of each decreases by
	//at least one bit. If they are initially n-bit integers, then the base case will be reached
	//within 2n recursive calls. And since each call involves a O(n^2) division, the running time
	//is O(n^3)
	public static int Gcd(int a, int b)
	{
		if(b == 0)
		{
			return a;
		}
		return Gcd(b, a%b);
	}
	
	//Motivation: How can we check if d is the gcd of a and b?
	//Lemma: If d divides both a and b, and d = ax + by for some integers x and y,
	//then necessarily d = gcd(a,b)
	
	//Input: 2 positive integers a and b with a >= b >= 0
	//Ouput: Integers x,y,d such that d = gcd(a,b) and ax+by =d
	public static int[] Gcd_extended(int a, int b)
	{
		int[] r = new int[3];
		if(b == 0)
		{
			r[0] = 1;	//x
			r[1] = 0;	//y
			r[2] = a;	//gcd
			return r;
		}
		r = Gcd_extended(b, a%b);	
		int x = r[0];
		int y = r[1];
		
		r[0] = y;
		r[1] = x - (a/b)*y;
		return r;
	}
	
	//For any a mod N, a has a multiplicative inverse module N if and only if it is relatively
	//prime to N. (gcd(a, N) == 1). When this inverse exists, it can be found in O(n^3) by running
	//the extended Euclid algorithm
	public static int ModularDivision(int a, int N)
	{
		if(Gcd(a, N) > 1)	//O(n^3)
		{
			return -1;	//inverse does not exist
		}
		int r[] = new int[3];
		r = Gcd_extended(N, a);	//O(n^3)
		return r[1];
	}
	
	public static void main(String[] args)
	{
		int[] r = new int[3];
		r = Gcd_extended(25,11);
		System.out.println(r[0]);
		System.out.println(r[1]);
		System.out.println(r[2]);
		
		int r1 = ModularDivision(11, 25);
		System.out.println(r1);
	}
}