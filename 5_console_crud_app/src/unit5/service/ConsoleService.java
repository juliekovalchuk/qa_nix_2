package unit5.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import unit5.entity.User;
import unit5.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class ConsoleService {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private final UserRepository userRepository;

    public User createNewUser() throws IOException {
        final String email = userEmailValidation();
        if (!userRepository.isEmailUnique(email)) {
            throw new IllegalArgumentException("Email is not unique");
        }
        final String firstName = firstName();
        final String lastName = lastName();
        final int age = ageValidation();
        final LocalDate date = birthDateValidation();
        return new User(firstName, lastName, email, age, date);
    }

    public String userEmailValidation() throws IOException {
        String emailRegEx = "^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        System.out.println("Enter the users's email address \nFor example: \"YourExample@gmail.com\"");
        while (true) {
            String recipient = READER.readLine();

            Pattern p = Pattern.compile(emailRegEx);
            Matcher m = p.matcher(recipient);
            if (m.find()) {
                return recipient;
            }
            System.err.println("E-mail is not correct, try again");
        }
    }

    public String firstName() throws IOException {
        System.out.println("Enter the first name of user");
        return READER.readLine();
    }

    public String lastName() throws IOException {
        System.out.println("Enter the last name of user");
        return READER.readLine();
    }


    public LocalDate birthDateValidation() throws IOException {
        LocalDate birthDate = null;
        System.out.println("Pay attention to a birthDate template, birthDate has to be entered only in this format\n" +
                "month/day/year - for example - 09/12/2020\n" +
                "Enter birthDate of new user:");
        int count = 0;
        do {
            try {
                count = 0;
                birthDate = LocalDate.parse(READER.readLine(), FORMATTER);
            } catch (DateTimeParseException exception) {
                count++;
                System.err.println("Pay attention to a birthDate template, and try again");
            }
        } while (count != 0);

        return birthDate;
    }

    public int ageValidation() throws IOException {
        System.out.println("Enter age of user, please");
        int age = 0;
        int count = 0;
        do {
            try {
                count = 0;
                age = Integer.parseInt(READER.readLine());
            } catch (NumberFormatException e) {
                count++;
                System.err.println("You have entered an unknown character, use only digits");
            }
        } while (count != 0);
        return age;
    }

    public int choiceValidation() throws IOException {
        int choiceNumber = 0;
        int count = 0;
        do {
            try {
                count = 0;
                choiceNumber = Integer.parseInt(READER.readLine());
                if (choiceNumber <= 0 || choiceNumber >= 5) {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException e) {
                count++;
                System.err.println("You have entered an unknown character, use only digits");
            } catch (IllegalArgumentException e) {
                count++;
                System.out.println("You can select only from 1,2,3,4 or 5, nothing else");
            }
        } while (count != 0);
        return choiceNumber;
    }

    public void doAction(int choice) throws IOException {
        switch (choice) {
            case 1:
                System.out.println("You`ve selected number ONE - see all users");
                System.out.println(Arrays.toString(userRepository.getUsers()));
                System.out.println("Great choice, would you like to do something else?");
                printChoices();
                break;
            case 2:
                System.out.println("You`ve selected number TWO - create new user");
                System.out.println("New create user - " + createNewUser().toString() + ", we have added him to DB");
                System.out.println("Great choice, would you like to do something else?");
                printChoices();
                break;
            case 3:
                System.out.println("You`ve selected number THREE - delete user");
                System.out.println("Enter email of user that you want to delete");
                userRepository.removeUserByEmail(userEmailValidation());
                System.out.println("User has been removed");
                System.out.println("Great choice, would you like to do something else?");
                printChoices();
                break;
            case 4:
                System.out.println("You`ve selected number FOUR - delete all users");
                userRepository.clearAllUsers();
                System.out.println("All users have been removed");
                System.out.println("Great choice, would you like to do something else?");
                printChoices();
                break;
            case 5:
                System.out.println("You`ve selected number FIVE - find specific user by email");
                userRepository.findUserByEmail(userEmailValidation());
                System.out.println("Great choice, would you like to do something else?");
                printChoices();
                break;
            default:
                throw new IllegalArgumentException("Unknown choice number");
        }
    }

    public void printWelcomeMessage() {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to small crud app which has been created by Julia");
        System.out.println("You can create/edit/delete/get user");
    }

    public void printChoices() throws IOException {
        System.out.println("Enter '1' - if you want to see all users");
        System.out.println("Enter '2' - if you want create new user");
        System.out.println("Enter '3' - if you want delete user");
        System.out.println("Enter '4' - if you want delete all users");
        System.out.println("Enter '5' - if you want find specific user by email");
        doAction(choiceValidation());
    }
}
