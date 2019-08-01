import mysql.connector

import serial
import RPi.GPIO as GPIO
import os, time

# Connect to server
mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  passwd="cuongnv20",
  database="giai_phap_sms"
)

# Get a cursor
mycursor = mydb.cursor()

mycursor.execute("SELECT number_phone, content, id_info_send_sms FROM info_send_sms WHERE status = 1")

# Fetch one result
# ================ fetchone()
# Fetch all result
# ================ fetchall()
myresult = mycursor.fetchone()
# ===================================================================================================
# =====================================SEND SMS======================================================
# =====================================VVVVVVVV======================================================

GPIO.setmode(GPIO.BOARD)   
# Enable Serial Communication
port = serial.Serial("/dev/ttyUSB0", baudrate=9600, timeout=1)
port.write('AT'+'\r\n')
rcv = port.read(10)
print rcv
time.sleep(1)
port.write('ATE0'+'\r\n')      # Disable the Echo
rcv = port.read(10)
print rcv
time.sleep(1)
port.write('AT+CMGF=1'+'\r\n')  # Select Message format as Text mode
rcv = port.read(10)
print rcv
time.sleep(1)
port.write('AT+CSQ'+'\r\n')  # kiem tra chat luong song
rcv = port.read(10)
print rcv
time.sleep(1)
port.write('AT+CNMI=2,1,0,0,0'+'\r\n')   # New SMS Message Indications
rcv = port.read(10)
print rcv
time.sleep(1)
# Sending a message to a particular Number
number = 'AT+CMGS=\"' + ''.join(myresult[0]) + '\"\r\n'
content = ''.join(myresult[1]) + '\r\n'

port.write(number.encode())
rcv = port.read(10)
print rcv
time.sleep(1)
port.write(content.encode())  # Message
rcv = port.read(10)
print rcv
port.write("\x1A") # Enable to send SMS
#0x1A send or 0x1B cancel
for i in range(10):
    rcv = port.read(10)
    print rcv
# =====================================^^^^^^^^======================================================
# =====================================SEND SMS======================================================
# ===================================================================================================
# update status  
update = mydb.cursor()
sql = "UPDATE info_send_sms SET status = '2' WHERE id_info_send_sms = %s AND status = '1'"
val = (myresult[2])
update.execute(sql, val)
mydb.commit()
print(update.rowcount, "record(s) affected")

# Close connection
mydb.close()