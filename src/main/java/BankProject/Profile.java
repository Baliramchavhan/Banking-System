package BankProject;

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


public class Profile extends HttpServlet {
	Connection con;
	
	public void init(ServletConfig config) throws ServletException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:orcl","system","oracle");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  PrintWriter pw=response.getWriter();
	  String s1=request.getParameter("anumber");
	  long l1=Long.parseLong(s1);
	  try {
		  PreparedStatement ps=con.prepareStatement("select* from sbi where adharnumber=?");
		  ps.setLong(1, l1);
		 // ps.executeUpdate();
		ResultSet rs=ps.executeQuery();
		pw.println("<html><body bgcolor=silver><center><h2>");
		if(rs.next()) {
			pw.println("<h2>Costomer Dtails..!</h2><br>");
			pw.println("Costomer Name : "+rs.getString("cname")+"<br>"+"-----------------------------------"+"<br>"+ "Branch Name : "+rs.getString("branch")+"<br>"+"-----------------------------------"+"<br>"+"Phone No : "+ rs.getLong("phone")+"<br>"+"-----------------------------------"+"<br>"+"Adhar No : "+rs.getLong("adharnumber")+"<br>"+"-----------------------------------"+"<br>"+" A/C NO : "+rs.getLong("anumber")+"<br>"+"-----------------------------------"+"<br>"+"Adress : "+rs.getString("address")+"<br>"+"-----------------------------------"+"<br>");
		
		 pw.println(" <a href=home1.html> Go back to home page</a>");
		}
		else {
			pw.println("Comstamer Dtails is  Not Found.......!<br>");
			 pw.println(" <a href=home1.html> Go back to home page</a>");
		}
		pw.println("</h1></center></body></html>");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		pw.println("Somthing went Wrong.....!");
	}
	 
	}

}
