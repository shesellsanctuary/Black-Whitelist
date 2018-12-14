import java.io.*;

class Interpreter {
   public void interpret(String[] args) {
      String command = args[0];
      if (args.length == 2) {
         String url = args[1];
         if (!isValidUrl(url)) {
            System.out.println("Invalid url, please provide a valid url.");
         } else {
            FileManager fileManager = new FileManager();
            switch (command) {
            case "verify":
               Checker ck = new Checker();
               ck.verify(url);
               break;
            case "add-whitelist":
               fileManager.addToList("whitelist", url);
               break;
            case "add-blacklist":
               fileManager.addToList("blacklist", url);
               break;
            case "remove-whitelist":
               fileManager.removeFromList("whitelist", url);
               break;
            case "remove-blacklist":
               fileManager.removeFromList("blacklist", url);
               break;
            default:
               System.out.println("Invalid command, please provide one of the available commands for url's");
            }
         }
      } else if (args.length == 1) {
         FileManager fileManager = new FileManager();
            switch (command) {
            case "show-whitelist":
               fileManager.printList("whitelist");
               break;
            case "show-blacklist":
               fileManager.printList("blacklist");
               break;
            default:
            System.out.println("Invalid command.");
            }
      } else {
         System.out.println(
               "No arguments or too many provided, please provide any of the available commands with the desired url or the show commands.");
      }
   }

   private boolean isValidUrl(String url) {
      String urlRegex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
      return (url.matches(urlRegex));
   }
}