import mysql.connector
import SendSMS as SMS
import os, time

# Connect to server
mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  passwd="cuongnv20",
  database="giai_phap_sms"
)
#loop 
#=======================Begin===================
while True:
	print "while True"

	print "setting mysql OK"
	# Get a cursor
	mycursor = mydb.cursor()
	mycursor.execute("SELECT number_phone, content, id_info_send_sms FROM info_send_sms WHERE status = 1")
	# Fetch one result
	# ================ fetchone()
	# Fetch all result
	# ================ fetchall()
	myresult = mycursor.fetchall()
	numRecBeginSend = mycursor.rowcount
	mycursor.close()
	numRecEndSend = 0
	for result in myresult:
		# ===================================================================================================
		# =====================================SEND SMS======================================================
		# =====================================VVVVVVVV======================================================
		SMS.send(result)
		# =====================================^^^^^^^^======================================================
		# =====================================SEND SMS======================================================
		# ===================================================================================================
		# update status  
		#TODO loi line 35
		#TODO 3 record 1 lan
		#  File "connect_database.py", line 35, in <module>
		#    update = mydb.cursor()
		#  File "/home/pi/.local/lib/python2.7/site-packages/mysql/connector/connection.py", line 809, in cursor
		#    raise errors.OperationalError("MySQL Connection not available.")
		#mysql.connector.errors.OperationalError: MySQL Connection not available.
		update = mydb.cursor()
		sql = "UPDATE info_send_sms SET status = 2 WHERE id_info_send_sms = %(idInfo)s AND status = 1"
		val = {'idInfo' : result[2]}
		print "================"
		print val
		update.execute(sql, val)
		mydb.commit()
		print("so record duoc update: ", update.rowcount)
		numRecEndSend = numRecEndSend + update.rowcount
		update.close()
	if numRecBeginSend != numRecEndSend:
		print("co ", (numRecBeginSend - numRecEndSend), " bi loi")
	time.sleep(1)
#loop 
# Close connection
mydb.close()