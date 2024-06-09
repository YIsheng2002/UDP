package UDP;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UDPClient {

    private JFrame frame;
    private JTextArea messageFld;
    private JTextArea responseFld;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UDPClient window = new UDPClient();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public UDPClient() {
        initialize();
        startReceiver();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 398, 279);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Enter Your Text:");
        lblNewLabel.setBounds(38, 31, 100, 13);
        frame.getContentPane().add(lblNewLabel);
        
        JButton sendBtn = new JButton("Send");
        sendBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        sendBtn.setBounds(297, 27, 85, 21);
        frame.getContentPane().add(sendBtn);
        
        messageFld = new JTextArea();
        messageFld.setBounds(38, 54, 244, 55);
        frame.getContentPane().add(messageFld);
        
        JLabel lblNewLabel_1 = new JLabel("Response:");
        lblNewLabel_1.setBounds(38, 133, 65, 13);
        frame.getContentPane().add(lblNewLabel_1);
        
        responseFld = new JTextArea();
        responseFld.setBounds(38, 156, 244, 55);
        frame.getContentPane().add(responseFld);
    }

    /**
     * Start the UDP receiver thread.
     */
    private void startReceiver() {
        int port = 8080; // The port the receiver will listen on
        UDPReceiver receiver = new UDPReceiver(port, responseFld);
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();
    }

    /**
     * Send the message entered in the messageFld.
     */
    private void sendMessage() {
        String message = messageFld.getText();
        String address = "10.200.81.49"; // Change this to the receiver's IP address
        int port = 8080;

        UDPSender sender = new UDPSender(message, address, port);
        Thread senderThread = new Thread(sender);
        senderThread.start();
    }
}
