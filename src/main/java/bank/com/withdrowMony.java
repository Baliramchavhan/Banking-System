package bank.com;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class withdrowMony extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private Connection conn;
    // static double bal;
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:orcl","system","oracle");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		try {
			
			
			
			String s1=request.getParameter("amount");
			long l1=Long.parseLong(s1);

			String s2=request.getParameter("pin");
			long l2=Long.parseLong(s2);
             if(l1<=20000) {
				
				if(l1>=100) {
					
			PreparedStatement ps=conn.prepareStatement("update SBI set amount=amount-? where  ADHARNUMBER=?");
		     ps.setLong(1, l1);
		    ps.setLong(2, l2);
	      int res	= ps.executeUpdate();
		
		    PreparedStatement ps1=conn.prepareStatement("select *from sbi");
				
			ResultSet set=ps1.executeQuery("select amount from sbi where  ADHARNUMBER="+l2+"");
			
			pw.println("<html><body bgcolor=pink><center><h1>");
		   
			if(set.next()) {
				   if(res>0) {
				int h1=set.getInt("amount");
				if(l1<=h1) {
				
					
				
			
		      pw.println("A/C  Debited for Rs: "+l1+"Successfully On<br>");
		    pw.println(LocalDateTime.now()+" "+ "<br>"+ " AVL BAL "+h1+" State Bank Of India<br>");
		    pw.println(" <a href=home1.html> Go back to home page</a>");
				}else {
					pw.println("INSUFFICIENT BALANCE TO YOUR A/C <br>");
					 pw.println(" <a href=home1.html> Go back to home page</a>");
				}
				   }
			}else {
				pw.println("Please Enter Valid A/C <br>");
				 pw.println(" <a href=home1.html> Go back to home page</a>");
			}
					
		 
			
					}else {	
			pw.println("Balace Enter Above Rs: 100 <br>");
			 pw.println(" <a href=home1.html> Go back to home page</a>");
					}
				
			}else {
				pw.println("YOUR MAXIMAM LIMITE IS RS: 20000 THOSAND ONLY<br>");
				 pw.println(" <a href=home1.html> Go back to home page</a>");
			}
			
	
           
		} catch (SQLException e) {
	       e.printStackTrace();
	   	
	   	pw.println("Samething Went Wrong..!<br>");
	    pw.println(" <a href=home1.html> Go back to home page</a>");
	    pw.println("</h1></center></body></html>");
		
		}
	}

}
