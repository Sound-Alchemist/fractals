import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

public class Sierpinski_triangle  {
	private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    private static GC graph;
    private static Image im;

    private static void SierpinskyTriangle(int order, Point p, int len, Color color){
    	Point r = new Point((p.x+len/2), (int)(p.y-len*Math.sqrt(3.0)/2));
        Point s = new Point((p.x+len), p.y);
        Point t = new Point(p.x, p.y);
        if(order == 0){
        	graph.drawLine(t.x, t.y, r.x, r.y);
            graph.drawLine(r.x, r.y, s.x, s.y);
            graph.drawLine(s.x, s.y, t.x, t.y);
            return;
        }
        Path path = new Path(null);
        path.moveTo(r.x, r.y);
        path.lineTo(s.x, s.y);
        path.lineTo(t.x, t.y);
        path.lineTo(r.x, r.y);
        path.close();
        graph.setForeground(color);
        graph.fillPath(path);

    	if(order > 0){
    		Point rr = new Point((p.x+len/4), (int)(p.y-len*Math.sqrt(3.0)/4));
            Point ss = new Point((p.x+3*len/4), (int)(p.y-len*Math.sqrt(3.0)/2));
            Point tt = new Point(p.x+len/2, p.y);
            path.moveTo(rr.x, rr.y);
            path.lineTo(ss.x, ss.y);
            path.lineTo(tt.x, tt.y);
            path.lineTo(rr.x, rr.y);
            path.close();
            //graph.setForeground(color);
            graph.fillPath(path);

            SierpinskyTriangle(order - 1,t,len/2, color); 
            SierpinskyTriangle(order - 1,tt,len/2, color); 
            SierpinskyTriangle(order - 1,rr,len/2, color);
    	}
        
    }

    public Image draw (Display display, Canvas fractalPic, int n){
    	im = new Image(display, WIDTH, HEIGHT);
        Color color = display.getSystemColor(SWT.COLOR_BLACK);
        Color color1 = display.getSystemColor(SWT.COLOR_YELLOW);
    	graph = new GC(im);
    	graph.setBackground(color);
    	graph.fillRectangle(im.getBounds ());
    	SierpinskyTriangle(n, new Point(100, 650), HEIGHT, color1);
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
