SRC = src
BUILD = build
DOC = doc

collect:
	find $(SRC) -name "*.java" > sources.txt

build: collect
	javac -d $(BUILD) @sources.txt

run:
	java -cp $(BUILD) Main.Main $(ARGS)

doc:
	javadoc -d $(DOC) @sources.txt

clean:
	rm -rf $(BUILD) sources.txt $(DOC)
test:
	java -cp $(BUILD) Tests.AdjacencyVectTest $(ARGS)
	java -cp $(BUILD) Tests.GraphTest $(ARGS)
	java -cp $(BUILD) Tests.SorterTest $(ARGS)
