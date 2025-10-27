import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC 
{
    public static void main(String[] args) 
    {
        try 
        {
            // 1. Message and Secret Key
            String message = "Hello Bob, Pay Rs.100";
            String secretKey = "MySecretKey123";

            System.out.println("Message: " + message);
            System.out.println("Secret Key: " + secretKey);

            // 2. Convert message and key into bytes
            byte[] messageBytes = message.getBytes();
            byte[] keyBytes = secretKey.getBytes();

            // 3. Create HMAC object (SHA-256)
            Mac mac = Mac.getInstance("HmacSHA256");

            // 4. Wrap key into SecretKeySpec
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "HmacSHA256");

            // 5. Initialize Mac with secret key
            mac.init(keySpec);

            // 6. Compute HMAC for the message
            byte[] hmacBytes = mac.doFinal(messageBytes);

            // 7. Convert raw HMAC bytes into Base64 string
            String hmacBase64 = Base64.getEncoder().encodeToString(hmacBytes);
            System.out.println("HMAC (Base64): " + hmacBase64);

            // 8. Verification - recompute HMAC and compare
            mac.init(keySpec); // reinitialize with same key
            String recomputedBase64 = Base64.getEncoder().encodeToString(mac.doFinal(messageBytes));

            if (hmacBase64.equals(recomputedBase64)) 
            {
                System.out.println("Message is authentic and not tampered!");
            } 
            else 
            {
                System.out.println("Message has been modified or key is wrong!");
            }

            // 9. Try tampering
            String tamperedMessage = "Hello Bob, Pay Rs.1000";
            String tamperedHmac = Base64.getEncoder().encodeToString(mac.doFinal(tamperedMessage.getBytes()));

            System.out.println("\nTampered Message: " + tamperedMessage);
            System.out.println("Tampered HMAC: " + tamperedHmac);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
