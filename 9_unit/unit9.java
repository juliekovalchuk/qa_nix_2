package unit9;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class unit9 {
    private static final String READ_FILE_NAME = "input.txt";
    private static final String WRITE_FILE_NAME = "output.txt";
    private static final String SPACE = " ";
    private static final String LINE_BREAK = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();
        final String content = readFile();
        builder.append(amountOfUniqueWords(content))
                .append(LINE_BREAK)
                .append(amountOfSentences(content))
                .append(LINE_BREAK)
                .append(LINE_BREAK)
                .append(writeAmountOfPalindrome(content));

        writeToFile(builder.toString());
    }

    private static String readFile() throws IOException {
        return Files.readString(Paths.get(READ_FILE_NAME));
    }

    private static void writeToFile(final String content) throws IOException {
        Files.writeString(Paths.get(WRITE_FILE_NAME), content, StandardCharsets.UTF_8);
    }

    private static String amountOfSentences(final String content) {
        return "Amount of sentences is - " + content.split("\\.").length;
    }

    private static String amountOfUniqueWords(final String content) {
        final List<String> words = splitContent(content);

        final Map<String, Integer> map = new HashMap<>();
        words.forEach(word -> {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        });

        StringBuilder builder = new StringBuilder();

        sortMap(map).forEach((key, value) -> builder.append(key)
                .append(" - ")
                .append(value)
                .append(LINE_BREAK));

        return builder.toString();
    }

    private static String writeAmountOfPalindrome(final String content) {
        final List<String> words = splitContent(content);

        final Map<String, Integer> map = new HashMap<>();
        words.forEach(word -> {
            if (isPalindrome(word)) {
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                } else {
                    map.put(word, map.get(word) + 1);
                }
            }
        });

        StringBuilder builder = new StringBuilder();

        sortMap(map).forEach((key, value) -> builder.append(key)
                .append(" - ")
                .append(value)
                .append(LINE_BREAK));

        return builder.toString();
    }

    private static List<String> splitContent(final String content) {
        // split by line brake than collect in to list
        // and split by space
        return Arrays.stream(content.split(LINE_BREAK))
                .collect(Collectors.toUnmodifiableList())
                .stream()
                .map(splitByLineBrake -> Arrays.stream(splitByLineBrake.split(SPACE))
                        .collect(Collectors.toUnmodifiableList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static LinkedHashMap<String, Integer> sortMap(final Map<String, Integer> map) {
        return map.
                entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static boolean isPalindrome(final String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        String rev = sb.toString();
        return str.equals(rev);
    }
}