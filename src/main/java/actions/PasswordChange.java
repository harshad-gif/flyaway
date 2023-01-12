package actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PasswordChange")
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PasswordChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String pass = request.getParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhigh","root","root");
			PreparedStatement stmt = con.prepareStatement("UPDATE admin SET password= ? WHERE username = 'admin';"); 
			stmt.setString(1, pass);
			stmt.execute();
			out.print("<h5 style='color:blue'>Password Change Successfully....!</h5>");
			RequestDispatcher rd=request.getRequestDispatcher("AddFlight.html");
			rd.forward(request, response);
			stmt.close();
			con.close();
						
		}catch(Exception e) {
		System.err.println(e);
		
		}
	}

}
