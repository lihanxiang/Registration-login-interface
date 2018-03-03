package service;

import dao.UserDao;
import domain.User;

public class UserService {
    //实例化
    private UserDao userDao = new UserDao();

    //如果注册成功，就将信息添加至数据库
    public void register(User user) throws UserException{
        //实例化User类用以查找用户
        User _user = userDao.findUser(user.getUsername());
        if(_user != null){
            throw new UserException("该用户名已被注册");
        }
        userDao.add(user);
    }

    //如果登陆成功，就以数据库中匹配的用户形式返回
    public User login(User user) throws UserException{
        //实例化User类用以查找用户
        User _user = userDao.findUser(user.getUsername());

        if(_user == null){
            throw new UserException("该用户不存在");
        } else if(!_user.getPassword().equals(user.getPassword())){
            throw new UserException("密码错误");
        }
        //以已注册的用户形式返回
        return _user;
    }
}
