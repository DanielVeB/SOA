package jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class JMSReceiver implements MessageListener {

    private final String userName;

    public JMSReceiver(String userName) {
        this.userName = userName;
    }

    @Override
    public void onMessage(Message m) {
        MapMessage message = (MapMessage) m;
        try {
            String text = message.getString("message");
            System.out.println("[MESSAGE]  " + userName + ":GET message" + text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
