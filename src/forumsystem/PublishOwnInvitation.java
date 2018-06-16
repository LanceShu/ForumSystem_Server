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

@WebServlet(name = "publish", urlPatterns = "/publish")
public class PublishOwnInvitation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/javascript");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter pw = response.getWriter();
        // get user's information want to publish;
        String content = request.getParameter("content");
        IInvitation iInvitation = DAOFactory.createInvitationDao();
        String result = "";
        if (UserUtil.getUserInstance() != null) {
            result = iInvitation.publishInvitation(UserUtil.getUserInstance(), content);
        }
        pw.write(result);
        pw.close();
    }
}
