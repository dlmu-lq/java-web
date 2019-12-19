package top.itlq.java.web.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSourceUtils {

    private static final Properties properties;

    /**
     * 加载类时加载jdbc配置文件
     */
    static {
        properties = new Properties();
        try (
                InputStream inputStream = DataSourceUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        ){
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("jdbc数据读取失败，确保classpath下有jdbc.properties文件");
        }
    }

    /**
     * 获取连接，使用完后需要关闭
     * @return
     */
    public static Connection getConnection(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(properties.getProperty("url"));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("通过MysqlDataSource获取Connection失败");
        }
    }

    /**
     * 每次操作获取新连接，用完即关闭
     * @return
     */
    public static ResultSet selectAllFromUser(){
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery("select * from user");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败！");
        }
    }
}
