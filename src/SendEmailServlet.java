

import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		Session session = (Session) s.getAttribute("session");
		String username = (String) s.getAttribute("email");
		String password = (String) s.getAttribute("password");
		String smtphost = (String) s.getAttribute("smtphost");
		
		try {
			// Step 3: Create a message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(request.getParameter("toInput")));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(request.getParameter("ccInput")));
			message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse((request.getParameter("bccInput"))));
			message.setSubject((request.getParameter("subjectInput")));
			MimeBodyPart messageBody = new MimeBodyPart();
			MimeMultipart multipart = new MimeMultipart();
			messageBody.setText((request.getParameter("bodyInput")));
			multipart.addBodyPart(messageBody);
			message.setContent(multipart);
			message.saveChanges();

			// Step 4: Send the message by javax.mail.Transport .
			Transport tr = session.getTransport("smtp"); // Get Transport object from session
			tr.connect(smtphost, username, password); // We need to connect
			tr.sendMessage(message, message.getAllRecipients()); // Send message
			
			RequestDispatcher rd = request.getRequestDispatcher("/EmailSent.jsp");
			rd.forward(request, response);

		} 
		catch (MessagingException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/EmailNotSent.jsp");
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
