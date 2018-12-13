import java.io.*;

class Checker {
    public void verify (String url) {
       FileManager fileManager = new FileManager();
       try{
            String output;
            fileManager.checkFiles("whitelist", "blacklist");

            if (checkUrl("whitelist", url)) {
                output = "safe";
            } else if (checkUrl("blacklist", url)) {
                output = "unsafe";
            } else {
                output = "Unknown url";
            }
   
            System.out.println(output);
       } catch(IOException e){
          System.out.println(e);
       }
    }
 
    boolean checkUrl (String listName, String url) {  
       File file = new File("./", listName + ".txt");
       BufferedReader reader = new BufferedReader(new FileReader(file));
       String line;
       while ((line = reader.readLine()) != null) {
          if (line == url) {
             reader.close();
             return true;
          }
       }
       reader.close();
       return false;
    }
 }