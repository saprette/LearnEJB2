package packt;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/test")
})
public class SalutationMessageBeanBean implements MessageListener {
    public SalutationMessageBeanBean() {
    }

    @Override
    public void onMessage(Message message) {

        try {
            String name = message.getStringProperty("name");
            Logger.getLogger("SalutationLog").log(Level.INFO, "Salutation processed");

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
