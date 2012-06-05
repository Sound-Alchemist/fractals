import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;


public class Koch_Snowflake {
	
	private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    private static GC graph;
    private static Image im;

    private static void drawKochCurve(Point p, Point q, int n, Color color, Display display){
        if(n == 0){
            graph.drawLine(p.x, p.y, q.x, q.y);
            return;
        }
        Point r = new Point((2*p.x + q.x)/3, (2*p.y + q.y)/3);
        Point s = new Point((int)((p.x + q.x)/2-(p.y - q.y)*Math.sqrt(3.0)/6), (int)((p.y + q.y)/2+(p.x - q.x)*Math.sqrt(3.0)/6));
        Point t = new Point((p.x + 2 * q.x)/3, (p.y + 2 * q.y)/3);
        Path path = new Path(null);
        path.moveTo(r.x, r.y);
        path.lineTo(s.x, s.y);
        path.lineTo(t.x, t.y);
        path.lineTo(r.x, r.y);
        path.close();
        graph.setBackground(color);
        //graph.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
        graph.fillPath(path);
        
        drawKochCurve(p, r, n - 1, color, display);
        drawKochCurve(r, s, n - 1, color, display);
        drawKochCurve(s, t, n - 1, color, display);
        drawKochCurve(t, q, n - 1, color, display);
    }

    private static void drawKochSnowflake(Point c, double d, int m, int n, Color color, Display display){
        Point[] vs = new Point[m];
        for (int i = 0; i < m; ++i) {
            vs[i] = new Point((int)(c.x + d*Math.cos(2*Math.PI/m*i)), (int)(c.y - d*Math.sin(2*Math.PI/m*i)));
        }
        Path path = new Path(null);
        path.moveTo(vs[0].x, vs[0].y);
        for (int i = 0; i < m; ++i) {
                path.lineTo(vs[(i + 1) % m].x, vs[(i + 1) % m].y);
        }
        path.close();
        graph.setBackground(color);
        graph.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
        graph.fillPath(path);
        for (int i = 0; i < m; ++i) {
            drawKochCurve(vs[(i + 1) % m], vs[i], n, color, display);
        }
    }
    
 /*   private static void drawKochAntiSnowflake(Point c, double d, int m, int n, Color color, Display display){
    	Point[] vs = new Point[m];
        for (int i = 0; i < m; ++i) {
   	        vs[i] = new Point((int)(c.x + d*Math.cos(2*Math.PI/m*i)), (int)(c.y + d*Math.sin(2*Math.PI/m*i)));
   	    }
   	    Path path = new Path(null);
   	    path.moveTo(vs[0].x, vs[0].y);
        for (int i = 0; i < m; ++i){
   	        path.lineTo(vs[(i + 1) % m].x, vs[(i + 1) % m].y);
   	    }
        path.close();
        graph.setForeground(color);
        graph.fillPath(path);
        for (int i = 0; i < m; ++i){
        	drawKochCurve(vs[(i + 1) % m], vs[i], n, color, display);
    	}
    }*/
    
    private static void drawKochMegaSnowflake(Point c, double d, int m, int k, int n, Color bc, Color ec, Display display){
    	for (int i = 0; i < k; ++i){
    		Color color = new Color(null,
    				bc.getRed() + (ec.getRed() - bc.getRed()) * i / k,
    	            bc.getGreen() + (ec.getGreen() - bc.getGreen()) * i / k,
    	            bc.getBlue() + (ec.getBlue() - bc.getBlue()) * i / k);
    	        drawKochSnowflake(c, d*(k - i) / k, m, n, color, display);
    	    }
    	}
    
    public Image draw(Display display, Canvas fractalPic, int m, int k, int n){
        im = new Image(display, WIDTH, HEIGHT);
        Color color = display.getSystemColor(SWT.COLOR_BLUE);
        Color color1 = display.getSystemColor(SWT.COLOR_RED);
        //Color color2 = display.getSystemColor(SWT.COLOR_RED);
    	graph = new GC(im);
    	graph.setBackground(color);
    	graph.fillRectangle(im.getBounds ());
    	//drawKochSnowflake(new Point(WIDTH/2, HEIGHT/2), WIDTH/3, 2, 5, color);
    	//drawKochAntiSnowflake(new Point(WIDTH/2, HEIGHT/2), WIDTH/3, 3, 4, color);
    	drawKochMegaSnowflake(new Point(WIDTH/2, HEIGHT/2), WIDTH/3, m, k, n, color1, color, display);
    	graph.dispose();
    	
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
