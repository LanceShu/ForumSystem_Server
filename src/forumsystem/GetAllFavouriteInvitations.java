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

@WebServlet(name = "getallfavourite", urlPatterns = "/getallfavourite")
public class GetAllFavouriteInvitations extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/javascript");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter pw = response.getWriter();
        IInvitation iInvitation = DAOFactory.createInvitationDao();
        String resp = "";
        if (UserUtil.getUserInstance() != null) {
            resp = iInvitation.queryFavouriteInvitations(UserUtil.getUserInstance());
        }
        pw.write(resp);
        pw.close();
    }
}
