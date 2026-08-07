# E-BANK Banking Management System

A console-based Banking Management System developed in Java using Object-Oriented Programming (OOP) principles.

## Features

- Create and manage bank clients
- Create regular bank accounts
- Create savings accounts with interest rates
- Deposit money into accounts
- Withdraw money from accounts
- Check account balances
- Transfer money between accounts
- Delete bank accounts
- Display all accounts and customer information
- Export account data to Excel using Apache POI

---

## Technologies Used

- Java 11
- Maven
- Apache POI (Excel export)

---

## Project Structure

```text
src/
├── Account.java
├── Bank.java
├── Client.java
├── Main.java
├── Person.java
└── SavingAccount.java
```

---

## OOP Concepts Used

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Composition

---

## Requirements

Before running the project, make sure you have installed:

- JDK 11 or higher
- Maven 3.6+

---

## Maven Dependencies

The project uses Apache POI for Excel export functionality.

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.2.3</version>
    </dependency>

    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.3</version>
    </dependency>
</dependencies>
```

---

## Build the Project

Run the following command inside the project directory:

```bash
mvn clean package
```

---

## Run the Application

### Using Maven

```bash
mvn exec:java -Dexec.mainClass="Main"
```

### Using JAR File

After building the project:

```bash
java -jar target/banking-management-system-1.0.0.jar
```

---

## Main Menu

```text
1. Display all accounts
2. Create account
3. Check balance
4. Deposit money
5. Withdraw money
6. Delete account
7. Create savings account
8. Export accounts to Excel
9. Transfer money
0. Exit
```

---

## Excel Export

The application exports account information into:

```text
bank_accounts.xlsx
```

The exported file contains:

- Account Number
- Customer Name
- Account Type
- Balance

---

## Example Workflow

1. Create a client
2. Create a regular or savings account
3. Deposit money
4. Withdraw money
5. Check balance
6. Export data to Excel

---

## Author

Youssef Errachid
