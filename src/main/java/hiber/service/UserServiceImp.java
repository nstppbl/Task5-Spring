package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      if (userDao.findByEmail(user.getEmail()) == null) {
         userDao.add(user);
         System.out.println("Пользователь " + user.getFirstName() + " добавлен.");
      } else {
         System.out.println("Пользователь с таким email уже существует: " + user.getEmail());
      }
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByCarModelAndSeries(String model, int series){
      return userDao.getUserByCarModelAndSeries(model, series);
   }


}
