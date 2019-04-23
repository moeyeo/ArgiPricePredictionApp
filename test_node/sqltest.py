import MySQLdb as dbapi
import sys
import csv

    dbServer='localhost'
    dbPass='password'
    dbSchema='dbTest'
    dbUser='root'

    dbQuery='SELECT * FROM pbTest.Orders;'

    db=dbapi.connect(host=dbServer,user=dbUser,passwd=dbPass)
    cur=db.cursor()
    cur.execute(dbQuery)
    result=cur.fetchall()

    c = csv.writer(open("temp.csv","wb"))
    c.writerow(result)