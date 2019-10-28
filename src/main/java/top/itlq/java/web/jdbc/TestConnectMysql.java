package top.itlq.java.web.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Properties;

/**
 * https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html
 * https://mariadb.com/kb/en/library/mariadb-connector-j/
 */
public class TestConnectMysql {

    private Formatter formatter = new Formatter(System.out);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeAll
    static void beforeAll(){
        Properties properties = new Properties();

        try (
                InputStream inputStream = DBUtils.class.getResourceAsStream("/jdbc.properties");
        ){
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("jdbc数据读取失败，确保classpath下有jdbc.properties文件");
        }
        DBUtils.connect(properties.getProperty("url"),
                properties.getProperty("user"),properties.getProperty("password"));
    }

    @Test
    void testSelect() throws SQLException {
        ResultSet resultSet = DBUtils.selectAllFromUser();
        String format =  "%-5s%-10s%-10s%-20s";
        formatter.format(format,"id","name","age","birth");
        System.out.println();
        while (resultSet.next()){
            formatter.format(format,
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    simpleDateFormat.format(resultSet.getDate("birth"))
            );
            System.out.println();
        }
    }

    @Test
    void testInsert() throws SQLException {
        int key = DBUtils.insertUserTest(
                "li",23, new java.util.Date());
        System.out.println("新增记录主键：" + key);
    }

    @Test
    void testUpdate() throws SQLException {
        DBUtils.updateTest(12,"lee",23, new Date());
    }

    @Test
    void testDelete() throws SQLException {
        DBUtils.deleteTest(8);
    }
}
