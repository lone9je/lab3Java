
import java.io.*;
/**
 * Главный класс для демонстрации работы класса TokenRemover.
 */
public class Main {
	/**
     * Точка входа в программу.
     *
     * @param args Аргументы командной строки:
     *             args[0] - путь к входному файлу,
     *             args[1] - путь к файлу с лексемами,
     *             args[2] - путь к выходному файлу.
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <inputFile> <tokensFile> <outputFile>");
            return;
        }

        String inputFilePath = args[0];
        String tokensFilePath = args[1];
        String outputFilePath = args[2];

        TokenRemover remover = new TokenRemover();
        try {
            remover.removeTokens(inputFilePath, tokensFilePath, outputFilePath);
            System.out.println("Tokens removed successfully. Check the output file: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
