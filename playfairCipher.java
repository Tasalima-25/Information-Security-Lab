import java.util.*;

public class playfairCipher 
{

    private static char[][] matrix;

    // Create the 5x5 key matrix
    public static void generateKeyMatrix(String key) 
    {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I"); 
        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[26];

        for (char c : key.toCharArray()) 
        {
            if (!used[c - 'A']) 
            {
                used[c - 'A'] = true;
                sb.append(c);
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) 
        {
            if (c == 'J') continue; 
            if (!used[c - 'A']) 
            {
                used[c - 'A'] = true;
                sb.append(c);
            }
        }

        matrix = new char[5][5];
        int index = 0;
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                matrix[i][j] = sb.charAt(index++);
            }
        }
    }

    
    public static List<String> prepareText(String text, boolean encrypt) 
    {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder(text);

       
        for (int i = 0; i < sb.length() - 1; i += 2) 
        {
            if (sb.charAt(i) == sb.charAt(i + 1)) 
            {
                sb.insert(i + 1, 'X');
            }
        }

        
        if (sb.length() % 2 != 0) 
        {
            sb.append('X');
        }

        List<String> pairs = new ArrayList<>();
        for (int i = 0; i < sb.length(); i += 2) 
        {
            pairs.add(sb.substring(i, i + 2));
        }
        return pairs;
    }

    public static int[] findPosition(char c) 
    {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) 
            {
                if (matrix[i][j] == c) 
                {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // Encryption
    public static String encrypt(String plaintext) 
    {
        List<String> pairs = prepareText(plaintext, true);
        StringBuilder cipher = new StringBuilder();

        for (String pair : pairs) 
        {
            char a = pair.charAt(0);
            char b = pair.charAt(1);
            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) 
            {
                cipher.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                cipher.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } 
            else if (posA[1] == posB[1]) 
            { 
                cipher.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                cipher.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } 
            else 
            { 
                cipher.append(matrix[posA[0]][posB[1]]);
                cipher.append(matrix[posB[0]][posA[1]]);
            }
        }
        return cipher.toString();
    }

    // Decryption
    public static String decrypt(String ciphertext) 
    {
        List<String> pairs = prepareText(ciphertext, false);
        StringBuilder plain = new StringBuilder();

        for (String pair : pairs) 
        {
            char a = pair.charAt(0);
            char b = pair.charAt(1);
            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) 
            { 
                plain.append(matrix[posA[0]][(posA[1] + 4) % 5]);
                plain.append(matrix[posB[0]][(posB[1] + 4) % 5]);
            } 
            else if (posA[1] == posB[1]) 
            { 
                plain.append(matrix[(posA[0] + 4) % 5][posA[1]]);
                plain.append(matrix[(posB[0] + 4) % 5][posB[1]]);
            } 
            else 
            {
                plain.append(matrix[posA[0]][posB[1]]);
                plain.append(matrix[posB[0]][posA[1]]);
            }
        }
        return plain.toString();
    }

    public static void main(String[] args) 
    {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\n--- Trupti Rajgonda Zore , 24UAM316 ---\n");

            System.out.print("Enter key: ");
            String key = sc.nextLine();
            generateKeyMatrix(key);

            System.out.println("\nKey Matrix:");
            for (char[] row : matrix) 
            {
                for (char c : row) 
                {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

            System.out.print("\nEnter plaintext: ");
            String plaintext = sc.nextLine();
            String encrypted = encrypt(plaintext);
            System.out.println("Encrypted: " + encrypted);

            String decrypted = decrypt(encrypted);
            System.out.println("Decrypted: " + decrypted);
            System.out.println();
        }
    }
}