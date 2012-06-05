import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class fern extends stohastic{
	private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    public static Random rand = new Random();
    private static GC graph;
    private static Image im;
    private int it=200000;
    private double n;
    
    public fern(double n){
    	this.n = n;
    }
	
    private void drawfern(Point c, int iter, double n, Color color){
    	int mid_x = c.x;
    	int mid_y = c.y - 100;
    	int radius = (int)(0.15 * mid_y);
    	float x = 1;
    	float y = 0;
    	float p, t;
    	
    	graph.setForeground(color);
    	
    	for (int k=0; k<iter; k++){
    		p = rand.nextFloat();
//    		System.out.print(p);
    		t = x;
    		if (p<=0.85){
    			x = (float)(0.84 * x - 0.045 * y);
    			y = (float)(0.045 * t + 0.86 * y + 1.6);
    		}
    		else 
    			if (p<=0.92) {
    				x = (float) (0.25*x - 0.26*y);
    				y = (float) (0.23*t + 0.25*y + 1.6);
    			}
    			else
    				if (p<0.99){
    					x= (float)(-0.135*x + 0.28*y);
    					y= (float)(0.26*t+0.245*y+0.44);
    				}
    				else{
    					x = 0;
    					y = (float)(0.16*y);
    				}
    		graph.drawPoint((int)(mid_x+(int)(radius*x)*n), (int) ((mid_y-(int)(radius*y)+300)*0.7));
    	}
    	
    }
    
    
    public Image draw (Display display, Canvas fractalPic){
    	im = new Image(display, WIDTH, HEIGHT);
    	graph = new GC(im);
    	Color color = display.getSystemColor (SWT.COLOR_BLACK);
    	Color color2 = display.getSystemColor (SWT.COLOR_DARK_GREEN);

    	graph.setBackground(color);
	    graph.fillRectangle(im.getBounds ());
	    Point mid = new Point(WIDTH/2, HEIGHT*3/4);
    	this.drawfern(mid, it, n, color2);
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