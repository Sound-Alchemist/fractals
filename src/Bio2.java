import java.util.Random;
import org.eclipse.swt.*;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Bio2 {
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
			return (new Color(d,102,51,51));
		case 7:
			return (new Color(d,204,204,204));
		case 8:
			return (new Color(d,51,51,51));
		case 9:
			return (new Color(d,0,255,255));
		case 10:
			return (new Color(d,0,204,51));
		case 11:
			return (new Color(d,0,204,204));
		case 12:
			return (new Color(d,255,102,102));
		case 13:
			return (new Color(d,255,153,255));
		case 14:
			return (new Color(d,255,255,0));
		case 15:
			return (new Color(d,255,255,255));
		default:
			return (new Color(d,0,102,0));
		}
	}
	
	private static Complex complex_pow3(Complex z) 
	{
		double im = z.getIm();
		double re = z.getRe();
		double re1 = re*(re*re-3*im*im);
		double im1 = im*(3*re*re-im*im);
		z.setRe(re1);
		z.setIm(im1);
		return z;
	}
	
	private static Color set_color(double re, double im, Display d, int iter)
	{
		Color rescolor = new Color(d,0,0,0); 
		if (Math.abs(re) < 7 || Math.abs(im) < 3)
			rescolor = set_palette(d,iter);
		else
			rescolor = set_palette(d, 15);
		
		return rescolor;
	}
	
	private static void drawBio2(Display d) 
	{
		Color current = new Color(d,0,0,0);
		//Complex t = new Complex(0,0);
		int X = WIDTH/2;
		int Y = HEIGHT/2;
		for (int y = -Y; y < Y; ++y)
		{
			for (int x = -X; x < X; ++x)
			{
				
				Complex z = new Complex (0.008*x,0.008*y);
				int n = 0;			
				
				while (n<10) 
				{
					z = complex_pow3(z);
					z = complex_pow3(z);
					
					double im = z.getIm();
					double re = z.getRe();
					re = re-0.9;
					im = im+0.18;
					z.setRe(re); z.setIm(im);
					
					if (re*re > 5) {
						current = set_color(re,im,d,n);
						break;
					}
					else if (im*im > 6) { 
						current = set_color(re,im,d,n);
						break;
					}
					n++;
				}
				graph.setForeground(current);
				graph.drawPoint(X+x,Y+y);
			}
		}
	}
	
	public Image draw (Display display, Canvas fractalPic){
    	im = new Image(display, WIDTH, HEIGHT);
    	//color = display.getSystemColor (SWT.COLOR_WHITE);
    	color = display.getSystemColor (SWT.COLOR_WHITE);
		color1 = display.getSystemColor (SWT.COLOR_BLACK);
		graph = new GC(im);
		graph.setForeground(color);
		graph.fillRectangle(im.getBounds ());
		graph.setForeground(color1);
		drawBio2(display);
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
