/* ENAPS Not Another Port Scanner

A very simple port scanner for educational purposes only.
Created by Charalampos Kontos, December 2021.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>. */

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
    
public class Scanner3{
    public static void main(String[] args) {
    JFrame window;
    JButton button;
    JTextField field;
    JTextArea ports;
    
    window = new JFrame("ENAPS v.1.1 by C. Kontos");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setSize(600, 600);
    window.setLayout(new BorderLayout(0,0));
    ports = new JTextArea();
    ports.setBorder(BorderFactory.createLineBorder(Color.black));
    ports.setEditable(false);
    ports.setText(" Instructions: Set the IP address you want to scan in the text field above.\n Click on \"Scan IP\" button to scan.");
    JScrollPane pane = new JScrollPane(ports, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    field = new JTextField("Type IP Address here");
    button = new JButton("Scan IP");
    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ports.setText(null);
            try {
                int timeout = 10;
                InetAddress host = InetAddress.getByName(field.getText());
        
                for (int port = 0; port <= 10000; port++)
                    if (openPort(port, host, timeout))
                    ports.append(" Port " + port + " on host " + host + " is open.\n");
                } catch (UnknownHostException h) {
                    ports.append(" Unknown host.");
                }
            }
        });
    window.add(pane, BorderLayout.CENTER);
    window.add(field, BorderLayout.PAGE_START);
    window.add(button, BorderLayout.PAGE_END);
    //window.add(ports);
    window.setVisible(true);

    }

    public static boolean openPort(int port, InetAddress ip, int timeout) {
       try {
            Socket mySocket = new Socket();
            mySocket.connect(new InetSocketAddress(ip, port), timeout);
            mySocket.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}