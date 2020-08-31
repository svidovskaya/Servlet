import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Servlet_create_prod")
public class Servlet_create_prod extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String price = request.getParameter("pr");
        String price_st = request.getParameter("pr1");
        int manuf = Integer.parseInt(request.getParameter("manuf"));
        PrintWriter writer = response.getWriter();

        String URL = "jdbc:mysql://localhost/dental?useUnicode=true&serverTimezone=UTC";
        String USERNAME = "root";
        String PASSWORD = "4549";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql_code_prod = "INSERT INTO products (p_name, p_price, p_manuf_id, p_price_stom) values (?, ?, ?, ?)";

            PreparedStatement preparedStatement_prod = connection.prepareStatement(sql_code_prod, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement_prod.setString(1, name);
            preparedStatement_prod.setFloat(2, Float.parseFloat(price));
            preparedStatement_prod.setInt(3, manuf);
            preparedStatement_prod.setFloat(4, Float.parseFloat(price_st));


            preparedStatement_prod.executeUpdate();


            response.sendRedirect("products");
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
