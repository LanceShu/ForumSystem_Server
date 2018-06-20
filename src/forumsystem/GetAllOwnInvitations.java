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

@WebServlet(name = "getallown", urlPatterns = "/getallown")
public class GetAllOwnInvitations extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/javascript");
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter pw = response.getWriter();
        IInvitation iInvitation = DAOFactory.createInvitationDao();
        String result = "";
//        if (UserUtil.getUserInstance() != null) {
//            result = iInvitation.queryOwnInvitations(UserUtil.getUserInstance());
//        }
        String username = request.getParameter("username");
        if (username.length() != 0) {
            result = iInvitation.queryOwnInvitations(username);
        }
        pw.write(result);
        pw.close();
    }
}
