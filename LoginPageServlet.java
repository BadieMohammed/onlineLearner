package de.unidue.inf.is;

import de.unidue.inf.is.stores.LoginStore;
import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String errorMessage = "";
    private static LoginStore loginStore = new LoginStore();

    public void setErrorMessage(String error)
    {
        errorMessage = error;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/loginPage.ftl").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String user = request.getParameter("username");
        if (loginStore.userAuthenticated(user).isEmpty())
        {
            errorMessage = "";
            errorMessage += "User Authentication Failed! Sign up or stop messing around!!";
            DBUtil.theUser = "";
            doGet(request, response);
        }
        else
        {
            DBUtil.theUser = user;
            errorMessage = "";
            MainPageServlet mainPageServlet = new MainPageServlet();
            mainPageServlet.doGet(request, response);
        }
    }
}
