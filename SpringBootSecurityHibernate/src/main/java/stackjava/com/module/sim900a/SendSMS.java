package stackjava.com.module.sim900a;

/**
 * 
 * @author CuongNV20
 * @see https://www.raspberrypi.org/forums/viewtopic.php?t=218198#p1341543
 *
 */
class SendSMS {
	public void sendFromDatabase() {
		String fetching = "python " + "c:\\Fetch.py \"" + songDetails + "\"";
		String[] commandToExecute = new String[] { "cmd.exe", "/c", fetching };
		Runtime.getRuntime().exec(commandToExecute);
	}
}