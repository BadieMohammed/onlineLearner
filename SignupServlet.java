package de.unidue.inf.is;

import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.LoginStore;
import de.unidue.inf.is.stores.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String errorMessage = "";
    private static UserStore userStore = new UserStore();
    private static LoginStore loginStore = new LoginStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/signupPage.ftl").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String newEmail = request.getParameter("username");
        if (newEmail.isEmpty())
        {
            errorMessage = "";
            errorMessage += "Error: You must enter your email!";
            doGet(request, response);
        }
        if (!(newEmail.contains("@gmail.com")) && !(newEmail.contains("@yahoo.com")))
        {
            errorMessage = "";
            errorMessage += "Error: Either yahoo or gmail accounts are accepted for now!";
            doGet(request, response);
        }
        if(!loginStore.userAuthenticated(newEmail).isEmpty())
        {
            errorMessage = "";
            errorMessage += "Error: This email address is already used!";
            doGet(request, response);
        }
        String newName = request.getParameter("name");
        if (newName.isEmpty())
        {
            errorMessage = "";
            errorMessage += "Error: You must enter your name!";
            doGet(request, response);
        }
        else {
            User newUserToAdd = new User(newEmail, newName);
            if (userStore.addNewUser(newUserToAdd)) {
                LoginPageServlet loginPageServlet = new LoginPageServlet();
                errorMessage = "";
                loginPageServlet.setErrorMessage(errorMessage);
                loginPageServlet.setErrorMessage("Success: Your account has been created!");
                loginPageServlet.doGet(request, response);
            } else {
                errorMessage = "";
                errorMessage += "Error: Something is wrong with database! Try later again!";
                doGet(request, response);
            }
        }
    }
}
