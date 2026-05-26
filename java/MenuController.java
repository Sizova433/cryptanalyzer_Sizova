package controller;
import service.BruteForceService;
import service.FileService;
import service.StatisticalAnalysisService;
import java.util.Scanner;

public class MenuController {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("""
                    
                    ===== ШИФР ЦЕЗАРЯ =====
                    1. Шифрование
                    2. Расшифровка
                    3. Brute Force
                    4. Статистический анализ
                    5. Выход
                    """);

            String choice = promptUser("Выберите действие: ").trim();

            switch (choice) {
                case "1" -> runEncryption();
                case "2" -> runDecryption();
                case "3" -> runBruteForce();
                case "4" -> runStatisticalAnalysis();
                case "5" -> {
                    System.out.println("Выход из программы...");
                    return;
                }
                default -> System.out.println("Неверный пункт меню!");
            }
        }
    }

    private void runEncryption() {
        try {
            String input = promptUser("Введите путь к исходному файлу: ");
            String output = promptUser("Введите путь к выходному файлу: ");
            int key = Integer.parseInt(promptUser("Введите ключ (сдвиг): "));

            FileService.processFile(input, output, key);
            System.out.println("Файл зашифрован успешно!");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Ключ шифрования должен быть целым числом.");
        } catch (Exception e) {
            System.out.println("Ошибка работы: " + e.getMessage());
        }
    }

    private void runDecryption() {
        try {
            String input = promptUser("Введите путь к зашифрованному файлу: ");
            String output = promptUser("Введите путь к выходному файлу: ");
            int key = Integer.parseInt(promptUser("Введите ключ (сдвиг): "));

            FileService.processFile(input, output, -key);
            System.out.println("Файл расшифрован успешно!");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Ключ должен быть целым числом.");
        } catch (Exception e) {
            System.out.println("Ошибка работы: " + e.getMessage());
        }
    }

    private void runBruteForce() {
        try {
            String input = promptUser("Введите путь к зашифрованному файлу: ");
            String output = promptUser("Введите путь к выходному файлу: ");

            BruteForceService.decrypt(input, output);
            System.out.println("Подбор ключа методом Brute Force успешно выполнен!");
        } catch (Exception e) {
            System.out.println("Ошибка работы: " + e.getMessage());
        }
    }

    private void runStatisticalAnalysis() {
        try {
            String input = promptUser("Введите путь к зашифрованному файлу: ");
            String output = promptUser("Введите путь к выходному файлу: ");

            StatisticalAnalysisService.decrypt(input, output);
            System.out.println("Статистический анализ успешно завершен!");
        } catch (Exception e) {
            System.out.println("Ошибка работы: " + e.getMessage());
        }
    }

    // Вспомогательный метод для сокращения повторяющегося кода ввода
    private String promptUser(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
