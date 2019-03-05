package com.kingh.stu.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class DatabaseInit {

//    private static final Logger logger = LoggerFactory.getLogger(DatabaseInit.class);

    // 控制sql的版本，每次修改自增
    private static final String version = "0.0";

    //数据库连接URL，当前连接的是E:/H2目录下的gacl数据库
    private static final String JDBC_URL = "jdbc:h2:~/mydb";
    //连接数据库时使用的用户名
    private static final String USER = "sa";
    //连接数据库时使用的密码
    private static final String PASSWORD = "";
    //连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
    private static final String DRIVER_CLASS = "org.h2.Driver";
    //要初始化的表
    private static final Set<String> tables = new HashSet<>();

    static {

        // 要初始化的表
        tables.add("cfg_db_version");
        tables.add("t_student");

        try {
            main(null);
        } catch (Exception e){
            System.out.println("初始化数据库失败");
        }
    }

    public static void main(String[] args) throws Exception {
        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();

        // 先查看是否有cfg_db_version这张表

        boolean hasInit = true;
        try{
            String selectVersion = "select _version from cfg_db_version";
            ResultSet rs = stmt.executeQuery(selectVersion);
            rs.next();
            String databaseVersion = rs.getString(1);
            if(databaseVersion != null && databaseVersion.compareTo(version) < 0) {
                hasInit = false;
            }
        } catch (Exception e) {
            hasInit = false;
        }

        if(!hasInit) {
            // 执行sql
            for (String table : tables) {
                execute(table, stmt);
            }
        }

        // 更新cfg_db_version为当前版本
        String updateSql = "update cfg_db_version set _version = '" + version + "'";
        stmt.execute(updateSql);

        //新增
        stmt.close();
        //关闭连接
        conn.close();
    }

    private static void execute(String sqlFile, Statement stmt) throws Exception {
        File file = new File("conf/" + sqlFile + ".sql");
        System.out.println(file.getAbsolutePath());
        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuffer content = new StringBuffer();
        String line = null;

        while((line = reader.readLine()) != null) {
            content.append(line);
        }

        String []st = content.toString().split(";");
        for(String s : st) {
            System.out.println("执行sql " + s);
            stmt.execute(s);
        }

    }


    public static void init() throws Exception {
        Class.forName(DatabaseInit.class.getName());
    }
}
