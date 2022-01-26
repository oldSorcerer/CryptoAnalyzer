public class CaesarCipher {

    public int maxSize = 36;

    public String encrypt(String message, int key) {

        String alphabetEnUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetEnLowerCase = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";

        String alphabetRuUpperCase = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        String alphabetRuLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        String symbols = ".,\":-!? +-*/\\@#$%^&(){}[];'|`~=_—©«».,\":-!? +-*/\\@#$%^&(){}[];'|`~=_—©«»";

        StringBuilder result = new StringBuilder();

        for (char aChar : message.toCharArray()) {

            int origAlphabetPosEnUC = alphabetEnUpperCase.indexOf(aChar);
            int origAlphabetPosEnLC = alphabetEnLowerCase.indexOf(aChar);

            int origAlphabetPosRuUC = alphabetRuUpperCase.indexOf(aChar);
            int origAlphabetPosRuLC = alphabetRuLowerCase.indexOf(aChar);

            int origSymbolPos = symbols.indexOf(aChar);

            int newAlphabetPos;
            char newCharacter = 0;

            if (origAlphabetPosEnUC >= 0) {
                newAlphabetPos = newAlphabetPos(origAlphabetPosEnUC, alphabetEnUpperCase, key);
                newCharacter = alphabetEnUpperCase.charAt(newAlphabetPos);
            } else if (origAlphabetPosEnLC >= 0) {
                newAlphabetPos = newAlphabetPos(origAlphabetPosEnLC, alphabetEnLowerCase, key);
                newCharacter = alphabetEnLowerCase.charAt(newAlphabetPos);
            } else if (origAlphabetPosRuUC >= 0) {
                newAlphabetPos = newAlphabetPos(origAlphabetPosRuUC, alphabetRuUpperCase, key);
                newCharacter = alphabetRuUpperCase.charAt(newAlphabetPos);
            } else if (origAlphabetPosRuLC >= 0) {
                newAlphabetPos = newAlphabetPos(origAlphabetPosRuLC, alphabetRuLowerCase, key);
                newCharacter = alphabetRuLowerCase.charAt(newAlphabetPos);
            } else if (origSymbolPos >= 0) {
                newAlphabetPos = newAlphabetPos(origSymbolPos, symbols, key);
                newCharacter = symbols.charAt(newAlphabetPos);
            }

            result.append(newCharacter);
        }

        return result.toString();
    }

    public String deEncrypt(String message, int key) {
        return encrypt(message, key * (-1));
    }

    private int newAlphabetPos(int origAlphabetPos, String alphabet, int key) {
        int newAlphabetPos;
        if (key > 0) {
            newAlphabetPos = (origAlphabetPos + key) % (alphabet.length() / 2);
        } else {
            newAlphabetPos = (origAlphabetPos + (alphabet.length() / 2) + key) % alphabet.length();
        }
        return newAlphabetPos;
    }
}
