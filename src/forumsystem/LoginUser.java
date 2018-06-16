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

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginUser extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        // get parameters('username' and 'password') from request;
//        String username = "syuban";
//        String password = "123456";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String result = "";
        IUser iUser = DAOFactory.createUserDao();
        result = iUser.loginSystem(username, password);
        pw.write(result);
        pw.close();
    }
}
