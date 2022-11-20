

package PA01;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Dictionary d = new Dictionary();
        d.ImportFromTxt("Slang.txt");
        
        Scanner input = new Scanner(System.in);

            // Creating option integer variable
            int option = 0;
              
            do {
                menu();
                option = input.nextInt();
                
                // Switch case
                switch (option) {
                    case 1:
                        
                        input.nextLine();
                        System.out.print("Enter the key: ");
                        String key = input.nextLine();
                        d.findDefByWord(key);        
                        break;

                    case 2:
                        
                        input.nextLine();
                        System.out.print("Enter the definition: ");
                        String def = input.nextLine();
                        d.findWordByDef(def);
                        break;

                    case 3:
                        
                        d.ViewHistory();
                        break;

                    case 4:
                        
                        input.nextLine();
                        System.out.print("Enter the key: ");
                        key = input.nextLine();
                        System.out.print("Enter the definition: ");
                        def = input.nextLine();
                        boolean add = d.addNewSlangWord(key, def);
                        if(add){
                            System.out.println("Add new slang word successfully");
                        }
                        else{
                            System.out.println("Already have this slang word. Please choose 1 below");
                            System.out.println("1.Duplicate");
                            System.out.println("2.Overwrite");
                            System.out.print("Enter your choice: ");
                            int choice = input.nextInt();
                            switch(choice){
                                case 1:
                                    d.duplicateSlangWord(key, def);
                                    System.out.println("Slang word duplicated");
                                    break;
                                case 2:
                                    d.overWriteSlangWord(key, def);
                                    System.out.println("Slang word overwrited");
                                    break;
                                default:
                                    System.out.println("\nInvalid input\n");
                                    break;  
                            }
                        }
                        d.ExportToTxt("Slang.txt");
                        break;


                    case 5:
                        
                        input.nextLine();
                        System.out.print("Enter the key: ");
                        key = input.nextLine();
                        
                        if(!d.existSlangWord(key)){
                            System.out.println("Slang word does not exist");
                            break;
                        }
                        
                        System.out.print("Enter the definition: ");
                        def = input.nextLine();
                        
                        d.overWriteSlangWord(key, def);
                        System.out.println("Slang word updated");
                        d.ExportToTxt("Slang.txt");
                        break;
                        
                    case 6:
                        input.nextLine();
                        System.out.print("Enter the key: ");
                        key = input.nextLine();
                        
                        if(!d.existSlangWord(key)){
                            System.out.println("Slang word does not exist");
                            break;
                        }
                        
                        d.deleteSlangWord(key);
                        System.out.println("Slang word deleted");
                        d.ExportToTxt("Slang.txt");
                        break;
                    case 7:
                        d.resetSlangWord("Slang.txt", "SlangOrigin.txt");
                        d.ImportFromTxt("Slang.txt");
                        System.out.println("Reset Successfully");
                        break;
                    case 8:
                        String randomKey = d.randomSlangWord();
                        System.out.println("Slang word: " + randomKey);
                        d.findDefByWord(randomKey);
                        break;
                        
                    case 9:
                        
                        d.QuizSlangWord();
                        break;
                    
                    case 10:
                        
                        
                        break;
                    default:
                        System.out.println("\nInvalid input\n");
                        break;
                }
            }
            while (option != 11);
    }
    public static void menu(){
            System.out.println("MENU");
            System.out.println("1: Find Slang Word by Name");
            System.out.println("2: Find Slang Word by Definition");
            System.out.println("3: History");
            System.out.println("4: Add new slang word");
            System.out.println("5: Edit slang word");
            System.out.println("6: Delete slang word");
            System.out.println("7: Reset list of slang word");
            System.out.println("8: Random 1 slang word");
            System.out.println("9: Quiz with random slang word");
            System.out.println("10: Quiz with random definition");
            System.out.println("11: Exit");
            System.out.print("Enter your selection : ");
	}
    
    public static void shuffle(String [] answers){
       int size = answers.length;
       for(int i=0;i<size;i++){
           int randIdx = new Random().nextInt(size);
           String temp = answers[i];
           answers[i] = answers[randIdx];
           answers[randIdx] = temp;
       }
   }
    
    public static void displayQuiz(String question, String [] answers, String correctAnswer){
        System.out.println(question);
       
       //System.out.println(question);
       for(int i=0;i<answers.length;i++){
           char c = (char) ((int)'A' + i);
           System.out.println(c + " " + answers[i]);
       }
       Scanner sc = new Scanner(System.in);
       
       do{
           System.out.print("Answer your question: ");
           char option = Character.toLowerCase(sc.next().charAt(0));

           if((int)option < (int)'a' || (int)option > (int)'d'){
               System.out.println("Invalid input");
               continue;
           }
           if(correctAnswer == answers[(int)option - (int)'a']){
               System.out.println("Correct Answer");
           }
           else{
               System.out.println("Wrong Answer");
               System.out.println("The answer is " + correctAnswer);
           }
           break;
       }while(true);
    }
}
