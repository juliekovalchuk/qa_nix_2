package unit5.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import unit5.entity.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Custom implementation DB in memory
 */
@Component
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRepository {
    User[] users = new User[]{
            new User("Alex", "Jonson", "alexEmail@gmail.com", 37, LocalDate.of(1984, 5, 23)),
            new User("Julia", "Roberts", "juliaEmail@gmail.com", 23, LocalDate.of(1997, 10, 4)),
            new User("Julia", "Miledi", "milediEmail@gmail.com", 24, LocalDate.of(1997, 10, 1))};

    public void clearAllUsers() {
        users = null;
    }

    public void addUserToRepository(@NonNull String firstName, @NonNull String lastName,
                                    @NonNull String email, @NonNull Integer age, @NonNull LocalDate birthDate) {
        if (isEmailUnique(email)) {
            final User newCreatedUser = new User(firstName, lastName, email, age, birthDate);
            if (users == null) {
                users = new User[1];
                users[0] = newCreatedUser;
            } else {
                users = addUser(users.length, users, newCreatedUser);
            }
        } else {
            throw new IllegalArgumentException("Email is not unique");
        }
    }

    public User[] addUser(int usersLength, User[] users, User newUser) {
        User[] newArray = new User[usersLength + 1];
        if (usersLength >= 0) {
            System.arraycopy(users, 0, newArray, 0, usersLength);
        }
        newArray[usersLength] = newUser;
        return newArray;
    }

    public boolean isEmailUnique(@NonNull String email) {
        if (users == null) {
            return true;
        }
        return IntStream.range(0, users.length)
                .noneMatch(i -> users[i].getEmail().equals(email));
    }

    public User findUserByEmail(@NonNull String email) {
        if (users == null) {
            throw new IllegalArgumentException("No users in DB - create someone first");
        }
        return Arrays.stream(users)
                .filter(u -> u.getEmail().equals(email))
                .findAny()
                .orElseThrow();

    }

    public void removeUserByEmail(@NonNull String email) {
        final User userByEmail = findUserByEmail(email);
        final User[] newUsers = new User[users.length - 1];
        int bound = users.length;
        for (int i = 0, j = 0; i < bound; i++) {
            if (!users[i].getEmail().equals(userByEmail.getEmail())) {
                newUsers[j++] = users[i];
            }
        }
        users = newUsers;
    }
}
