import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

  private final static String QUEUE_NAME = "hello_queue";
  private final static String ANOTHER_QUEUE = "another_queue";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    
    // Define the broker we want to connect to, here the local machine
    factory.setHost("localhost");
    // create a connection to the server
    Connection connection = factory.newConnection();
    // create a channel (same as the receiver)
    Channel channel = connection.createChannel();
    
    // Declare a queue
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    channel.queueDeclare(ANOTHER_QUEUE, false, false, false, null);
    
    String message1 = "===== message1 ======";
    String message2 = "===== message2 ======";
    String message3 = "===== message3 ======";
    
    // publish messages
    channel.basicPublish("", QUEUE_NAME, null, message1.getBytes("UTF-8"));
    System.out.println(" [x] Sent to " + QUEUE_NAME + "' "  + message1 + "'");
    channel.basicPublish("", QUEUE_NAME, null, message2.getBytes("UTF-8"));
    System.out.println(" [x] Sent to " + QUEUE_NAME + "' "  + message2 + "'");
    
    channel.basicPublish("", ANOTHER_QUEUE, null, message3.getBytes("UTF-8"));
    System.out.println(" [x] Sent to " + ANOTHER_QUEUE + "' "  + message3 + "'");
    
    //close channel
    channel.close();
    //close connection
    connection.close();
  }
}