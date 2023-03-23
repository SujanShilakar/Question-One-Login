package com.servletMain;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "/LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String U_username = request.getParameter("uname");
        String U_password = request.getParameter("password");

        try{
            PrintWriter out = response.getWriter();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","root");
            PreparedStatement stm = conn.prepareStatement("Select * from users where userName=? and password=?");
            stm.setString(1,U_username);
            stm.setString(2,U_password);
            ResultSet rs = stm.executeQuery();

            if(rs.next()) {

                out.println(" <h1> Username : </h1>" + rs.getString(4));
                out.println("<h1> Password : </h1>" + rs.getString(5));
            }else{
                out.println("<font color = 18 size = 18 > Incorrect Credentials ! <br>");
                out.println("<a href= index.jsp> Try Again! </a>");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
