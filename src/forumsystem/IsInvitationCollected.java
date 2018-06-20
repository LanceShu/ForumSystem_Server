package forumsystem;

import idao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "iscollected", urlPatterns = "/iscollected")
public class IsInvitationCollected extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter pw = response.getWriter();
        // get parameters(username, content, date);
        String username = request.getParameter("username");
        String content = request.getParameter("content");
        String date = request.getParameter("date");
        String resp = "";
        if (username.length() != 0) {
            resp = DAOFactory.createInvitationDao().isCollected(username, content, date);
        }
        pw.write(resp);
        pw.close();
    }
}
