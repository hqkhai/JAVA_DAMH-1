
package PA01;

import java.util.Collection;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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
           System.out.println(oldKeys);
           
           
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
                System.out.println(str);
           }while(str!=null);
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
   
   
}
