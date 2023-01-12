package actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FlightDetails")
public class FlightDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FlightDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");  
        out.println("<html><body>"); 
	    System.out.println();
	    
	    try {
	    	
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhigh","root","root");
	    	Statement stmt=con.createStatement();
	    	ResultSet	rs=stmt.executeQuery("select * from flight");
	        out.println("<table border=1>"); 
	        out.println("<h1> Flight List </h1>");
	        out.println("<tr><th>FLIGHT Name</th><th>FLIGHT No</th><th>Price</th><th>From</th><th>To</th><th>Date</th></tr>");
	        
	        while(rs.next()) {
	        	 
	        	out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3)+"<br></td><td>"+rs.getString(4)+"</br></td><td>"+rs.getString(5)+ "</td><td>" + rs.getString(6)+"</br></tr>");   
	            out.print("<br>");  
	        }
	        out.println("</table>"); 
	        
	        stmt.close();
			con.close();
	    }catch(Exception e){
	    	System.out.println(e);
	    }
	}

}
