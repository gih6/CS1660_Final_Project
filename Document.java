package test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hende
 */
//adding some new things
public class Document {
   int id;
   int frequency;
   String filename;
   String folder;
    
   Document(String filename, String folder, int frequency, int id){
       this.filename = filename;
       this.folder = folder;
       this.frequency = frequency;
       this.id = id;
   }
   
   public String getFileName(){return this.filename;}
   public String getFolderName(){return this.folder;}
   
    public int getID(){ return this.id;}
    
    public int getFrequency(){ return this.frequency;}
    public void setFrequency(int i) {this.frequency=i;}
    
    public void updateFrequency(){this.frequency = this.frequency+1;}
    
    @Override
    public String toString(){
        return filename + " " + folder+ " " + frequency + " " + id;
    }
}
