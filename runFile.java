
package test;

import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.utils.IOUtils;

public class runFile {
  public static void main(String[] args) {
    // Path to input file, which is a 
    // tar file compressed to create gzip file
    String INPUT_FILE = "C:\\Users\\grhen\\OneDrive\\Documents\\Data\\Tolstoy.tar.gz";
    // This folder should exist, that's where
    // .tar file will go
    String TAR_FOLDER = "C:\\Users\\grhen\\OneDrive\\Documents\\Data\\";
    // After untar files will go to this folder
    String DESTINATION_FOLDER = "C:\\Users\\grhen\\OneDrive\\Documents\\Data\\";
    runFile unTarDemo = new runFile();
    try {
      File inputFile = new File(INPUT_FILE);
      String outputFile = getFileName(inputFile, TAR_FOLDER);
      System.out.println("outputFile " + outputFile);
      File tarFile = new File(outputFile);
      // Calling method to decompress file
      tarFile = unTarDemo.deCompressGZipFile(inputFile, tarFile);
      File destFile = new File(DESTINATION_FOLDER);
      if(!destFile.exists()){
        destFile.mkdir();
      }
      // Calling method to untar file
      unTarDemo.unTarFile(tarFile, destFile);            
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
    
  /**
   * 
   * @param tarFile
   * @param destFile
   * @throws IOException
   */
  private void unTarFile(File tarFile, File destFile) throws IOException{
    FileInputStream fis = new FileInputStream(tarFile);
    TarArchiveInputStream tis = new TarArchiveInputStream(fis);
    TarArchiveEntry tarEntry = null;
        
    // tarIn is a TarArchiveInputStream
    while ((tarEntry = tis.getNextTarEntry()) != null) {
      File outputFile = new File(destFile + File.separator + tarEntry.getName());        
      if(tarEntry.isDirectory()){            
        System.out.println("outputFile Directory ---- " 
            + outputFile.getAbsolutePath());
        if(!outputFile.exists()){
          outputFile.mkdirs();
        }
      }else{
        //File outputFile = new File(destFile + File.separator + tarEntry.getName());
        System.out.println("outputFile File ---- " + outputFile.getAbsolutePath());
        outputFile.getParentFile().mkdirs();
        //outputFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(outputFile); 
        IOUtils.copy(tis, fos);
        fos.close();
      }
    }
    tis.close();
  }
    
  /**
   * Method to decompress a gzip file
   * @param gZippedFile
   * @param newFile
   * @throws IOException
   */
  private File deCompressGZipFile(File gZippedFile, File tarFile) throws IOException{
    FileInputStream fis = new FileInputStream(gZippedFile);
    GZIPInputStream gZIPInputStream = new GZIPInputStream(fis);
    
    FileOutputStream fos = new FileOutputStream(tarFile);
    byte[] buffer = new byte[1024];
    int len;
    while((len = gZIPInputStream.read(buffer)) > 0){
      fos.write(buffer, 0, len);
    }        
    fos.close();
    gZIPInputStream.close();
    return tarFile;               
  }
    
  /**
   * This method is used to get the tar file name from the gz file
   * by removing the .gz part from the input file
   * @param inputFile
   * @param outputFolder
   * @return
   */
  private static String getFileName(File inputFile, String outputFolder){
    return outputFolder + File.separator + 
      inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.'));
  }
}