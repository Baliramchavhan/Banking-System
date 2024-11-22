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


public class TransferMony extends HttpServlet {
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
		try {
			

			String s3=request.getParameter("res");
			long l3=Long.parseLong(s3);
			
			String s1=request.getParameter("amount");
			long l1=Long.parseLong(s1);

			String s2=request.getParameter("anumber");
			long l2=Long.parseLong(s2);
		
		
			
			 ps=conn.prepareStatement("select*from sbi where anumber=?");
		     ps.setLong(1, l2);
		    
		   
		
			ResultSet set=ps.executeQuery();
			
			if(set.next()) {
				double balance=set.getDouble("amount");
				if(l1<=balance) {
					String debit_query="update sbi set amount=amount-? where anumber=?";
					String creadit_query="update sbi set amount=amount+? where anumber=?";
					PreparedStatement cps=conn.prepareStatement(creadit_query);
					PreparedStatement dps=conn.prepareStatement(debit_query);
					cps.setDouble(1, balance);
					cps.setLong(2, l3);
					dps.setDouble(1 ,balance);
					dps.setLong(2, l2);
					int rows1 =cps.executeUpdate();
					int rows2=dps.executeUpdate();
					if(rows1>0 && rows2 >0) {
						pw.println("Transaction successfuly...");
						pw.println("Rs."+ balance + " Trasfered successful...");
						conn.commit();
						conn.setAutoCommit(true);
					}else {
						conn.rollback();
						conn.setAutoCommit(true);
					}
					
				}else {
					pw.println("Insuficent Balance...");
				}
				}else {
					pw.println("Wrong A/C");
				}
			
		}catch(SQLException e) {
			
			pw.println("Transaction faild...");
			e.printStackTrace();
		}
		     
		finally {
			try {

				if(ps!=null);
				ps.close();
			
				if(conn!=null);
				//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
