package com.ui.qa.genes.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * 
 * @author Muzzamil
 * 
 * 
 * This class is data engine that responsible to collect all test data  files from "Test Data" folder and fetch test data from them
 * keep ready to utilize it in test cases.
 *
 */
public class PropertiesReader extends Initializer {
	
	
	
	public static void loadAllFiles(String filePath) {
		prop = new Properties();
		try (Stream<Path> walk = Files.walk(Paths.get(filePath))) {

			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			for (String file : result) {
				
				loadValues(file);
				System.out.println(file + " :  is loaded.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	public static void loadValues(String filePath) {
		  try (InputStream input = new FileInputStream(filePath)) {

	             

	            if (input.equals(null)) 
	            {
	                System.out.println("Sorry, unable to find properties file.");
	                return;
	            }

	            prop.load(input);
	            
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }

	    }	
	
}
