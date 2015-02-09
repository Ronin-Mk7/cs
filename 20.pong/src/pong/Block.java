// A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Block
{
	private int xPos;
	private int yPos;
	private int width;
	private int height;

	private Color color;
	
	public Block()
	{
		xPos = 200;
		
		yPos = 200;
		
		width = 100;
		
		height = 100;
		
		color = Color.white;

	}

	// add other Block constructors - x , y , width, height, color
   // you can find what constructors you need by looking at BlockTestOne and BlockTestTwo
	
	public Block(int x, int y, Color col)
	{
		xPos = x;
		
		yPos = y;
		
		width = 10;
		
		height = 10;
		
		color = col;
	}
	
	public Block(int x, int y)
	{
		xPos = x;
		
		yPos = y;
		
		width = 10;
		
		height = 10;
		
		color = Color.white;
	}

	public Block(int x, int y, int wid, int hei)
	{
		xPos = x;
		
		yPos = y;
		
		width = wid;
		
		height = hei;
		
		color = Color.white;
	}
	
	public Block(int x, int y, int wid, int hei, Color col)
	{
		xPos = x;
		
		yPos = y;
		
		width = wid;
		
		height = hei;
		
		color = col;

	}



	public void setColor( Color col )
	{
		color = col;

	}

	public void setPos( int x, int y )
	{
		xPos = x;
		
		yPos = y;
	}
	
	public void setX( int x )
	{
		xPos = x;
	}
	
	public void setY( int y )
	{
		yPos = y;
	}
	
	public void setWidth(int wid)
	{
		width = wid;
	}
	
	public void setHeight(int hei)
	{
		height = hei;
	}
	
	
	// add the other set methods
   // you can see what modifier methods to add by looking at the instance variables

	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}

	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Color getColor()
	{
		return color;
	}

   // use the draw method provided about to figure out how to complete this method
	public void draw(Graphics window, Color col)
	{
		window.setColor(col);
		
		window.fillRect(getX(), getY(), getWidth(), getHeight());
	}

   // review the equals method from book and library
   // show me your completed equals method please
	public boolean equals (Object obj)
	{
		Block s = (Block) obj; 
		
		if( s.getX() != xPos)
		{
			return false;
		}
		
		else if(s.getY() != yPos)
		{
			return false;
		}
		
		else if(s.getWidth() != width)
		{
			return false;
		}
		
		else if(s.getHeight() != height)
		{
			return false;
		}
		
		else if(s.getColor() != color)
		{
			return false;
		}
		
		else
		{
			return true;
		}
	}

	public String toString()
	{
		return xPos + " " + yPos + " " + width + " " + height + " " + color;
	}

	//add a toString() method  - x , y , width, height, color
}
