package forumsystem;

import idao.DAOFactory;
import idao.IUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterUser extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        // get user's information from request('username', 'password', 'gender');
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String result = "";
        IUser iUser = DAOFactory.createUserDao();
        result = iUser.registerUser(username, password, gender);
        pw.write(result);
        pw.close();
    }
}
