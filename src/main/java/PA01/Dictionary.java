
package PA01;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Dictionary {
     
   HashMap<String, String> Dictionary;
   public Dictionary(){
       Dictionary = new HashMap<>();
   }
   
   public void ImportFromTxt(String filename){
       try
        {  
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            br.readLine();
            String str;
            do{
                str = br.readLine();
                if(str==null) break;
                //System.out.println(str);
                if(!str.contains("`")) continue;
                String key = str.substring(0, str.indexOf('`'));
                String def = str.substring(str.indexOf('`')+1);
                Dictionary.put(key, def);
            }while(true);
            br.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
   }
   
   public void ExportToTxt(String filename){
       try
        {  
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                   new FileOutputStream(filename),"UTF-8"));
           bw.write("Slag`Meaning");
           bw.newLine();
           for(Entry<String, String> entry: Dictionary.entrySet()){
                String slangword = entry.getKey()+"`"+entry.getValue();
                bw.write(slangword);
                bw.newLine();
            }
           bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
   }
   public void SaveHistory(ArrayList<String> keys){
       try{
           
           BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("History.txt")));
           
           //READ OLD HISTORY
           ArrayList<String> oldKeys = new ArrayList<>();
           String str;
           str = br.readLine();
           while(str!=null){
               oldKeys.add(str);
               str = br.readLine();
           }
           br.close();
           //System.out.println(oldKeys);
           
           
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                   new FileOutputStream("History.txt"),"UTF-8"));
           //WRITE NEW HISTORY AT THE BEGINING OF THE FILE
           for(String key : keys){
               //System.out.println(key);
               bw.write(key);
               bw.newLine();
           }
           
           for(String key : oldKeys){
               //System.out.println(key);
               bw.write(key);
               bw.newLine();
           }
           
           bw.flush();
           bw.close();
       }
       catch(IOException e){}
   }
   
   public void ViewHistory(){
       try{
           BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("History.txt")));
           String str;
           System.out.println("HISTORY");
           do{
                str = br.readLine();
                if(str==null) break;
                System.out.println(str);
           }while(true);
           br.close();
       }
       catch(IOException e){}
   }
   
   public void findDefByWord(String key){
       ArrayList<String> keys = new ArrayList<>();
       String def = Dictionary.get(key);
       if(def==null){
           System.out.println("Cannot find slang word");
       }
       else{
           System.out.println("Definition: " + def);
           keys.add(key);
           SaveHistory(keys);
       }
   }
   
   
   public void findWordByDef(String def){
       ArrayList<String> keys = new ArrayList<>();
       for(Entry<String, String> entry: Dictionary.entrySet()){
           if(entry.getValue().contains(def)){
               System.out.println(entry.getKey() + " means " + entry.getValue());
               keys.add(entry.getKey());
           }
       }
       SaveHistory(keys);
 
   }
   
   public boolean addNewSlangWord(String key, String def){
       //String definition = Dictionary.get(key);
       if(!this.existSlangWord(key)){
           Dictionary.put(key, def);
           return true;
       }
       else{
           return false;
       }
   }
   
   public void duplicateSlangWord(String key, String def){
       String def_dup = def + " | " + Dictionary.get(key);
       Dictionary.put(key, def_dup);
   }
   
   
   public void overWriteSlangWord(String key, String def){
       Dictionary.put(key, def);
   }
   
   
   public boolean existSlangWord(String key){
       return Dictionary.containsKey(key);
   }
   
   public void deleteSlangWord(String key){
       Dictionary.remove(key);
   }
   
   
   public void resetSlangWord(String current, String origin){
       try{
           BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(origin)));
           
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                   new FileOutputStream(current),"UTF-8"));
           String str;
           while( (str=br.readLine()) != null ){
               bw.write(str);
               bw.newLine();
           }
           bw.flush();
           bw.close();
           br.close();
       }
       catch(IOException e){}
   }
   
   public String randomSlangWord(){
       Set<String> keySet = Dictionary.keySet();
       List<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIdx = new Random().nextInt(size);
       //String randomValue = Dictionary.get(randomKey);
       //System.out.println("Slag: " + randomKey + ", Meaning: " + randomValue);

        return keyList.get(randIdx);
   }
   
   
   public String getDef(String key){
       return Dictionary.get(key);
   }
   
   
   public String[] QuizSlangWord(String random){
       //String question = randomSlangWord();
       
       String [] answers;
       answers = new String[4];
       
       int i = 1;
       while(i<answers.length){
           String choice = randomSlangWord();
           if(choice!=random)
                answers[i] = Dictionary.get(choice);
           else
               continue;
           i++;
       }
       return answers;
       
   }
   
   public String [] QuizDefinition(String correctAnswer){
       
       //String question = Dictionary.get(correctAnswer);
       
       String [] answers;
       answers = new String[4];
       
       answers[0] = correctAnswer;
       
       int i = 1;
       while(i<answers.length){
           String choice = randomSlangWord();
           if(choice!=correctAnswer)
                answers[i] = choice;
           else
               continue;
           i++;
       }
       return answers;
   }
}
