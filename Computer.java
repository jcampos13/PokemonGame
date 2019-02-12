package project2;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

/** 
 * Computer class-represents a computer object
 * @author julian
 */

public class Computer {
    
    /** The map, which holds a patterns and a value*/
    private HashMap<Pattern, Integer> patterns;
    
 /** 
 * constructor-creates a computer with a map
 */
    public Computer()
    {
        patterns=new HashMap<Pattern, Integer>();     
    }
    
/** 
 * makes predictions based off of items in the hash map
 * @param p the users new choice plus their previous three
 * @return the counter to the opponents choice
 */
    public char makePrediction(String p)
    {            
        char charArray[]=new char[]{'F','G','W'};
        int rnd = new Random().nextInt(charArray.length);
        boolean isEmpty=patterns.isEmpty();
      
        if(isEmpty==true)
        {
            if(p.length()<4)                           
            return charArray[rnd];
        }        
                                  
        else if(isEmpty==false)
        {           

                Pattern pat1 = new Pattern(p.substring(0, 3) + "G");
                Pattern pat2 = new Pattern(p.substring(0, 3) + "W");
                Pattern pat3 = new Pattern(p.substring(0, 3) + "F");
                int pattern1 = 0;
                int pattern2 = 0;
                int pattern3 = 0;

                if (patterns.containsKey(pat1)) {
                    pattern1 = patterns.get(pat1);
                    //System.out.println(pattern1);
                }

                if (patterns.containsKey(pat2)) {
                    pattern2 = patterns.get(pat2);
                    //System.out.println(pattern2);
                }

                if (patterns.containsKey(pat3)) {
                    pattern3 = patterns.get(pat3);
                    //System.out.println(pattern3);
                }

                if (pattern1 > pattern2 && pattern1 > pattern3) 
                {
                    return 'F';
                }
                if (pattern2 > pattern1 && pattern2 > pattern3) 
                {
                    return 'G';
                }
                if (pattern3 > pattern1 && pattern3 > pattern2) 
                {
                    return 'W';
                }
                if (pattern1 > pattern3 && pattern1 > pattern2) 
                {
                    return 'F';
                }
                if (pattern2 > pattern3 && pattern2 > pattern1) 
                {
                    return 'G';
                }
                if(pattern3 > pattern2 && pattern3 > pattern1)
                {
                    return 'W';
                }
                
                if(pattern1==pattern2 && pattern2==pattern3)
                {
                    return charArray[rnd];
                }
                if(pattern3==pattern2)
                {
                    char fiftyFif[]=new char[]{'W','G'};
                    int rand = new Random().nextInt(charArray.length);
                    return fiftyFif[rand];
                }
                
                if(pattern3==pattern1)
                {
                    char fiftyFif[]=new char[]{'W','F'};
                    int rand = new Random().nextInt(charArray.length);
                    return fiftyFif[rand];
                }
                
                if(pattern2==pattern1)
                {
                    char fiftyFif[]=new char[]{'G','F'};
                    int rand = new Random().nextInt(charArray.length);
                    return fiftyFif[rand];
                }  
                
        }
        return charArray[rnd];
    }
    
/** 
 * stores new patterns if they don't already exist in map
 * @param p the users new choice plus their previous three
 */
    public void storePattern(String p)
    {
        Pattern pattern=new Pattern(p);
        
        if(patterns.containsKey(pattern))
        {
            patterns.put(pattern, patterns.get(pattern)+1);
        }  
        
        else
        {
            patterns.put(pattern, 1);
        }   
    }

/** 
 * saves the map to a text file for later use
 * @param f the file which it is saved to
 */    
    public void saveMapToFile(File f)
    {
        try
        {
            PrintWriter writer=new PrintWriter(f);
            for(Pattern pattern:patterns.keySet())
            {
                writer.println(pattern.getPattern() + "," + patterns.get(pattern));
            }
            writer.flush();
            writer.close();
        }
        
        catch(FileNotFoundException notFound)       
        {
            System.out.println("The file was not Found!\n " + "ERROR:" + notFound);
        }      
    }
    
/** 
 * loads a previous game that was saved
 * @return the most common pattern from the previous game
 */  
    public String readFile()
    {
        try
        {
            int largest = 0;
            String startingStr = "";
            File savedGame = new File("savedGame.txt");
            Scanner scan = new Scanner(savedGame);
            while (scan.hasNext()) 
            {
                String line = scan.nextLine();
                String tokens[] = line.split(",");
                if (Integer.parseInt(tokens[1]) > largest) 
                {
                    largest = Integer.parseInt(tokens[1]);
                    startingStr = tokens[0];
                }
            }
            return startingStr;
        }
        catch(FileNotFoundException fileError)
        {
            System.out.println("No previous saved game file found!");
        }
        return null;
    }
}
