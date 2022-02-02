package de.unidue.inf.is;

import de.unidue.inf.is.domain.*;
import de.unidue.inf.is.stores.*;
import de.unidue.inf.is.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static CourseStore courseStore = new CourseStore();
    private static UserStore userStore = new UserStore();
    private static RegistrationStore registrationStore = new RegistrationStore();
    private static AufgabeStore aufgabeStore = new AufgabeStore();
    private static EinreichenStore einreichenStore = new EinreichenStore();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String courseID = request.getParameter("kid");
        int intCourseID = Integer.parseInt(courseID);
        List<CourseWithProducersName> list3 = new ArrayList<>();
        List<CourseWithProducersName> list33 = new ArrayList<>();
        List<Task> myTasks = new ArrayList<>();
        List<Delivery> myDeliveryList = new ArrayList<>();
        List<String> myGradeList = new ArrayList<>();
        List<HandInToShow> myTasksToShow = new ArrayList<>();
        String aufgabenTitle = "";
        String title = "";
        String titletwo = "";
        String titlethree = "";
        List<Integer> myOwnCourses = new ArrayList<>();
        if(!DBUtil.theUser.isEmpty()) {
            myOwnCourses = registrationStore.fetchCourseIDFromUserID(userStore.fetchBNummerFromEmail(DBUtil.theUser));
            for (int j = 0; j < myOwnCourses.size(); j++) {
                if (myOwnCourses.get(j).equals(Integer.valueOf(intCourseID))) {
                    List<Integer> list11 = new ArrayList<>();
                    list11.add(intCourseID);
                    List<Course> list22 = courseStore.showMyOwnCourses(list11);
                    for (int i = 0; i < list22.size(); i++) {
                        list33.add(new CourseWithProducersName(list22.get(i).getkID(), list22.get(i).getName(),
                                userStore.fetchNameFromBNummer(list22.get(i).getErsteller()), list22.get(i).getFreiePlaetze(),
                                list22.get(i).getBeschreibungsText()));
                    }
                    aufgabenTitle += "List of Tasks";
                    title += "Task";
                    titletwo += "My Delivery";
                    titlethree += "Grade";
                    myTasks = aufgabeStore.fetchTasksFromCourseID(intCourseID);
                    for (int k=0; k < myTasks.size(); k++) {
                        if (einreichenStore.fetchAbgabeID(userStore.fetchBNummerFromEmail(DBUtil.theUser),
                                myTasks.get(k).getkID(), myTasks.get(k).getaNummer()) != 0) {
                            Delivery myDelivery = einreichenStore.fetchAbgabeTextFromAbgabeID(
                                    einreichenStore.fetchAbgabeID(userStore.fetchBNummerFromEmail(DBUtil.theUser),
                                            myTasks.get(k).getkID(), myTasks.get(k).getaNummer()));
                            myDeliveryList.add(myDelivery);
                            myGradeList.add(aufgabeStore.fetchGrade(einreichenStore.fetchAbgabeID(userStore.fetchBNummerFromEmail(DBUtil.theUser),
                                    myTasks.get(k).getkID(), myTasks.get(k).getaNummer())));
                        }
                        else
                        {
                            Delivery delivery = new Delivery(0, "Keine Abgabe");
                            myDeliveryList.add(delivery);
                            myGradeList.add("Noch keine Bewertung");
                        }
                    }
                    for (int k=0; k < myTasks.size(); k++)
                    {
                            myTasksToShow.add(new HandInToShow(myTasks.get(k).getName(),
                                    myDeliveryList.get(k).getAbgabeText(), myTasks.get(k).getaNummer(),
                                    myTasks.get(k).getkID(), myGradeList.get(k)));
                    }

                    request.setAttribute("title", title);
                    request.setAttribute("titletwo", titletwo);
                    request.setAttribute("titlethree", titlethree);
                    request.setAttribute("owntask", myTasksToShow);
                    request.setAttribute("course", list3);
                    request.setAttribute("aufgaben", aufgabenTitle);
                    request.setAttribute("owncourse", list33);
                    request.getRequestDispatcher("/detailsPage.ftl").forward(request, response);
                }
            }
            List<Integer> list1 = new ArrayList<>();
            list1.add(intCourseID);
            List<Course> list2 = courseStore.showMyOwnCourses(list1);
            for (int i = 0; i < list2.size(); i++) {
                list3.add(new CourseWithProducersName(list2.get(i).getkID(), list2.get(i).getName(),
                        userStore.fetchNameFromBNummer(list2.get(i).getErsteller()), list2.get(i).getFreiePlaetze(),
                        list2.get(i).getBeschreibungsText()));
            }
            request.setAttribute("title", title);
            request.setAttribute("titletwo", titletwo);
            request.setAttribute("titlethree", titlethree);
            request.setAttribute("owntask", myTasksToShow);
            request.setAttribute("course", list3);
            request.setAttribute("aufgaben", aufgabenTitle);
            request.setAttribute("owncourse", list33);
            request.getRequestDispatcher("/detailsPage.ftl").forward(request, response);
        }
        else
        {
            aufgabenTitle += "Access is denied! Login first!";
            request.setAttribute("title", title);
            request.setAttribute("titletwo", titletwo);
            request.setAttribute("titlethree", titlethree);
            request.setAttribute("owntask", myTasksToShow);
            request.setAttribute("course", list3);
            request.setAttribute("aufgaben", aufgabenTitle);
            request.setAttribute("owncourse", list33);
            request.getRequestDispatcher("/detailsPage.ftl").forward(request, response);
        }
    }
}
