

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class FilterContactsServlet
 * This class is used to filter contacts based on first or second name
 * via user input.
 */
@WebServlet("/FilterContactsServlet")
public class FilterContactsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterContactsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(); // get the session
		Connection c = (Connection) s.getAttribute("connection"); // get the connection
		PrintWriter output = response.getWriter(); // get ready to print output
		
		try {
			Statement statement = c.createStatement(); // start the create statement object
			String filter = ""; // initialise variable that will hold the statement text
			
			if(request.getParameter("firstNameInput").equals("")) {
				if (request.getParameter("secondNameInput").equals("")) { // if both fields were entered as empty
					RequestDispatcher dispatcher = request.getRequestDispatcher("/FilterFailed.jsp"); // if both fields were entered as empty
			        dispatcher.forward(request, response); // if both fields were entered as empty
				}
				else { // if only the second name field has text
					filter = "SELECT FirstName, SecondName, EmailAddress FROM Contacts WHERE SecondName LIKE '%" + request.getParameter("secondNameInput") + "%';";
				}
			} 
			else if(request.getParameter("secondNameInput").equals("")) { // if only the first name field has text
				filter = "SELECT FirstName, SecondName, EmailAddress FROM Contacts WHERE FirstName LIKE '%" + request.getParameter("firstNameInput") + "%';";
			}
			else { // if both fields have text
				filter = "SELECT FirstName, SecondName, EmailAddress FROM Contacts WHERE FirstName LIKE '%" + request.getParameter("firstNameInput") // if both fields have text
						  + "%' OR SecondName LIKE '%" + request.getParameter("secondNameInput") + "%';"; // if both fields have text
			}
			ResultSet rs = statement.executeQuery(filter); // set the result set
			setOutputToDefaultLayout(output); // set it to the default layout of our page
			try {
				if (!rs.isBeforeFirst()) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/FilterFailed.jsp"); // if there's no records, redirect them to the fail page
			        dispatcher.forward(request, response); // if there's no records, redirect them to the fail page
				}
				else {
					while (rs.next()) { // while we have more records
						output.println("<tr>"); // add each one to a table row appropriately
						for(int i = 1; i < 4; i++) {
							output.println("<td>" + rs.getString(i));
						}
						output.println("</tr>");
					}
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			output.println("</table><form action='MainMenuServlet' method='get'><button type='submit' class='btn btn-primary btn-lg'>Main Menu</button></form></body></html>");
		}
		catch (SQLException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/FilterFailed.jsp");
	        dispatcher.forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void setOutputToDefaultLayout(PrintWriter output) {
		output.println("<!DOCTYPE html>");
		output.println("<html><head>");
		output.println("    <meta charset='utf-8'> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta name='viewport' content='width=device-width, initial-scale=1'> "
				+ "<meta name='description' content=''> <meta name='author' content=''>");
		output.println("<link rel='icon' href='${pageContext.request.contextPath}/resources/favicon.ico'>");
		output.println("<title>Records found</title>");
		output.println("<link href='${pageContext.request.contextPath}/resources/dist/css/bootstrap.min.css' rel='stylesheet' >");
		output.println("<link href='${pageContext.request.contextPath}/resources/cover.css' rel='stylesheet' > </head>");
		output.println("<body> <div class='site-wrapper'> <div class='site-wrapper-inner'> <div class='cover-container'> <div class='masthead clearfix'>"
				+ "<div class='inner'> <h2 class='masthead-brand'>SSC Exercise 5 - Java Servlet</h2> </div> </div> </div> </div> </div>");
		output.println("<table class='table-striped table-condensed table-hover'><tr><th>First Name<th>Second Name<th>Email Address</tr>");
		
		
	}

}
