package customer;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    Connection con;

    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/orderdb", 
                "root", 
                "lalli@2007"
            );
			
            System.out.println("Database connected.");
        } catch (Exception e) {
            throw new ServletException("DB Connection error: " + e.getMessage());
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String action = req.getParameter("action");

        if ("place".equals(action)) {
            String name = req.getParameter("name");
            String item = req.getParameter("item");
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            try {
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO orders(name, item, quantity) VALUES (?, ?, ?)"
                );
                ps.setString(1, name);
                ps.setString(2, item);
                ps.setInt(3, quantity);

                int i = ps.executeUpdate();
                if (i > 0) {
                    out.println("Order Placed Successfully!<br>");
                    out.println("Customer: " + name + "<br>");
                    out.println("Item: " + item + "<br>");
                    out.println("Quantity: " + quantity + "<br><br>");
                    out.println("<a href='order.html'>Place Another Order</a><br>");
                } else {
                    out.println("Order Failed!<br>");
                    out.println("<a href='order.html'>Try Again</a>");
                }
            } catch (SQLException e) {
                out.println("Error: " + e.getMessage());
            }
        } else {
            out.println("Invalid Action!<br>");
            out.println("<a href='order.html'>Place Order</a>");
        }
    }

  public void destroy() {
        try {
            if (con != null) con.close();
            System.out.println("Database connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
