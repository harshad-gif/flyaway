package actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SearchFlight")
public class SearchFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String froom = request.getParameter("source");
		String to = request.getParameter("destination");
		String date = request.getParameter("date");
		//String count = request.getParameter("count");
		System.out.println(date);
		System.out.println(froom);
		System.out.println(to);
		//System.out.println(count);
		int i=0;
		int k =1;
		ArrayList<Integer> al=new ArrayList<Integer>(); 
		ArrayList<Integer> price=new ArrayList<Integer>(); 
		 HttpSession session=request.getSession();
		 //session.setAttribute("pcount", count);
		response.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyhigh","root","root");
			PreparedStatement stmt=con.prepareStatement("select * from flight where (source , destination,date) =(?,?,?);");	
			stmt.setString(1,froom);
			stmt.setString(2,to);
			stmt.setString(3,date);
			ResultSet	rs=stmt.executeQuery();
			
			 out.println("<table border=1 style=\"text-align:center;\">"); 
			
		     out.println("<tr><th>FLIGHT Name</th><th>FLIGHT No</th><th>Price</th><th>From</th><th>TO</th><th>Date</th></tr>");
		     if (!rs.isBeforeFirst() ) {    
		    	    out.println("<h5 style = \"color:red;\">SORRY CURRENTLY NO FLIGHTS AVAILABLE </h5>\r\n"
		    	    		+ "</center>"); 
		    	}
		     else { while(rs.next())
			 {
		    	 out.println("<tr><td>" + rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td> "+rs.getString(3)+"</td><td> "+rs.getString(4)+ "</td><td>" + rs.getString(5)+"<td> "+rs.getString(6)+"</td><td><a href=\"BookFlight.html\">Book Flight</a></td></tr>");   
				    out.print("<br>");
				    
			 }}
			
		     
			out.print("Click here to  <a href='HomePage.html'>GO BACK </a>");
				stmt.close();
				con.close();
		       }catch(Exception e) {
		    	   System.err.println(e);
           
            
		}
	}

}
