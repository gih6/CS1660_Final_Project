/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hende
 */

import java.util.ArrayList; // import the ArrayList class

public class Word implements Comparable<Word>{
   ArrayList<Document> Document = new ArrayList<Document>(); 
   String word;
   int totalFrequency;
    
    @Override
    public int compareTo(Word e){

            return  e.totalFrequency-this.totalFrequency;
    }
    
    @Override
    public String toString(){
        return this.word + " " + this.totalFrequency;
    }
   
    Word(String wordin, Document doc){
        this.word = wordin;
        this.Document.add(doc);
        this.totalFrequency = 1;
    }
    
 
    public void addDocument(Document doc){this.Document.add(doc);}
    
    public void addFrequency(){this.totalFrequency = this.totalFrequency+1;}
    
    public int getFrequency(){return this.totalFrequency;}
    
    public ArrayList<Document> getDocuments(){ return this.Document;}
    
}

