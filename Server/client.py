#!/usr/local/python2

""" 
Echo client 
""" 

import socket, sys, getopt


def main(argv):
	host = 'localhost' 
	port = 50000 
	size = 1024 

	arguments=argv[0]
	if arguments:
		s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
		s.connect((host,port)) 
		s.send(argv[0])
		print 'OK. now we waiting anser...'
		data = s.recv(size)
		if data:
			print 'Received:', data
		s.close() 

	else:
		print 'type: client.py <arguments>'




if __name__ == "__main__":
   main(sys.argv[1:])