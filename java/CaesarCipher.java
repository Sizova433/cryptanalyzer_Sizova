package service;
public class CaesarCipher {
    private static final String RU_UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String RU_LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static char encrypt(char ch, int offset) {
        int upperIdx = RU_UPPER.indexOf(ch);
        if (upperIdx != -1) {
            return shiftCharacter(upperIdx, offset, RU_UPPER);
        }

        int lowerIdx = RU_LOWER.indexOf(ch);
        if (lowerIdx != -1) {
            return shiftCharacter(lowerIdx, offset, RU_LOWER);
        }

        return ch;
    }

    private static char shiftCharacter(int currentIdx, int offset, String alphabet) {
        int length = alphabet.length();
        // Защита от отрицательного остатка при делении в Java
        int newIdx = (currentIdx + (offset % length) + length) % length;
        return alphabet.charAt(newIdx);
    }
}
