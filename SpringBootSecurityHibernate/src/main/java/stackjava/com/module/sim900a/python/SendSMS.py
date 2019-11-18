import serial
import RPi.GPIO as GPIO
import os, time

def send(result):
	GPIO.setmode(GPIO.BOARD)   
	# Enable Serial Communication
	port = serial.Serial("/dev/ttyUSB0", baudrate=9600, timeout=1)
	port.write('AT'+'\r\n')
	rcv = port.read(10)
	print rcv
	time.sleep(1)
	port.write('ATE0'+'\r\n')      # Tat che do phan hoi (Echo mode)
	rcv = port.read(10)
	print rcv
	time.sleep(1)
	port.write('AT+CMGF=1'+'\r\n')  # Chon che do TEXT Mode
	rcv = port.read(10)
	print rcv
	time.sleep(1)
	port.write('AT+CMGDA="DEL ALL"'+'\r\n')  # xoa toan bo tin nhan
	rcv = port.read(10)
	print rcv
	time.sleep(1)
	port.write('AT+CSQ'+'\r\n')  # kiem tra chat luong song
	rcv = port.read(10)
	print('chat luong song')
	print rcv
	time.sleep(1)
	#############port.write('AT+CNMI=2,1,0,0,0'+'\r\n')   # New SMS Message Indications
	#############rcv = port.read(10)
	#############print rcv
	#############time.sleep(1)
	# Sending a message to a particular Number
	number = 'AT+CMGS=\"' + ''.join(result[0]) + '\"\r\n'
	content = ''.join(result[1]) + '\r\n'

	port.write(number.encode())
	rcv = port.read(10)
	print rcv
	time.sleep(1)
	port.write(content.encode())  # Message
	rcv = port.read(10)
	print rcv
	port.write("\x1A\r\n") # Enable to send SMS
	#0x1A send or 0x1B cancel
	for i in range(10):
		rcv = port.read(10)
		print rcv