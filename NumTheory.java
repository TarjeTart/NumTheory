import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumTheory {
	
	private NumTheory(){};

	//gcd by euclidean algorithm
	public static int gcd(int a, int b) {
		if(b>a) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		if(a%b != 0)
			return gcd(b,a%b);
		return b;
	}
	
	public static List<Integer> extendedEuc(int a, int b) {
		//make sure a>b
		if(b>a) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		//create a list of all euc alg steps
		List<Integer> nums = new ArrayList<Integer>();
		int r = a%b;
		nums.add(a);
		nums.add(a/b);
		nums.add(b);
		nums.add(r);
		while(r!=0) {
			a=b;
			b=r;
			r=a%b;
			nums.add(a);
			nums.add(a/b);
			nums.add(b);
			nums.add(r);
		}
		//use list to go backwards
		return nums;
	}
	
	//modular arithmetic with exponents
	public static String exponentMods(int base,int power,int mod) {
		BigInteger result = BigInteger.valueOf(base);
		for(int i = 0; i < power-1;i++)
			result = result.multiply(BigInteger.valueOf(base));
		return base + " to the power of " + power + " mod " + mod + " is " + result.mod(BigInteger.valueOf(mod)).toString();
	}
	
	//large mods
	public static String largeMods(int base,int mod) {
		BigInteger result = BigInteger.valueOf(base);
		return base + " mod " + mod + " is " + result.mod(BigInteger.valueOf(mod)).toString();
	}
	
	//list of primes up to or equal to n
	public static List<Integer> primes(int n){
		//create a boolean array that will be indexed for 2 to n
		boolean[] boolArr = new boolean[n];
		//set all initially to true
		Arrays.fill(boolArr, true);
		//Sieve of Eratosthenes
		for(int i = 2;i < Math.sqrt(n);i++) {
			if(boolArr[i]) {
				for(int j = (int)Math.pow(i,2);j<n;j+=i) {
					boolArr[j] = false;
				}
			}
		}
		//create a list of ints were boolArr is true
		List<Integer> toReturn = new ArrayList<Integer>();
		for(int i = 2; i < n;i++) {
			if(boolArr[i]) {
				toReturn.add(i);
			}
		}
		//return result
		return toReturn;
	}
	
	//checks if n is prime
	public static boolean isPrime(int n) {
		boolean toReturn = true;
		for(int i:primes((int)Math.sqrt(n)+1)) {
			if(n%i==0)
				toReturn = false;
		}
		return toReturn;
	}
	
	//return a double array of primes and powers containing the prime factorization of n
	public static int[][] primeFac(int n){
		//decomp will keep a running list of the factors and their powers
		List<int[]> decomp = new ArrayList<int[]>();
		while(!isPrime(n)) {
			//go through the numbers primes
			for(int i :primes(n)) {
				//if the prime divides n it is a factor
				if(n%i==0) {
					//add factor to decomp
					decomp = addNum(i,decomp);
					//sets n = n divided by the factor
					n=n/i;
					//stops for loop
					break;
				}
			}
		}
		//adds the last remaining factor
		decomp = addNum(n,decomp);
		int[][] toReturn = new int[decomp.size()][2];
		int count = 0;
		//while decomp has elements
		while(decomp.size()>0) {
			int smallest = decomp.get(0)[0];
			int toRemove = 0;
			//get the index of the smallest factor
			for(int[] i:decomp) {
				if(i[0]<smallest) {
					toRemove = decomp.indexOf(i);
				}
			}
			//add smallest factor and power to returning array
			toReturn[count++] = decomp.get(toRemove);
			//remove smallest factor
			decomp.remove(toRemove);
		}
		return toReturn;
	}
	
	//finds the # of positive divisors of n
	public static int d(int n) {
		int toReturn = 1;
		for(int[] i : primeFac(n)) {
			toReturn*=(i[1]+1);
		}
		return toReturn;
	}
	
	//add 1 to power if exist or add base if not
	private static List<int[]> addNum(int j,List<int[]> list) {
		boolean existed = false;
		//check if factor exist
		for(int[] i:list) {
			if(i[0] == j) {
				//if it does add 1 to its power
				i[1]++;
				existed = true;
			}
		}
		//if factor didnt exist
		if(!existed) {
			int[] tmp = {j,1};
			//add factor to list
			list.add(tmp);
		}
		return list;
	}
	
}
