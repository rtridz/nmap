#!/usr/local/bin/python2 

""" 
Echo execute server 
""" 

import socket, subprocess

host = '' 
port = 50000 
backlog = 5 
size = 1024 
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
s.bind((host,port)) 
s.listen(backlog) 
while 1: 
    client, address = s.accept() 
    print 'New connection from %s:%d' % (address[0], address[1])
    data = client.recv(size) 
    if data:
        print "Input arguments:",data
        execute = subprocess.Popen('nmap '+data, shell=True, stderr=subprocess.PIPE, stdout=subprocess.PIPE)
        if execute:
            output = str(execute.stdout.read())
            print "Output:", output
            client.send(output)
        else:
            error = str(execute.stderr.read())
            print "Error:",error
            client.send(error)
    client.close()

