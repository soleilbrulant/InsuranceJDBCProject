# Insurance Management System

## 📋 Description
A comprehensive JDBC-based Insurance Management System that provides functionality for managing customers, policy types, and claims. Built using Java and MySQL with proper DAO pattern implementation.

## 🛠 Technologies Used
- **Java 8+** - Core programming language
- **JDBC** - Database connectivity
- **MySQL** - Database management system
- **Maven-style project structure** - Code organization

## 📁 Project Structure
```
.
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── insurance/
│                   ├── Main.java                    # Application entry point
│                   ├── dao/                         # Data Access Objects
│                   │   ├── CustomerDAO.java
│                   │   ├── PolicyTypeDAO.java
│                   │   └── ClaimDAO.java
│                   ├── model/                       # POJO classes
│                   │   ├── Customer.java
│                   │   ├── PolicyType.java
│                   │   ├── PolicyCustomer.java
│                   │   └── Claim.java
│                   └── util/                        # Utility classes
│                       └── DBConnection.java        # DB connection manager
├── lib/
│   └── mysql-connector-java-8.0.33.jar            # MySQL JDBC driver
├── resources/
│   └── schema.sql                                  # Database schema
├── build/
│   └── InsuranceProject.jar                        # Compiled application
├── build.sh                                        # Build script
├── run.sh                                          # Run script
├── MANIFEST.MF                                     # JAR manifest
└── README.md
```

## 🚀 Setup and Installation

### Prerequisites
- Java JDK 8 or higher
- MySQL Server 5.7 or higher
- MySQL JDBC Driver (provided in lib/)

### Database Setup
1. Create a MySQL database:
```sql
CREATE DATABASE insurance_db;
```

2. Import the schema:
```bash
mysql -u root -p insurance_db < resources/schema.sql
```

3. Update database connection settings in `src/main/java/com/insurance/util/DBConnection.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/insurance_db";
private static final String USERNAME = "your_username";
private static final String PASSWORD = "your_password";
```

### Building the Project

#### Using the Build Script (Recommended)
```bash
./build.sh
```

#### Manual Compilation
```bash
# Clean build directory
rm -rf build/*
mkdir -p build

# Compile with dependencies
javac -cp "lib/*" -d build/ src/main/java/com/insurance/util/DBConnection.java
javac -cp "lib/*:build" -d build/ src/main/java/com/insurance/model/*.java
javac -cp "lib/*:build" -d build/ src/main/java/com/insurance/dao/*.java
javac -cp "lib/*:build" -d build/ src/main/java/com/insurance/Main.java

# Copy resources
cp resources/* build/

# Create JAR
cd build
jar cfm InsuranceProject.jar ../MANIFEST.MF com/ schema.sql
cd ..
```

### Running the Application

#### Using the Run Script (Recommended)
```bash
./run.sh
```

#### Manual Execution
```bash
java -cp "build:lib/*" com.insurance.Main
```

## 🎯 Features

### Customer Management
- Create new customers with complete profile information
- View all customers
- Search customers by ID
- Update customer contact information

### Policy Type Management
- Create different policy types with risk scoring
- View all available policy types
- Filter policy types by risk score range

### Claims Management
- File new insurance claims
- View claims by status (Pending, Approved, Rejected)
- Update claim status
- Track claim amounts and dates

## 💻 Usage Examples

### Creating a New Customer
The application will prompt for:
- Personal information (name, DOB, contact)
- Address details (city, state, postal code, country)
- Identity information (email, national ID, gender)
- Background information

### Filing a Claim
Required information:
- Customer Policy ID
- Incident date and application date
- Claim type and amount
- Claim notes and status

## 🗃 Database Schema

The system uses a normalized database schema with the following main entities:
- **customer**: Stores customer information
- **policytype**: Defines different insurance policy types  
- **policy_customer**: Links customers to their policies
- **claim**: Manages insurance claims
- **riskassessment**: Tracks risk evaluations
- **premium_payment**: Handles payment tracking

## 🔧 Configuration

### Database Connection
Update the following in `DBConnection.java`:
- Database URL
- Username and password
- Connection pool settings (if needed)

### JAR File Contents
The generated JAR includes:
- All compiled `.class` files
- Database schema (`schema.sql`)
- MySQL JDBC driver dependency

## 📝 Development Notes

- Follows DAO (Data Access Object) pattern
- Uses PreparedStatements to prevent SQL injection
- Implements proper exception handling
- Resource management with try-with-resources
- Modular design for easy maintenance

## 🚨 Troubleshooting

### Common Issues

1. **Database Connection Failed**
   - Verify MySQL server is running
   - Check database credentials
   - Ensure database exists

2. **ClassNotFoundException: MySQL Driver**
   - Verify `mysql-connector-java-8.0.33.jar` is in lib/ directory
   - Check classpath in run command

3. **Compilation Errors**
   - Ensure Java JDK is properly installed
   - Verify all source files are present
   - Check file permissions

## 📄 License
This project is created for educational purposes.
