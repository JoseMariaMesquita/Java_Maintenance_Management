# Java Maintenance Management

A Java-based maintenance management application designed to manage maintenance reports, scheduled tasks, and employee administration through a role-based permission system.

---

## Project Overview

**Java_Maintenance_Management** is a desktop application developed in Java that allows maintenance teams and supervisors to manage maintenance operations efficiently.

The application includes different functionalities depending on the user's permission level, providing separate tools for maintenance workers and management staff.

The system is connected to a **MariaDB** database using **JDBC**, where all data related to employees, machines, reports, and maintenance tasks is stored.

---

## Features

### Maintenance Staff
Users with maintenance permissions can:

- Create incident and maintenance reports
- View maintenance task schedules
- Access previous reports
- Complete assigned maintenance tasks
- Register maintenance activity times

### Managers / Supervisors
Users with management permissions can additionally:

- Register new employees
- View employee lists
- Create maintenance tasks
- Export reports
- Import employees from text files
- Manage maintenance schedules

---

## Main Functionalities

### Incident Report Management
The application allows users to create reports including:

- Employee selection
- Machine selection
- Problem ID registration
- Report type
- Start and finish times
- Problem description

### Maintenance Task System
Tasks can be scheduled as:

- Daily maintenance
- Monthly maintenance
- Annual maintenance

Each task stores information about:

- Assigned machine/laser
- Maintenance type
- Scheduled date
- Employee who completed the task

### Employee Management
Management users can:

- Register employees manually
- Import employee data from text files
- Manage user credentials and permissions
- View all registered workers

### Report Exporting
Reports can be:

- Opened for detailed reading
- Exported as documents/PDF files

---

## Technologies Used

- Java
- Java Swing (GUI)
- JDBC
- MariaDB
- File Import/Export System

---

## 📈 Planned Features

The project also planned an optional integration with a **Business Intelligence** application to analyze reports and machine performance statistics.

---

## 📷 GUI Structure

The application includes a graphical user interface with different panels and menus depending on the logged-in user's role.

---

## ▶️ Installation

1. Clone the repository:

```bash
git clone https://github.com/yourusername/Java_Maintenance_Management.git
```

2. Configure the MariaDB database.

3. Add your JDBC driver.

4. Run the project from your preferred Java IDE.

---

## 📄 License

This project was created for educational purposes.
