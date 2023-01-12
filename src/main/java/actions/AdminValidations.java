package actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminValidations
 */
@WebServlet("/AdminValidations")
public class AdminValidations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminValidations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		PrintWriter out=response.getWriter();
		 response.setContentType("text/html");
		 out.println("<html><body>");
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhigh","root","root");
			Statement stmt=con.createStatement();
			ResultSet	rs=stmt.executeQuery("select * from admin");
			
			while(rs.next()) {
				if(username.equalsIgnoreCase(rs.getString(2)) && password.equalsIgnoreCase(rs.getString(3)))
				{
					RequestDispatcher rd= request.getRequestDispatcher("AddFlight.html");
					rd.forward(request, response);
				}
				
				out.print("<h5 style='color:red'>Sorry InValid Credentials....!</h5>");
				RequestDispatcher rd=request.getRequestDispatcher("HomePage.html");
				rd.include(request, response);
				
			}
			stmt.close();
			con.close();
			
		}catch(Exception e){
			System.err.println(e);
		}
	}

}
