// A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Pong extends AbstractPong
{
  private Ball ball;
  private Paddle leftPaddle;
  private Paddle rightPaddle;

  public Pong()
  {
	  ball = new Ball(Color.black);
	  
	  leftPaddle = new Paddle(10, 0, 10, 200, 6);
	  
	  rightPaddle = new Paddle(780, 0, 10, 200, 6);
  }

  public void render(Graphics window)
  {
    ball.moveAndDraw(window);
    leftPaddle.draw(window, Color.black);
    rightPaddle.draw(window, Color.black);

    //see if ball hits left wall or right wall
    if((ball.getX()<0 || ball.getX()>790))
    {
      ball.setXSpeed(0);
      
      ball.setYSpeed(0);
    }

    
    //see if the ball hits the top or bottom wall

    if((ball.getY()<0 || ball.getY()>570))
    {
      ball.setYSpeed(-ball.getYSpeed());
    }
    
    
    if ((ball.getX() <= leftPaddle.getX()) && //check if paddle is within bounds of x
		((leftPaddle.getY() <= ball.getY()) && (ball.getY() <= leftPaddle.getY() + leftPaddle.getHeight()) //check if paddle is within bounds of Y
		))
    {
    	ball.setXSpeed(-ball.getXSpeed());
    	//ball.setYSpeed((int)(-ball.getYSpeed() /*+ 0.1*(((Math.abs(leftPaddle.getY() + leftPaddle.getHeight()))/2) - (ball.getY()))*/));
    	// + (int)((double)leftPaddle.getSpeed() * (1.5*(double)Math.random()-0.5));
    }
    
    if ((ball.getX() >= rightPaddle.getX()) && //check if paddle is within bounds of x
    		((rightPaddle.getY() <= ball.getY()) && (ball.getY() <= rightPaddle.getY() + rightPaddle.getHeight()) //check if paddle is within bounds of Y
    		))
        {
        	ball.setXSpeed(-ball.getXSpeed());
        	//ball.setYSpeed((int)(-ball.getYSpeed() /*+ 0.1*(((Math.abs(leftPaddle.getY() + leftPaddle.getHeight()))/2) - (ball.getY()))*/));
        	// + (int)((double)leftPaddle.getSpeed() * (1.5*(double)Math.random()-0.5));
        }



    //see if the paddles need to be moved
    if(keyIsPressed('W') == true)
    {
      leftPaddle.moveUpAndDraw(window);
    }
    if(keyIsPressed('Z') == true)
    {
      leftPaddle.moveDownAndDraw(window);
    }
    
    if(keyIsPressed('I') == true)
    {
      rightPaddle.moveUpAndDraw(window);
    }
    if(keyIsPressed('M') == true)
    {
      rightPaddle.moveDownAndDraw(window);
    }
  }
}
