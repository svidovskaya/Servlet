package servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class Servlet_products extends HttpServlet {
   public String URL;
   public String USERNAME;
   public String PASSWORD;
   public String TIMEZONE;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        URL = getServletContext().getInitParameter("URL");
        PASSWORD = getServletContext().getInitParameter("PASSWORD");
        USERNAME = getServletContext().getInitParameter("USERNAME");
        TIMEZONE = getServletContext().getInitParameter("TIMEZONE");
        URL=URL+"&"+TIMEZONE;

        int a =0;
        String b ="";
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        Enumeration<String> prmNames = request.getParameterNames(); // Имена параметров
        while (prmNames.hasMoreElements()) { // Проходим по Enumeration
            String prmName = prmNames.nextElement();
            a = Integer.parseInt(prmName);
            String prmValues[] = request.getParameterValues(prmName); // Все значения одного параметра
            prmName = Arrays.toString(prmValues);
            b = prmName;
        }


        try{

            Class.forName("com.mysql.jdbc.Driver");
            writer.println(" <a href=\"index.jsp\">Меню</a>");
            writer.println(" <a href=\"create_prod.jsp\">Создать продукт</a>");

            writer.println("Connection !!!!!!!!!!!");
            if (b.equals("[Удалить]")){
                // writer.println("Удалить элемент №" + a);
                delete(a);
                show(writer);
            } else {
                show(writer);
            }

        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {
            writer.close();
        }
    }

    public void show(PrintWriter writer){

        writer.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">  </head>\n");
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            int r_c = 0;

            String sql_code = "select count(prod_id) from products where p_show like 'try' ;";
            ResultSet resSet = statement.executeQuery(sql_code);
            while (resSet.next()) {
                r_c = resSet.getInt("count(prod_id)");
            }
            String[][] result_h =new String[r_c][8];
            String sql_code_1 = "Select prod_id, p_shtrih, p_code, p_articul, p_name, p_price, p_price_stom, manuf_name from " +
                    "products inner join manufacturer on manuf_id = products.p_manuf_id where p_show like 'try' ;";
            ResultSet resSet_1 = statement.executeQuery(sql_code_1);
            int i = 0;
            writer.println("<form action=\"\" method=\"GET\"><table>");
            while (resSet_1.next()) {
                writer.println("<tr><td>");
                result_h[i][0] = String.valueOf(resSet_1.getInt("prod_id"));
                writer.println(result_h[i][0]);
                writer.println("</td><td>");
                result_h[i][1] = resSet_1.getString("p_shtrih");
                writer.println(result_h[i][1]);
                writer.println("</td><td>");
                result_h[i][2] = resSet_1.getString("p_code");
                writer.println(result_h[i][2]);
                writer.println("</td><td>");
                result_h[i][3] = resSet_1.getString("p_articul");
                writer.println(result_h[i][3]);
                writer.println("</td><td>");
                result_h[i][4] = resSet_1.getString("p_name");
                writer.println(result_h[i][4]);
                writer.println("</td><td>");
                result_h[i][5] = resSet_1.getString("p_price");
                writer.println(result_h[i][5]);
                writer.println("</td><td>");
                result_h[i][6] = resSet_1.getString("p_price_stom");
                writer.println(result_h[i][6]);
                writer.println("</td><td>");
                result_h[i][7] = resSet_1.getString("manuf_name");
                writer.println(result_h[i][7]);
                writer.println("</td><td><input type=\"submit\" variant=\"del\" name=\""+result_h[i][0]+"\""+"value=\"Удалить\"></td></tr>");

                i+=1;
            }
            writer.println("</table></form>");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int id){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code_o = "update products set p_show=? where products.prod_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(sql_code_o);
            preparedStmt.setString(1, "false");
            preparedStmt.setInt(2, id);
            preparedStmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}