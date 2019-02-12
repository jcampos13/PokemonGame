package project2;
import java.util.Scanner;
import java.io.*;

public class Project2 
{    
 /** 
 * Where the user interacts with the program 
 * @author julian
 * @param args the array of command-line arguments 
 */ 
    public static void main(String[] args) 
    {             
        
       boolean gameStatus=true;
       Computer comp=new Computer();
       Scanner scan=new Scanner(System.in);            
       String userInput="";
       char input;
       char compPrediction='F';
       int rounds = 0;
       int compScore = 0;
       int userScore = 0;
       
       String resumeGame=comp.readFile();
       
       //asks the user if they want to load the previous game
       if(resumeGame!=null)
       {
           System.out.println("A previous game has been located. Would you like to start where you left off?\n1.YES\n2.NO");
           int loadGame=scan.nextInt();
           if (loadGame==1)
           {
               comp.storePattern(resumeGame);
               userInput=resumeGame.substring(0,3);
           }          
       }
       
       System.out.println("\nWelcome to the Pokémon mind reader game!");
       System.out.println("Choose a Pokémon of type water (W), fire (F), or grass (G)...");       
       while(gameStatus==true)
       {
                              
           System.out.println("\nRounds: " + rounds);
           System.out.println("Your score: " + userScore);
           System.out.println("Computer's score: " + compScore);

           System.out.println("\n1.water\n2.grass\n3.fire\n4.quit");
           
           
           int option = scan.nextInt();
       
        //appends characters to the string, which will be used to create patterns
           switch (option) 
           {
               case 1:
                   input = 'W';
                   userInput += input;
                   break;
               case 2:
                   input = 'G';
                   userInput += input;
                   break;
               case 3:
                   input = 'F';
                   userInput += input;
                   break;
               case 4:
                   int saveGame;
                   Scanner gameOver = new Scanner(System.in);
                   System.out.println("\nDo you want to save the game?\n1.YES\n2.NO");
                   saveGame = gameOver.nextInt();

                   if (saveGame == 1) {
                       File saveFile = new File("savedGame.txt");
                       comp.saveMapToFile(saveFile);
                   }
                   gameStatus = false;
                   System.exit(0);
           }
        
           if (userInput.length() < 5) 
           {
               comp.makePrediction(userInput);
               if (userInput.length() == 4) 
               {
                   comp.storePattern(userInput);
               }
           }
        
           else 
           {
               userInput = userInput.substring(1, userInput.length());
               compPrediction = comp.makePrediction(userInput);
               comp.storePattern(userInput);
           }
                
        //determines winners
        if(compPrediction == 'F' && userInput.charAt(userInput.length()-1)=='W')
        {
            System.out.println("\nCongrats! Water beats Fire ");
            userScore++;
            rounds++;
        }
        
        if(compPrediction == 'F' && userInput.charAt(userInput.length()-1)=='G')
        {
            System.out.println("\nSorry, Fire beats Grass ");
            compScore++;
            rounds++;
        }
        
        if(compPrediction == 'W' && userInput.charAt(userInput.length()-1)=='F')
        {
            System.out.println("\nSorry, Water beats Fire ");
            compScore++;
            rounds++;
        }  
        
        if(compPrediction == 'G' && userInput.charAt(userInput.length()-1) == 'W')
        {
            System.out.println("\nSorry, Grass beats Water ");
            compScore++;
            rounds++;
        }
        
        if(compPrediction == 'G' && userInput.charAt(userInput.length()-1) == 'F')
        {
            System.out.println("\nCongrats! Fire beats Grass ");
            userScore++;
            rounds++;
        }
        
        if(compPrediction == 'W' && userInput.charAt(userInput.length()-1) == 'G')
        {
            System.out.println("\nCongrats! Grass beats Water ");
            userScore++;
            rounds++;
        }
        
        if(compPrediction == 'W' && userInput.charAt(userInput.length()-1) == 'W')
        {
            System.out.println("\nThis round is a tie! ");
            rounds++;
        }
        
        if(compPrediction == 'G' && userInput.charAt(userInput.length()-1) == 'G')
        {
            System.out.println("\nThis round is a tie! ");
            rounds++;    
        }
        
        if(compPrediction == 'F' && userInput.charAt(userInput.length()-1) == 'F')
        {
            System.out.println("\nThis round is a tie! ");
            rounds++; 
        }
        
        float compPercent = ((compScore*100/rounds));
        System.out.println("\nThe computer's percentage of rounds won: " + compPercent + "%");
       }      
    }  
}
