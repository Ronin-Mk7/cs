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
	  
	  leftPaddle = new Paddle();
	  
	  rightPaddle = new Paddle();
  }

  public void render(Graphics window)
  {
    ball.moveAndDraw(window);
    leftPaddle.draw(window, Color.black);
    rightPaddle.draw(window, Color.black);

    //see if ball hits left wall or right wall
    if(!(ball.getX()>=10 && ball.getX()<=780))
    {
      ball.setXSpeed(0);
      ball.setYSpeed(0);
    }


    //see if the ball hits the top or bottom wall

    if(!(ball.getY()>=10 && ball.getY()<=780))
    {
      ball.setYSpeed(-ball.getYSpeed());
    }

    
    //see if the ball hits the left paddle
    if((ball.getX() <= leftPaddle.getX() +leftPaddle.getWidth() + Math.abs(ball.getXSpeed()) && (ball.getY() >= leftPaddle.getY() &&ball.getY() <= leftPaddle.getY() + leftPaddle.getHeight()  ||ball.getY() + ball.getHeight() >= leftPaddle.getY() &&ball.getY() +ball.getHeight()  < leftPaddle.getY() + leftPaddle.getHeight())))
    {
      if(ball.getX()<=leftPaddle.getX()+leftPaddle.getWidth()-Math.abs(ball.getXSpeed()))
      {
    	  ball.setYSpeed(-ball.getYSpeed());
      }
      else
      { 
    	  ball.setXSpeed(-ball.getXSpeed());
      }
   }



    //see if the ball hits the right paddle

    if((ball.getX() <= rightPaddle.getX() +rightPaddle.getWidth() + Math.abs(ball.getXSpeed()) && (ball.getY() >= rightPaddle.getY() &&ball.getY() <= rightPaddle.getY() + rightPaddle.getHeight()  ||ball.getY() + ball.getHeight() >= rightPaddle.getY() &&ball.getY() +ball.getHeight()  < rightPaddle.getY() + rightPaddle.getHeight())))
    {
      if(ball.getX()<=rightPaddle.getX()+rightPaddle.getWidth()-Math.abs(ball.getXSpeed()))
      {
    	  ball.setYSpeed(-ball.getYSpeed());
      }
      else
      { 
    	  ball.setXSpeed(-ball.getXSpeed());
      }
   }



    //see if the paddles need to be moved
    if(keyIsPressed('W') == true)
    {
      leftPaddle.moveUpAndDraw(window);
    }
    if(keyIsPressed('X') == true)
    {
      leftPaddle.moveDownAndDraw(window);
    }

  }
}
