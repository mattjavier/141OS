HW9 and GUI:
	- GUI shows only the 4 USERS, 3 PRINTERS, and 2 DISKS.

	- Program can allow modification of NUMBER_OF_USERS, NUMBER_OF_PRINTERS, 
	and NUMBER_OF_DISKS, provided the same number of USER input files are given.

	- GUI begins with all USERS, PRINTERS, and DISKS as green boxes. A green box 
	indicates an inactive event. Once the event becomes active, such as a DISK being 
	written to/read from, or a PRINTER printing out to a file, the event becomes a red box. 

	- GUI has a START button to start program execution, an EXIT button to exit program, 
	and a sometimes a Text Field if a disk is being requested/released, something is being 
	saved to a disk, a disk is reading/writing, a Printer is being requested/released, 
	and  if a UserThread is sending an output to be printed.
	
	- If the text is the color red, then an operation on a DISK is occuring, if the text 
	is dark gray, then a PRINTER operation is occuring, and if the text is blue, then a USER
	operation is occuring.
 
To execute: type "make" into command line from 141OS/ directory, 
a Java Swing application window will pop up, click start to begin.

Program and GUI both work on macOS, Program without GUI works on openlab.Not sure 
if program will work on Windows.
