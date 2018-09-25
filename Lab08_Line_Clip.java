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

public class Lab08_Line_Clip implements GLEventListener{
	
	static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    
   public static void main(String[] args) {
	   Lab08_Line_Clip l = new Lab08_Line_Clip();
	      //creating frame
	      glcanvas.addGLEventListener(l);
	      glcanvas.setSize(1000, 1000);
	      
	      final JFrame frame = new JFrame ("straight Line");
	      //adding canvas to frame
	      frame.getContentPane().add(glcanvas);
	      frame.setSize(frame.getContentPane().getPreferredSize());
	      frame.setVisible(true);
	      
	   }
   
   
   int INSIDE = 0;
   int TOP= 8;
   int BOTTOM= 4;
   int LEFT = 1 ;
   int RIGHT = 2 ;
   
   float  xMax = 0.5f;
   float  xMin = -0.5f;
   float  yMax = 0.5f;
   float  yMin = -0.5f;
   
   
   public int ComputeOutcode(double x , double y ) {
	  int c =0 ;
	  if(y>yMax)c=8;
	    if(y<yMin)c=4;
	    if(x>xMax)c=c|2;
	    if(x<xMin)c=c|1;
		  return c ; 
   }
   
   void Cohen_line(float x0 ,float y0 ,float x1 ,float y1 ,GL2 gl   ) {
	   int c1 = ComputeOutcode(x0,y0);
	   int c2= ComputeOutcode(x1,y1 );
	   
	   float m = (y1-y0)/(x1-x0);
	   float x = x0; 
	   float y = y0;
	  // int c ;
	   
	   while(true) {
		   if((c1 & c2) != 0   ) {
			   System.out.println("REjected casue both outside");
			   break ;
		   }else if((c1 |c2)== 0 ) {
			   // drawline 
				  gl.glBegin (GL2.GL_LINE_LOOP);//static field
		          gl.glVertex2d(xMin, yMin);
		          gl.glVertex2d(xMin,yMax );
		          gl.glVertex2d(xMax,yMax);
		          gl.glVertex2d(xMax,yMin );
		          gl.glEnd();
		          gl.glColor3f(1.0f, 0.0f, 0.0f);
		          gl.glBegin(GL2.GL_LINES);
		          gl.glVertex2d(x0,y0);
		          gl.glVertex2d(x1,y1);
		          gl. glEnd();
		         
		          

			   
			   
			   break ;
		   }else {
			   int c = (c1 != 0 ) ? c1 : c2;
			   
			  
			   if((c & TOP)!= 0 ) {
				   x = x0 + (x1 - x0) * (yMax - y0) / (y1 - y0);
				   y = yMax;
				   
			   }else  if ((c & BOTTOM) != 0   ) {
				   x = x0 + (x1 - x0) * (yMin - y0) / (y1 - y0);
				   y = yMin;
			   }
			    else if((c & RIGHT)!= 0  ) {
			    	 y = y0 + (y1 - y0) * (xMax - x0) / (x1 - x0);
			    	 x = xMax;
				   
			   }else {
				   y = y0 + (y1 - y0) * (xMin - x0) / (x1 - x0);
				   x = xMin;
			   }
			   
			   if(c== c2) {
				   x1 = x;
				   y1 = y;
				   c2 = ComputeOutcode(x1, y1);
				   
			   }else {
				   x0 = x ;
				   y0 = y ;
				   c1= ComputeOutcode(x0, y0);
				   
			   }
		   }
		   
		
	   }
   }
   
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
       float x0 = -0.8f;
       float y0 = 0.8f;
       float x1 = 0.2f;
       float y1 = 0.2f;
       
       gl.glBegin(GL2.GL_LINES);
       gl.glVertex2d(x0,y0);
       gl.glVertex2d(x1,y1);
       gl. glEnd();
       
      Cohen_line(x0,y0, x1 ,y1 ,gl)   ;
      

     
      
      
      
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
