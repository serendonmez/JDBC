package stepDefinitions;

import io.cucumber.java.en.Given;
import manage.QueryManage;
import org.testng.Assert;
import utilities.ConfigReader;
import utilities.JDBCReusableMethods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class StepDefinition {

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String query;
    int intResult;
    int silinecekID;

    QueryManage queryManage = new QueryManage();

    @Given("Database baglantisi kurulur.")
    public void database_baglantisi_kurulur() throws SQLException {
/*
	String URL= "jdbc:mysql://195.35.59.63/u201212290_loantec";
	String USERNAME= "u201212290_loantecuser";
	String PASSWORD="HPo?+7r$";
*/
	/*	connection = DriverManager.getConnection(ConfigReader.getProperty("URL"),
				ConfigReader.getProperty("USERNAME"),
				ConfigReader.getProperty("PASSWORD"));
		*/

        connection = JDBCReusableMethods.getConnection();

    }

    @Given("Database baglantisi kapatilir.")
    public void database_baglantisi_kapatilir() throws SQLException {

        JDBCReusableMethods.closeConnection();
    }

    @Given("SQL Query01 hazirlanir ve calistirilir.")
    public void sql_query01_hazirlanir_ve_calistirilir() throws SQLException {
        query = queryManage.getQuery01();

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(query);
    }

    @Given("SQL Query01 sonuclari test edilir.")
    public void sql_query01_sonuclari_test_edilir() throws SQLException {
        int expectedData = 1;

        resultSet.next();
        int actualData = resultSet.getInt(1);

        assertEquals(actualData, expectedData);

        System.out.println(" Actual Data = " + actualData);
    }


    // SQL QUERY02


    @Given("SQL Query02 hazirlanir ve calistirilir.")
    public void sql_query02_hazirlanir_ve_calistirilir() throws SQLException {

        query = queryManage.getQuery02();

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(query);

    }

    @Given("SQL Query02 sonuclari test edilir.")
    public void sql_query02_sonuclari_test_edilir() throws SQLException {

        // 5 Minutes
        // 10 Minutes

        List<String> expectedResultList = new ArrayList<>();
        expectedResultList.add("5 Minutes");
        expectedResultList.add("10 Minutes");

        List<String> actualResultList = new ArrayList<>();

        while (resultSet.next()) {

            String name = resultSet.getString("name");
            actualResultList.add(name);

        }

        for (int i = 0; i < actualResultList.size(); i++) {
            assertEquals(actualResultList.get(i), expectedResultList.get(i));
            System.out.println(i + ". index dogrulandi.");
        }

    }

// UPDATE QUERY 01

    @Given("UpdateQuery01 hazirlanir ve calistirilir.")
    public void update_query01_hazirlanir_ve_calistirilir() throws SQLException {

        query = queryManage.getPreparedUpdate01();
        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);

        preparedStatement.setString(1, "222222222");
        preparedStatement.setString(2, "%e_");
        //"UPDATE users SET mobile = ? WHERE username LIKE ?;";
        intResult = preparedStatement.executeUpdate();

    }
    @Given("UpdateQuery01 sonuclari test edilir.")
    public void update_query01_sonuclari_test_edilir() {

        int expectedResult = 1;
        assertEquals(intResult,expectedResult);

    }


//************UPDATE QUERY 02 ***********************

    @Given("UpdateQuery02 hazirlanir ve calistirilir.")
    public void update_query02_hazirlanir_ve_calistirilir() throws SQLException {

        // UPDATE admin_notifications SET is_read = ? WHERE id = ?;

        query = queryManage.getPreparedUpdate02();
        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);

        preparedStatement.setInt(1,1);
        preparedStatement.setInt(2, 7);

        intResult = preparedStatement.executeUpdate();  // INSERT , DELETE ve UPDATE query'leri executeUpdate() methodu ile calistirilir.
        // Dolayisi ile bize bir int (etkilenen satir sayisi) sonuc doner
        System.out.println(intResult);

    }
    @Given("UpdateQuery02 sonuclari test edilir.")
    public void update_query02_sonuclari_test_edilir() {

        int expectedResult= 1;

        assertEquals(intResult, expectedResult);

    }
    // eger executeQuery() methodu calismis ise sonuc ResultSet olarak doner
    // eger executeUpdate() methodu calismis ise sonuc bize int(etkilenen satir sayisi) olarak doner.

// ***************** INSERT QUERY 01 *************************


    @Given("InsertQuery01 hazirlanir ve calistirilir.")
    public void ınsert_query01_hazirlanir_ve_calistirilir() throws SQLException {
        query = queryManage.getInsertQuery01();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        // "INSERT INTO device_tokens (id, user_id, is_app,token)VALUES(?,?,?,?);";
        preparedStatement.setInt(1, 5);
        preparedStatement.setInt(2, 3);
        preparedStatement.setInt(3, 7);
        preparedStatement.setString(4, "token111");

        intResult = preparedStatement.executeUpdate();

    }
    @Given("InsertQuery01 sonuclari test edilir.")
    public void ınsert_query01_sonuclari_test_edilir() {

        // int expectedResult = 1;

        assertEquals(intResult,1);

    }
    // ************** DELETE QUERY 01 ***********************

    @Given("DeleteQuery01 hazirlanir ve calistirilir.")
    public void delete_query01_hazirlanir_ve_calistirilir() throws SQLException {

        query = queryManage.getDeleteQuery01();
        silinecekID = 16;

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        // DELETE FROM subscribers WHERE id = ?;
        preparedStatement.setInt(1, silinecekID);

        intResult = preparedStatement.executeUpdate();


    }
    @Given("DeleteQuery01 sonuclari test edilir.")
    public void delete_query01_sonuclari_test_edilir() throws SQLException {

        assertEquals(intResult, 1);

        // SELECT * FROM subscribers WHERE id = ?;   Control Query'si

        String controlQuery= queryManage.getDeleteControlQuery();

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(controlQuery);

        preparedStatement.setInt(1, silinecekID);

        resultSet = preparedStatement.executeQuery();

        // resultSet.next();    resultSet icerisinde data varsa true yoksa false donecek.

        assertFalse(resultSet.next());

    }
}