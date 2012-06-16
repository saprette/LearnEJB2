package servlet;

import packt.SalutationBean;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/MessageDrivenServlet"})
public class MessageDrivenServlet extends HttpServlet {


    @EJB
    private SalutationBean salutationBean;

    @Resource(mappedName = "java:/ConnectionFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "java:jboss/exported/jms/queue/test")
    private Queue queue;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SalutationServlet</title>");
            out.println("<h1>" +
                    salutationBean.getFormalSalutation("Sherlock Holmes ajout de jboss") +
                    "</h1>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
            sendJmsMessage();
        } finally {
            out.flush();
            out.close();

        }
    }

    private void sendJmsMessage() {
        String message = "Salutation generated for MDB";
        try {
            Connection connection = queueConnectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            messageProducer.send(textMessage);
            Logger.getLogger("SalutationLog").log(Level.WARNING,
                    "Message sent successfully", "Message sent successfully2");


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
