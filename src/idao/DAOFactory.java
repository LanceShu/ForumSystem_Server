package idao;

import dao.InvitationDao;
import dao.UserDao;

public class DAOFactory {
    public static IUser createUserDao() {
        return new UserDao();
    }

    public static IInvitation createInvitationDao() {
        return new InvitationDao();
    }
}
