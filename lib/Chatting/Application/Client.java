package Chatting.Application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Client implements ActionListener{
	
	static JFrame f = new JFrame();
	JTextField text;
	static Box vertical = Box.createVerticalBox();
	static DataOutputStream dos;
	static JPanel a1;
	static JScrollPane scrollPane;
	Client(){
		JPanel p1 = new JPanel();
		p1.setBounds(0,0,500,60);
		p1.setBackground(new Color(	30, 150 ,80));
		f.add(p1);
		p1.setLayout(null);
		// Back Button code
		
		ImageIcon m1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png")); // adding image 
		Image m2 = m1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);   // minimize the image to look good on frame 
		ImageIcon m3 = new ImageIcon(m2); // we can't pass image in the method so need to convert it into ImageIcon
		
		JLabel back = new JLabel(m3);
		back.setBounds(5,20,25,25);
		p1.add(back);
		
//		back.addMouseListener(new MouseAdapter() {
//			public void MouseClicked(MouseEvent ae) {
//				setVisible(false);
//				System.exit(0);
//			}
//		});
		
		back.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent ae) {
		    	f.setVisible(false);
		        System.exit(0);
		    }
		});
		
		// profile Picture code
		
		ImageIcon m4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png")); // adding image 
		Image m5 = m4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);   // minimize the image to look good on frame 
		ImageIcon m6 = new ImageIcon(m5); // we can't pass image in the method so need to convert it into ImageIcon
		
		JLabel profile = new JLabel(m6);
		profile.setBounds(40,7,50,50);
		p1.add(profile);
		
		// video Picture code
		
		ImageIcon m7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png")); // adding image 
		Image m8 = m7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);   // minimize the image to look good on frame 
		ImageIcon m9 = new ImageIcon(m8); // we can't pass image in the method so need to convert it into ImageIcon
		
		JLabel video = new JLabel(m9);
		video.setBounds(350,14,30,30);
		p1.add(video);
		
		
		// call picture code 
		ImageIcon m10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png")); // adding image 
		Image m11 = m10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);   // minimize the image to look good on frame 
		ImageIcon m12 = new ImageIcon(m11); // we can't pass image in the method so need to convert it into ImageIcon
		
		JLabel call = new JLabel(m12);
		call.setBounds(400,14,30,30);
		p1.add(call);
		
		// morevalt picture code
		
		ImageIcon m13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png")); // adding image 
		Image m14 = m13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);   // minimize the image to look good on frame 
		ImageIcon m15 = new ImageIcon(m14); // we can't pass image in the method so need to convert it into ImageIcon
		
		JLabel morevalt = new JLabel(m15);
		morevalt.setBounds(450,16,10,25);
		p1.add(morevalt);
		
		//User Name
		JLabel name = new JLabel("Savgun");
		name.setBounds(100,7,70,30);
		name.setForeground(Color.white);
		name.setFont(new Font("SAN_SERIF",Font.BOLD,16));
		p1.add(name);
		
		// Status Code
		
		JLabel status = new JLabel("Active now ");
		status.setBounds(100,7,90,69);
		status.setForeground(Color.white);
		status.setFont(new Font("SAN_SERIF",Font.BOLD,12));
		p1.add(status);
		
		// mesage Body code
		
		a1 = new JPanel();
		a1.setBounds(5, 75, 490, 565);
		scrollPane = new JScrollPane(a1);
        scrollPane.setBounds(5, 75, 490, 565);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        f.add(scrollPane);
		
		// Text Field code
		
		
		text = new JTextField();
		text.setBounds(10, 650, 380, 40);
//		text.setBounds(5, 655, 310, 40);
		text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		f.add(text);
		
		// send button code
		JButton button = new JButton("Send");
//		button.setBounds(400, 650, 80, 40);
		button.setBounds(410, 650, 82, 40);
//		button.setBounds(410, 610, 80, 40);
		button.addActionListener(this);
		button.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		button.setBackground(new Color(30, 150 ,80));
		button.setForeground(Color.white);
		
		f.add(button);
		
		// JFrame code
		f.setUndecorated(true);
		f.setLayout(null);
		f.getContentPane().setBackground(Color.white);
		f.setSize(500,700);
		f.setLocation(800,100);
		
		f.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			// TODO Auto-generated method stub
			String output = text.getText();
			System.out.println("Text is : "+output);
			a1.setLayout(new BorderLayout());
			JPanel p1 = formatedLabel(output,new Color(	30, 150 ,80));
			// Create panel to add String to the panel
			
			JPanel right = new JPanel(new BorderLayout());
			
			// Add message at the privies message ended. 
			
			right.add(p1,BorderLayout.LINE_END);
			
			// Add the component to a box Component
			
			vertical.add(right);
			vertical.add(Box.createVerticalStrut(15));
			
			// Add to the body panel
			
			a1.add(vertical,BorderLayout.PAGE_START);
			
			// Reload the JFrame to show the Msg on the Frame 
			dos.writeUTF(output);
			text.setText("");
			f.repaint();
			f.invalidate();
			f.validate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static JPanel formatedLabel(String output, Color color) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel l1 = new JLabel("<html><p style=\" width: 150px\">"+output+"</p></html>");
		l1.setFont(new Font("Tahoma",Font.PLAIN,16));
		
		l1.setBackground(color); // it set the background color but not visible yet.
		
		l1.setOpaque(true); // to show background colors
		
		l1.setBorder(new EmptyBorder(15,15,15,15)); // to set padding on the msg
		panel.add(l1);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
		
		JLabel time = new JLabel();
		time.setText(sdf.format(cal.getTime()));
		
		panel.add(time);
		// Scroll to the bottom
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
	            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
	        }
	    });
		// Scroll to the bottom
// SwingUtilities.invokeLater(new Runnable() {
//     public void run() {
//         JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
//         verticalScrollBar.setValue(a1.getPreferredSize().height);
//     }
// });

		return panel;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client(); // Enormous Object
		try {
		Socket s = new Socket("127.0.0.1",3000);
		DataInputStream dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		while(true) {
			a1.setLayout(new BorderLayout());
			String msg = dis.readUTF();  // protocol to read msg 
			
			JPanel panel = formatedLabel(msg ,new Color(255, 255, 255));
			JPanel left = new JPanel(new BorderLayout());
			left.add(panel,BorderLayout.LINE_START);
			vertical.add(left);
			vertical.add(Box.createVerticalStrut(15));
			a1.add(vertical,BorderLayout.PAGE_START);
			f.validate();
			// This is for msg resieved now for msg send
				
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
