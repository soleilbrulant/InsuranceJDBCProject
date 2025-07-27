#!/bin/bash

# Insurance Project Build Script

echo "Building Insurance Management System..."

# Clean build directory
echo "Cleaning build directory..."
rm -rf build/*

# Create build directory structure
mkdir -p build/com/insurance/dao
mkdir -p build/com/insurance/model
mkdir -p build/com/insurance/util
mkdir -p build/com/insurance

# Check if MySQL connector exists (not just placeholder)
if [ -f "lib/mysql-connector-java-8.0.33.jar" ] && [ $(wc -c < lib/mysql-connector-java-8.0.33.jar) -gt 1000 ]; then
    CLASSPATH="lib/*"
    echo "Using MySQL connector from lib/"
else
    CLASSPATH=""
    echo "Warning: MySQL connector not found. Compiling without database dependencies."
    echo "Download mysql-connector-java-8.0.33.jar to lib/ directory before running."
fi

# Compile all Java files
echo "Compiling Java files..."

# First compile utility classes
javac -cp "$CLASSPATH" -d build/ src/main/java/com/insurance/util/DBConnection.java

# Then compile model classes
javac -cp "$CLASSPATH:build" -d build/ src/main/java/com/insurance/model/*.java

# Then compile DAO classes
javac -cp "$CLASSPATH:build" -d build/ src/main/java/com/insurance/dao/*.java

# Finally compile Main class
javac -cp "$CLASSPATH:build" -d build/ src/main/java/com/insurance/Main.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
else
    echo "Compilation failed!"
    exit 1
fi

# Copy resources to build directory
echo "Copying resources..."
cp -r resources/* build/

# Create JAR file
echo "Creating JAR file..."
cd build
jar cfm InsuranceProject.jar ../MANIFEST.MF com/ schema.sql
cd ..

if [ -f "build/InsuranceProject.jar" ]; then
    echo "JAR file created successfully: build/InsuranceProject.jar"
    echo "Build completed successfully!"
else
    echo "Failed to create JAR file!"
    exit 1
fi
