package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SQLConnectionManager {

    private static SQLConnectionManager instance;
    private static ComboPooledDataSource ds;

    /**
     * 对MySQL数据库进行初始化，进行连接；
     * */
    static {
        ResourceBundle rb = ResourceBundle.getBundle("c3p0");
        ds = new ComboPooledDataSource();
        try{
            ds.setDriverClass(rb.getString("driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        ds.setJdbcUrl(rb.getString("url"));
        ds.setUser(rb.getString("username"));
        ds.setPassword(rb.getString("password"));
    }

    /**
     * 获取数据库实例；
     * 单例模式;
     * */
    public synchronized static final SQLConnectionManager getInstance(){
        if(instance == null){
            instance = new SQLConnectionManager();
        }
        return instance;
    }

    /**
     * 获取数据库连接
     * */
    public synchronized final Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接;
     * */
    public static void close(ResultSet rs, Statement statement,Connection connection){
        try {
            if(rs != null){
                rs.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 释放数据库的资源
     * */
    @Override
    protected void finalize() throws Throwable {
        DataSources.destroy(ds);
        super.finalize();
    }
}
