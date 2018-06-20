package dao;

import idao.IInvitation;
import model.Invitation;
import utils.JsonUtil;
import utils.SQLConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvitationDao implements IInvitation {

    static class Status {
        private String status;
        private String content;

        public void setContent(String content) {
            this.content = content;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getContent() {
            return content;
        }

        public String getStatus() {
            return status;
        }
    }

    static class Result {
        private String status;
        private List<Invitation> invitationList;

        public void setStatus(String status) {
            this.status = status;
        }

        public void setInvitationList(List<Invitation> invitationList) {
            this.invitationList = invitationList;
        }

        public String getStatus() {
            return status;
        }

        public List<Invitation> getInvitationList() {
            return invitationList;
        }
    }

    // publish a new invitation to public;
    @Override
    public String publishInvitation(String author, String head, String content) {
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        boolean result;
        Status status = new Status();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        try {
            statement = manager.prepareStatement("INSERT INTO all_invitation(iauthor, idate, icontent, iscollected, ihead) VALUES (\""+author+"\",\""+date+"\",\""+content+"\","+false+",\""+head+"\")");
            result = statement.execute();
            if (result) {
                status.setStatus("failure");
                status.setContent("发布失败");
                return JsonUtil.objectToJson(status);
            }
            statement = manager.prepareStatement("INSERT INTO own_invitation(oauthor, odate, ocontent, iscollected, ohead) VALUES (\""+author+"\",\""+date+"\",\""+content+"\","+false+",\""+head+"\")");
            result = statement.execute();
            status.setStatus(result ? "failure" : "success");
            status.setContent(result ? "发布失败" : "发布成功");
            return JsonUtil.objectToJson(status);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLConnectionManager.close(null, statement, manager);
        }
        return null;
    }

    // query all invitation in the database;
    @Override
    public String queryAllInvitations() {
        List<Invitation> invitationList = new ArrayList<>();
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Result result = new Result();
        try {
            statement = manager.prepareStatement("SELECT * FROM all_invitation");
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                do {
                    Invitation invitation = new Invitation(resultSet.getString("iauthor")
                            , resultSet.getString("idate")
                            , resultSet.getString("icontent")
                            , resultSet.getString("ihead")
                            , resultSet.getBoolean("iscollected"));
                    invitationList.add(invitation);
                } while(resultSet.next());
            }
            result.setStatus("success");
            result.setInvitationList(invitationList);
            return JsonUtil.objectToJson(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLConnectionManager.close(resultSet, statement, manager);
        }
        return null;
    }

    // query all invitations user favourite;
    @Override
    public String queryFavouriteInvitations(String author) {
        List<Invitation> invitationList = new ArrayList<>();
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Result result = new Result();
        try {
            statement = manager.prepareStatement("SELECT * FROM favourite_invitation WHERE fauthor='"+author+"'");
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                do {
                    Invitation invitation = new Invitation(resultSet.getString("fowner")
                            , resultSet.getString("fdate")
                            , resultSet.getString("fcontent")
                            , resultSet.getString("fhead")
                            , resultSet.getBoolean("iscollected"));
                    invitationList.add(invitation);
                } while(resultSet.next());
            }
            result.setStatus("success");
            result.setInvitationList(invitationList);
            return JsonUtil.objectToJson(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLConnectionManager.close(resultSet, statement, manager);
        }
        return null;
    }

    // query all invitations owned;
    @Override
    public String queryOwnInvitations(String author) {
        List<Invitation> invitationList = new ArrayList<>();
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Result result = new Result();
        try {
            statement = manager.prepareStatement("SELECT * FROM own_invitation WHERE oauthor='"+author+"'");
            resultSet = statement.executeQuery();
            if (resultSet.first()) {
                do {
                    Invitation invitation = new Invitation(resultSet.getString("oauthor")
                            , resultSet.getString("odate")
                            , resultSet.getString("ocontent")
                            , resultSet.getString("ohead")
                            , resultSet.getBoolean("iscollected"));
                    invitationList.add(invitation);
                } while(resultSet.next());
            }
            result.setStatus("success");
            result.setInvitationList(invitationList);
            return JsonUtil.objectToJson(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLConnectionManager.close(resultSet, statement, manager);
        }
        return null;
    }

    // collect a invitation;
    @Override
    public String collectInvitation(String author, String owner, String date, String content, String head) {
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        Status status = new Status();
        boolean result;
        try {
            statement = manager.prepareStatement("INSERT INTO favourite_invitation(fauthor,fowner,fdate,fcontent,fhead) VALUES (\""+author+"\",\""+owner+"\",\""+date+"\",\""+content+"\",\""+head+"\")");
            result = statement.execute();
            status.setStatus(result ? "failure" : "success");
            status.setContent(result ? "收藏失败" : "收藏成功");
            return JsonUtil.objectToJson(status);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLConnectionManager.close(null, statement, manager);
        }
        return null;
    }

    // uncollect invitation in the datebase;
    @Override
    public String discollectInvitation(String author, String owner, String date, String content) {
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        boolean result;
        Status status = new Status();
        try {
            statement = manager.prepareStatement("DELETE FROM favourite_invitation WHERE fauthor='"+author+"' AND fcontent='"+content+"' AND fowner='"+owner+"'");
            result = statement.execute();
            status.setStatus(result ? "failure" : "success");
            status.setContent(result ? "取消收藏失败" : "取消收藏成功");
            return JsonUtil.objectToJson(status);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLConnectionManager.close(null, statement, manager);
        }
        return null;
    }

    @Override
    public String deleteOwnInvitation(String author, String content, String date) {
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        boolean result;
        Status status = new Status();
        try {
            statement = manager.prepareStatement("DELETE FROM own_invitation WHERE oauthor='"+author+"' AND ocontent='"+content+"' AND odate='"+date+"'");
            result = statement.execute();
            status.setStatus(result ? "failure" : "success");
            status.setContent(result ? "删除失败" : "删除成功");
            return JsonUtil.objectToJson(status);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLConnectionManager.close(null, statement, manager);
        }
        return null;
    }

    @Override
    public String isCollected(String author, String content, String date) {
        Connection manager = SQLConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Status status = new Status();
        try {
            statement = manager.prepareStatement("SELECT * FROM favourite_invitation WHERE fauthor=\'"+author+"\' AND fcontent=\'"+content+"\' AND fdate=\'"+date+"\'");
            resultSet = statement.executeQuery();
            status.setStatus(resultSet.first() ? "success" : "failure");
            status.setContent(resultSet.first() ? "已收藏" : "未收藏");
            return JsonUtil.objectToJson(status);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLConnectionManager.close(resultSet, statement, manager);
        }
        return null;
    }
}
