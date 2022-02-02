package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.EinreichenStore;
import de.unidue.inf.is.stores.UserStore;
import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";
    private static CourseStore courseStore = new CourseStore();
    private static UserStore userStore = new UserStore();
    private static EinreichenStore einreichenStore = new EinreichenStore();
    private static int idInt;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        if(!DBUtil.theUser.isEmpty()) {
            String courseID = request.getParameter("kid");
            List<Course> listOfCourse = new ArrayList<>();
            if (!courseID.isEmpty() && !courseID.equals("null")) {
                idInt = Integer.parseInt(courseID);
            }

                List<Integer> list = new ArrayList<>();
                list.add(idInt);
                listOfCourse = courseStore.showMyOwnCourses(list);
                if (listOfCourse.get(0).getErsteller() == userStore.fetchBNummerFromEmail(DBUtil.theUser)) {
                    errorMessage = "";
                    request.setAttribute("registered", listOfCourse);
                    request.setAttribute("error", errorMessage);
                } else {
                    List<Course> emptyList = new ArrayList<>();
                    errorMessage = "";
                    errorMessage += "Error: Only the producer of a course is authorized to delete it!";
                    request.setAttribute("registered", emptyList);
                    request.setAttribute("error", errorMessage);
                }
                request.getRequestDispatcher("deletePage.ftl").forward(request, response);

        }
        else
        {
            errorMessage = "";
            errorMessage += "Access is denied! Login first!";
            List<Course> emptyList = new ArrayList<>();
            request.setAttribute("registered", emptyList);
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("deletePage.ftl").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
            List<Integer> listAID = einreichenStore.fetchAbgabeID(idInt);
            if (courseStore.deleteCourse(idInt, listAID))
            {
                MainPageServlet mainPageServlet = new MainPageServlet();
                mainPageServlet.doGet(request, response);
            }
            else
            {
                errorMessage = "";
                errorMessage += "Error: Something is wrong with database! Try again later!";
                doGet(request, response);
            }
        doGet(request, response);
    }
}
