package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.RegistrationStore;
import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage1 = "";
    private static String errorMessage2 = "";
    private static CourseStore courseStore = new CourseStore();
    private static String passToCheck = "";
    private static String enteredPass = "";
    private RegistrationStore registrationStore = new RegistrationStore();
    private static int idToRegisterInt;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        if(!DBUtil.theUser.isEmpty()) {
            errorMessage1 = "";
            passToCheck = "";
            String idToRegister = "";
            idToRegister += request.getParameter("kid");
            if(!idToRegister.isEmpty() && !idToRegister.equals("null")) {
                idToRegisterInt = Integer.parseInt(idToRegister);
            }
            List<Integer> list = new ArrayList<>();
            list.add(idToRegisterInt);
            List<Course> listOfCourse = courseStore.showMyOwnCourses(list);
            passToCheck += listOfCourse.get(0).getEinschreibeSchluessel();
            List<Course> emptyList = new ArrayList<>();
            if(!passToCheck.isEmpty() && !passToCheck.equals(null)) {
                request.setAttribute("registered", listOfCourse);
                request.setAttribute("registered2", emptyList);
            }
            else
            {
                errorMessage2 = "";
                request.setAttribute("registered2", listOfCourse);
                request.setAttribute("registered", emptyList);
            }
            request.setAttribute("error", errorMessage1);
            request.setAttribute("errorr", errorMessage2);
            request.getRequestDispatcher("/registerPage.ftl").forward(request, response);
        }
        else
        {
            errorMessage1 = "";
            errorMessage1 += "Access denied: You must login first as an authorized user!";
            errorMessage2 = "";
            List<Course> listOfCourse = new ArrayList<>();
            request.setAttribute("registered", listOfCourse);
            request.setAttribute("registered2", listOfCourse);
            request.setAttribute("error", errorMessage1);
            request.setAttribute("errorr", errorMessage2);
            request.getRequestDispatcher("/registerPage.ftl").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        enteredPass = "";
        enteredPass += request.getParameter("pass");
        if(enteredPass.equals("register"))
        {
            if(registrationStore.registerInCourse(idToRegisterInt)) {
                MainPageServlet mainPageServlet = new MainPageServlet();
                mainPageServlet.doGet(request, response);

            }
            else
            {
                errorMessage2 = "";
                errorMessage2 += "Registration Failed: Something is wrong with database! Try again later!";
                doGet(request, response);
            }
        }
        else {
        if(enteredPass.equals(passToCheck))
        {
            if(registrationStore.registerInCourse(idToRegisterInt))
            {
                MainPageServlet mainPageServlet = new MainPageServlet();
                mainPageServlet.doGet(request, response);

            }
            else
            {
                errorMessage2 = "";
                errorMessage2 += "Registration Failed: Something is wrong with database! Try again later!";
                doGet(request, response);
            }
        }
        else
        {
            errorMessage2 = "";
            errorMessage2 += "Wrong Password!";
            doGet(request, response);
        }
        }
    }
}
