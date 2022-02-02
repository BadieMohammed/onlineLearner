package de.unidue.inf.is;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.domain.CourseWithProducersName;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.stores.CourseStore;
import de.unidue.inf.is.stores.LoginStore;
import de.unidue.inf.is.stores.RegistrationStore;
import de.unidue.inf.is.stores.UserStore;
import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String errorMessage = "";

    private static CourseStore courseStore = new CourseStore();
    private static LoginStore loginStore = new LoginStore();
    private static RegistrationStore registrationStore = new RegistrationStore();
    private static UserStore userStore = new UserStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

            if (!loginStore.userAuthenticated(DBUtil.theUser).isEmpty()) {
                if (!courseStore.showCourse().isEmpty()) {
                    List<Course> listOfCourses = new ArrayList<>();
                    listOfCourses = courseStore.showCourse();
                    List<CourseWithProducersName> answerList = new ArrayList<>();
                    for(int j=0; j < listOfCourses.size(); j++)
                    {
                        if(listOfCourses.get(j).getFreiePlaetze() != 0) {
                            answerList.add(new CourseWithProducersName(listOfCourses.get(j).getkID(),
                                    listOfCourses.get(j).getName(),
                                    userStore.fetchNameFromBNummer(listOfCourses.get(j).getErsteller()),
                                    listOfCourses.get(j).getFreiePlaetze()));
                        }
                    }
                    request.setAttribute("mycourse", answerList);
                    List<Integer> listOfCourseIDs = new ArrayList<>();
                    listOfCourseIDs = registrationStore.fetchCourseIDFromUserID(userStore.fetchBNummerFromEmail(DBUtil.theUser));
                    List<Course> listOfMyOwnCourses = new ArrayList<>();
                    listOfMyOwnCourses = courseStore.showMyOwnCourses(listOfCourseIDs);
                    List<CourseWithProducersName> answerListForMe = new ArrayList<>();
                    for(int k=0; k < listOfMyOwnCourses.size(); k++)
                    {
                        answerListForMe.add(new CourseWithProducersName(listOfMyOwnCourses.get(k).getkID(),
                                listOfMyOwnCourses.get(k).getName(),
                                userStore.fetchNameFromBNummer(listOfMyOwnCourses.get(k).getErsteller()),
                                listOfMyOwnCourses.get(k).getFreiePlaetze()));
                    }
                    request.setAttribute("myowncourse", answerListForMe);
                    errorMessage = "";
                    request.setAttribute("error", errorMessage);
                }
                else {
                    request.setAttribute("mycourse", "There is no course available at all!");
                    request.setAttribute("myowncourse", "There is no course available at all!");
                    errorMessage = "";
                    request.setAttribute("error", errorMessage);
                }
            }
            else
            {
                List<User> kosUser = new ArrayList<>();
                request.setAttribute("myowncourse", kosUser);
                List<Course> kosCourse = new ArrayList<>();
                request.setAttribute("mycourse", kosCourse);
                errorMessage = "";
                errorMessage += "Access denied: You must login first as an authorized user!";
                request.setAttribute("error", errorMessage);
            }
        request.getRequestDispatcher("/mainPage.ftl").forward(request, response);
    }
}
