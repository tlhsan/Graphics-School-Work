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
public class FlagDrawing implements GLEventListener{
    
    static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
          //getting the capabilities object of GL2 profile
             
          
          FlagDrawing l = new FlagDrawing();
          //creating frame
          glcanvas.addGLEventListener(l);
          glcanvas.setSize(600,400);
          
          final JFrame frame = new JFrame ("straight Line");
          //adding canvas to frame
          frame.getContentPane().add(glcanvas);
          frame.setSize(frame.getContentPane().getPreferredSize());
          frame.setVisible(true);
          
       }
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
             gl.glBegin (GL2.GL_POINTS);//static field
             //Drawing The green part of the FLAG
             double y0 = -.5;
             double y1 = .5;
             gl.glColor3f(0f,.5f,0.0f); 
             while(y0<y1){
                 double x0 = -0.5;
                 double x1 = 0.5;
                 gl.glVertex2d(x0,y0);
                 while(x0<x1){
                   gl.glVertex2d(x0,y0);
                   x0+= .001;
                 }
                 y0+= .001;
             }
//             //Drawing the stick
//             y0 = -1.0;
//             gl.glColor3f(.5f,.5f,.5f);
//             while(y0<y1){
//               double x0 = -0.55;
//               double x1 = -0.5;
//               gl.glVertex2d(x0,y0);
//               while(x0<x1){
//                 gl.glVertex2d(x0,y0);
//                 x0+= .001;
//               }
//               y0+= .001;
//           }
             //Drawing The Circle
             gl.glColor3f(1f,0f,0f);
             double r = 0.15;                          
             while(r>=0){
                 double x = 0;
                 double y = r;
                 double dinit = 1 - y;
                 gl.glVertex2d(+x,y);
                 while(x<y){
                   if(dinit<0){
                       dinit += 2*x+.001;
                   }else{
                       dinit += 2*x-2*y+.001;
                       y -= .001;
                   }
                   x += .001;
                   gl.glVertex2d(x-0.1,y);
                   gl.glVertex2d(x-0.1,-y);
                   gl.glVertex2d(-x-0.1,y);
                   gl.glVertex2d(-x-0.1,-y);
                     gl.glVertex2d(y-0.1,x);
                     gl.glVertex2d(y-0.1,-x);
                     gl.glVertex2d(-y-0.1,x);
                   gl.glVertex2d(-y-0.1,-x);                 
               }
                 r -= .001;
             }
          /*gl.glVertex2d(0.5f,0.5f);
          gl.glVertex2d(0.5f,-0.5f);
          gl.glVertex2d(-0.5f,0.5f);
          gl.glVertex2d(-0.5f,-0.5f);*/
          gl.glEnd();
          
      
   }
   
   public void dispose(GLAutoDrawable arg0) {
      //method body
   }

   
   public void init(GLAutoDrawable drawable) {
      // method body
       //4. drive the display() in a loop
        }
   
   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
      // method body
   }
   //end of main
}//end of classimport javax.media.opengl.GL2;