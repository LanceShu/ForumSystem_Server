package idao;

public interface IUser {
    // the function of register;
    String registerUser(String username, String password, String gender);
    // the function of login in the system;
    String loginSystem(String username, String password);
}
