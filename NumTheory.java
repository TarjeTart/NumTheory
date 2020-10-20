import java.math.BigInteger;

public class NumTheory {

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
	
}
