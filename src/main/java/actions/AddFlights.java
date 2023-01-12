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


@WebServlet("/AddFlights")
public class AddFlights extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public AddFlights() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		PrintWriter out=response.getWriter();
		
		String fname = request.getParameter("flightName");
		String fno = request.getParameter("flightNo");
		String date = request.getParameter("date");
		String price = request.getParameter("price");
		String src = request.getParameter("source");
		String dest = request.getParameter("destination");
		
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhigh","root","root");
			PreparedStatement stmt=con.prepareStatement("insert into flight values(?,?,?,?,?,?)");	
		
			stmt.setString(1,fname);
			stmt.setString(2,fno);
			stmt.setString(3,price);
			stmt.setString(4,src);
			stmt.setString(5,dest);
			stmt.setString(6, date);
			
			out.print("<h5 style='color:red'>Flight Added Successfully....!</h5>");
			RequestDispatcher rd= request.getRequestDispatcher("AddFlight.html");
			rd.forward(request, response);
			
			stmt.execute();
			stmt.close();
			con.close();
			System.out.println("DATA INSERTED SUCESSFULLY");
			
		}catch(Exception e){
			System.err.println(e);
		}
	}

}
