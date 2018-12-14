import java.io.*;

import jdk.nashorn.internal.runtime.regexp.RegExpResult;

class FileManager {

    public void checkFiles (String... files) throws IOException{

        for (String file : files){
            checkFile(file);
        }
    }

    public void printList (String listName) {
        try{
            File file = checkFile(listName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
               System.out.println(line);
            }
            reader.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void addToList (String listName, String url) {
        if (isInFile(listName, url) == false){
            try{
                FileWriter file = new FileWriter(listName + ".txt", true);
                BufferedWriter writer = new BufferedWriter(file);
                writer.append(url);
                writer.newLine();
                writer.close();
                System.out.println("Url added to " + listName + ".");
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }

    public void removeFromList (String listName, String url) {
        try{
            File file = checkFile(listName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
               if (line == url) {
                    line.replace(url, "");
                    reader.close();
                    System.out.println("Url removed from " + listName + ".");
               }
            }
            System.out.println("Url not in list to be removed.");
            reader.close();

        }catch(IOException e){
            System.out.println(e);
        }
    }

    private File checkFile (String fileName) throws IOException{
        File file = new File("./", fileName + ".txt");
        if(!file.exists()){
            if(!file.createNewFile()){
                throw new IOException("Error creating new file: " + file.getAbsolutePath());
            }else{
                return file;
            }
        }else {
            return file;
        }
    }

    private boolean isInFile (String fileName, String url) {
        try{
            File file = checkFile(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.printf("line= ",line);
                if (line == url) {
                    System.out.println("Url is already on " + fileName + ".");
                    reader.close();
                    return true;
                }
            }
            reader.close();
            return false;
        }catch(IOException e){
            System.out.println(e);
            return true;
        }
        
    }
}