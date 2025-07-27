#!/bin/bash

# Insurance Project Run Script

echo "Starting Insurance Management System..."

# Check if JAR file exists
if [ ! -f "build/InsuranceProject.jar" ]; then
    echo "JAR file not found! Please run build.sh first."
    exit 1
fi

# Check if MySQL connector exists and is not just a placeholder
if [ -f "lib/mysql-connector-java-8.0.33.jar" ] && [ $(wc -c < lib/mysql-connector-java-8.0.33.jar) -gt 1000 ]; then
    echo "MySQL connector found."
    CLASSPATH="build:lib/*"
else
    echo "Warning: MySQL connector not found or is placeholder!"
    echo "Download mysql-connector-java-8.0.33.jar to lib/ directory."
    echo "See MYSQL_SETUP.md for instructions."
    echo ""
    echo "Running without MySQL connector (will fail if database operations are attempted)..."
    CLASSPATH="build"
fi

# Run the application
echo "Running Insurance Management System..."
java -cp "$CLASSPATH" com.insurance.Main

echo "Application finished."
