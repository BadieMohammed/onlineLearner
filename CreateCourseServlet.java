package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.UserStore;
import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String errorMessage = "";
    private static UserStore userStore = new UserStore();
    private static CourseStore courseStore = new CourseStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/newCoursePage.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException
    {
        String newName = request.getParameter("titel");
        String newPass = request.getParameter("pass");
        String newFreeSeats = request.getParameter("seats");
        int newFreeSeatsInt = 0;
        if(!newFreeSeats.isEmpty()) {
            newFreeSeatsInt = Integer.parseInt(newFreeSeats);
        }
        String newDescrip = request.getParameter("descrip");
        if (DBUtil.theUser.isEmpty())
        {
            errorMessage = "";
            errorMessage += "Access denied: You must login first as an authorized user!";
            doGet(request, response);
        }
        if ((newName.length()==0) || (newName.length()>50))
        {
            errorMessage = "";
            errorMessage += "Error: The entered name is either longer than 50 characters or empty!";
            doGet(request, response);
        }
        if ((newFreeSeats.isEmpty()) || (newFreeSeatsInt > 100))
        {
            errorMessage = "";
            errorMessage += "Error: The entered number of free seats cannot be empty or bigger than 100!";
            doGet(request, response);
        }
        if (newDescrip.isEmpty())
        {
            errorMessage = "";
            errorMessage += "Error: The discription cannot remain empty!";
            doGet(request, response);
        }

        Course newCourse = new Course(newName, newDescrip, newPass, newFreeSeatsInt,
                userStore.fetchBNummerFromEmail(DBUtil.theUser));
        if (courseStore.addNewCourse(newCourse))
        {
            errorMessage = "";
            errorMessage += "Success: New course was successfully created!";
            doGet(request, response);
        }
        else
        {
            errorMessage = "";
            errorMessage += "Error: Something is wrong with database. Try later again!";
            doGet(request, response);
        }
    }
}
