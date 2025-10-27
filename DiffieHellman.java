import java.util.Scanner;

class DiffieHellman {
    public static void main(String[] args) {

        System.out.println("\n--- Trupti Rajgonda Zore , 24UAM316 ---\n");

        // Step 1: Take inputs:
        try (Scanner sc = new Scanner(System.in)) {
            // Step 1: Take inputs:
            System.out.print("Enter prime number (p) = ");
            long p = sc.nextLong();
            
            System.out.print("Enter base number (primeroot of p) (bs) = ");
            long bs = sc.nextLong();
            
            // Step 2: A & B Secrete keys:
            System.out.print("Enter Alice's secret key (a) = ");
            long a = sc.nextLong();
            
            System.out.print("Enter Bob's secret key (b) = ");
            long b = sc.nextLong();
            
            // Step 3: Calculate Public keys:
            long A = (long) Math.pow(bs, a) % p;
            long B = (long) Math.pow(bs, b) % p;
            
            // Step 4: Calculate Shared keys:
            long aliceShared = (long) Math.pow(B, a) % p;
            long bobShared   = (long) Math.pow(A, b) % p;
            
            
            System.out.println("\n--- Diffie-Hellman Key Exchange ---");
            System.out.println("Prime (p) = " + p);
            System.out.println("Base (bs) = " + bs);
            System.out.println("\nAlice's Public Key (A) = " + A);
            System.out.println("Bob's Public Key (B) = " + B);
            System.out.println("\nAlice's Shared Secret = " + aliceShared);
            System.out.println("Bob's Shared Secret = " + bobShared);
            System.out.println("\nShared Secret Equal ? = "  + (aliceShared == bobShared));
            System.out.println();
        }
    }
}
