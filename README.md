# 💰 Expenses Tracker

A robust desktop application designed to track personal finances, built with **Java** and **Swing**. This project demonstrates the implementation of the **Model-View-Controller (MVC)** architectural pattern and **JDBC** for database connectivity.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![SQL Server](https://img.shields.io/badge/SQL%20Server-CC2927?style=for-the-badge&logo=microsoft-sql-server&logoColor=white)
![MVC](https://img.shields.io/badge/Pattern-MVC-blue?style=for-the-badge)

## 📋 Project Overview

The **Expenses Tracker** allows users to log daily expenditures and view historical data. Unlike simple file-based apps, this project persists data into a **SQL Server** database, allowing for advanced filtering and data management.

## 🚀 Features

* **Add Expenses:** Form validation for description, price, category, and date.
* **Persist Data:** Saves records securely to a SQL Server database.
* **View History:** Displays all registered expenses in a tabular view.
* **Advanced Filtering:** Filter the expenses list by **Category**, **Month**, and **Year**.
* **Error Handling:** Robust error management for invalid inputs and database connection issues.

## 🏗️ Architecture (MVC + DAO)

The project follows strict software engineering principles:

* **Model (`model`):** Represents the data (`Expense` object).
* **View (`view`):** The UI built with **Java Swing** (`ExpensesView`, `ExpensesTableView`).
* **Controller (`controller`):** Handles logic and user events (`ExpensesController`).
* **DAO (`model.modelDAO`):** The **Data Access Object** pattern separates the SQL logic from the business logic.

## 🛠️ Prerequisites

* Java JDK 11 or higher.
* Microsoft SQL Server (Express or Standard).
* VS Code or Eclipse IDE.
* **Microsoft JDBC Driver for SQL Server** (Included in `lib/` folder).

## ⚙️ Setup & Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/nicolassebastiandelrio/ExpenseTrackerJavaMVC.git](https://github.com/nicolassebastiandelrio/ExpenseTrackerJavaMVC.git)
    ```

2.  **Database Setup:**
    Open SQL Server Management Studio (SSMS) and run the following script to create the database and table:

    ```sql
    CREATE DATABASE expenses_db;
    GO
    USE expenses_db;
    GO
    CREATE TABLE expenses (
        id INT IDENTITY(1,1) PRIMARY KEY,
        description VARCHAR(255) NOT NULL,
        price MONEY NOT NULL,
        category VARCHAR(50),
        date DATE NOT NULL
    );
    ```

3.  **Configure Connection:**
    Open `src/database/DBConnection.java` and update the `user` and `password` variables to match your SQL Server credentials:
    ```java
    private static final String user = "sa";
    private static final String password = "YOUR_PASSWORD_HERE";
    ```

4.  **Run the App:**
    * Open the project in VS Code.
    * Navigate to `src/App.java`.
    * Click **Run** (or press `F5`).

## 📷 Usage

1.  **Main Screen:** Fill in the details (e.g., "Lunch", "15.50", "Food", "2023-10-25") and click **Add Expense**.
2.  **List View:** Click **View All Expenses** to see the table.
3.  **Filtering:** In the table view, select a category or type a specific Month/Year and click **Filter** to narrow down results.

## 👤 Author

**Nicolás del Rio**
* **Role:** Software Engineering Student & Full-Stack Developer
* **Email:** Mnicolassebastiandelrio@gmail.com
* **LinkedIn:** [Nicolás del Rio](https://linkedin.com)

---
*This project is part of my professional portfolio to demonstrate competency in Java, SQL, and Software Architecture.*
