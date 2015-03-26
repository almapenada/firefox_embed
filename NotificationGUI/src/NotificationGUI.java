import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class NotificationGUI extends JFrame{

	private int width=300;
	private int height=200;
	private int taskbarsize=47;
	private NotificationGUI f=this;
	private int defaultspacing=0;
	private int cnn;
	private NotificationManager nm;
	private double swidth;
	private double sheight;
	private long initTime;
	private boolean disposed=false;
	
	public NotificationGUI(NotificationManager nm, String text) {
		getContentPane().setBackground(Color.CYAN);
		this.width=nm.getNotewidth();
		this.height=nm.getNoteheight();
		this.setInitTime(System.currentTimeMillis());
		this.cnn=nm.getCurrentNotificationNumber();
		this.nm=nm;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		swidth = screenSize.getWidth();
		sheight = screenSize.getHeight();
		setUndecorated(true);
		
		if(getCnn()>=1){
			defaultspacing=10*getCnn();
		}else{
			defaultspacing=0;
		}
		
		GradientPanel pbase = new GradientPanel();
		JPanel pright = new JPanel();
//		JPanel pleft = new JPanel();
//		JPanel pup = new JPanel();
//		JPanel pdown = new JPanel();
		JPanel pcenter = new JPanel();
		JTextArea l1 = new JTextArea(text);//max 77
		l1.setLineWrap(true);
		
		pright.setOpaque(false);
		pcenter.setOpaque(false);
		l1.setOpaque(false);
		pbase.setLayout(new BorderLayout());
		l1.setFont(new Font("Something Strange",1,22));
		l1.setForeground(Color.RED);
		l1.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 5));
		l1.setEditable(false);
		
		setSize(this.width,this.height);
		setLocation((int)(swidth-this.width),(int)(sheight-taskbarsize-this.height*(getCnn()+1)-defaultspacing));
		
		JButton b1 = new JButton("X");
		b1.setBackground(Color.RED);
		b1.setForeground(Color.WHITE);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.setDisposed(true);
				f.dispose();
				nm.throwWarning((NotificationGUI) f);
			}
		});
		
		pbase.add(pright, BorderLayout.EAST);
		pright.add(b1);
		pbase.add(pcenter, BorderLayout.CENTER);
//		pbase.add(pleft, BorderLayout.WEST);
//		pbase.add(pup, BorderLayout.NORTH);
//		pbase.add(pdown, BorderLayout.SOUTH);
		pbase.add(l1);
		
		b1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setOpacity(0.55f);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				f.setOpacity(1);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		add(pbase);
		setOpacity(0.55f);
		setAlwaysOnTop(true);
		setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = getBackground();
        Color color2 = color1.darker();
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(
            0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
	
	public void moveDown(int cnn){
		if(cnn>=2){
			defaultspacing=10*cnn;
		}else{
			defaultspacing=0;
		}
		setLocation((int)(swidth-this.width),(int)(sheight-taskbarsize-this.height*cnn-defaultspacing));
		this.cnn--;
	}

	public int getCnn() {
		return cnn;
	}

	public void setCnn(int cnn) {
		this.cnn = cnn;
		moveDown(cnn);
	}

	public long getInitTime() {
		return initTime;
	}

	public void setInitTime(long initTime) {
		this.initTime = initTime;
	}

	public boolean isDisposed() {
		return disposed;
	}

	public void setDisposed(boolean disposed) {
		this.disposed = disposed;
	}

}
