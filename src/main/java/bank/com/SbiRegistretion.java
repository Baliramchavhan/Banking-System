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
import java.util.Random;


public class SbiRegistretion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection conn;
    PreparedStatement ps;

	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebookdb","root","root");
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int number=0;
		
		Random rm=new Random();
		String s1=request.getParameter("branch");
		String s22=request.getParameter("ifc");
		String s33=request.getParameter("adharnumber");
     	long l33=Long.parseLong(s33);
     	
	     String s3=request.getParameter("cname");
	     
		String s4=request.getParameter("phone");
		long l4=Long .parseLong(s4);
		
		String s5=request.getParameter("amount");
		long l5=Long .parseLong(s5);
		
		String s6=request.getParameter("address");
		PrintWriter pw=response.getWriter();
		Random r1=new Random();
       long uname= r1.nextInt(10000);
      String m1=s3 + uname + "@Gmail.com";
      
      long p=uname;
		if(l4>999999999 && l4<99999999999l) {
		if(l5>=1000) {
		try {
			 ps=conn.prepareStatement("insert into SBI values(?,?,?,?,?,?,?,?,?,?)");
		  
			ps.setString(1, s1);
			ps.setString(2, s22);
			ps.setLong(3,l33);
		
			long accoundNumber=rm.nextLong(1000000000l);
            ps.setLong(4,accoundNumber);
			ps.setString(5, s3);
			ps.setLong(6, l4);	
			ps.setLong(7, l5);
			ps.setString(8, s6);
			ps.setString(9, m1);
			ps.setLong(10, p);
		
			
			ps.executeUpdate();
			
			
			pw.println("<html><body bgcolor=red> <center><h2>");
			pw.println("Dear "+s3+" Your Account Created successfully....<br>");
			pw.println("<a href=home1.html>Submit</a><br>");
			pw.println("</h2></center></body></html>");
		
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			pw.println("<html><body bgcolor=red> <center><h2>");
			pw.println("THIS USER ALREDY EXIST.... TRY WITH DIFFERENT INFORNMATION.! OR <br>");
			pw.println("SOMETHING WENT WRONG..... <br>");
		    pw.println(" <a href=Home.html> Go back to home page</a>");
			pw.println("</h2></center></body></html>");
	       	e.printStackTrace();
			
	}
		}else {
			 pw.println("<html><body bgcolor=red> <center><h2>");
       	  pw.println("A/C Balnce Should be above Rs:1000<br>");
       	  pw.println("<a href=sbireg.html>GO BACK</a><br>");
       	  pw.println("</h2></center></body></html>");
		}
          }else {
        	  pw.println("<html><body bgcolor=red> <center><h2>");
        	  pw.println("Enter 10 Digits Phone No:<br>");
        	  pw.println("<a href=sbireg.html>GO BACK</a><br>");
        	  pw.println("</h2></center></body></html>");
		}
       
	}

}
