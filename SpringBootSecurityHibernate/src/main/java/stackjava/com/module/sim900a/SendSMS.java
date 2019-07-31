package stackjava.com.module.sim900a;

import java.io.IOException;

/**
 * 
 * @author CuongNV20
 * @see https://www.raspberrypi.org/forums/viewtopic.php?t=218198#p1341543
 *
 */
public class SendSMS {
	public static void sendFromDatabase() {
//		String fetching = "sudo python /home/pi/Desktop/SmS/connect_database.py";
//		String[] commandToExecute = new String[] { "cmd.exe", "/c", fetching };
		try {
			Runtime.getRuntime().exec("python /home/pi/Desktop/SmS/connect_database.py");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}