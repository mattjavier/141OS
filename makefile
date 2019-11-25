JFLAGS = -g
JC = javac -cp src
JVM = java -cp src
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $<
CLASSES = \
 	src/Disk.java \
 	src/DirectoryManager.java \
 	src/DiskManager.java \
	src/ResourceManager.java \
	src/FileInfo.java \
	src/Printer.java \
 	src/UserThread.java \
	src/PrintJobThread.java \
 	src/MainClass.java

MAIN = MainClass

default: classes
	$(JVM) $(MAIN)

classes: $(CLASSES:.java=.class)

clean:
	$(RM) ./src/*.class
