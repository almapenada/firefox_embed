import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class main {

	public static void main(String[] args) {
		GraphicsEnvironment ge = 
	            GraphicsEnvironment.getLocalGraphicsEnvironment();
	        GraphicsDevice gd = ge.getDefaultScreenDevice();
		if (!gd.isWindowTranslucencySupported(TRANSLUCENT)) {
			System.err.println(
					"Translucency is not supported");
			System.exit(0);
		}
		//runsOnBattery();
		NotificationManager nm = new NotificationManager();
		nm.addNote("adeus");
		nm.addNote("aAAA!");
		nm.addNote("pam");
		nm.addNote("pim pim pim pam pam pam pum pum pummmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
		nm.addNote("ola");
		nm.addNote("eheh");
		nm.addNote("ihih");
		nm.addNote("ohoh");
		nm.addNote("ultima");
		nm.start();
	}
	
	private static Boolean runsOnBattery() {
	    try {
	        Process proc = Runtime.getRuntime().exec("cmd.exe /c battstat.bat");

	        BufferedReader stdInput = new BufferedReader(
	            new InputStreamReader(proc.getInputStream()));

	        String s;
	        while ((s = stdInput.readLine()) != null) {
	            if (s.contains("mains power")) {
	                return false;
	            } else if (s.contains("Discharging")) {
	                return true;
	            }
	        }
	    } catch (IOException ex) {
	        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    return false;
	}

}
