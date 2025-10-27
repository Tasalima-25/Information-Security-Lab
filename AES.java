public class AES 
{
    private static final int[] SBOX = {
        0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5,
        0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76
       
    };

    private static final long KEY = 0x2b7e151628aed2a6L; 

    // Substitute bytes using S-box
    private static long substitute(long block)
    {
        long output = 0;
        for (int i = 0; i < 8; i++) 
        {
            int b = (int)((block >> (8 * (7 - i))) & 0xFF);
            int sb = SBOX[b % SBOX.length]; 
            output = (output << 8) | sb;
        }
        return output;
    }

    
    private static long simpleRound(long block, long key) 
    {
        long sub = substitute(block);
        return sub ^ key;
    }

    public static long encrypt(long plainBlock) 
    {
        long round = simpleRound(plainBlock, KEY);
        return round;
    }

    public static long decrypt(long cipherBlock) 
    {
        
        long round = cipherBlock ^ KEY;
        
        return round;
    }

    public static long stringToBlock(String s) 
    {
        byte[] bytes = s.getBytes();
        long block = 0;
        for (int i = 0; i < 8; i++) {
            block <<= 8;
            block |= (i < bytes.length ? bytes[i] & 0xFF : 0);
        }
        return block;
    }

    public static String blockToString(long block) 
    {
        byte[] bytes = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bytes[i] = (byte) (block & 0xFF);
            block >>= 8;
        }
        return new String(bytes);
    }

    public static void main(String[] args) 
    {
        System.out.println("\n--- Trupti Rajgonda Zore , 24UAM316 ---\n");

        String plainText = "Trupti Zore";
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