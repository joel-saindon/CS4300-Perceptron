Joel Saindon
CS4300 Proj5
Perceptron

Project was made in Eclipse. And should contain the following files:
	/src/Main.java
	/src/Perceptron.java
	./README.txt
built-in test files
	./5by6.txt
	./AND.txt

Items implemented
	-Input file parsing into "table"
	-Weights initialization
	-Loop with process samples and recalculating weights as needed
	-Infinite loop that lets user input vectors to get a classification


Important Notes:
	-items that can be changed to effect output can be changed within Main.java
		-weightZero (theta, weight zero)
		-correctionFactor (correction factor when weights need to be recalculated)
		-MAXITER (Maximum iterations to attempt to find correct weights)
		-STARTINGWEIGHT (starting weight of each weight for xi)
		-BIAS (bias of x0)
	-some debug println's may be commented out (but it works as far as I can tell)

Main issues:
	-Ran into some scanner issues late in the project, but I think I have them all fixed
	-Spent a lot of time figuring out the correct way to do the actual math when it came to processing samples (also should be fixed)