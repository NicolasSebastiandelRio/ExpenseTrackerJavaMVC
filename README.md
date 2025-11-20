# 💰 Expenses Tracker

A robust desktop application designed to track personal finances, built with **Java** and **Swing**. This project demonstrates the implementation of the **Model-View-Controller (MVC)** architectural pattern to ensure code modularity, scalability, and maintainability.

## 📋 Project Overview

The **Expenses Tracker** allows users to log daily expenditures efficiently. The primary goal of this project is to provide a functional tool for personal finance management while serving as a practical example of layered programming and desktop application development in Java.

## 🏗️ Architecture (MVC)

The project is strictly organized following the **MVC Pattern**, decoupling the user interface from the business logic:

  * **📂 Model :** Manages the data logic. Defines the `Expense` entity with attributes like description, amount, category, and date.
  * **📂 View :** Handles the UI using **Java Swing**. It displays the forms and captures user input without processing it.
  * **📂 Controller :** Acts as the intermediary. It listens to events from the View, processes the data, and updates the Model.

## 🚀 Features

  * **Add Expenses:** Intuitive form to input description, amount, category, and date.
  * **Categorization:** Pre-defined categories (Food, Transport, Services, Leisure, Education).
  * **Data Validation:** Basic input validation to ensure data integrity.
  * **Scalable Design:** Built ready for future database integration.

## 🛠️ Technologies Used

  * **Language:** Java (JDK 17+)
  * **GUI Framework:** Java Swing (AWT/Swing)
  * **IDE:** Visual Studio Code / Eclipse
  * **Version Control:** Git & GitHub

## 💻 How to Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/nicolassebastiandelrio/ExpenseTrackerJavaMVC.git
    ```
2.  **Open in your IDE:**
      * **VS Code:** Open the folder and ensure the "Extension Pack for Java" is installed.
      * **Eclipse:** `File` \> `Open Projects from File System`.
3.  **Run the App:**
      * Navigate to `src/App.java`.
      * Run the `main` method to launch the application window.

## 🔜 Future Roadmap

This project is actively being developed. Upcoming features include:

  * **Persistence:** Integration with **SQL Server** or **Firebase** to save data permanently.
  * **Data Visualization:** Charts and graphs to analyze spending habits (referencing my background in data analysis).
  * **Export:** Ability to export reports to PDF or Excel.

## 👤 Author

**Nicolás del Rio**

  * **Role:** Software Engineering Student & Full-Stack Developer
  * **LinkedIn:** [Nicolás del Rio](www.linkedin.com/in/nicolás-del-rio-08810523b)
  * **Email:** nicolassebastiandelrio@gmail.com

-----

*This project is part of my professional portfolio to demonstrate competency in Object-Oriented Programming and Software Architecture.*
