/**
 * Level 0 Junior consultan exercise
 * 
 * Exercise 2
 * 
 * To test this program a series of JUnit integration tests have been made. These tests run the program with the same
 * input data as given by the examples.
 * 
 * This tests has been developed with Eclipse IDE for Java developers version 2019-12 (4.14.0)
 * and with JUnit 4.
 *  
 * @author Francisco García Navarrete
 * 
 * */

package tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class TicketTest extends TestCase {

	/**
	 * Creates the ticket given in the first example.
	 * @result Shows the ticket with the same result as in the first example.
	 * */
	@Test
	public void test1() {
		String p1 = "libro\n1\n12,49\ns\nn\ns\n";
		String p2 = "CD de música\n1\n14,99\nn\nn\ns\n";
		String p3 = "barrita de chocolate\n1\n0,85\ns\nn\n";
		
		InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream((p1 + p2 + p3 + "\nn\nn\n").getBytes()));
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
		
        main.launcher.Main.main(null);
        
        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString();
        
        System.out.println(outputText);
        
        String output = "1 libro: 12.49 €\n1 CD de música: 16.49 €\n1 barrita de chocolate: 0.85 €\n"
        		+ "Impuestos sobre las ventas: 1.50 €\nTotal: 29.83 €\n¿Desea crear otro ticket? (s/n) ";
        
		Assert.assertEquals(output, outputText.substring(outputText.indexOf("1 libro")).replace("\r", ""));
	}
	
	/**
	 * Creates the ticket given in the second example.
	 * @result Shows the ticket with the same result as in the second example.
	 * */
	@Test
	public void test2() {
		String p1 = "caja de bombones\n1\n10,00\ns\ns\ns\n";
		String p2 = "frasco de perfume\n1\n47,50\nn\ns\n";
		
		InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream((p1 + p2 + "\nn\nn\n").getBytes()));
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
		
        main.launcher.Main.main(null);
        
        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString();
        
        System.out.println(outputText);
        
        String output = "1 caja de bombones importado: 10.50 €\n1 frasco de perfume importado: 54.65 €\n"
        		+ "Impuestos sobre las ventas: 7.65 €\nTotal: 65.15 €\n¿Desea crear otro ticket? (s/n) ";
        
		Assert.assertEquals(output, outputText.substring(outputText.indexOf("1 caja")).replace("\r", ""));
	}
	
	/**
	 * Creates the ticket given in the third example.
	 * @result Shows the ticket with the same result as in the third example.
	 * */
	@Test
	public void test3() {
		String p1 = "frasco de perfume\n1\n27,99\nn\ns\ns\n";
		String p2 = "frasco de perfume\n1\n18,99\nn\nn\ns\n";
		String p3 = "caja de pastillas para el dolor de cabeza\n1\n9,75\ns\nn\ns\n";
		String p4 = "caja de bombones\n1\n11,25\ns\ns\n";
		
		InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream((p1 + p2 + p3 + p4 + "\nn\nn\n").getBytes()));
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);
		
        main.launcher.Main.main(null);
        
        System.setIn(stdin);
        System.setOut(stdout);

        String outputText = byteArrayOutputStream.toString();
        
        System.out.println(outputText);
        
        String output = "1 frasco de perfume importado: 32.19 €\n1 frasco de perfume: 20.89 €\n"+
        			"1 caja de pastillas para el dolor de cabeza: 9.75 €\n1 caja de bombones importado: 11.85 €\n"
        		+ "Impuestos sobre las ventas: 6.70 €\nTotal: 74.68 €\n¿Desea crear otro ticket? (s/n) ";
        
		Assert.assertEquals(output, outputText.substring(outputText.indexOf("1 frasco")).replace("\r", ""));
	}
}
