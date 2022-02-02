package de.unidue.inf.is.stores;

import de.unidue.inf.is.domain.Course;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseStore implements Closeable {
    private Connection connection;
    private boolean complete;


    public Connection makeConnection() throws StoreException
    {
        try{
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
            complete = false;
            return connection;
        } catch (SQLException e)
        {
            throw new StoreException(e);
        }
    }
    public List<Course> showCourse() throws StoreException {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select kid, name, ersteller, freieplaetze from dbp151.kurs");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Course> result = new ArrayList<>();
            while (resultSet.next())
            {
                result.add(new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getInt(4)));
            }
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return result;
        }
        catch (SQLException | IOException e) {
            throw new StoreException(e);
        }
    }
    public List<Course> showMyOwnCourses(List<Integer> courseIDs) throws StoreException
    {
        makeConnection();
        List<Course> result = new ArrayList<>();
        try {
            for (int i = 0; i < courseIDs.size(); i++) {
                try {
                    PreparedStatement preparedStatement = connection
                            .prepareStatement("select * from dbp151.kurs where kid=?");
                    preparedStatement.setInt(1, courseIDs.get(i));
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        result.add(new Course(resultSet.getInt("kid"), resultSet.getString("name"),
                                resultSet.getString("beschreibungsText"), resultSet.getString("einschreibeschluessel"),
                                resultSet.getInt("freiePlaetze"),
                                resultSet.getInt("ersteller")));
                    }
                    resultSet.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
            complete = true;
            close();
            return result;
        } catch (IOException e)
        {
            throw new StoreException(e);
        }
    }
    public boolean addNewCourse(Course addedCourse) throws StoreException
    {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into dbp151.kurs (name,beschreibungstext," +
                            "einschreibeschluessel,freiePlaetze,ersteller) values (?,?,?,?,?)");
            preparedStatement.setString(1, addedCourse.getName());
            preparedStatement.setString(2, addedCourse.getBeschreibungsText());
            preparedStatement.setString(3, addedCourse.getEinschreibeSchluessel());
            preparedStatement.setInt(4, addedCourse.getFreiePlaetze());
            preparedStatement.setInt(5, addedCourse.getErsteller());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            preparedStatement.close();
            complete = true;
            close();
            return complete;
        } catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }
    }
    public boolean deleteCourse(int kid, List<Integer> list) throws StoreException
    {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from dbp151.einreichen where kid=?");
            preparedStatement.setInt(1, kid);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            for (int j=0; j < list.size(); j++) {
                PreparedStatement preparedStatement1 = connection
                        .prepareStatement("delete from dbp151.bewerten where aid=?");
                preparedStatement1.setInt(1, list.get(j));
                preparedStatement1.executeUpdate();
                preparedStatement1.close();
            }
            for (int k=0; k < list.size(); k++) {
                PreparedStatement preparedStatement2 = connection
                        .prepareStatement("delete from dbp151.abgabe where aid=?");
                preparedStatement2.setInt(1, list.get(k));
                preparedStatement2.executeUpdate();
                preparedStatement2.close();
            }
            PreparedStatement preparedStatement3 = connection
                    .prepareStatement("delete from dbp151.aufgabe where kid=?");
            preparedStatement3.setInt(1, kid);
            preparedStatement3.executeUpdate();
            preparedStatement3.close();

            PreparedStatement preparedStatement4 = connection
                    .prepareStatement("delete from dbp151.einschreiben where kid=?");
            preparedStatement4.setInt(1, kid);
            preparedStatement4.executeUpdate();
            preparedStatement4.close();

            PreparedStatement preparedStatement5 = connection
                    .prepareStatement("delete from dbp151.kurs where kid=?");
            preparedStatement5.setInt(1, kid);
            preparedStatement5.executeUpdate();
            preparedStatement5.close();

            complete = true;
            close();
            return complete;
        } catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                }
                else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                throw new StoreException(e);
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }
}
