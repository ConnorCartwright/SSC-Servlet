

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 * This class is used to log in to the Application
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(); // get the session
		Connection c = null;
		String username = request.getParameter("emailInput"); // get the email/username entered
		String password = request.getParameter("passwordInput"); // get the password
		String smtphost = "smtp.gmail.com";

		// Step 1: Set all Properties
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", smtphost);
		props.put("mail.smtp.port", "587");
		// Set Property with username and password for authentication
		props.setProperty("mail.user", username);
		props.setProperty("mail.password", password);
		// Step 2: Establish a mail session (java.mail.Session)
		Session session = Session.getDefaultInstance(props);	

		s.setAttribute("email", username); // set the attributes
		s.setAttribute("password", password); // set the attributes
		s.setAttribute("smtphost", smtphost); // set the attributes 
		s.setAttribute("session", session); // set the attributes
		
		try {
			Transport transport = session.getTransport("smtp");
			transport.connect(smtphost, username, password);
			RequestDispatcher rd = request.getRequestDispatcher("/MainMenu.jsp");
			rd.forward(request, response);
		}
		catch (AuthenticationFailedException e){
			RequestDispatcher rd = request.getRequestDispatcher("/FailedLogin.jsp");
			rd.forward(request, response);
			
		} catch (MessagingException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/FailedLogin.jsp");
			rd.forward(request, response);
		}
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			s.setAttribute("connection", c);
			Statement statement = c.createStatement();
			String drop = "DROP TABLE Contacts";
			statement.executeUpdate(drop);
			String create = "CREATE TABLE Contacts (ID serial, FirstName varchar(50), SecondName varchar(50), EmailAddress varchar(50))";
			statement.executeUpdate(create);
			statement.close();
			System.out.println("Connected...");
			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
