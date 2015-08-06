import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author vijendrab
 */
public class SmallestPrimeFactor {

	static boolean [] noprimes = new boolean[1000000];
	static List<Integer> primeCollection= new ArrayList<Integer>();
	static TreeMap<Integer, Integer> stats = new TreeMap<Integer, Integer>();
/*	
	Problem statement:
	Let smpf(n) be the smallest prime factor of n.
	smpf(91)=7 because 91=7Ã—13 and smpf(45)=3 because 45=3Ã—3Ã—5.
	Let S(n) be the sum of smpf(i) for 2 <= i <= n.
	E.g. S(100)=1257.
	
	Find S(1012) mod 109.
*/
	public static void main(String[] args) {
		buildPrimes(1000000);
		System.out.println(primeCollection.size());
		System.out.println(getSumOfSmallestPrimeFactors(10000));
		System.out.println("Completed execution");
		
		//Print all values stored in ConcurrentHashMap instance
		for(Entry<Integer, Integer> e : stats.entrySet()) {
		  System.out.println(e.getKey()+" = "+e.getValue());
		}
	}

	/**
	 * smallest prime factor of a number is always <= the sqrt(num).
	 * i.e all the numbers till 10^12 will have smallest prime factor below 10^6
	 * 
	 * @param input
	 */
	private static long getSumOfSmallestPrimeFactors(int input) {
		long sum=0;
		for(int i = 2; i<=input;i++){
			sum += getSmallestPrimeFactor(i);
			System.out.println("Summation upto "+ i + " is + "+ sum);
		}
		return sum;
	}


//	/**
//	 * @param number
//	 */
//	public static long getSmallestPrimeFactor(long number){
//		int divs = 2;
//		//if number is prime, smallest prime factor will be the number itself
//		if (isPrime(divs)){return divs;}
//		//if number is not a prime
//		//TODO We need to find only smallest prime factor
//		//instead of iterating natural numbers, iterate prime numbers
//		while (divs <= number) {
//			if ((number % divs) == 0 && isPrime(divs)) {
//				//System.err.println("Smallest prime factor of "+number + " is " +divs);
//				break;
//			}
//			divs++;
//		}
//		return divs;
//	}

	/**
	 * @param number
	 */
	public static long getSmallestPrimeFactor(int number){
		int i = 0;
		int limit = primeCollection.size()-1;
		while(i<limit && (number % primeCollection.get(i)!=0)){
			i++;
		}
		int smallestPrimeFactor = primeCollection.get(i);
		if(stats.get(smallestPrimeFactor) == null)stats.put(smallestPrimeFactor, 1);
		else stats.put(smallestPrimeFactor, stats.get(smallestPrimeFactor)+1);
		
		return smallestPrimeFactor;
		//return primeCollection.get(i);
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
	}



	/**
	 * This method builds array of prime numbers for given range
	 * It uses simple array traversal hence very effective as compared to old techniqe
	 * Uses "Sieve of Eratosthenes" algorithm to find the prime numbers
	 *
	 * @param number
	 */
	public static void buildPrimes(int number) {
		
		for(int i=1;i<number;i++)
		{
			if(noprimes[i] == false){
				// ie i+1 the number is prime
				//mark the multiples of i+1 as no prime i.e. true
				primeCollection.add(i+1);
				markNoPrimes(noprimes,i+1,number);
			}
		}
		
	}
	
	/**
	 * This method is used by buildPrimes(int).
	 * It is used to mark all the multiples of a given number as noPrime.
	 * 
	 */
	private static void markNoPrimes(boolean noprimes[], int multipleOf, int limit) {
		int multiplyBy = 2, num;
		while ((num = multipleOf * multiplyBy) <= limit) {
			noprimes[num - 1] = true;
			multiplyBy++;
		}
	}
	
}	
	
