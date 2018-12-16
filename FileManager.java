import java.io.*;
import java.util.Scanner;
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
        ensureUniqueness(listName, url);
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
        } else {
            System.out.println("Url is already on " + listName + ".");
        }
    }

    public void removeFromList (String listName, String url) {
        if(isInFile(listName, url)){
            try{
                File file = checkFile(listName);
                File temp = File.createTempFile("tempFile", ".txt", file.getParentFile());
                String charset = "UTF-8";
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        line = line.replace(url, "");
                        writer.println(line);                    }
                }
                System.out.println("Url removed from " + listName + ".");
                reader.close();
                writer.close();
                file.delete();
                temp.renameTo(file);
            }catch(IOException e){
                System.out.println(e);
            }
        } else {
            System.out.println("Url not in "+listName+" to be removed.");
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

    public boolean isInFile (String fileName, String url) {
        try {
            File file = checkFile(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.equals(url)) { 
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
            return false;
        } catch(IOException e) { 
            e.printStackTrace();
            return true;
        }
    }

    private void ensureUniqueness (String listName, String url) {
        String whitelist = "whitelist";
        String blacklist = "blacklist";
        
        if(listName.equals(whitelist)){
            if(isInFile(blacklist, url)){
                System.out.println("Removing from " + blacklist + " first.");
                removeFromList(blacklist, url);
            }
        } else if(listName.equals(blacklist)){
            if(isInFile(whitelist, url)){
                System.out.println("Removing from " + whitelist + " first.");
                removeFromList(whitelist, url);
            }
        }
    }
}