package cslabs;

import java.util.Scanner;

public class cs09gcdrunner
{
	//main method
	public static void main(String[] args)
	{
		//instantiate Scanner object
		Scanner in = new Scanner(System.in);
		
		//prompt user for numerator
		System.out.print("Numerator: ");
		int n = in.nextInt();
		
		//prompt user for denominator
		System.out.print("Denominator: ");
		int d = in.nextInt();
		
		//instantiate GCD object
		cs09gcd gcd = new cs09gcd(n, d);
		
		//call toString() method
		System.out.println(gcd.toString());
		
	}
}
