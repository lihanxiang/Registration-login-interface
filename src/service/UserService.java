package service;


import dao.UserDao;
import domain.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public void regist(User user) throws UserException{
        User user1 = userDao.findUser(user.getUsername());
        if(user1 != null){
            throw new UserException("该用户名已被注册");
        }
        userDao.add(user);
    }

    public User login(User user) throws UserException{
        User user1 = userDao.findUser(user.getUsername());
        if(user1 == null){
            throw new UserException("该用户名不存在");
        }
        if(!user1.getPassword().equals(user.getPassword())){
            throw new UserException("密码错误");
        }
        return user1;
    }
}
