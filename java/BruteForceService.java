import java.nio.file.Files;
import java.nio.file.Path;

public class BruteForceService {
    // Список частых русских слов и предлогов для оценки осмысленности текста
    private static final String[] VALIDATION_PATTERNS = {" и ", " в ", " не ", " на ", " что "};
    private static final int ALPHABET_SIZE = 33;

    public static void decrypt(String srcPath, String destPath) throws Exception {
        String encryptedText = Files.readString(Path.of(srcPath));
        String optimalText = "";
        int highestScore = -1;

        for (int key = 0; key < ALPHABET_SIZE; key++) {
            String decryptedAttempt = decryptAttempt(encryptedText, key);
            int currentScore = evaluateText(decryptedAttempt);

            if (currentScore > highestScore) {
                highestScore = currentScore;
                optimalText = decryptedAttempt;
            }
        }

        Files.writeString(Path.of(destPath), optimalText);
    }

    private static String decryptAttempt(String encryptedText, int key) {
        StringBuilder builder = new StringBuilder(encryptedText.length());
        for (char ch : encryptedText.toCharArray()) {
            builder.append(CaesarCipher.encrypt(ch, -key));
        }
        return builder.toString();
    }

    private static int evaluateText(String text) {
        String lowerText = text.toLowerCase();
        int score = 0;
        for (String pattern : VALIDATION_PATTERNS) {
            if (lowerText.contains(pattern)) {
                score++;
            }
        }
        return score;
    }
}
