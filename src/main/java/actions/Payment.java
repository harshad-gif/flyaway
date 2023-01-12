package actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		 HttpSession session=request.getSession(false);  
		 String n=(String)session.getAttribute("fid");
		 String m=(String) session.getAttribute("uphone");
		 Integer o=(Integer) session.getAttribute("itr");
		 System.out.println(n);
		 System.out.println(m);
		 out.println(n);
		 out.println("</br>");
		 out.println(m);
		 out.println("</br>");
		 out.println(o);
	}

}
