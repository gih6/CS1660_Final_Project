/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hende
 */
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap; // import the HashMap class
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;


public class Indexer{
    
    //global variables of program 
    HashMap<String, Word> storage = new HashMap<String, Word>();
    ArrayList<Word> listOfValues = new ArrayList<Word>();
    int ID_NUM;
    
    
   
    
    
    public void tokenizer(String line, File file){
      //  System.out.println("we are printing line");
    //    System.out.println(line);
        line = line.replaceAll("[,!.?]", "");  //getting rid of all special characters. 
        line = line.toLowerCase();
        StringTokenizer st = new StringTokenizer(line);
        while(st.hasMoreTokens()){
           mapper(st.nextToken(),file);
        }
    }
    
    public  void mapper(String token, File file){
        //First search if in Map already. 
        if(storage.containsKey(token)){ //checking if word already exists
            Word temp2 = storage.get(token);
            temp2.addFrequency();
            ArrayList<Document> documents = temp2.getDocuments();
            int found =0;
            for(int i=0; i<documents.size(); i++){
                if(documents.get(i).getFileName().equals(file.getName()) &&documents.get(i).getFolderName().equals(file.getParentFile().getName())){
                     documents.get(i).updateFrequency();
                     found =1;
                //     System.out.println("updating existing document");
                }
            }
            if (found == 0) { // document not yet put in for this word 
            Document newDoc = new Document(file.getName(), file.getParentFile().getName(), 1, 1);
            temp2.addDocument(newDoc);
            //System.out.println("Adding a new document to existing word: " + newDoc);
            }
        }else{
          
            Document newDoc = new Document(file.getName(), file.getParentFile().getName(), 1, 1);
            Word temp = new Word(token, newDoc); // need to get ID, DOcument Name, DOcument FOlder
            storage.put(token,temp );
          //  System.out.println("Adding NEW  & WORD: "+ newDoc + "Size of documents is: " + temp.getDocuments().size());
        }
        
 
    }
    
    public  void openFile(File file) throws FileNotFoundException{
         try {
        Scanner sc = new Scanner(file);
        
        while(sc.hasNextLine()){
            tokenizer(sc.nextLine(), file);
        }
        } catch(Exception e){
            System.out.println("no");
        }
    }
    
    public void printMap(){
        System.out.println("******");
         System.out.println("******");
          System.out.println("******");
        for (String i : storage.keySet()) {
            System.out.println("key: " + i + " value: " + storage.get(i).getDocuments()+ "size of document array: " + storage.get(i).getDocuments().size());
            System.out.println("******");
        }
    }
    
    public void searchForTerm(String term){
         if(storage.containsKey(term)){
            Word temp2 = storage.get(term);
            System.out.println("These are document folder: " + temp2.getDocuments());

         }
    }
    
     
    public void makeArrayofMax(){
        Collection<Word> values = storage.values(); 
        listOfValues = new ArrayList<Word>(values);
        Collections.sort(listOfValues);
    }
    
    public void getMaxFrequencies(int N){
         for(int i=0;i<N;i++){
             System.out.print(listOfValues.get(i));
             System.out.print(" ");
         }
        

    }
    
    
    public static void main(String args[])throws FileNotFoundException{
    //   System.out.println("we are beginning to try this");
     //  File file = new File ("C:\\Users\\hende\\Documents\\Data\\extract_Data\\comedies\\asyoulikeit.txt");
     //  File file2 = new File ("C:\\Users\\hende\\Documents\\CS1660\\testData\\file1.txt");
      // System.out.println(file.getName());
      // System.out.println(file.getParentFile().getName());
       

       Indexer fileSys = new Indexer();
       
    //  fileSys.openFile(file);
     // fileSys.openFile(file2);
     // fileSys.printMap();
    //  fileSys.searchForTerm("super");
     // System.out.println("---------------");
    //  fileSys.makeArrayofMax();
     // fileSys.getMaxFrequencies(3);
   //   System.out.println("FILE NAMES ---------------");
      
   
    
      File folder = new File("C:\\Users\\hende\\Documents\\Data\\extract_Data");
      String filepath = "C:\\Users\\hende\\Documents\\Data\\extract_Data";
      String[] lists = folder.list();
      for(int i=0; i<lists.length;i++) {
     //   System.out.println(lists[i]);
        String newFilePath = filepath.concat("\\");
        newFilePath = newFilePath.concat(lists[i]);
     //   System.out.println("*In This directory: " + lists[i]);
        //go through all files
        folder = new File(newFilePath);
        File[] listOfFiles = folder.listFiles();
        for (File files : listOfFiles) {
            if (files.isFile()) {
        //        System.out.println(files.getName());
                fileSys.openFile(files);
        }
        
      }
        
       

        
}
       System.out.println("getting the most for king search");
        fileSys.searchForTerm("king");
        fileSys.makeArrayofMax();
        fileSys.getMaxFrequencies(10);
       
}
       
       
    
}


