# MySQL JDBC Driver Setup

To run this project, you need to download the MySQL JDBC driver.

## Download Instructions

1. Download the MySQL Connector/J from:
   https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar

2. Save it as: `lib/mysql-connector-java-8.0.33.jar`

3. Alternatively, you can use this command:
   ```bash
   curl -L -o lib/mysql-connector-java-8.0.33.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar
   ```

4. After downloading, rebuild the project:
   ```bash
   ./build.sh
   ```

## Alternative Connectors

You can also use other versions of the MySQL connector by:
1. Placing the JAR file in the `lib/` directory
2. Updating the `MANIFEST.MF` file to reference the correct JAR name
3. Rebuilding the project
