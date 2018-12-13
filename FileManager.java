import java.io.*;

class FileManager {

    public void checkFiles (String whiteFileName, String blackFileName) throws IOException{
        File whitelist = new File("./", whiteFileName + ".txt");
        File blacklist = new File("./", blackFileName + ".txt");
        if(!whitelist.exists()){
            if(!whitelist.createNewFile()){
                throw new IOException("Error creating new file: " + whitelist.getAbsolutePath());
            }
        } 
        if(!blacklist.exists()) {
            if(!blacklist.createNewFile()) {
                throw new IOException("Error creating new file: " + blacklist.getAbsolutePath());
            }
        }
    }

    public void printList (String listName) {
        
    }

    public void addToList (String listName, String url) {

    }

    public void removeFromList (String listName, String url) {
        
    }
}