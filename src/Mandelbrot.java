import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Mandelbrot {
	private static final int WIDTH = 900;
	private static final int HEIGHT = 700;
	private static GC graph;
	private static Image im;
	private static Color color,color1;
	static Random rand = new Random();
	
	private static Color set_palette(Display d, int k)
	{
		switch (k) {
		case 0:
			return (new Color(d,0,0,0));
		case 1:
			return (new Color(d,0,0,255));
		case 2:
			return (new Color(d,0,255,0));
		case 3:
			return (new Color(d,0,204,255));
		case 4:
			return (new Color(d,255,0,0));
		case 5:
			return (new Color(d,102,0,153));
		case 6:
			return (new Color(d,102,100,51));
		case 7:
			return (new Color(d,254,204,204));
		case 8:
			return (new Color(d,51,151,0));
		case 9:
			return (new Color(d,0,255,255));
		case 10:
			return (new Color(d,0,204,51));
		case 11:
			return (new Color(d,0,204,204));
		case 12:
			return (new Color(d,255,190,102));
		case 13:
			return (new Color(d,200,153,255));
		case 14:
			return (new Color(d,255,255,0));
		case 15:
			return (new Color(d,255,255,255));
		default:
			return (new Color(d,0,102,0));
		}
	}
	
	private static void drawMandelbrot(Display d) 
	{
		int max = 10;
		int max_iter = 50;
		Complex t = new Complex(0,0);
		/*Color current = new Color(d,0,0,0);*/
		int X = WIDTH/2;
		int Y = HEIGHT/2;
		for (int y = -Y; y < Y; ++y)
		{
			for (int x = -X; x < X; ++x)
			{
				Complex c = new Complex (x*0.004,y*0.004);
				Complex z = new Complex(0,0);
				int n = 0;
				while ( (Math.pow(z.getRe(),2)+Math.pow(z.getIm(),2) < max) && (n < max_iter) )
				{
					t.setRe(z.getRe());
					t.setIm(z.getIm());
					z.setRe(Math.pow(t.getRe(),2)-Math.pow(t.getIm(),2) + c.getRe());
					z.setIm(2*t.getRe()*t.getIm()+c.getIm());
					n++;
				}
				
				if (n<max_iter)
				{
					int col = max - (n % max);
					//graph.setAlpha(n * 100);
					graph.setForeground(set_palette(d,col));
					graph.drawPoint(X+x,Y+y);
				}
			}
		}

	}
	
	public Image draw (Display display, Canvas fractalPic){
    	im = new Image(display, WIDTH, HEIGHT);
    	//color = display.getSystemColor (SWT.COLOR_WHITE);
    	color = display.getSystemColor (SWT.COLOR_WHITE);
		color1 = display.getSystemColor (SWT.COLOR_BLACK);
		graph = new GC(im);
		graph.setBackground(color1);
		graph.fillRectangle(im.getBounds ());
		graph.setForeground(color);
		drawMandelbrot(display);
    	graph.dispose ();
    	
    	fractalPic.addPaintListener (new PaintListener () {
    		public void paintControl (PaintEvent e) {
    			if(im != null){
    				e.gc.drawImage(im, 0, 0);
    			}
    		}
    	});
    	fractalPic.layout();
    	return im;
    }

}
