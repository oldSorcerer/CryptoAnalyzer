import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaesarCipherTest {

    private final CaesarCipher caesar = new CaesarCipher();

    private final String stringNotEncrypt = "Hello World!";
    private final String stringEncrypt = "Khoor*Zruog+";
    private final int key = 3;

    @Test
    public void encrypt() {
        assertEquals(caesar.encrypt(stringNotEncrypt, key), stringEncrypt);
    }
    @Test
    public void decrypt() {
        assertEquals(caesar.decrypt(stringEncrypt, key), stringNotEncrypt);
    }

}