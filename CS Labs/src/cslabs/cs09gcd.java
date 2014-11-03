package cslabs;

public class cs09gcd
{
	//instance variables - DO NOT ADD ANY MORE INSTANCE VARIABLES
	private int numerator;
	private int denominator;
	
	//default constructor
	public cs09gcd()
	{
		numerator = denominator = 0;
	}
	
	//initialization constructor
	public cs09gcd(int n, int d)
	{
		numerator = n;
		denominator = d;
	}
	
	//modifier method for numerator
	public void setN(int n) { numerator = n; }
	
	//modifier method for denominator
	public void setD(int d) { denominator = d; }
	
	//accessor method for numerator
	public int getN() { return numerator; }
	
	//accessor method for denominator
	public int getD() { return denominator; }
	
	//method to determine gcd
	public int gcd()
	{
		int largest = numerator;
		if (denominator > numerator) { largest = denominator; }
		int gcd = 1;
		
		for (int i = 2; i <= largest / 2; i++)
		{
			if ((numerator % i == 0) && (denominator % i == 0)) { gcd = i; }
		}
		
		return gcd;
	}
	
	//method to determine lowest term of numerator
	public int lowNumerator()
	{
		return numerator / gcd();
	}
	
	//method to determine lowest term of denominator
	public int lowDenominator()
	{
		return denominator / gcd();
	}
	
	//toString() method
	public String toString() {
		return "GCD: " + gcd() + "\r\nSimplest fraction: " + lowNumerator() + "/" + lowDenominator();
	}
}