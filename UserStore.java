package de.unidue.inf.is.stores;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;



public final class UserStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public Connection makeConnection() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection();
            connection.setAutoCommit(false);
            complete = false;
            return connection;
        }
        catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public int fetchBNummerFromEmail(String email) throws StoreException {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                            .prepareStatement("select bnummer from dbp151.benutzer where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> listRes = new ArrayList<>();
            while (resultSet.next())
            {
                listRes.add(resultSet.getInt(1));
            }
            int result = listRes.get(0);
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return result;
        } catch (SQLException | IOException e) {
            throw new StoreException(e);
        }
    }
    public String fetchNameFromBNummer(int bnummer) throws StoreException
    {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select name from dbp151.benutzer where bnummer=?");
            preparedStatement.setInt(1, bnummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }
            String answer = result.get(0);
            resultSet.close();
            preparedStatement.close();
            complete = true;
            close();
            return answer;
        } catch (SQLException | IOException e)
        {
            throw new StoreException(e);
        }
    }
    public boolean addNewUser(User newUser) throws StoreException
    {
        makeConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into dbp151.benutzer (email, name) values (?,?)");
            preparedStatement.setString(1, newUser.getEmail());
            preparedStatement.setString(2, newUser.getName());
            preparedStatement.executeUpdate();

            preparedStatement.close();
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
