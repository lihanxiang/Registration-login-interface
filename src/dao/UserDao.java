package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;

public class UserDao {

    //配置数据源
    private ComboPooledDataSource dataSource = new ComboPooledDataSource();
    //将数据源作为QueryRunner的参数
    private QueryRunner queryRunner = new QueryRunner(dataSource);

    //添加用户
    public void add(User user){
        try{
            //sql语句中的 ？将用Object数组元素作为替换参数
            String sql = "INSERT INTO user VALUES (?,?,?,?)";
            Object[] parameter = {user.getUsername(), user.getPassword(),
                    user.getPhone(), user.getEmail()};
            //执行sql语句
            queryRunner.update(sql,parameter);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //在注册和登陆之前，都需要在数据库中查找用户
    public User findUser(String username){
        //实例化
        User user = new User();
        try{
            //用username作为参数，将查询结果封装并返回
            String sql = "SELECT * FROM user WHERE username = ?";
            user = queryRunner.query(sql, new BeanHandler<>(User.class), username);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
