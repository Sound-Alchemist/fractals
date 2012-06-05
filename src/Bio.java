import java.util.Random;
import org.eclipse.swt.*;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Bio {
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
	
	private static void drawBio (Display d) 
	{
		int max = 50;
		int max_iter = 50;
		Complex t = new Complex(0,0);
		int X = WIDTH/2;
		int Y = HEIGHT/2;
		for (int y = -Y; y < Y; ++y)
		{
			for (int x = -X; x < X; ++x)
			{
				Complex z = new Complex (x*0.01,y*0.01);
				Complex c = new Complex(1.00003,1.01828);
				int n = 0;
				while ( (Math.pow(z.getRe(),2) < max) && (Math.pow(z.getIm(),2) < max) && (n < max_iter) )
				{
					t.setRe(z.getRe());
					t.setIm(z.getIm());
					z.setRe(Math.pow(t.getRe(),4)+Math.pow(t.getIm(),4) - 6*Math.pow(t.getRe(),2)*Math.pow(t.getIm(),2) + c.getRe());
					z.setIm(4*Math.pow(t.getRe(),3)*t.getIm()-4*Math.pow(t.getIm(),3)*t.getRe() + c.getIm());
					n++;
				}
				if (Math.abs(z.getRe())>10 || Math.abs(z.getIm())>1000 )
				{
					int col = n % max;
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
		graph.setForeground(color);
		graph.fillRectangle(im.getBounds ());
		graph.setForeground(color1);
		drawBio(display);
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
