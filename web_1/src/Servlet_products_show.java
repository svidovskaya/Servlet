import model.Manufacturer;
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

@WebServlet("/Servlet_products_show")
public class Servlet_products_show extends HttpServlet {


    List<Product> products = new ArrayList<Product>();
    String filter;
    List<Manufacturer>manufacturers = new ArrayList<Manufacturer>();
    String[] filters={"", "", ""};
    Cookie cookie;
    String a;
    String b;

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
        if (a.equals("add")){
            String[] id_prod = request.getParameterValues("add");
            System.out.println(id_prod.length);
            if (id_prod.length > 0) {
                cookie = new Cookie(id_prod[0], id_prod[0]);
                response.addCookie(cookie);
                response.setCharacterEncoding("UTF-8");
                String redirectionWebsite = "/Servlet_products_show?filter_name=&filter_m=All&filter_pr=По+убыванию";
                response.setHeader("Location", redirectionWebsite);

                getServletContext().getRequestDispatcher("/Servlet_products_show?filter_name=&filter_m=All&filter_pr=По+убыванию").forward(request, response);


            }
        } else {


            filter = request.getParameter("filter_name");
            filters[0] = "%" + filter + "%";
            String a = request.getParameter("filter_pr");
            if (a.equals("По возрастанию")) {
                a = "asc";
            } else {
                a = "desc";
            }
            filters[2] = a;
            String[] filter_m = request.getParameterValues("filter_m");
            String f_m = "";

            for (int i = 0; i < filter_m.length; i++) {
                if (i == 0) {
                    f_m += "and ";
                } else {
                    f_m += "or ";
                }
                if (filter_m[i].equals("All")) {
                    f_m += "manuf_name like '%%' ";
                } else {
                    f_m += "manuf_name like '" + filter_m[i] + "' ";
                }
            }
            filters[1] = f_m;

            products.clear();
            manufacturers.clear();
            manufacturers = show_manuf();
            products = show(filters);
            request.setAttribute("manufacturer", manufacturers);
            request.setAttribute("product", products);
            response.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/product_jsp.jsp").forward(request, response);


        }

    }
    public List<Manufacturer> show_manuf(){
        try (Connection connection = DriverManager.getConnection(DBProcessor.URL, DBProcessor.USERNAME, DBProcessor.PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql_code_m = "Select manuf_id, manuf_name, manuf_country from " +
                    "manufacturer";
            ResultSet resSet_m = statement.executeQuery(sql_code_m);
            while (resSet_m.next()) {
                Manufacturer manufacturer = new Manufacturer(0, "", "");
                manufacturer.setId(resSet_m.getInt("manuf_id"));
                manufacturer.setName(resSet_m.getString("manuf_name"));
                manufacturer.setCountry(resSet_m.getString("manuf_country"));
                manufacturers.add(manufacturer);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;

    }




    public List<Product> show(String[] filters){
        try (Connection connection = DriverManager.getConnection(DBProcessor.URL, DBProcessor.USERNAME, DBProcessor.PASSWORD);
             Statement statement = connection.createStatement()) {


            String sql_code_1 = "Select prod_id, p_shtrih, p_code, p_articul, p_name, p_price, p_price_stom, manuf_name from " +
                    "products inner join manufacturer on manuf_id = products.p_manuf_id where p_show like 'try' and p_name like '"+filters[0]+"' "+filters[1]+" order by p_price "+filters[2]+";";
            ResultSet resSet_1 = statement.executeQuery(sql_code_1);

            int i = 0;

            while (resSet_1.next()) {
                Product product = new Product(0, "", "", "", "", 0, 0);
                product.setId(resSet_1.getInt("prod_id"));
                product.setShtrih(resSet_1.getString("p_shtrih"));
                product.setKode(resSet_1.getString("p_code"));
                product.setName(resSet_1.getString("p_name"));
                product.setPrice(resSet_1.getFloat("p_price"));
                product.setPrice_stom(resSet_1.getFloat("p_price_stom"));
                product.setManuf(resSet_1.getString("manuf_name"));
                products.add(product);


                i+=1;
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;

    }



}
