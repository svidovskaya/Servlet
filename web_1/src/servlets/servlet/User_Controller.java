package servlets.servlet;

import com.mysql.cj.Session;
import dao.UserDao;
import model.User;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/User_Controller")
public class User_Controller extends HttpServlet {
    public User_Controller(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao().getInstance();

        String id = request.getParameter("id");
        String action = request.getParameter("action");
        String name = request.getParameter("u_name");
        String surname = request.getParameter("u_surname");
        String middlename = request.getParameter("u_middlename");
        String phone = request.getParameter("u_phone");
        String mail = request.getParameter("u_mail");
        String login = request.getParameter("login");
        String role = request.getParameter("u_role");
        User user = null;
        if ("update".equals(action)){
            if (id != null){
                int id_us = Integer.parseInt(id);
                user = new User(id_us, login, name, surname, middlename, phone, mail, role);
                userDao.updateUser(id_us, user);
            }
        } else {}
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext context = getServletContext();

        UserDao userDao = new UserDao().getInstance();

        String login = request.getUserPrincipal().getName();
        String action = request.getParameter("action");


        User users = userDao.findUserByLogin(login);
        request.setAttribute("user", users);

        String userID = String.valueOf(users.getId());
        if (session.getAttribute("visitCount") == null){
            session.setAttribute("visitCount", 0);
        }
        if (context.getAttribute("count") == null){
            context.setAttribute("count", 0);
            System.out.println("1");
        }
        if (context.getAttribute("user_id")==null){
            context.setAttribute("user_id", userID);
            int count = (int) context.getAttribute("count");
            count = count + 1;
            context.setAttribute("count", count);
            System.out.println("2");
        } else if (context.getAttribute("user_id").equals(userID)){
            System.out.println("3");
        } else {
            System.out.println("4");
            int count = (int) context.getAttribute("count");
            count = count + 1;
            context.setAttribute("count", count);
        }
        int visitCount = 0;
        if (session.isNew()){ // Новый клиент
            session.setAttribute("userID", userID);

        } else { // Повторный запрос
            visitCount = (int) session.getAttribute("visitCount");
            visitCount = visitCount + 1;
            userID = (String)session.getAttribute("userID");

        }
        session.setAttribute("visitCount", visitCount);

        response.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/jsp/user_info.jsp").forward(request, response);
    }
}
