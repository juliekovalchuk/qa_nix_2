package reversestring;

import java.util.Arrays;

public class CustomStringBuilder {

    //classic reverse
    public static String reverse(String data) {
        StringBuilder builder = new StringBuilder();
        char [] reversedArray = new char [data.length()];
        char [] array = data.toCharArray();

        for (int i = 1; i < array.length + 1; i++) {
            reversedArray[array.length - i] = array[i - 1];
        }

        for (int i = 0; i < reversedArray.length; i++) {
            builder.append(reversedArray[i]);
        }
        return builder.toString();
    }

    public static String reverse(String data, boolean reverseAll) {
        if (reverseAll) {
            return reverse(data);
        } else {
            String [] splitBySpace = data.split(" ");
        }
    }


    public static void main(String[] args) {
        System.out.println(reverse("hello world"));
    }
}
