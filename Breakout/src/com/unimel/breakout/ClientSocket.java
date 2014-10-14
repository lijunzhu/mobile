package com.unimel.breakout;

import java.io.BufferedInputStream;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.net.Socket;  
import java.net.UnknownHostException;  
public class ClientSocket {  
    private String ip;  
    private int port;  
    private Socket socket = null;  
    DataOutputStream out = null;  
    DataInputStream getMessageStream = null;  
    public ClientSocket(String ip, int port) {  
        this.ip = ip;  
        this.port = port;  
    }  
    public void createConnection() {  
        try {  
            socket = new Socket(ip, port);  
        } catch (UnknownHostException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            if (socket != null) {  
                try {  
                    socket.close();  
                } catch (IOException e1) {  
                    // TODO Auto-generated catch block  
                    e1.printStackTrace();  
                }  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            if (socket != null) {  
                try {  
                    socket.close();  
                } catch (IOException e1) {  
                    // TODO Auto-generated catch block  
                    e1.printStackTrace();  
                }  
            }  
        } finally {  
        }  
    }  
    public void sendMessage(String sendMessage) {  
        try {
        	out.writeUTF(sendMessage);  
            out.flush();  
              
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            if (out != null) {  
                try {  
                    out.close();  
                } catch (IOException e1) {  
                    // TODO Auto-generated catch block  
                    e1.printStackTrace();  
                }  
            }  
        }  
    }  
    public DataInputStream getMessageStream() {  
        try {  
            getMessageStream = new DataInputStream(new BufferedInputStream(  
                    socket.getInputStream()));  
            // return getMessageStream;  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            if (getMessageStream != null) {  
                try {  
                    getMessageStream.close();  
                } catch (IOException e1) {  
                    // TODO Auto-generated catch block  
                    e1.printStackTrace();  
                }  
            }  
        }  
        return getMessageStream;  
    }  
    public void shutDownConnection() {  
        try {  
            if (out != null) {  
                out.close();  
            }  
            if (getMessageStream != null) {  
                getMessageStream.close();  
            }  
            if (socket != null) {  
                socket.close();  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}  