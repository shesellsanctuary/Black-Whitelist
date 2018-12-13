import java.io.*;

class FileManager {

    public void checkFiles (String... files) throws IOException{

        for (String file : files){
            checkFile(file);
        }
    }

    public void printList (String listName) {
        
    }

    public void addToList (String listName, String url) {

    }

    public void removeFromList (String listName, String url) {
        
    }

    private void checkFile (String fileName) throws IOException{
        File file = new File("./", fileName + ".txt");
        if(!file.exists()){
            if(!file.createNewFile()){
                throw new IOException("Error creating new file: " + file.getAbsolutePath());
            }
        } 
    }
}