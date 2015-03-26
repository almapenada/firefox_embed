import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

public class NotificationManager extends Thread {

	private Queue<NotificationGUI> wnotifications = new LinkedList<NotificationGUI>();
	private NotificationGUI[] cnotifications;
	private int currentNotificationNumber = 0;
	private int notewidth = 300;
	private int noteheight = 200;
	private long defaultTimer = 60000;
	private long speedTimer = 5000;
	private int maxNotes;
	private int screensizeheight;

	public NotificationManager() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screensizeheight = (int) screenSize.getHeight();
		maxNotes = screensizeheight / getNoteheight();
		cnotifications = new NotificationGUI[maxNotes];
	}

	@Override
	public void run() {
		while (!interrupted()) {
			try {
				long ctime=System.currentTimeMillis();
				for(int i=0;i<cnotifications.length;i++){
					if(cnotifications[i]!=null && ctime>=cnotifications[i].getInitTime()+defaultTimer){
						cnotifications[i].setVisible(false);
						cnotifications[i].dispose();
						cnotifications[i]=null;
						sleep(100);
					}
				}
				sleep(250);
				boolean flag = false;
				for(int i=0;i<cnotifications.length;i++){
					if(flag || cnotifications[i]==null || cnotifications[i].isDisposed()){
						flag=true;
						if(i==cnotifications.length-1){
							if(wnotifications.isEmpty()){
								cnotifications[i]=null;
							}else{
								NotificationGUI temp = wnotifications.poll();
								cnotifications[i].setCnn(i);
								cnotifications[i]=temp;
							}
						}else{
							cnotifications[i]=cnotifications[i+1];
							cnotifications[i].setCnn(i+1);
						}
					}
				}
				flag=false;
				sleep(250);
			} catch (NullPointerException | InterruptedException e1) {}

		}
	}

	public synchronized void addNote(String title) {
		NotificationGUI ng = new NotificationGUI(this, title);
		if(cnotifications[cnotifications.length-1]==null){
			cnotifications[currentNotificationNumber]=ng;
			setCurrentNotificationNumber(getCurrentNotificationNumber() + 1);
		}else{
			wnotifications.add(ng);
			setCurrentNotificationNumber(getCurrentNotificationNumber() + 1);
		}
	}

	public int getCurrentNotificationNumber() {
		return currentNotificationNumber;
	}

	public void setCurrentNotificationNumber(int currentNotificationNumber) {
		this.currentNotificationNumber = currentNotificationNumber;
	}

	public void throwWarning(NotificationGUI f) {
		//Enumeration<Integer> enumKey = notifications.keys();
		//		 notifications.remove(f.getCnn()-1);
		//		try {
		//			for (int i = f.getCnn() - 1; i < notifications.size()-1; i++) {
		//				System.out.println(i);
		//				notifications.put(i, notifications.get(i + 1));
		//				notifications.get(i).moveDown(i);
		//			}
		//		} catch (NullPointerException e) {
		//			System.err.println("oops");
		//		}
		//		notifications.remove(notifications.size());
		//		System.err.println("aqui");
		//		try {
		//			for(int i=f.getCnn();i<cnotifications.length-1;i++){
		//				cnotifications[i].moveDown(i);
		//				cnotifications[i]=cnotifications[i+1];
		//				System.out.println(i);
		//			}
		//			cnotifications[cnotifications.length-1]=null;
		//			System.out.println("/////");
		//		} catch (NullPointerException e) {
		//			System.err.println("oops");
		//		}
	}

	public int getNotewidth() {
		return notewidth;
	}

	public void setNotewidth(int notewidth) {
		this.notewidth = notewidth;
	}

	public int getNoteheight() {
		return noteheight;
	}

	public void setNoteheight(int noteheight) {
		this.noteheight = noteheight;
	}

}
