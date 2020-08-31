package servlets.servlet;

import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/Servlet_cookie")
public class Servlet_cookie extends HttpServlet {

    List<Product> products = new ArrayList<Product>();
    Cookie cookies[];
    String a;
    String b;
    Cookie del_cookie;
    public String URL;
    public String USERNAME;
    public String PASSWORD;
    public String TIMEZONE;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Enumeration<String> prmNames = request.getParameterNames(); // Имена параметров
        while (prmNames.hasMoreElements()) { // Проходим по Enumeration
            String prmName = prmNames.nextElement();
            a = prmName;

            String prmValues[] = request.getParameterValues(prmName); // Все значения одного параметра
            prmName = Arrays.toString(prmValues);
            b = prmName;

        }

        URL = getServletContext().getInitParameter("URL");
        PASSWORD = getServletContext().getInitParameter("PASSWORD");
        USERNAME = getServletContext().getInitParameter("USERNAME");
        TIMEZONE = getServletContext().getInitParameter("TIMEZONE");
        URL=URL+"&"+TIMEZONE;

        cookies = request.getCookies();

        products.clear();
        products = show(cookies);
        request.setAttribute("product", products);
        response.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/jsp/cookies.jsp").forward(request, response);

    }

    public List<Product> show(Cookie cookies[]){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            for (Cookie c: cookies){
                if (c.getValue().length()>5){

                }else {
                String sql_code_1 = "Select prod_id, p_shtrih, p_code, p_articul, p_name, p_price, p_price_stom, manuf_name from " +
                        "products inner join manufacturer on manuf_id = products.p_manuf_id where prod_id = "+Integer.parseInt(c.getValue())+";";
                ResultSet resSet_1 = statement.executeQuery(sql_code_1);
                while (resSet_1.next()){
                    Product product = new Product(0, "", "", "", "", 0, 0);
                    product.setId(resSet_1.getInt("prod_id"));
                    System.out.println(product.getId());
                    product.setShtrih(resSet_1.getString("p_shtrih"));
                    product.setKode(resSet_1.getString("p_code"));
                    product.setName(resSet_1.getString("p_name"));
                    product.setPrice(resSet_1.getFloat("p_price"));
                    product.setPrice_stom(resSet_1.getFloat("p_price_stom"));
                    product.setManuf(resSet_1.getString("manuf_name"));
                    products.add(product);
                }

            }}


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;

    }






}

