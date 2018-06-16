package forumsystem;

import idao.DAOFactory;
import idao.IInvitation;
import utils.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "collect", urlPatterns = "/collect")
public class CollectInvitation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/javascript");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter pw = response.getWriter();
        // collect invitation user favourite (author, owner, content, date);
        String owner = request.getParameter("owner");
        String content = request.getParameter("content");
        String date = request.getParameter("date");
//        String owner = "syuban";
//        String content = "love you, zc";
//        String date = "2018-06-13 16:23:56";
        IInvitation iInvitation = DAOFactory.createInvitationDao();
        String result = "";
        if (UserUtil.getUserInstance() != null) {
            result = iInvitation.collectInvitation(UserUtil.getUserInstance(), owner, date, content);
        }
        pw.write(result);
        pw.close();
    }
}
