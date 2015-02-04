// A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

public class BlockTestTwo extends Canvas
{
	public BlockTestTwo()
	{
		setBackground(Color.black);
	}

	public void paint(Graphics window)
	{
		Block one = new Block();
		one.draw(window, Color.blue);

		Block two = new Block(50,50,30,30);
		two.draw(window, Color.red);

		Block three = new Block(350,350,15,15,Color.red);
		three.draw(window, Color.yellow);

		//two.draw(window, Color.white);

		Block four = new Block(450,50,20,60, Color.green);
		four.draw(window, Color.green);
		
		//add test cases to check all of the constructors you created			
	}
}
