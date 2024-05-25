# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the maintainer label
LABEL authors="a7med"

# Set the working directory in the container
WORKDIR /app

# Copy the contents of Huffman-Compression directory into the container at /app
COPY Huffman-Compression/ /app

# Compile the Java program
RUN javac Huffman-Compression/Huffman.java Huffman-Compression/Node.java Huffman-Compression/NodeComparator.java

# Default command to run the main program
ENTRYPOINT ["java", "-cp", "Huffman-Compression", "Huffman"]
