# 🧾 Insurance Management System (JDBC + MySQL)

This is a simple Java project that lets you manage insurance-related data like customers, policies, and claims using **JDBC** and **MySQL**. It's built as a console-based application using the **DAO pattern**, following a clean folder structure and SQL schema.

---

## 🛠 Technologies Used

- Java 8+
- MySQL
- JDBC
- Basic shell scripts (no frameworks)

---

## 📁 Project Structure

.
├── src/main/java/com/insurance/ # Main Java classes
│ ├── Main.java # Entry point
│ ├── dao/ # DAO classes
│ ├── model/ # Entity models
│ └── util/ # DB connection logic
│
├── lib/ # MySQL JDBC JAR
├── resources/ # schema.sql
├── build/ # Compiled output (.class, .jar)
├── build.sh # Compile script
├── run.sh # Run script
├── MANIFEST.MF # Manifest for jar
└── README.md


---

## ⚙️ How to Run

### 1️⃣ Setup the Database

Make sure MySQL is running and create the DB:

```bash
mysql -u root -p
CREATE DATABASE insurance_db;
USE insurance_db;
source resources/schema.sql;
Update DBConnection.java with your DB username/password.

2️⃣ Build the Project
./build.sh
3️⃣ Run the App
./run.sh
If that doesn't work:

java -cp "build:lib/*" com.insurance.Main
✨ Features

Add new customers
View all customers / search by ID
Add/view policies
View risk scores
File a claim
View/update claim status (Pending / Approved / Rejected)
💡 Example

To file a claim:

Provide policy ID, claim type, and other required info.
Status will initially be Pending.
📊 Tables Used

customer
policytype
policy_customer
claim
riskassessment
premium_payment
🧪 Notes

Uses PreparedStatement to prevent SQL injection
Follows try-with-resources
No frameworks (pure JDBC)
Ideal for understanding core DBMS-Java interaction
