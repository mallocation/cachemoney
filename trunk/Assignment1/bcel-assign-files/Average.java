import java.io.*;
import java.util.*;


/** This program is a test program to use with your ChangeOp program
 *  <p>
 *  It reads in a series of numbers from a file and finds their 
 *  <ol>
 *  <li>average
 *  <li>negative average 
 *  <li>average of squares </ol>
 * <br> The <b>average</b> is computed by summing the nubmers and dividing by N.
 * <br> The <b>negative average</b> is computed by subtracting the numbers and dividing by N. The initial number is subtracted from zero making the negative sum equal to the average times 
minus one.  If this does not hold at the end of the program a message is output indicating 
that an error has occured. 
 * <br> The <b> average of squares</b> is computed by squaring each number, summing the squares and dividing by N. 
 * <br> The <b> remainder of average</b> is computed by summing the numbers, dividing by N, and taking the remainder. 
 * <p>JavaDoc note: the javadoc comments must be placed in specific locations in the code 
 *                -- see javdoc documentation for more info.
 * @author Hyunsook Do
 * @version 1.0
 * 
 */




public class Average{



/** This method prints a usage message to standard output and exits the program with 
    a return value of 1. 
 *  It takes no agruments.
 */
    private static void printUsage() {
        System.err.println("Usage: Average <input-file-name>");
        System.exit(1);
    }

/** This method reads the numbers from a BufferedReader object and places them in a vector. 
    It expects to have an open buffered reader object passed in. It returns nothing. 
 * @param br the buffered reader object 
 * @param v the empty vector that will contain the numbers at the end of the method
 */
    public void readNumbers(BufferedReader br, Vector v) throws IOException{
        boolean eof = false;
        int i =0;
        while (!eof){
            String line = br.readLine();
            if(line == null)
                eof = true;
            else{
                v.add(line);
                i++;
            }
        }

    }

    /** Main function
     * @param argv the name of the input file containing the numbers to be averaged
    */
    public static void main (String[] argv) throws IOException{
    
  
        Vector numbers = new Vector();  // will hold numbers to be averaged 

	// if the wrong number of arguments are passed in print a usage message
        if(argv.length !=1)
            printUsage();

        // create an average class
        Average avg = new Average();
        //open the file
        BufferedReader br = new BufferedReader(new FileReader(argv[0]));
        //read in the numbers
	avg.readNumbers(br, numbers);

        int sum =0;
        int neg_sum =0;
        int sq_sum =0;
	int rem = 0;
        double average =0;
        double neg_average =0;
        double sq_average =0;
	Integer s2i = null;
    
	// this loop performs the calculations.

 	for(int i=0; i< numbers.size(); i++){
	    String sNum = (String)numbers.get(i);
	    int num = s2i.parseInt(sNum);
	    // for average
	    sum += num;   
            // for neg average      
	    neg_sum -= num; 
            // for squares average
	    sq_sum += num*num;
        }

        if (numbers.size() == 0){    // to prevent div 0  
            average = 0;      
            neg_average = 0;      
            sq_average = 0;      
	    rem = 0;
        } else{
            average = (double) sum/numbers.size();
            neg_average = (double) neg_sum/numbers.size();
            sq_average = (double) sq_sum/numbers.size();
	    rem = sum % numbers.size();
        }

        // output a message if the average and negative average are a mismatch
	if(average != neg_average * (-1)){
            System.err.println("The average function is not correct!");
	}
            System.out.println("The average is "+ average);
            System.out.println("The negative average is "+ neg_average);
            System.out.println("The average of squares is "+ sq_average);
            System.out.println("The remainder of average is "+ rem);
    }

}
