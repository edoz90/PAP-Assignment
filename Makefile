JFLAGS = -g
JC = javac
JVM = java
CP = -cp "classes:lib/*"

SOURCES += src/pap/ass03/shape/*.java
SOURCES += src/pap/ass04/textball/*.java
SOURCES += src/pap/ass05/cooperativeTeam/*.java
SOURCES += src/pap/ass05/CTF/*.java
SOURCES += src/pap/ass05/minDistance/*.java
SOURCES += src/pap/ass06/GOL/*.java
SOURCES += src/pap/ass07/oracle/*.java
SOURCES += src/pap/ass08/GOL/msg/*.java
SOURCES += src/pap/ass08/GOL/*.java
SOURCES += src/pap/ass08/tempSensor/*.java
classes = $(SOURCES:.java=.class)

all: $(classes)
	cp src/pap/ass03/shape/style.css classes/pap/ass03/shape/
	cp src/pap/ass06/GOL/style.css classes/pap/ass06/GOL/
	cp src/pap/ass08/tempSensor/style.css classes/pap/ass08/tempSensor/

%.class: %.java
	$(JC) $(CP) -d classes -sourcepath $(shell dirname $<) $(shell dirname $<)/*.java

clean:
	rm -r classes/ out/

ass01:
	ghci src/pap/ass01/ASS01.hs

ass02:
	ghci src/pap/ass02/ASS02.hs

ass03_hs:
	ghci src/pap/ass03/haskell/ASS03.hs
ass03_jv:
	$(JVM) $(CP) pap.ass03.shape.Viewer
ass03_jv_test:
	$(JVM) $(CP) pap.ass03.shape.TestShapes

ass04:
	$(JVM) $(CP) pap.ass04.textball.TestBruteForce $(secret)
ass04_ball:
	echo $(@)
	$(JVM) $(CP) pap.ass04.textball.BouncingWords $(balls)

ass05_hs:
	ghci src/pap/ass05/haskell/ASS05.hs
ass05_ctf:
	$(JVM) $(CP) pap.ass05.CTF.CTF
ass05_coop:
	$(JVM) $(CP) pap.ass05.cooperativeTeam.CooperativeTeam
ass05_distance:
	$(JVM) $(CP) pap.ass05.minDistance.MinDistance

ass06:
	$(JVM) $(CP) pap.ass06.GOL.Main

ass07:
	$(JVM) $(CP) pap.ass07.oracle.Main $(players)

ass08_gol:
	$(JVM) $(CP) pap.ass08.GOL.Main
ass08_temp:
	$(JVM) $(CP) pap.ass08.tempSensor.GUI
