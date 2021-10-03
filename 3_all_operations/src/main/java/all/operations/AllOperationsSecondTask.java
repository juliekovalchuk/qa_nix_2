package all.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class AllOperationsSecondTask {
    public static void main(String[] args) throws IOException {
        System.out.println("Please, enter your message, when you finish - just press 'Enter'");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        char [] array = input.toCharArray();
        // Character - key , Integer - value
        Map<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < array.length; i++) {
            if (Character.isLetter(array[i])) {
                final Integer amountOfRepetition = map.get(array[i]);
                if (map.containsKey(array[i])) {
                    map.put(array[i], amountOfRepetition + 1);
                } else {
                    map.put(array[i], 1);
                }
            }
        }
        System.out.println("Your sorted values - " + map);
        reader.close();
    }
}
