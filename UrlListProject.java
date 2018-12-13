import java.io.*; 
class UrlListProject { 
   public static void main(String[] args) { 
     Interpreter interpreter = new Interpreter();
     interpreter.interpret(args);
   }
}

class Interpreter {
   public void interpret (String[] args) {
      String command;
      if (args.length == 2){
         command = args[0];
         String url = args[1];
         if (!isValidUrl(url)) {
            System.out.println("Invalid url, please provide a valid url.");
         } else {
            switch(command){
               case "verify":    Checker ck = new Checker();
                                 ck.verify(url);
                                 break; // verify where url is and print it
               case "add-whitelist": 
               case "add-blacklist":
               case "remove-whitelist":
               case "remove-blacklist":
               default: System.out.println("Invalid command, please provide one of the available commands for url's");
            }
         }
      }else if(args.length == 1){
         command = args[0];

         System.out.println(command); 
      }else {
         System.out.println("No arguments or too many provided, please provide any of the available commands with the desired url or the show commands."); 
      }
   }

   static boolean isValidUrl (String url) {
      String urlRegex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
      return (url.matches(urlRegex));
   }
}


