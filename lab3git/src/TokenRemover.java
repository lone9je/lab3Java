
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class TokenRemover {
	  /**
     * Удаляет лексемы из входного файла и сохраняет результат в выходной файл.
     *
     * @param inputFilePath  Путь к входному файлу.
     * @param tokensFilePath Путь к файлу с лексемами для удаления.
     * @param outputFilePath Путь к выходному файлу.
     * @throws IOException Если возникает ошибка ввода-вывода.
     */
    public void removeTokens(String inputFilePath, String tokensFilePath, String outputFilePath) throws IOException {
        Set<String> tokens = readTokens(tokensFilePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, false))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = processLine(line, tokens);
                writer.write(processedLine);
                writer.newLine();
            }
        }
    }
    /**
     * Обрабатывает строку, удаляя из нее лексемы.
     *
     * @param line   Строка для обработки.
     * @param tokens Множество лексем для удаления.
     * @return Обработанная строка.
     */
    private String processLine(String line, Set<String> tokens) {
        String[] words = line.split(" ");
        StringBuilder newLine = new StringBuilder();

        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
            if (!tokens.contains(cleanWord)) {
                newLine.append(word).append(" ");
            }
        }

        return newLine.toString().trim();
    }

    /**
     * Читает лексемы из файла.
     *
     * @param tokensFilePath Путь к файлу с лексемами.
     * @return Множество лексем.
     * @throws IOException Если возникает ошибка ввода-вывода.
     */
    private Set<String> readTokens(String tokensFilePath) throws IOException {
        Set<String> tokens = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(tokensFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tokens.add(line.trim());
            }
        }
        return tokens;
    }
    // Новый метод для подсчета количества удаленных лексем
    public int countRemovedTokens(String inputFilePath, String tokensFilePath) throws IOException {
        Set<String> tokens = readTokens(tokensFilePath);
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String token : tokens) {
                    if (line.contains(token)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
