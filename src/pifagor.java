import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class pifagor extends stohastic{
	private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    private static GC graph;
    private static Image im;
    
    private int l;
    private int rec;
    private float n;
    private float m;
    
    public pifagor(int l, int rec, float n, float m) {
		this.l=l;
		this.rec=rec;
		this.n=n;
		this.m=m;
	}
	
    private void drawPifagor(Point c, float l, float a, int rec, float n, float m, Color color){
    	if (l>rec){
    		l*=0.7;
    		graph.setForeground(color);
    		graph.drawLine(c.x, c.y, (int)(c.x+l*Math.cos(a)), (int)(c.y-l*Math.sin(a)));
/*    		Path path = new Path(null);
            path.moveTo(c.x, c.y);
            path.lineTo((int)(c.x+l*Math.cos(a)), (int)(c.y-l*Math.sin(a)));
            path.lineTo(c.x, c.y);
            path.close();
            //graph.setForeground(color);
            graph.fillPath(path);*/
    		c.x+=l*Math.cos(a);
    		c.y-=l*Math.sin(a);
    		drawPifagor(new Point(c.x, c.y), l, (float) (a+4*Math.atan(1)/n), rec, n, m, color);
    		drawPifagor(new Point(c.x, c.y), l, (float) (a-4*Math.atan(1)/m), rec, n, m, color);
    		
    	}
    }
    
    public Image draw (Display display, Canvas fractalPic){
    	im = new Image(display, WIDTH, HEIGHT);
    	graph = new GC(im);
    	Color color = display.getSystemColor (SWT.COLOR_WHITE);
        Color color1 = display.getSystemColor (SWT.COLOR_DARK_RED);

    	graph.setBackground(color1);
	    graph.fillRectangle(im.getBounds ());
	    //graph.setForeground(color1);
	    Point mid = new Point(WIDTH/2, HEIGHT*3/4);
    	//drawPifagor(mid, 140, (float) (2*Math.atan(1)), 1, (float)5, (float)9);
	    this.drawPifagor(mid, l, (float) (2*Math.atan(1)), rec, n, m, color);
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