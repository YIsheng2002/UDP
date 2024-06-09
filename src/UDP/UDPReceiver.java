package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JTextArea;

public class UDPReceiver implements Runnable {
    
    private int port;
    private JTextArea responseFld;

    public UDPReceiver(int port, JTextArea responseFld) {
        this.port = port;
        this.responseFld = responseFld;
    }

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(port);
            byte[] buffer = new byte[10000];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                
                // Update the JTextArea on the Event Dispatch Thread
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        responseFld.setText(receivedMessage);
                    }
                });

                // Optionally, send a response back to the sender
                InetAddress senderAddress = packet.getAddress();
                int senderPort = packet.getPort();
                byte[] responseBuffer = "This is a response from receiver...".getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, senderAddress, senderPort);
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
