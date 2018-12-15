import java.io.*;
class Checker {
    public void verify (String url) {
       FileManager fileManager = new FileManager();
       try{
            String output;
            fileManager.checkFiles("whitelist", "blacklist");

            if (fileManager.isInFile("whitelist", url)) {
                output = "safe";
            } else if (fileManager.isInFile("blacklist", url)) {
                output = "unsafe";
            } else {
                output = "Unknown url";
            }
   
            System.out.println(output);
       } catch(IOException e){
          System.out.println(e);
       }
    }
 }