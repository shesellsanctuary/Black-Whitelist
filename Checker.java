import java.io.*;

class Checker {
    public void verify (String url) {
       FileManager fileManager = new FileManager();
       try{
          fileManager.checkFiles("whitelist", "blacklist");
       } catch(IOException e){
          System.out.println(e);
       }
       String output;
       
       System.out.println(url);
       if (checkList("whitelist", url)) {
          output = "safe";
       } else if (checkList("blacklist", url)) {
          output = "unsafe";
       } else {
          output = "Unknown url";
       }
 
       System.out.println(output);
    }
 
    static boolean checkList (String listName, String url) {  
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