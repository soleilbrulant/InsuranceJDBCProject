📘 Insurance Management System (JDBC Project)

🔍 Overview

This is a simple Java project that lets you manage insurance-related data like customers, policies, and claims using JDBC and MySQL. It’s built as a console-based application using the DAO pattern and connects to a MySQL database.

🛠 Technologies Used

Java 8+
JDBC
MySQL
Basic file structure (not using frameworks like Spring)
📁 Folder Structure

.
├── src/                  # Java source files
│   └── main/java/com/insurance/
│       ├── Main.java
│       ├── dao/
│       ├── model/
│       └── util/
├── lib/                  # MySQL JDBC jar
├── resources/            # Contains schema.sql
├── build/                # Compiled output
├── build.sh              # Shell script to compile
├── run.sh                # Shell script to run
├── MANIFEST.MF
└── README.md
⚙️ How to Run

🧱 Setup
Make sure you have Java and MySQL installed.
Create a new MySQL database:
CREATE DATABASE insurance_db;
Import the schema:
mysql -u root -p insurance_db < resources/schema.sql
Update DBConnection.java with your DB username/password.
🔨 Build
Run the shell script:

./build.sh
▶️ Run
Run the app with:

./run.sh
If that doesn't work:

java -cp "build:lib/*" com.insurance.Main
📌 Features

Customers
Add new customer
View all customers
Search by ID
Update contact info
Policy Types
Add and view policies
View by risk score range
Claims
File a claim
View by status (Pending / Approved / Rejected)
Update claim status
🧪 Example Usage

When adding a customer, the app asks for name, DOB, address, email, etc.
To file a claim, you’ll provide the policy ID, claim type, amount, etc.
🗃 Database Tables

customer
policytype
policy_customer
claim
riskassessment
premium_payment
❗ Notes

Uses PreparedStatement to avoid SQL injection
Resources are handled using try-with-resources
Project is console-based and good for learning JDBC basics
❓ Common Errors

Issue	Fix
Can’t connect to DB	Check if MySQL is running and credentials are correct
MySQL driver not found	Make sure the .jar is in lib/
Compilation issues	Check Java version and file paths
