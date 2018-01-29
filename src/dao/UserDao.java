package dao;

import cn.itcast.jdbc.TxQueryRunner;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UserDao {
    private QueryRunner queryRunner = new TxQueryRunner();

    public void add(User user){
        try{
            String sql = "INSERT INTO user VALUES (?,?,?,?,?)";
            Object[] parameter = {user.getID(), user.getPassword(), user.getPassword(),
                    user.getPhone(), user.getEmail()};

            queryRunner.update(sql,parameter);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*public void edit(User user){
        try{
            String sql = "UPDATE user SET username = ?, password = ?, phone = ?," +
                        " email = ?";
            Object[] parameter = {user.getPassword(), user.getPassword(),
                                    user.getPhone(), user.getEmail()};
            queryRunner.update(sql, parameter);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(String id){
        try{
            String sql = "DELETE FROM user WHERE id = ?";
            queryRunner.update(sql, id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }*/

    public User findUser(String username){
        User user = new User();
        try{
            String sql = "SELECT * FROM user WHERE username = ?";
            queryRunner.query(sql, new ScalarHandler<>(),username);
            user.setUsername(username);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }


}
