package main.java;

public class CaesarCipher {

    private static final String ALPHABET_PART_ONE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                                                    "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!? +-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789";
    private static final String ALPHABET_PART_TWO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
                                                    "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!? +-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789";
    private static final String ALPHABET = ALPHABET_PART_ONE + ALPHABET_PART_TWO;

    public int alphabetLength() {
        return ALPHABET.length() / 2;
    }

    public String encrypt(String message, int key) {

        StringBuilder result = new StringBuilder();

        for (char aChar : message.toCharArray()) {

            int origAlphabetPos = ALPHABET.indexOf(aChar);

            int newAlphabetPos;
            char newCharacter = 0;

            if (origAlphabetPos >= 0) {
                if (key >= 0) {
                    newAlphabetPos = (origAlphabetPos + key) % (ALPHABET.length() / 2);
                } else {
                    int newKey = key % (ALPHABET.length() / 2);
                    newAlphabetPos = (origAlphabetPos + (ALPHABET.length() / 2) + newKey) % ALPHABET.length();
                }
                newCharacter = ALPHABET.charAt(newAlphabetPos);
            }
            result.append(newCharacter);
        }
        return result.toString();
    }

    public String deEncrypt(String message, int key) {
        return encrypt(message, key * (-1));
    }
}
