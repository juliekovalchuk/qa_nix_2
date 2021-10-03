package all.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AllOperationsThirdTask {
    public static void main(String[] args) throws IOException {
        System.out.println("Please, enter number of lesson, from 1 to 10, when you finish - just press 'Enter'");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isLessonNumberCorrect = false;
        int lessonNumber = 0;
        while (!isLessonNumberCorrect) {
            try {
                lessonNumber = Integer.parseInt(reader.readLine());
                if (lessonNumber < 0 || lessonNumber > 11) {
                    System.out.println("Lesson number can be from 1 to 10");
                } else {
                    isLessonNumberCorrect = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, input correct number of lesson");
            }
        }
        LocalDateTime lessonStartTime = LocalDate.now().atTime(9, 0);
        int durationOfAllLessons = lessonNumber * 45;
        int durationOfBrake = 0;
        if (lessonNumber > 1) {
            for (int i = 0; i < lessonNumber - 1; i++) {
                if (i % 2 == 0) {
                    durationOfBrake += 5;
                } else {
                    durationOfBrake += 15;
                }
            }
        }
        final LocalDateTime lessonFinishedTime = lessonStartTime.plusMinutes(durationOfAllLessons + durationOfBrake);
        System.out.println("Lesson will be finished at " +
                lessonFinishedTime.getHour() + ":" + lessonFinishedTime.getMinute());
        reader.close();
    }
}
