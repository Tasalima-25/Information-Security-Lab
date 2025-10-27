public class DES 
{
    // Initial Permutation Table
    private static final int[] IP = 
    {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };

    // Final Permutation Table
    private static final int[] FP = 
    {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };

    // Simple key for demonstration (64 bits)
    private static final long KEY = 0x133457799BBCDFF1L;

    // Permutation function
    private static long permute(long input, int[] table) 
    {
        long output = 0;
        for (int i = 0; i < table.length; i++) 
        {
            output <<= 1;
            output |= (input >> (64 - table[i])) & 1;
        }
        return output;
    }

    // Simple DES round (for demonstration, not full DES)
    private static long simpleRound(long block, long key) 
    {
        // XOR with key
        return block ^ key;
    }

    // Encrypt 64-bit block
    public static long encrypt(long plainBlock) 
    {
        long ip = permute(plainBlock, IP);
        long round = simpleRound(ip, KEY);
        long fp = permute(round, FP);
        return fp;
    }

    // Decrypt 64-bit block
    public static long decrypt(long cipherBlock) 
    {
        long ip = permute(cipherBlock, IP);
        long round = simpleRound(ip, KEY);
        long fp = permute(round, FP);
        return fp;
    }

    // Helper to convert string to 64-bit block
    public static long stringToBlock(String s) 
    {
        byte[] bytes = s.getBytes();
        long block = 0;
        for (int i = 0; i < 8; i++) 
        {
            block <<= 8;
            block |= (i < bytes.length ? bytes[i] & 0xFF : 0);
        }
        return block;
    }

    // Helper to convert 64-bit block to string
    public static String blockToString(long block) 
    {
        byte[] bytes = new byte[8];
        for (int i = 7; i >= 0; i--) 
        {
            bytes[i] = (byte) (block & 0xFF);
            block >>= 8;
        }
        return new String(bytes);
    }

    public static void main(String[] args) 
    {
        System.out.println("\n--- Trupti Rajgonda Zore , 24UAM316 ---\n");

        String plainText = "Trupti";
        long block = stringToBlock(plainText);
        long encrypted = encrypt(block);
        long decrypted = decrypt(encrypted);

        System.out.println("Plaintext: " + plainText);
        System.out.println();
        System.out.println("Encrypted: " + Long.toHexString(encrypted));
        System.out.println();
        System.out.println("Decrypted: " + blockToString(decrypted));
        System.out.println();
    }
}