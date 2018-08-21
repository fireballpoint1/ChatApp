/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package chatapp;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author m
 */

public class Client extends JFrame implements Runnable,ActionListener 
{
    private static Scanner scanner = new Scanner( System.in );
    JButton b1,b2,messageSendButton,messageSendAllButton,listUsersButton,addUserButton,leaveRoomButton,createRoomButton,showRoomButton;
    JTextField t1,ip,port,chatRooms,message;
    JLabel t1Label,ipLabel,portLabel,chatRoomLabel,messageLabel;
    JTextArea listUsers;
    int server_port,self_port;
 Client()
 {
  showRoomButton=new JButton("Show Room");
  chatRoomLabel=new JLabel("Rooms:");
  chatRooms=new JTextField(4);
  createRoomButton=new JButton("Create Room");
  listUsersButton=new JButton("List Users");
  listUsers=new JTextArea(5,40);
  b1 =new JButton("Select File");
  b2 = new JButton("Send File");
  t1Label=new JLabel("File:");
  t1 = new JTextField(15);
  ipLabel=new JLabel("IP:");
  ip=new JTextField(20);
  portLabel=new JLabel("Port");
  port=new JTextField(4);
  messageLabel=new JLabel("Message");
  message=new JTextField(20);
  messageSendButton=new JButton("Send Message");
  messageSendAllButton=new JButton("Send to Chat room");
  
  
  setLayout(new FlowLayout());
  add(showRoomButton);
  add(chatRoomLabel);
  add(chatRooms);
  add(createRoomButton);
  add(listUsersButton);
  add(listUsers);
  add(b1);
  add(t1Label);
  add(t1);
  add(ipLabel);
  add(ip);
  add(portLabel);
  add(port);
  add(b2);
  add(messageLabel);
  add(message);
  add(messageSendButton);
  add(messageSendAllButton);
  ip.setText("127.0.0.1");
  port.setText("20");
  b1.addActionListener(this);
  b2.addActionListener(this);
 }
 public void run(int server_port){
     try{
         ServerSocket ss=new ServerSocket(server_port);
         while(true)
         {
             Socket s=ss.accept();
             InputStream is=s.getInputStream();
             byte b[]=new byte[102400];
             is.read(b);
             String s1=new String(b);
             System.out.println(s+" sends "+s1);
             message.setText(s1);
         }
         
     }catch(Exception e){e.printStackTrace();}
 }
 public void actionPerformed(ActionEvent ae)
 {
   if(ae.getSource()==b1)
  {
   FileDialog fd;
   Frame f1=new Frame();
   fd=new FileDialog(f1,"Select File");
   fd.setSize(300,300); 
   fd.setVisible(true);
   t1.setText(fd.getDirectory()+fd.getFile());
   
   }
  if(ae.getSource()==b2)
  {
    String s=t1.getText();
    String ipString=ip.getText();
    String portString=port.getText();
  try
  {
 FileInputStream f1 = new FileInputStream(s);
   byte b[]=new byte[102400];
   DataInputStream dis1 = new DataInputStream(f1);
   dis1.read(b,0,b.length);
  Socket so = new Socket("127.0.0.1",20);
  OutputStream os=so.getOutputStream();
          os.write(b);
         os.flush();
        so.close();
      }
catch(Exception e) { e.printStackTrace(); }
 }    
}
public static void main(String s[])
 {
//     System.out.print("Input IP :");
//     String ip=scanner.nextLine();
//     System.out.print("Enter port :");
//     String port=scanner.nextLine();
   new Client().setVisible(true);
 }
}
