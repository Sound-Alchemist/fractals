/*import java.awt.image.*;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
*/
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class MyShellClass{ /*Фасад*/

	  Fractal str;
	  Combo fractalTypes;
	  Canvas fractalPic;
	  Image pic;
	  Group parametrs;
	  int n;
	  
	  private static MyShellClass uniqueInstance = new MyShellClass(); /*Синглтон*/
	  private MyShellClass(){}
	  public static MyShellClass getInstance(){
		  return uniqueInstance;
	  }
	  
	  
	  public static void main(String[] args){
		
	    Display display = new Display();
	    Shell shell = new MyShellClass().createShell(display);
	    shell.open();
	    while (!shell.isDisposed()) 
	    {
	      if (!display.readAndDispatch())
	        display.sleep();
	    }
    	display.dispose ();
	  }
	  
	  public Shell createShell(final Display display){
		
		final Shell shell = new Shell(display);
		shell.setText("Fractals");
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3;
	    shell.setLayout(gridLayout);
	    
	    new Label(shell, SWT.NONE).setText("Type of fractals:");
	    fractalTypes = new Combo(shell, SWT.NONE);
	    fractalTypes.setItems(new String[] { "Algebraic", "Geometric", "Stahostic" });
	    fractalTypes.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
	    
	    Label label1 = new Label(shell, SWT.NONE);
	    label1.setText("Parametrs");
	    label1.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, false, false));
	    
	    
	    GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
	    gridData.horizontalSpan = 2; //занять 2 ячейки табл
	    new Label(shell, SWT.NONE).setText("Image:");
	    Group group = new Group (shell, SWT.NONE);
    	group.setLayout (new GridLayout());
	    fractalPic = new Canvas(group, SWT.BORDER);
	    gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
	    gridData.widthHint = 900;
	    gridData.heightHint = 700;
	    gridData.verticalSpan = 3;
	    fractalPic.setLayoutData(gridData);
	    
	    parametrs = new Group(shell, SWT.NONE);
	    gridLayout = new GridLayout();
	    parametrs.setLayout(new GridLayout());
	    
	    gridData = new GridData(GridData.FILL, GridData.FILL, true, false);
	    gridData.verticalSpan = 3;
	    parametrs.setLayoutData(gridData);
	    
	    final Composite contentPanel = new Composite(parametrs, SWT.BORDER);
	    final StackLayout layout = new StackLayout();
	    contentPanel.setLayout(layout);

	    // create the first page's content
	    final Composite page0 = new Composite(contentPanel, SWT.NONE);
	    page0.setLayout(new RowLayout());
	    final Button radioButton00 = new Button(page0, SWT.RADIO);
		radioButton00.setText("Ferns");
		Label NP = new Label(page0, SWT.NONE);
	    NP.setText("Width of the sheet n = ");
	    final Text NPText = new Text(page0, SWT.SINGLE | SWT.BORDER);
	    NPText.setMessage("     0.7     ");
		final Button radioButton001 = new Button(page0, SWT.RADIO);
		radioButton001.setText("Tree");
		final Button radioButton002 = new Button(page0, SWT.RADIO);
		radioButton002.setText("Pifagor");
		Label MM = new Label(page0, SWT.NONE);
	    MM.setText("Angle of right branch m = ");
	    final Text MMText = new Text(page0, SWT.SINGLE | SWT.BORDER);
	    MMText.setMessage("     9     ");
	    Label NN = new Label(page0, SWT.NONE);
	    NN.setText("Angle of left branch n = ");
	    final Text NNText = new Text(page0, SWT.SINGLE | SWT.BORDER);
	    NNText.setMessage("     5     ");
	    Label L = new Label(page0, SWT.NONE);
	    L.setText("Size l = ");
	    final Text LLText = new Text(page0, SWT.SINGLE | SWT.BORDER);
	    LLText.setMessage("     140     ");
	    Label R = new Label(page0, SWT.NONE);
	    R.setText("Recursion r = ");
	    final Text KKText = new Text(page0, SWT.SINGLE | SWT.BORDER);
	    KKText.setMessage("     1     ");
	    final Button yes0 = new Button(page0, SWT.PUSH);
	    yes0.setText("Draw");
	    gridData = new GridData(GridData.FILL, GridData.CENTER, true, true);
	    yes0.setLayoutData(gridData);
		page0.setLayout(new GridLayout());

		// create the second page's content
	    final Composite page1 = new Composite(contentPanel, SWT.NONE);
	    page1.setLayout(new RowLayout());
	    final Button radioButton0 = new Button(page1, SWT.RADIO);
		radioButton0.setText("Sierpinski triangles");
		final Button next = new Button(page1, SWT.PUSH);
	    next.setText("+");
	    final Button prev = new Button(page1, SWT.PUSH);
	    prev.setText("-");
	    gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
	    next.setLayoutData(gridData);
	    prev.setLayoutData(gridData);
		final Button radioButton01 = new Button(page1, SWT.RADIO);
		radioButton01.setText("Dragon");
		final Button next2 = new Button(page1, SWT.PUSH);
	    next2.setText("+");
	    final Button prev2 = new Button(page1, SWT.PUSH);
	    prev2.setText("-");
	    gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
	    next2.setLayoutData(gridData);
	    prev2.setLayoutData(gridData);
		final Button radioButton02 = new Button(page1, SWT.RADIO);
		radioButton02.setText("Koch snowflake");
		
		Label M = new Label(page1, SWT.NONE);
	    M.setText("Number of copies m = ");
	    final Text MText = new Text(page1, SWT.SINGLE | SWT.BORDER);
	    MText.setMessage("     5     ");
	    Label N = new Label(page1, SWT.NONE);
	    N.setText("Number of curvesn = ");
	    final Text NText = new Text(page1, SWT.SINGLE | SWT.BORDER);
	    NText.setMessage("     5     ");
	    Label K = new Label(page1, SWT.NONE);
	    K.setText("Nesting snowflakes k = ");
	    final Text KText = new Text(page1, SWT.SINGLE | SWT.BORDER);
	    KText.setMessage("     6     ");
	    final Button yes = new Button(page1, SWT.PUSH);
	    yes.setText("Draw");
	    gridData = new GridData(GridData.FILL, GridData.CENTER, true, true);
	    yes.setLayoutData(gridData);
	
		//radioButton02.setSelection(true);
		page1.setLayout(new GridLayout());
	    
	    final Composite page2 = new Composite(contentPanel, SWT.NONE);
	    page2.setLayout(new RowLayout());
	    
		
		final Button radioButton1 = new Button(page2, SWT.RADIO);
		radioButton1.setText("Biomorphic");
		final Button radioButton3 = new Button(page2, SWT.RADIO);
		radioButton3.setText("Biomorphic2");
		final Button radioButton = new Button(page2, SWT.RADIO);
		radioButton.setText("Mandelbrot set");
		final Button radioButton4 = new Button(page2, SWT.RADIO);
		radioButton4.setText("Sink");
		final Button radioButton2 = new Button(page2, SWT.RADIO);
		radioButton2.setText("Zhyuylya set");
		page2.setLayout(new GridLayout());
		Label J = new Label(page2, SWT.NONE);
	    J.setText("Point of Mondelbrota Re = ");
	    final Text JText = new Text(page2, SWT.SINGLE | SWT.BORDER);
	    JText.setMessage("     0.11     ");
	    Label JJ = new Label(page2, SWT.NONE);
	    JJ.setText("Point of Mondelbrota Im = ");
	    final Text JJText = new Text(page2, SWT.SINGLE | SWT.BORDER);
	    JJText.setMessage("     -0.66     ");
	    final Button yes1 = new Button(page2, SWT.PUSH);
	    yes1.setText("Draw");
	    gridData = new GridData(GridData.FILL, GridData.CENTER, true, true);
	    yes1.setLayoutData(gridData);

	    fractalTypes.addListener(SWT.Selection, new Listener() {
	      public void handleEvent(Event event) {
	    	String fractalsType = fractalTypes.getText().intern();
	    	if(fractalsType == "Algebraic"){
	    		final ArifmFractals arf = new ArifmFractals(); 
	    		layout.topControl = page2;
	    		radioButton.addSelectionListener(
	    				new SelectionAdapter(){
	    					public void widgetSelected(SelectionEvent e){
	    						yes1.addSelectionListener(new SelectionAdapter(){
	    	                		public void widgetSelected(SelectionEvent event){
	    	                			if(pic != null){
	    	                				pic = null;
	    	                				//fractalPic.redraw();	
	    	                			}
	    	                			arf.man.draw(display, fractalPic);
	    	                			fractalPic.redraw();
	    	                		}
	    						});
	    					}
	    				});
	    		radioButton1.addSelectionListener(
	    				new SelectionAdapter(){
	    					public void widgetSelected(SelectionEvent e){
	    						yes1.addSelectionListener(new SelectionAdapter(){
	    	                		public void widgetSelected(SelectionEvent event){
	    	                			if(pic != null){
	    	                				pic = null;
	    	                				//fractalPic.redraw();	
	    	                			}
	    	                			arf.bio.draw(display, fractalPic);
	    	                			fractalPic.redraw();
	    	                		}
	    						});
	    					}
	    				});
	    		radioButton2.addSelectionListener(
	    				new SelectionAdapter(){
	    					public void widgetSelected(SelectionEvent e){
	    						yes1.addSelectionListener(new SelectionAdapter(){
	    	                		public void widgetSelected(SelectionEvent event){
	    	                			String jstring = JText.getText(); double j = 0.11;
	    	                			char j1[] = jstring.toCharArray();
	    	                			if(j1.length != 0)
	    	                				j = Double.parseDouble(jstring);
	    	                			String jjstring = JJText.getText(); double jj = -0.66;
	    	                			char jj1[] = jjstring.toCharArray();
	    	                			if(jj1.length != 0)
	    	                				jj = Double.parseDouble(jjstring);
	    	                			if(pic != null){
	    	                				pic = null;
	    	                				//fractalPic.redraw();	
	    	                			}
	    	                			arf.jul.draw(display, fractalPic, j, jj);
	    	                			fractalPic.redraw();
	    	                		}
	    						});
	    					}
	    				});
	    		radioButton3.addSelectionListener(
	    				new SelectionAdapter(){
	    					public void widgetSelected(SelectionEvent e){
	    						yes1.addSelectionListener(new SelectionAdapter(){
	    	                		public void widgetSelected(SelectionEvent event){
	    	                			if(pic != null){
	    	                				pic = null;
	    	                				//fractalPic.redraw();	
	    	                			}
	    	                			arf.bio2.draw(display, fractalPic);
	    	                			fractalPic.redraw();
	    	                		}
	    						});
	    					}
	    				});
	    		radioButton4.addSelectionListener(
	    				new SelectionAdapter(){
	    					public void widgetSelected(SelectionEvent e){
	    						yes1.addSelectionListener(new SelectionAdapter(){
	    	                		public void widgetSelected(SelectionEvent event){
	    	                			if(pic != null){
	    	                				pic = null;
	    	                				//fractalPic.redraw();	
	    	                			}
	    	                			arf.sink.draw(display, fractalPic);
	    	                			fractalPic.redraw();
	    	                		}
	    						});
	    					}
	    				});
	    	}
	    	else if(fractalsType == "Geometric"){
	    		final Geometry_fractals gf = new Geometry_fractals();
	    		layout.topControl = page1;
	    		radioButton02.addSelectionListener(
	    				new SelectionAdapter() {
	    	                public void widgetSelected(SelectionEvent e) {
	    	                	yes.addSelectionListener(new SelectionAdapter(){
	    	                		public void widgetSelected(SelectionEvent event){
	    	                			int mm = 0, nn = 0, kk = 0;
	    	                			String mstring = MText.getText();
	    	                			char m[] = mstring.toCharArray();
	    	                			if(m.length != 0)
	    	                				mm = (int)(m[0]-48);
	    	                			else mm = 5;
	    	                			String nstring = NText.getText();
	    	                			char n[] = nstring.toCharArray();
	    	                			if(n.length != 0)
	    	                				nn = (int)(n[0]-48);
	    	                			else nn = 5;
	    	                			String kstring = KText.getText();
	    	                			char k[] = kstring.toCharArray();
	    	                			if(k.length != 0)
	    	                				kk = (int)(k[0]-48);
	    	                			else kk = 6;
	    	                			if(pic != null){
	    	                				pic = null;
	    	                				//fractalPic.redraw();	
	    	                			}
	    	                			pic = gf.snow.draw(display, fractalPic, mm, kk, nn);
	    	                			fractalPic.redraw();
	    	                		}
							    });
	    	                }
	    				});
	    		
	    		radioButton0.addSelectionListener(
	    				new SelectionAdapter() {
	    	                public void widgetSelected(SelectionEvent e) {
	    	                	n = 0;
	    	                	next.addSelectionListener(new SelectionAdapter(){
	    	    	         		public void widgetSelected(SelectionEvent event){
	    	    	         			if(n < 9) n += 1;
	    	    	               		if(pic != null){
	    	    	           				pic = null;
	    	    	                		//fractalPic.redraw();	
	    	    	                	}
	    	    	                	pic = gf.triangle.draw(display, fractalPic, n);
	    	    	                	fractalPic.redraw();
	    	    	                }
	    	                	});
	    	                	prev.addSelectionListener(new SelectionAdapter(){
	    	    	          		public void widgetSelected(SelectionEvent event){
	    	    	                	if(n > 1) n -= 1;
	    	    	                	if(pic != null){
	    	    	                		pic = null;
	    	    	                		//fractalPic.redraw();	
	    	    	                	}
	    	    	                	pic = gf.triangle.draw(display, fractalPic, n);
	    	    	                	fractalPic.redraw();
	    	    	               	}
	    	                	});
	    	                }
	    				});
	    		radioButton01.addSelectionListener(
	    				new SelectionAdapter() {
	    	                public void widgetSelected(SelectionEvent e) {
	    	                	n = 0;
	    	                	next2.addSelectionListener(new SelectionAdapter(){
	    	    	         		public void widgetSelected(SelectionEvent event){
	    	    	         			if(n < 15) n += 1;
	    	    	               		if(pic != null){
	    	    	           				pic = null;
	    	    	                		//fractalPic.redraw();	
	    	    	                	}
	    	    	            		pic = gf.dragon.draw(display, fractalPic, n);
	    	    	                	fractalPic.redraw();
	    	    	                }
	    	                	});
	    	                	prev2.addSelectionListener(new SelectionAdapter(){
	    	    	          		public void widgetSelected(SelectionEvent event){
	    	    	                	if(n > 1) n -= 1;
	    	    	                	if(pic != null){
	    	    	                		pic = null;
	    	    	                		//fractalPic.redraw();	
	    	    	                	}
	    	    	            		pic = gf.dragon.draw(display, fractalPic, n);
	    	    	                	fractalPic.redraw();
	    	    	               	}
	    	                	});

	    	                }
	    				});
	    	}
	    	else{
	    		//final stohastic st = new stohastic();
	    		layout.topControl = page0;

	    		radioButton00.addSelectionListener(
	    				new SelectionAdapter(){
	    					public void widgetSelected(SelectionEvent e){
	    						yes0.addSelectionListener(new SelectionAdapter(){
	    	                		public void widgetSelected(SelectionEvent event){
	    	                			String nstring = NPText.getText(); double nn = 0.7;
	    	                			char n[] = nstring.toCharArray();
	    	                			if(n.length != 0)
	    	                				nn = Double.parseDouble(nstring);
	    	                			if(pic != null){
	    	    	                		pic = null;
	    	    	                		//fractalPic.redraw();	
	    	    	                	}
	    	                			str = new fern(nn);
	    	    	            		pic = str.draw(display, fractalPic);
	    	    	                	fractalPic.redraw();
	    	                		}
	    						});
	    						
	    					}
	    				});
	    		radioButton001.addSelectionListener(
	    				new SelectionAdapter(){
	    					public void widgetSelected(SelectionEvent e){
	    						yes0.addSelectionListener(new SelectionAdapter(){
	    	                		public void widgetSelected(SelectionEvent event){
	    	                			if(pic != null){
	    	                				pic = null;
	    	                				//fractalPic.redraw();	
	    	                			}
	    	                			str = new tree();
	    	                			pic = str.draw(display, fractalPic);//.tr.draw(display, fractalPic);
	    	                			fractalPic.redraw();
	    	                		}
	    						});
	    					}
	    				});
	    		radioButton002.addSelectionListener(
	    				new SelectionAdapter(){
	    					public void widgetSelected(SelectionEvent e){
	    						yes0.addSelectionListener(new SelectionAdapter(){
	    	                		public void widgetSelected(SelectionEvent event){
	    	                			int mm = 0, nn = 0, kk = 0, ll = 0;
	    	                			String mstring = MMText.getText();
	    	                			char m[] = mstring.toCharArray();
	    	                			if(m.length != 0)
	    	                				mm = Integer.parseInt(mstring);
	    	                			else mm = 9;
	    	                			String nstring = NNText.getText();
	    	                			char n[] = nstring.toCharArray();
	    	                			if(n.length != 0)
	    	                				nn = Integer.parseInt(nstring);
	    	                			else nn = 5;
	    	                			String kstring = KKText.getText();
	    	                			char k[] = kstring.toCharArray();
	    	                			if(k.length != 0)
	    	                				kk = Integer.parseInt(kstring);
	    	                			else kk = 1;
	    	                			String lstring = LLText.getText();
	    	                			char l[] = lstring.toCharArray();
	    	                			if(l.length != 0)
	    	                				ll = Integer.parseInt(lstring);
	    	                			else ll = 140;
	    	                			if(pic != null){
	    	    	                		pic = null;
	    	    	                		//fractalPic.redraw();	
	    	    	                	}
	    	                			str = new pifagor(ll, kk, (float)nn, (float)mm);
	    	    	            		pic = str.draw(display, fractalPic);
	    	    	                	fractalPic.redraw();
	    	                		}
	    						});
	    					}
	    				});
	    	}
	    	
//			/*canvas.addPaintListener(new PaintListener(){
//    			public void paintControl(final PaintEvent event){
//    				if(pic != null){
//    					event.gc.drawImage(pic, 0, 0,0,0,0,0,0,0);
//    				}
//    			}
//    	    });*/
	    	contentPanel.layout();
	      }
	      
	    });
	    
	    Button enter = new Button(parametrs, SWT.PUSH);
	    enter.setText("Save");
	    gridData = new GridData(GridData.FILL, GridData.CENTER, true, true);
	    enter.setLayoutData(gridData);
	    
	    enter.addSelectionListener(new SelectionAdapter(){
	      public void widgetSelected(SelectionEvent event){
	        if(pic != null){
	        	String fileName = new FileDialog(shell).open();
	            if (fileName != null) {
	            	ImageLoader loader = new ImageLoader();
	            	loader.data = new ImageData[]{pic.getImageData()};
	            	loader.save(fileName, SWT.IMAGE_JPEG);
	            }
/*	        	File outputFile = new File("image.jpeg");
	        	BufferedImage bi = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB)
	        	bi.getGraphics().drawImage(pic, 0, 0, null);
	        	ImageIO.write(bi, "JPEG", outputFile);*/
	        }
	      }
	    });
	    
	    shell.pack();
	    
	    return shell;
	  }
}