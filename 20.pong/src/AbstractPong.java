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
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

abstract public class AbstractPong extends Canvas implements KeyListener, Runnable
{
  private boolean[] keys;
  private BufferedImage back;

  public AbstractPong()
  {
    keys = new boolean[4];

    setBackground(Color.WHITE);
    setVisible(true);

    new Thread(this).start();
    addKeyListener(this);   //starts the key thread to log key strokes
  }

  public void update(Graphics window){
    paint(window);
  }

  public void paint(Graphics window){
    //take a snap shop of the current Frame and same it as an image
    //that is the exact same width and height as the current Frame
    if(back==null)
      back = (BufferedImage)(createImage(getWidth(),getHeight()));

    //create a graphics reference to the back ground image
    //we will draw all changes on the background image
    Graphics graphToBack = back.createGraphics();

    render(graphToBack);

    ((Graphics2D)window).drawImage(back, null, 0, 0);
  }

  abstract public void render(Graphics window);

  public boolean keyIsPressed(char key) {
    switch(toUpperCase(key))
    {
      case 'W' : return keys[0];
      case 'Z' : return keys[1];
      case 'I' : return keys[2];
      case 'M' : return keys[3];
    }
    return false;
  }

  public void keyPressed(KeyEvent e)
  {
    switch(toUpperCase(e.getKeyChar()))
    {
      case 'W' : keys[0]=true; break;
      case 'Z' : keys[1]=true; break;
      case 'I' : keys[2]=true; break;
      case 'M' : keys[3]=true; break;
    }
  }

  public void keyReleased(KeyEvent e)
  {
    switch(toUpperCase(e.getKeyChar()))
    {
      case 'W' : keys[0]=false; break;
      case 'Z' : keys[1]=false; break;
      case 'I' : keys[2]=false; break;
      case 'M' : keys[3]=false; break;
    }
  }

  public void keyTyped(KeyEvent e) {}

  public void run()
  {
    try {
      while(true)
      {
        Thread.currentThread().sleep(8);
        repaint();
      }
    }catch(Exception e) { }
  }
}

