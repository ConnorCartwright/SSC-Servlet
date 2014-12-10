

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddContactServlet
 * This class is used to add a contact to the Database
 */
@WebServlet("/AddContactServlet")
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(); // get the session
		Connection c = (Connection) s.getAttribute("connection"); // get the attribute
		try {
			Statement statement = c.createStatement();
			String addContact =  "INSERT INTO Contacts(FirstName, SecondName, EmailAddress) VALUES ('" + request.getParameter("firstNameInput") + // statement to add a contact
							     "', '" + request.getParameter("secondNameInput") + "', '" + request.getParameter("emailAddressInput") + "');";  // to the database
			statement.executeUpdate(addContact); // execute the statement
			statement.close(); // and close the statement object
			
			RequestDispatcher rd = request.getRequestDispatcher("/ContactAdded.jsp"); // redirect the user
			rd.forward(request, response); 
		}
		catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/ContactAddedFail.jsp"); // redirect the user to the fail page, if things went wrong
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
