package DDA;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.lang.Math;
import javax.swing.JFrame;

class ThirdGLEventListener implements GLEventListener {

    /**
     * Interface to the GLU library.
     */
    private GLU glu;

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        /*
		 * put your code here
         */
        //drawLineC(gl,2,2,8,5);

        //drawLineC(gl, 0, 0, 3);
        drawCircle(gl, 0, 0, 50);

        // drawLine(gl, 0, 0, -100, -100);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
            int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
            boolean deviceChanged) {
    }

    

      

    
       
    private void drawCircle(GL2 gl, int x_centre, int y_centre, int radius) {
    	gl.glPointSize(1.0f);
        gl.glBegin(GL2.GL_POINTS);// begin plotting points
    	
    	 
        int x = radius, y = 0;
     
        // Printing the initial point
        // on the axes after translation
//        System.out.print("(" + (x + x_centre) 
//                + ", " + (y + y_centre) + ")");
     
        // When radius is zero only a single
        // point will be printed
        if (radius > 0) {
             
//            System.out.print("(" + (x + x_centre) 
//                + ", " + (-y + y_centre) + ")");
            gl.glVertex2i((x + x_centre), (-y + y_centre));
                 
//            System.out.print("(" + (y + x_centre) 
//                 + ", " + (x + y_centre) + ")");
            gl.glVertex2i((y + x_centre), (x + y_centre));
                  
//            System.out.println("(" + (-y + x_centre)
//                   + ", " + (x + y_centre) + ")");
            gl.glVertex2i((-y + x_centre), (x + y_centre));
        }
     
        // Initialising the value of P
        int P = 1 - radius;
        while (x > y) {
             
            y++;
         
            // Mid-point is inside or on the perimeter
            if (P <= 0)
                P = P + 2 * y + 1;
         
            // Mid-point is outside the perimeter
            else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }
         
            // All the perimeter points have already 
            // been printed
            if (x < y)
                break;
         
            // Printing the generated point and its 
            // reflection in the other octants after
            // translation
//            System.out.print("(" + (x + x_centre) 
//                    + ", " + (y + y_centre) + ")");
            gl.glVertex2i((x + x_centre), (y + y_centre));
                     
//            System.out.print("(" + (-x + x_centre) 
//                    + ", " + (y + y_centre) + ")");
            gl.glVertex2i((-x + x_centre), (y + y_centre));
                     
//            System.out.print("(" + (x + x_centre) + 
//                    ", " + (-y + y_centre) + ")");
            gl.glVertex2i((x + x_centre), (-y + y_centre));
                     
//            System.out.println("(" + (-x + x_centre) 
//                    + ", " + (-y + y_centre) + ")");
            gl.glVertex2i((-x + x_centre), (-y + y_centre));
         
            // If the generated point is on the 
            // line x = y then the perimeter points
            // have already been printed
            if (x != y) {
                 
//                System.out.print("(" + (y + x_centre)
//                      + ", " + (x + y_centre) + ")");
                gl.glVertex2i((y + x_centre), (x + y_centre));
                       
//                System.out.print("(" + (-y + x_centre) 
//                      + ", " + (x + y_centre) + ")");
                gl.glVertex2i((-y + x_centre), (x + y_centre));
                       
//                System.out.print("(" + (y + x_centre) 
//                      + ", " + (-x + y_centre) + ")");
                gl.glVertex2i((y + x_centre), (-x + y_centre));
                       
//                System.out.println("(" + (-y + x_centre) 
//                    + ", " + (-x + y_centre) +")");
                gl.glVertex2i((-y + x_centre), (-x + y_centre));
            }
        }
        int mx = 250, my = 150;

        for (int i = -mx; i <= mx; i++) {
            gl.glVertex2i(i, 0);
        }

        for (int j = -my; j <= my; j++) {
            gl.glVertex2i(0, j);
        }

        gl.glEnd();// end drawing of points
    
    	
    }

   
        
       
        

    public void dispose(GLAutoDrawable arg0) {

    }
}

public class DrawCircle {

    public static void main(String args[]) {
        // getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        ThirdGLEventListener b = new ThirdGLEventListener();
        // ThirdGLEventListener b1=new ThirdGLEventListener();
        glcanvas.addGLEventListener(b);
        // glcanvas.addGLEventListener(b1);
        glcanvas.setSize(400, 400);
        // creating frame
        final JFrame frame = new JFrame("Basic frame");
        // adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}
