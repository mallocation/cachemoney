import java.io.*;

import org.apache.bcel.classfile.*;  // The static component of the BCEL API 
import org.apache.bcel.Repository;

/* The ByteViewer is used to display a human-readable listing of the
   compiled Java bytecode for a class.
*/ 

public class ByteViewer {

  /* Throws IOException If there is an error reading from the class file.
  */
  public static void main(String[] argv) throws IOException {

    try {
      /* Load the class from CLASSPATH. 
      */
      JavaClass       clazz   = Repository.lookupClass(argv[0]);

      /* Get data in a method. 
      */ 
      Method[]        methods = clazz.getMethods();

      System.out.println(clazz);
      for(int i=0; i < methods.length; i++) {
	  System.out.println(methods[i]);
	  Code code = methods[i].getCode();
	  if(code != null)
	      System.out.println(code);
      }
    } catch (Exception e) { e.printStackTrace(); } 
  }
}
