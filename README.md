# Web Automation 
# 🧪 TestingAutomation: Selenium + Cucumber Framework

Automated UI Testing project for [SauceDemo](https://www.saucedemo.com/) using **Selenium WebDriver**, **Cucumber BDD**, and **Java TestNG/JUnit**.

This project demonstrates login scenarios and checkout flow for multiple user types in SauceDemo.

---

## 🚀 Features

- 🔐 Login tests with multiple users (e.g. `standard_user`, `locked_out_user`)
- 🛒 Add product to cart
- 💳 Checkout flow
- ✅ Order confirmation validation
- 🔄 Reusable step definitions using Cucumber
- 📦 Page Object Model (POM) design
- 📜 HTML test report generation

---

## 📂 Project Structure

TestingAutomation/
├── src/
│ ├── main/
│ │ └── java/
│ │ └── demo/
│ │ ├── base/ # BaseTest setup (WebDriver init/teardown)
│ │ └── pages/ # Page Object classes
│ └── test/
│ ├── java/
│ │ └── selenium/definitions/ # Step Definitions (Cucumber steps)
│ │ └── runners/ # Cucumber Runner class
│ │ └── utils/ # Enum for UserType
│ └── resources/
│ └── features/ # Gherkin feature files
│ └── config/ # (optional) test config files
└── pom.xml # Maven dependencies



---

## 🧪 Sample Scenarios

```gherkin
Scenario: Login as standard user
  Given User is on the SauceDemo login page
  When User logs in as "standard"
  Then User should see "inventory page"

Scenario: Login as locked_out user
  Given User is on the SauceDemo login page
  When User logs in as "locked_out"
  Then User should see "Epic sadface: Sorry, this user has been locked out."

▶️ How to Run Tests
✅ Pre-requisites
Java 17+
Maven installed and added to PATH
ChromeDriver compatible with your browser (v137 for Chrome v137)

🔧 Clone the project
git clone https://github.com/NabilaCahyani27/TestingAutomation.git
cd TestingAutomation

🧪 Run tests via Maven
# With TestNG
mvn clean test

# With JUnit (if configured)
mvn test -Dcucumber.options="--tags @yourTag"

📄 Reports
target/cucumber-report.html
