package service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class StatisticalAnalysisService {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final char MOST_FREQUENT_RU_CHAR = 'о';

    public static void decrypt(String srcPath, String destPath) throws Exception {
        String content = Files.readString(Path.of(srcPath)).toLowerCase();
        Map<Character, Integer> charFrequencies = new HashMap<>();

        for (char ch : content.toCharArray()) {
            if (ALPHABET.indexOf(ch) != -1) {
                charFrequencies.put(ch, charFrequencies.getOrDefault(ch, 0) + 1);
            }
        }

        char mostPopularChar = MOST_FREQUENT_RU_CHAR;
        int maxFrequency = -1;

        for (Map.Entry<Character, Integer> entry : charFrequencies.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostPopularChar = entry.getKey();
            }
        }

        int estimatedKey = ALPHABET.indexOf(mostPopularChar) - ALPHABET.indexOf(MOST_FREQUENT_RU_CHAR);
        FileService.processFile(srcPath, destPath, -estimatedKey);
    }
}
