package forumsystem;

import idao.DAOFactory;
import idao.IInvitation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "discollect", urlPatterns = "/discollect")
public class DiscollectInvitation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/javascript");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter pw = response.getWriter();
        // discollect the invitation by parameters('author', 'owner', 'content', 'date')
        String owner = request.getParameter("owner");
        String content = request.getParameter("content");
        String date = request.getParameter("date");
//        String owner = "syuban";
//        String content = "love you, zc";
//        String date = "2018-06-13 16:23:56";
        IInvitation iInvitation = DAOFactory.createInvitationDao();
        String result = "";
        String username = request.getParameter("username");
        if (username.length() != 0) {
            result = iInvitation.discollectInvitation(username, owner, date, content);
        }
//        if (UserUtil.getUserInstance() != null) {
//            result = iInvitation.discollectInvitation(UserUtil.getUserInstance(), owner, date, content);
//        }
        pw.write(result);
        pw.close();
    }
}
