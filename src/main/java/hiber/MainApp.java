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

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Yerzhan", "Khabulov", "e.khabulov@gmail.com");
      User user2 = new User("Nuraly", "Nurgaziyev", "n.nurgaziyev@gmail.com");
      User user3 = new User("Adil", "Kalzhigitov", "a.kalzhigitov@gmail.com");
      User user4 = new User("Yernur", "Nurtusov", "e.nurtusov@gmail.com");

      Car car1 = new Car("Toyota", 111);
      Car car2 = new Car("Lexus", 777);
      Car car3 = new Car("Honda", 333);
      Car car4 = new Car("BMW", 666);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User userByCar = userService.getUserByCarModelAndSeries("Toyota", 111);
      System.out.println(user1.getCar() + " у " + userByCar.getFirstName() + " " + userByCar.getLastName());
      User userByCar2 = userService.getUserByCarModelAndSeries("BMW", 666);
      System.out.println(user2.getCar() + " у " + userByCar2.getFirstName() + " " + userByCar2.getLastName());




      context.close();
   }
}
