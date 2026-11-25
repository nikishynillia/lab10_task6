import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main{public static void main(String[] args){

Scanner scanner = new Scanner(System.in);
System.out.print("enter bank code: ");
String code = scanner.nextLine();
String address = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";

try (BufferedReader reader = new BufferedReader(new InputStreamReader(
        URI.create(address).toURL().openStream(),
        Charset.forName("IBM852")))) {
    String line;
    while ((line = reader.readLine())!=null){
        String[]parts = line.split("\t");
        if (parts.length >= 2 && parts[0].trim().equals(code)){
            System.out.println("bank " + parts[1].trim());
            return;
        }
    }
    System.out.println("bank was not found");
}
catch (IOException e){
    System.out.println(e.getMessage());
}
}
}