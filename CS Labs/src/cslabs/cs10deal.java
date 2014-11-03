package cslabs;
import java.io.PrintStream;
import java.util.Scanner;

public class cs10deal {
	public static void main(String[] args) {
		while (run()) { }
	}
	
	public static boolean run() {
		boolean[] doors = new boolean[] { false, false, false };
		int cardoor = (int)Math.floor(Math.random()*3); //car door in array
		doors[cardoor] = true;
		
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;
		out.print("Monty Hall Problem\r\nCopyright (c) 2014 Arthur Pachachura\r\n\r\n");
		out.print("Which door? (1-3)");
		
		int userdoor;
		try {
			userdoor = in.nextInt(); //user selected door
		} catch (Exception ex) { out.println("\r\nYou're an idiot!"); return false; }
		
		//find which door monty picks
		int runcount = (int)Math.floor(Math.random()*2+1); //should we select the second door we find or the first?
		int counted = 0;
		int montydoor = 0; //door monty selected
		for (montydoor = 0; counted < runcount; montydoor++)
		{
			if (doors[montydoor]) { continue; }
			counted++;
		}
		
		out.println("Monty shows you DOOR " + montydoor + ".  Great, it's a sheep.");
		out.print("Do you switch doors? (y/n) ");
		int doorselected = 0;
		try {
			 = in.nextInt(); //user selected door
		} catch (Exception ex) { out.println("\r\nYou're an idiot!"); return false; }
		
		return true;
	}
}
