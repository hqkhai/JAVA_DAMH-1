

package PA01;
import java.io.*;
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
                                    break;
                                case 2:
                                    d.overWriteSlangWord(key, def);
                                    break;
                                default:
                                    System.out.println("\nInvalid input\n");
                                    break;  
                            }
                        }
                        d.ExportToTxt("Slang.txt");
                        break;


                    case 5:
                        
                        break;
                        
                    case 6:
                        
                        break;
                    case 7:
                        
                        break;
                    case 9:
                        
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
}
