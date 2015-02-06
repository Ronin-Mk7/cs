// A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Block
{
  private int xSpeed;
  private int ySpeed;

  public Ball()
  {
    super(200,200);
    xSpeed = 2;
    ySpeed = 2;
  }

  // add the other Ball constructors
  // like block look at BallTestOne and BallTestTwo to determine what constructors you need
  
  public Ball(Color col)
  {
	  super(200,200, col);
	  
	  xSpeed = 2;
	  ySpeed = 2;
  }
  
  public Ball(int x, int y)
  {
	  super(x,y);
	  
	  xSpeed = 2;
	    ySpeed = 2;
  }
  
  public Ball(int x, int y, int width, int height)
  {
	  super(x,y, width, height);
	  
	  xSpeed = 2;
	    ySpeed = 2;
  }
  
  public Ball(int x, int y, int width, int height, Color col)
  {
	  super(x,y, width, height, col);
	  
	  xSpeed = 2;
	    ySpeed = 2;
  }
  
  public Ball(int x, int y, int width, int height, Color col, int xSped, int ySped)
  {
	  super(x,y, width, height, col);
	  
	  xSpeed = xSped;
	  
	  ySpeed = ySped;
  }

  public void setXSpeed(int x)
  {
	  xSpeed = x;
  }
  
  public void setYSpeed(int y)
  {
	  ySpeed = y;
  }

  public int getXSpeed()
  {
	  return xSpeed;
  }
  
  public int getYSpeed()
  {
	  return ySpeed;
  }

  // add the set methods


  public void moveAndDraw(Graphics window)
  {
    //draw a white ball at old ball location
    draw(window,Color.white);

    setX(getX()+xSpeed);
    //setY
    setY(getY()+ySpeed);
    
    //draw the ball at its new location
    draw(window, super.getColor());
  }

  public boolean equals(Object obj)
  {
	  Ball s = (Ball) obj;

	  if( s.getXSpeed() != xSpeed)
	  {
			return false;
	  }
		
	  else if(s.getYSpeed() != ySpeed)
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
	  return super.toString() + " " + xSpeed + " " + ySpeed;
  }
}
