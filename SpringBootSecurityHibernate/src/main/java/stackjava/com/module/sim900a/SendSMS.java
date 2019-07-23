package stackjava.com.module.sim900a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

/**
 * 
 * @author CuongNV20
 * @see https://www.raspberrypi.org/forums/viewtopic.php?t=218198#p1341543
 *
 */
class SendSMS {
	private boolean fim;

	public SendSMS() {
		fim = false;
	}

	private CommPortIdentifier obterPortaCommSerial() {
		Enumeration portasComm = CommPortIdentifier.getPortIdentifiers();

		while (portasComm.hasMoreElements()) {
			CommPortIdentifier portaComm = (CommPortIdentifier) portasComm.nextElement();

			if (portaComm.getPortType() == CommPortIdentifier.PORT_SERIAL && portaComm.getName().equals("/dev/ttyS0"))
				return (portaComm);
		}

		return (null);
	}

	public void enviarMensagemSMS(String numeroCelular, String mensagem) {
		CommPortIdentifier portaComm = obterPortaCommSerial();
		List<String> mensagensSIM = new ArrayList<String>();

		mensagensSIM.add("AT" + "\r\n");
		mensagensSIM.add("ATE0" + "\r\n");
		mensagensSIM.add("AT+CMGF=1" + "\r\n");
		mensagensSIM.add("AT+CMGS=\"" + numeroCelular + "\"" + "\r\n");
		mensagensSIM.add(mensagem + "\r");

		try {
			SerialPort portaSerial = (SerialPort) portaComm.open("/dev/ttyS0", 2000);
			portaSerial.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			OutputStream streamSaida = portaSerial.getOutputStream();

			final Thread threadLeituraStream = new Thread() {
				@Override
				public void run() {
					try {
						final BufferedReader leitor = new BufferedReader(
								new InputStreamReader(portaSerial.getInputStream()));
						String linha = null;
						while ((linha = leitor.readLine()) != null && !fim)
							System.out.println(linha);
						leitor.close();
					} catch (final Exception excecao) {
						excecao.printStackTrace();
					}
				}
			};
			threadLeituraStream.start();

			for (int i = 0; i < mensagensSIM.size(); ++i) {
				streamSaida.write((mensagensSIM.get(i)).getBytes());
				streamSaida.flush();
				Thread.sleep(1000);
			}
			fim = !fim;

			streamSaida.close();
			portaSerial.close();
		} catch (PortInUseException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		} catch (UnsupportedCommOperationException excecao) {
			excecao.printStackTrace();
		} catch (InterruptedException excecao) {
			excecao.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SendSMS comunicacaoMovel = new SendSMS();
		comunicacaoMovel.enviarMensagemSMS("+84973483873", "Test");
	}
}