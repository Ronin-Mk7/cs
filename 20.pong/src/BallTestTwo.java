// A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BallTestTwo extends Canvas implements Runnable
{
	private Ball ball;

	public BallTestTwo()
	{
		setBackground(Color.white);
		setVisible(true);

		//test the Ball thoroughly
		//ball = new Ball();
		
		//uncomment each one test make sure Ball is perfect
		ball = new Ball(50,50,3,3);   //test initialization constructor
		ball = new Ball(100,350,10,10,Color.RED);   //test initialization constructor
		ball = new Ball(200,90,10,10,Color.BLUE);   //test initialization constructor
		ball = new Ball(300,10,10,10,Color.GREEN);   //test initialization constructor

		//test all constructors

		new Thread(this).start();
	}
	
	public void update(Graphics window)
	{
		paint(window);
	}

	public void paint(Graphics window)
	{
		ball.moveAndDraw(window);

		if(!(ball.getX()>=10 && ball.getX()<=550))
		{
			ball.setXSpeed(-ball.getXSpeed());
		}

		if(!(ball.getY()>=10 && ball.getY()<=450))
		{
			ball.setYSpeed(-ball.getYSpeed());
		}
	}
	
   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread();
   		   Thread.sleep(19);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}	
}
