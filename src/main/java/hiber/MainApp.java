package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class, Car.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru")
                .setCar(new Car("BMW", 123));

        User user2 = new User("User2", "Lastname2", "user1@mail.ru")
                .setCar(new Car("BMW", 234));

        User user3 = new User("User3", "Lastname3", "user1@mail.ru")
                .setCar(new Car("BMW", 345));

        User user4 = new User("User4", "Lastname4", "user1@mail.ru")
                .setCar(new Car("BMW", 456));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        userService.delete(user3);


        System.out.println(userService.getUserByCar("BMW", 123));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        context.close();
    }
}