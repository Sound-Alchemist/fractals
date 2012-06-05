import java.util.Random;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class tree extends stohastic {
	private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    public static Random rand = new Random();
    private static GC graph;
    private static Image im;
    
    public tree(){}
	
	private void tdraw(Point c, double d, int l, Color c1, Color c2, Color c3){
		   // 	c.y+=10;
		    	int x1, y1, p, s, i;
		    	float a1;
		    	if(l<15) return;
		    	x1=(int)(c.x+l*Math.cos(d));
		    	y1=(int)(c.y+l*Math.sin(d));
//		    	if (l>200) p=200;
//		    	else p=10;
		    	p=l;
//		    	System.out.print(p);
		    	if (p<40){
		    		if (rand.nextFloat() > 0.5)
		    			graph.setForeground(c1);
		    		else graph.setForeground(c2);
		    		for (i=0; i<3; i++)
		    			graph.drawLine(c.x+i, c.y, x1, y1);
		    	}
		    	else{
		    		graph.setForeground(c3);
		    		for (i=0; i<p/6; i++){
		    			graph.drawLine(c.x+i-p/12, c.y, x1, y1);
		    		}
		    	}
		    	for (i=0; i<40-rand.nextInt(10); i++){
		    		s=rand.nextInt(l-l/6)+(l/6);
		    		a1 = (float) (d + 1.6 * (0.5 - rand.nextFloat()));// {Угол наклона веток}
		    		x1 = (int)(c.x + s * Math.cos(d));
		    		y1 = (int)(c.y + s * Math.sin(d));
		    		tdraw(new Point(x1, y1), a1, p - 15 - rand.nextInt(30), c1, c2, c3);// {Чем меньше вычетаем, тем пышнее дерево}
		    	}
		    	
	}
	public Image draw (Display display, Canvas fractalPic){
    	im = new Image(display, WIDTH, HEIGHT);
    	graph = new GC(im);
    	Color color = display.getSystemColor (SWT.COLOR_WHITE);
        Color color1 = display.getSystemColor (SWT.COLOR_BLACK);
    	Color color2 = display.getSystemColor (SWT.COLOR_DARK_GREEN);
        Color color3 = display.getSystemColor (SWT.COLOR_GREEN);
        Color color4 = display.getSystemColor (SWT.COLOR_DARK_CYAN);

    	graph.setForeground(color);
	    graph.fillRectangle(im.getBounds ());
	    graph.setForeground(color1);
	    Point mid = new Point(WIDTH/2, HEIGHT*3/4);
	    this.tdraw(mid, 6*Math.atan(1.0), 100, color2, color3, color4);
    	//tdraw(mid, 6*Math.atan(1.0), 100, color2, color3, color4);
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