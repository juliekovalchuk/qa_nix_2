package all.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllOperationsFirstTask {
    public static void main(String[] args) throws IOException {
        System.out.println("Please, enter your message, when you finish - just press 'Enter'");
        int sum = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        char [] array = input.toCharArray();

        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i])) {
                sum = sum + Character.getNumericValue(array[i]);
            }
        }
        System.out.println("Your result is - " + sum);
        reader.close();
    }
}
