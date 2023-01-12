package actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/BookFlight")
public class BookFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BookFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession(false);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
	    String username = request.getParameter("username");
	    String phone = request.getParameter("phone");
	    String age = request.getParameter("age");
	    String address = request.getParameter("address");
	    String gender = request.getParameter("GENDER");
	    String email = request.getParameter("email");
	
	System.out.println(username);
    
    try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhigh","root","root");
		PreparedStatement stmt=con.prepareStatement("insert into user(username,phone,age,gender,email) values(?,?,?,?,?);");					
		stmt.setString(1,username);
		stmt.setString(2,phone);
		stmt.setString(3,age);
		stmt.setString(4,gender);
		stmt.setString(5, email);
		
		
	 
		stmt.execute();
		stmt.close();
		con.close();
		RequestDispatcher rd=request.getRequestDispatcher("Payment.html");  
        rd.forward(request, response);
		System.out.println();
		System.out.println();
		System.out.println("DATA INSERTED SUCESSFULLY");
	
	}catch(Exception e) {System.err.println(e);
	
	
	}
}

	
}
