import java.util.Scanner;

public class RSA_Algorithm 
{
    // gcd function
    static int gcd(int a, int b) 
    {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Find modular inverse of e under mod phi
    static int modInverse(int e, int phi) 
    {
        for (int d = 1; d < phi; d++) {
            if ((d * e) % phi == 1)
                return d;
        }
        return -1;
    }

    // Modular exponentiation: (base^exp) % mod
    static int power(int base, int exp, int mod) 
    {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
    }

    public static void main(String[] args) 
    {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\n--- Trupti Rajgonda Zore , 24UAM316 ---\n");
            
            // Step 1: Take primes from user
            System.out.print("Enter prime number p = ");
            int p = sc.nextInt();
            System.out.print("Enter prime number q = ");
            int q = sc.nextInt();
            
            int n = p * q;
            int phi = (p - 1) * (q - 1);
            
            // Step 2: Choose e
            int e = 2;
            while (e < phi) 
            {
                if (gcd(e, phi) == 1) break;
                e++;
            }
            
            // Step 3: Find d
            int d = modInverse(e, phi);
            
            System.out.println("\nPublic Key (e, n) = (" + e + ", " + n + ")");
            System.out.println("Private Key (d, n) = (" + d + ", " + n + ")");
            
            // Step 4: Input message
            System.out.print("\nEnter a number message (less than " + n + ") = ");
            int message = sc.nextInt();
            
            // Step 5: Encrypt
            int encrypted = power(message, e, n);
            System.out.println("Encrypted message = " + encrypted);
            
            // Step 6: Decrypt
            int decrypted = power(encrypted, d, n);
            System.out.println("Decrypted message = " + decrypted);
            System.out.println();
        }
    }
}