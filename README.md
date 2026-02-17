
# 🏦 MSME Loan Readiness & Self-Assessment Tool

> A rule-based expert system powered by Drools and Spring Boot that enables MSMEs to evaluate loan readiness using transparent, auditable credit assessment rules aligned with RBI lending principles.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Backend-green)
![Drools](https://img.shields.io/badge/Drools-RuleEngine-red)
![AWS](https://img.shields.io/badge/AWS-EC2-yellow)
![Security](https://img.shields.io/badge/Security-ModSecurity_WAF-blue)
![Status](https://img.shields.io/badge/Status-Production_Live-success)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## 🌐 Live Production Deployment

**Access the tool here:**

https://msmeloancheck.me

Deployed on:

- AWS EC2 (Production server)
- ModSecurity Web Application Firewall (WAF)
- Secure public internet access
- Real-world deployment environment

---

## 📖 Overview

The MSME Loan Readiness Tool is a **knowledge-engineered expert system** designed to help Micro, Small, and Medium Enterprises assess their credit readiness before applying for loans.

Unlike AI/ML-based systems, this tool uses:

- Explicit, deterministic business rules
- Transparent decision logic
- Fully explainable reasoning
- Audit-compliant inference model

The system uses **70+ Drools production rules**, derived from:

- RBI MSME lending principles
- Banking credit risk frameworks
- Financial ratio standards
- Real-world credit analyst workflows

---

## ❗ Problem It Solves

MSMEs often face opaque lending decisions because they cannot evaluate:

- Credit eligibility beforehand
- Financial weaknesses
- Risk indicators
- Areas requiring improvement

This system provides:

- Transparent pre-loan assessment
- Multi-dimensional risk analysis
- Explainable credit recommendations
- Actionable financial improvement guidance

---

## ✨ Key Features

- Rule-based credit assessment using Drools
- Fully explainable decision reasoning
- Multi-dimensional financial risk scoring
- Automated PDF report generation
- Interactive web dashboard
- Production deployment on AWS EC2
- Protected by ModSecurity WAF
- Deterministic and audit-compliant logic

---

## 🏗️ System Architecture

```mermaid
flowchart LR

    User[MSME User]

    Browser[Web Browser UI<br>Thymeleaf Dashboard]

    WAF[ModSecurity Web Application Firewall<br>NGINX + OWASP CRS]

    AWS[AWS EC2 Production Server]

    AppService[Spring Boot Application Service<br>User Input Processing<br>Risk Evaluation Orchestration<br>PDF Generation]

    RuleEngine[Drools Rule Engine<br>Forward Chaining Inference<br>Stateless Knowledge Session]

    PDF[Generated PDF Report]

    User --> Browser

    Browser --> WAF

    WAF --> AWS

    AWS --> AppService

    AppService --> RuleEngine

    RuleEngine --> AppService

    AppService --> PDF

    AppService --> Browser

    Browser --> User

````

---

## ⚙️ Architecture Explanation

### User Layer

* MSME enters business and financial information
* Accesses system via secure web interface

### Application Layer (Spring Boot)

Handles:

* User input validation
* Data processing
* Rule engine integration
* PDF report generation
* Dashboard rendering

### Rule Engine Layer (Drools)

Core decision engine:

* Executes 70+ credit assessment rules
* Uses forward chaining inference
* Applies salience-based execution priority
* Generates explainable decisions


### Infrastructure Layer (AWS EC2 + ModSecurity WAF)

Provides:

* Production hosting
* Secure internet access
* Protection against attacks
* Real-world deployment capability

---

## 🧠 Rule Engine Architecture

The Drools rule base is organized into 14 logical modules:

| Module           | Purpose                     |
| ---------------- | --------------------------- |
| Data Validation  | Ensures input completeness  |
| Eligibility      | Business eligibility checks |
| Liquidity        | Current Ratio analysis      |
| Solvency         | Debt-to-Equity evaluation   |
| Cash Flow        | DSCR analysis               |
| Profitability    | ROE and margin analysis     |
| Compliance       | Regulatory checks           |
| Credit Behavior  | Payment history analysis    |
| Sector Risk      | Industry risk evaluation    |
| Operational Risk | Stability analysis          |
| Risk Aggregation | Multi-factor scoring        |
| Decision Rules   | Final classification        |

---

## 📊 Decision Outcomes

The system produces one of four outcomes:

| Decision            | Meaning                            |
| ------------------- | ---------------------------------- |
| APPROVE             | Low risk, strong financial profile |
| CONDITIONAL_APPROVE | Moderate risk, improvements needed |
| REJECT              | High financial or compliance risk  |
| REFER               | Requires human review              |

---

## 🔍 Explainability (Key Differentiator)

The system provides full reasoning transparency:

* List of fired Drools rules
* Identified financial weaknesses
* Risk score breakdown
* Improvement recommendations
* Traceable decision path

This ensures:

* Audit compliance
* Regulatory alignment
* Analyst-level explainability

---

## 📄 PDF Report Generation

Automatically generates professional reports containing:

* Financial ratio analysis
* Risk scores
* Decision classification
* Improvement recommendations
* Rule execution summary

Suitable for:

* Bank submission
* Consultant review
* Internal financial planning

---

## 🧱 Tech Stack

| Layer       | Technology      |
| ----------- | --------------- |
| Backend     | Spring Boot     |
| Language    | Java 17         |
| Rule Engine | Drools          |
| Frontend    | Thymeleaf       |
| Database    | PostgreSQL      |
| Build Tool  | Maven           |
| Cloud       | AWS EC2         |
| Security    | ModSecurity WAF |
| Reporting   | PDF Generation  |

---

## ☁️ Production Deployment Architecture

Production environment includes:

* AWS EC2 hosting
* Public domain access
* ModSecurity Web Application Firewall protection
* Secure HTTP access
* Persistent backend service

This demonstrates real-world deployment capability.

---

## 🚀 Local Installation

### Prerequisites

* Java 17
* Maven
* PostgreSQL

---

### Clone Repository

```bash
git clone https://github.com/your-repo/msme-loan-assessment.git

cd msme-loan-assessment
```

---

### Run Application

```bash
mvn clean install

mvn spring-boot:run
```

Access:

```
http://localhost:8080
```

---

## 🧪 Testing

Run tests:

```bash
mvn test
```

Includes:

* Unit tests
* Rule engine tests
* Integration tests

---

## 📌 Use Cases

* MSME loan readiness assessment
* Credit analyst training tool
* Financial consulting tool
* Banking pre-screening system
* Regulatory compliance demonstration

---

## 🔐 Security

Protected using:

* ModSecurity Web Application Firewall
* Input validation
* Secure server deployment on AWS EC2

---

## 🔮 Future Enhancements

* What-if scenario simulation
* Advanced analytics dashboard
* Sector-specific rule modules
* External rule management system
* Accounting software integration

---

## 👨‍💻 Author

Vasan S P

GitHub: [https://github.com/vasan12sp](https://github.com/vasan12sp)

Live System: [https://msmeloancheck.me](https://msmeloancheck.me)

---

## 📄 License

MIT License

