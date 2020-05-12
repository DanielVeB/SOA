package jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.UUID;

@Stateless
public class Subscriber {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "java:/jms/topic/SOA")
    private Topic jmsTopic;

    private final Logger logger = LoggerFactory.getLogger(Subscriber.class);

    public void registerSubscriber(String userName) throws JMSException {

        logger.info("Register subscriber {}", userName);
        Connection connection = cf.createConnection();
        connection.setClientID(userName);

        connection
                .createSession(false, Session.AUTO_ACKNOWLEDGE)
                .createDurableConsumer(jmsTopic, "username", "username ='" + userName + "'", true)
                .setMessageListener(new JMSReceiver(userName));
        connection.start();
    }

    public void registerSubscriberToForum(String forumName, String userName) throws JMSException {

        logger.info("Register subscriber {} to forum {}", userName, forumName);
        Connection connection = cf.createConnection();
        connection.setClientID(UUID.randomUUID().toString());

        connection
                .createSession(false, Session.AUTO_ACKNOWLEDGE)
                .createDurableSubscriber(jmsTopic, "forum", "forum ='" + forumName + "'", true)
                .setMessageListener(new JMSReceiver(userName));
        connection.start();
    }
}
