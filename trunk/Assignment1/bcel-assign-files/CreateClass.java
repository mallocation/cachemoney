import java.io.*;

import org.apache.bcel.generic.*;  // The dynamic component of the BCEL API
import org.apache.bcel.Constants;

/* 
   The CreateClass creats a NewClass.class using the BCEL API.
*/

public class CreateClass {

  /* Throws IOException If there is an error reading from the class file.
  */
  public static void main(String[] argv) throws IOException{

      int flag = Constants.ACC_PUBLIC; 

      /* Create a ClassGen object.
      */
      ClassGen cg = new ClassGen("NewClass", "java.lang.Object", 
                    "NewClass.java", flag, null);

      ConstantPoolGen cp = cg.getConstantPool();

      Type fType = Type.INT;

      /* Create a FieldGen with "newField" name.
      */ 
      FieldGen fg = new FieldGen(flag, fType, "newField", cp);
      cg.addField(fg.getField());
      try{
          /* Dump the class to "NewClass.class."  
	  */
          cg.getJavaClass().dump("NewClass.class");
      } catch(java.io.IOException e) {System.err.println(e);}
  }
} 
