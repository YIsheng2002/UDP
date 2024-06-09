package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender implements Runnable {
    
    private String message;
    private String address;
    private int port;

    public UDPSender(String message, String address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = message.getBytes();
            InetAddress varAdd = InetAddress.getByName(address);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, varAdd, port);

            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();

            System.out.println("Data Sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
