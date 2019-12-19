package top.itlq.java.web.jdbc;

import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc 简单操作数据库
 */
public class DBUtils {

    // 连接加载类时即创建，不关闭；线程安全问题？，见DataSourceUtils
    private static final Connection connection;

    static {
        Properties properties = new Properties();
        try(
                InputStream inputStream = DBUtils.class.getResourceAsStream("/jdbc.properties");
        ) {
//             不再必须，DriverManager 在 getConnection 时会自动加载驱动
//            Class.forName("org.mariadb.jdbc.Driver");
//            Class.forName("com.mysql.jdbc.Driver");
            properties.load(inputStream);
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("user"),properties.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("jdbc数据读取失败，确保classpath下有jdbc.properties文件");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接失败！");
        }
    }

    /**
     *
     *
     * 简单查询测试
     * @return
     */
    public static ResultSet selectAllFromUser(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败！");
        }
    }

    /**
     * 插入测试
     */
    public static int insertUserTest(String name, int age, java.util.Date birth) throws SQLException {
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into user(name,age,birth) values(?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ){
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setDate(3, new Date(birth.getTime()));
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }else{
                throw new RuntimeException("插入失败");
            }
        }
    }

    /**
     * 更新测试
     */
    public static void updateTest(Integer id, String name, Integer age, java.util.Date birth) throws SQLException {
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "update user set name = ?,age = ?,birth = ? where id = ?")
        ){
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setDate(3, new Date(birth.getTime()));
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
        }
    }

    /**
     * 删除测试
     */
    public static void deleteTest(Integer id) throws SQLException {
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "delete from user where id = ?")
        ){
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }
}
