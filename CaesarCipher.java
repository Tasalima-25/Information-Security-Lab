
import java.util.Scanner;

public class CaesarCipher {

    public static String encrypt(String message, int key) 
    {
        String result = "";  

        for (int i = 0; i < message.length(); i++) 
        {
            char ch = message.charAt(i);
           
            if (ch >= 'A' && ch <= 'Z') 
            {
                char encryptedChar = (char)((ch - 'A' + key) % 26 + 'A');
                result += encryptedChar;
            }
            
            else if (ch >= 'a' && ch <= 'z') 
            {
                char encryptedChar = (char)((ch - 'a' + key) % 26 + 'a');
                result += encryptedChar;
            }
            
            else 
            {
                result += ch;
            }
        }

        return result;
    }

    public static void main(String[] args) 
    {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\n--- Trupti Rajgonda Zore , 24UAM316 ---\n");
            System.out.print("Enter Text = ");
            String text = sc.nextLine();
                
            System.out.print("Enter key to shift = ");
            int shift = sc.nextInt();
                
            String encrypted = encrypt(text, shift);
                
            System.out.println();
            System.out.println("Plaintext = " + text);
            System.out.println("Encrypted = " + encrypted + "\n");
        }
        
    }
    
}
