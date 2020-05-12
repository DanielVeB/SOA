package jms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.*;

@ApplicationScoped
public class JMSPublisher {


    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "jms/topic/SOA")
    private Topic topic;


    private Logger logger = LoggerFactory.getLogger(JMSPublisher.class);


    public void sendMessage(String userName, String message) {
        try (Connection con = cf.createConnection()) {
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(topic);
            con.start();

            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("message", message);
            mapMessage.setStringProperty("username", userName);
            publisher.send(mapMessage);

        } catch (Exception ex) {
            logger.error("ERROR");
        }
    }

    public void broadcastForumMessage(String forumName, String message) {

        try (Connection con = cf.createConnection()) {
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(topic);
            con.start();

            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("message", message);
            mapMessage.setStringProperty("forum", forumName);
            publisher.send(mapMessage);

        } catch (Exception ex) {
            logger.error("ERROR");
        }
    }

}
