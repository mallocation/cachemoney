import java.io.*;

import org.apache.bcel.classfile.*;
import org.apache.bcel.generic.*;
import org.apache.bcel.Repository;
import org.apache.bcel.Constants;

/*
   The ModifyClass changes the method name "main" to "MAIN."
*/
public class ModifyClass {

  /* Throws IOException If there is an error reading from the class file.
  */
  public static void main(String[] argv) throws IOException{

      try{

      /* Load the class from CLASSPATH.
      */
      JavaClass classFile = Repository.lookupClass(argv[0]);
      ClassGen clazz = new ClassGen(classFile); 
      ConstantPoolGen cpg = clazz.getConstantPool();
      Method[] methods = clazz.getMethods();

      for (int i=0; i<methods.length; i++){
	  MethodGen mg = new MethodGen(methods[i], 
                    classFile.getClassName(),cpg);
	  
	  if(methods[i].getName().equals("main")){
		mg.setName("MAIN");
		methods[i]=mg.getMethod();
		clazz.setMethodAt(mg.getMethod(), i); 
	  }
      } 

//      clazz.getJavaClass().dump("Average.class");
      clazz.getJavaClass().dump(argv[0]+".class");
      } catch(Exception e) {System.err.println(e);}
  }
} 
