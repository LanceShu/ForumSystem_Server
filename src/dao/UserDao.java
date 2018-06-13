package dao;

import idao.IUser;
import utils.JsonUtil;
import utils.SQLConnectionManager;
import utils.UserUtil;

import java.sql.*;

public class UserDao implements IUser {

    static class Status{
        private String status;
        private String content;

        Status(){}
        Status(String status, String content) {
            this.status = status;
            this.content = content;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public String getContent() {
            return content;
        }
    }

    // register user;
    @Override
    public String registerUser(String username, String password, String gender) {
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        boolean result;
        if (isNewUser(username)) {
            try {
                statement = manager.prepareStatement("INSERT INTO user_list(uname,gender,password) VALUES (\""+username+"\", \""+gender+"\", \""+password+"\")");
                result = statement.execute();
                Status status = result ? new Status("failure", "用户注册失败")
                        : new Status("success", "用户注册成功");
                return JsonUtil.objectToJson(status);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLConnectionManager.close(null, statement, manager);
            }
        } else {
            Status status = new Status("failure", "该用户已被注册");
            return JsonUtil.objectToJson(status);
        }
        return null;
    }

    // the user login in the system;
    @Override
    public String loginSystem(String username, String password) {
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Status status = new Status();
        if (isNewUser(username)) {
            status.setStatus("failure");
            status.setContent("用户不存在");
            return JsonUtil.objectToJson(status);
        } else {
            try {
                statement = manager.prepareStatement("SELECT * FROM user_list WHERE uname = '" + username + "' AND password = '" + password + "'");
                resultSet = statement.executeQuery();
                status.setStatus(resultSet.first() ? "success" : "failure");
                status.setContent(resultSet.first() ? "登录成功" : "登录失败");
                // set current author;
                UserUtil.setUserInstance(resultSet.first() ? username : null);
                return JsonUtil.objectToJson(status);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SQLConnectionManager.close(resultSet, statement, manager);
            }
        }
        return null;
    }

    // whether the newUser is in the databases;
    private boolean isNewUser(String username) {
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = manager.prepareStatement("SELECT * FROM user_list WHERE uname = '" + username + "'");
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLConnectionManager.close(resultSet, statement, manager);
        }
        return true;
    }
}
