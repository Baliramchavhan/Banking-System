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
import java.util.Random;


public class deposite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection conn;
       PreparedStatement ps;
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
		Random rand=new Random();
		int i=rand.nextInt(100000000);
		try {
			
			
			
			String s1=request.getParameter("amount");
			long l1=Long.parseLong(s1);

			String s2=request.getParameter("anumber");
			int l2=Integer.parseInt(s2);
			
			 if(l1>=100) {
			 ps=conn.prepareStatement("update SBI set amount=amount+? where ADHARNUMBER=?");
		     ps.setLong(1, l1);
		    ps.setInt(2, l2);
		    
			ps.executeUpdate();
			 ps=conn.prepareStatement("select*from sbi");
			pw.println("<html><body bgcolor=pink><center><h1>");
			 
			ResultSet set=ps.executeQuery("select amount from sbi where ADHARNUMBER="+l2+"");
			
			if(set.next()) {
				int balance1=set.getInt("amount");
			//	if(l1<=bal) {
		       pw.println("A/C  Credited for Rs: "+l1+" Successfully On <br>");
		    pw.println(LocalDateTime.now()+"AVL Balance "+balance1+" REF No by On: "+i+"<br"+" State Bank Of India<br>");
		    pw.println(" <a href=Home.html> Go back to home page</a>");
	   
			
			}else {
				pw.println("Please Enter Valid A/C");
				 pw.println(" <a href=home1.html> Go back to home page</a>");
			}
			  }else {
					pw.println("Enter Amount Above 100 Rs:");
					 pw.println(" <a href=home1.html> Go back to home page</a>");
				}
			pw.println("</h1></center></body></html>");
		      
		} catch (SQLException e) {
	       e.printStackTrace();
		}
		finally {
			/*try {
			
				if(ps!=null);
				ps.close();
				if(conn!=null);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}

}
