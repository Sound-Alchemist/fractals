import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Dragon {
	private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    private static GC graph;
    private static Image im;
    
    private static void ris(Point p, Point q, int k, Color color){
    	if(k == 0){
            graph.drawLine(p.x, p.y, q.x, q.y);
            return;
        }
    	Point n = new Point((p.x+q.x)/2 +(q.y-p.y)/2, (p.y+q.y)/2 -(q.x-p.x)/2); 
    	Path path = new Path(null);
    	path.moveTo(p.x, p.y);
    	path.lineTo(n.x, n.y);
        path.lineTo(q.x, q.y);
        path.lineTo(n.x, n.y);
        path.lineTo(p.x, p.y);
        path.close();
        graph.setForeground(color);
        //graph.setBackground(color);
        graph.fillPath(path);
    	ris(p, n, k-1, color); 
  	    ris(q, n, k-1, color); 
    }
    

    
    public Image draw (Display display, Canvas fractalPic, int n){
    	im = new Image(display, WIDTH, HEIGHT);
        Color color = display.getSystemColor (SWT.COLOR_GREEN);
        Color color1 = display.getSystemColor (SWT.COLOR_BLACK);
    	graph = new GC(im);
    	graph.setBackground(color1);
    	graph.fillRectangle(im.getBounds ());
    	ris(new Point(200,300), new Point(600,400), n, color); 
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

