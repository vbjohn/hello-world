/**
 * @author vijendrab
 */
public class SmallestPrimeFactor {

	static boolean noprimes[] = new boolean[1000000];
/*	Let smpf(n) be the smallest prime factor of n.
	smpf(91)=7 because 91=7×13 and smpf(45)=3 because 45=3×3×5.
	Let S(n) be the sum of smpf(i) for 2 <= i <= n.
	E.g. S(100)=1257.
	
	Find S(1012) mod 109.
*/
	public static void main(String[] args) {
		//System.err.println(getSumOfSmallestPrimeFactors(1000000000000l)%1000000000l);
		//getSumOfSmallestPrimeFactors(1000000000000l);
		//getSmallestPrimeFactor(999999999997);
		//System.err.println(getSmallestPrimeFactor(1000000000000l));
		
		buildPrimes(1000000);
		System.err.println("Completed");
	}
	
	/**
	 * @param i
	 */
	private static long getSumOfSmallestPrimeFactors(long input) {
		long sum=0;
		for(long i = input; i>=2;i--){
		//for(long i = 2; i<=input;i++){
			sum += getSmallestPrimeFactor(i);
			System.err.println("Summation upto "+ i + " is + "+ sum);
		}
		return sum;
	}

	public static long getSmallestPrimeFactor(long number){
		int divs = 2;
		//if number is prime, smallest prime factor will be the number itself
		if (isPrime(divs)){return divs;}
		//if number is not a prime
		//TODO We need to find only smallest prime factor
		//instead of iterating natural numbers, iterate prime numbers
		while (divs <= number) {
			if ((number % divs) == 0 && isPrime(divs)) {
				//System.err.println("Smallest prime factor of "+number + " is " +divs);
				break;
			}
			divs++;
		}
		return divs;
	}

	/**
	 * 
	 * @param divs
	 * @return
	 * 
	 * TODO optimize prime check 
	 * check for condition and expression to speed up the calculation
	 */
	private static boolean isPrime(int divs){
		for(int i = 2; i <= divs/2; i++)
		{
		   if((divs % i)==0)
		   {
			   return false;
		   }
		}System.err.println(divs +" is prime ? " + true);
		return true;
//		if((divs % 2) !=0 && (divs % 3!=0) && (divs % 5) !=0 && (divs % 7!=0))
//		return true;
//		else return false;
	}
	/**
	 * This method builds array of prime numbers below the given range
	 * 
	 * @param divs
	 * @return
	 * 
	 * element at index 1 will be always prime
	 * 
	 * TODO optimize prime check
	 */
	private static void buildPrimes(int number) {
		
		for(int i=1;i<=number;i++)
		{
			if(noprimes[i] == false){
				// ie i+1 the number is prime
				//mark the multiples of i+1 as no prime i.e. true
				markNoPrimes(noprimes,i+1,number);
			}
		}
		
	}
	
	private static void markNoPrimes(boolean noprimes[], int multiplesOf, int limit) {
	int multiplyBy = 2,num;
	while((num = multiplesOf*multiplyBy) <= limit){
		noprimes[num-1]=true;
		multiplyBy++;
	}
	}
	
}	
	
