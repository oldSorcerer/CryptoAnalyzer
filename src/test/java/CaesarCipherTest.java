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

    @Test
    public void encrypt1() {
        assertEquals(caesar.encrypt("abc", 3),"def");
    }

    @Test
    public void encrypt2() {
        assertEquals(caesar.encrypt("abc", 29),"def");
    }

    @Test
    public void encrypt3() {
        assertEquals(caesar.encrypt("zoo", 3),"crr");
    }

    @Test
    public void encrypt4() {
        assertEquals(caesar.encrypt("Δ", 3),"");
    }

    @Test
    public void decrypt1() {
        assertEquals(caesar.decrypt("def", 3), "abc");
    }

    @Test
    public void decrypt2() {
        assertEquals(caesar.decrypt("def", 29),"abc");
    }

    @Test
    public void decrypt3() {
        assertEquals(caesar.decrypt("crr", 3),"zoo");
    }

}