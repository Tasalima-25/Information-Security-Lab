import java.util.Scanner;

public class VigenereCipher 
{
   public VigenereCipher() {
   }

   public static String encrypt(String var0, String var1) 
   {
      var0 = var0.toUpperCase();
      var1 = var1.toUpperCase();
      String var2 = " ";
      int var3 = 0;

      for(int var4 = 0; var4 < var0.length(); ++var4) 
      {
         char var5 = var0.charAt(var4);
         if (Character.isLetter(var5)) 
         {
            int var6 = var1.charAt(var3) - 65;
            char var7 = (char)((var5 - 65 + var6) % 26 + 65);
            var2 = var2 + var7;
            var3 = (var3 + 1) % var1.length();
         } 
         else 
         {
            var2 = var2 + var5;
         }
      }

      return var2;
   }

   public static String decrypt(String var0, String var1) 
   {
      var0 = var0.toUpperCase();
      var1 = var1.toUpperCase();
      String var2 = "";
      int var3 = 0;

      for(int var4 = 0; var4 < var0.length(); ++var4) 
      {
         char var5 = var0.charAt(var4);
         if (Character.isLetter(var5)) 
         {
            int var6 = var1.charAt(var3) - 65;
            char var7 = (char)((var5 - 65 - var6 + 26) % 26 + 65);
            var2 = var2 + var7;
            var3 = (var3 + 1) % var1.length();
         } 
         else 
         {
            var2 = var2 + var5;
         }
      }

      return var2;
   }

   public static void main(String[] var0) 
   {
       try (Scanner var1 = new Scanner(System.in)) {

         System.out.println("\n--- Trupti Rajgonda Zore , 24UAM316 ---\n");

         System.out.println("Enter plaintext = ");
         String var2 = var1.nextLine();
         System.out.println("Enter keyword = ");
         String var3 = var1.nextLine();
         System.out.println();
         String var4 = encrypt(var2, var3);
         System.out.println("Encrypted ciphertext = " + var4);
         String var5 = decrypt(var4, var3);
         System.out.println("Decrypted plaintext = " + var5);
         System.out.println();

       }
   }
}