ASCIIAnimator.class: ASCIICanvas.class
	javac ASCIIAnimator.java
ASCIICanvas.class: 
	javac ASCIICanvas.java
jar: ASCIIAnimator.class ASCIICanvas.class Manifest.txt
	jar cvfm ASCIIAnimator.jar Manifest.txt ASCIIAnimator.class ASCIICanvas.class
clean:
	rm -f *.class