public class CaesarCipher {

    private static final String ALPHABET =   "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                        "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
                        ".,\":!? +-*/\\@#$%^&(){}[];'|`~=_©«»—" + "0123456789";

    public int alphabetLength() {
        return ALPHABET.length();
    }

    public String encrypt(String message, int key) {
        StringBuilder builder = new StringBuilder();
        for (char aChar : message.toCharArray()) {
            int index = ALPHABET.indexOf(aChar);
            if (index >= 0) {
                int newIndex = (index + key) % ALPHABET.length();
                char charAt = newIndex < 0 ? ALPHABET.charAt(ALPHABET.length() + newIndex) : ALPHABET.charAt(newIndex);
                builder.append(charAt);
            }
        }
        return builder.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, key * (-1));
    }
}