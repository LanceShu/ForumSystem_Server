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

@WebServlet(name = "getallown", urlPatterns = "/getallown")
public class GetAllOwnInvitations extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        IInvitation iInvitation = DAOFactory.createInvitationDao();
        String result = "";
        if (UserUtil.getUserInstance() != null) {
            result = iInvitation.queryOwnInvitations(UserUtil.getUserInstance());
        }
        pw.write(result);
        pw.close();
    }
}
