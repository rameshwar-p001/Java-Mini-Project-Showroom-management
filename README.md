# 🚗 Showroom Management System

A modern Java-based GUI application designed for efficiently managing showroom operations. Developed using **Java Swing** for the interface and **SQLite** as the backend database, this project covers all major functionalities such as product and customer management, sales tracking, and secure login access.

---

## 📌 Features

- 🔐 **Login System** — Secure entry for authorized users only.
- ➕ **Add Product** — Add new products to the showroom inventory.
- 👁️ **View Products** — Browse and search all added products.
- 👤 **Add Customer** — Store customer details for better tracking.
- 💸 **Sell Product** — Record sales transactions in real-time.
- 📊 **View Sales** — Review all past sales and analyze performance.

---

## 🛠️ Tech Stack

- **Language:** Java  
- **GUI Library:** Swing  
- **Database:** SQLite  
- **IDE:** VS Code

---

## 🛠️ Tech Stack

| Component      | Technology       |
|----------------|------------------|
| Language       | Java             |
| GUI Framework  | Swing (JFC)      |
| Database       | SQLite           |
| IDE            | VS Code          |

---

## 🗃️ Database Design

The project uses **SQLite** as a local database. Below are the primary tables:

### 🧾 Table: `users`
| Column Name | Type     | Description               |
|-------------|----------|---------------------------|
| id          | INTEGER  | Primary Key (AutoIncrement) |
| username    | TEXT     | Login username            |
| password    | TEXT     | Hashed password           |

### 🚙 Table: `products`
| Column Name | Type     | Description               |
|-------------|----------|---------------------------|
| id          | INTEGER  | Primary Key (AutoIncrement) |
| name        | TEXT     | Product name (e.g., Model) |
| price       | REAL     | Product price             |
| stock       | INTEGER  | Available quantity        |

### 👥 Table: `customers`
| Column Name | Type     | Description               |
|-------------|----------|---------------------------|
| id          | INTEGER  | Primary Key               |
| name        | TEXT     | Customer name             |
| contact     | TEXT     | Mobile number or email    |

### 💼 Table: `sales`
| Column Name  | Type     | Description                      |
|--------------|----------|----------------------------------|
| id           | INTEGER  | Primary Key                      |
| customer_id  | INTEGER  | Foreign Key → customers(id)      |
| product_id   | INTEGER  | Foreign Key → products(id)       |
| quantity     | INTEGER  | Number of units sold             |
| date         | TEXT     | Sale date                        |


---

---
📸 Screenshots
![Screenshot 2025-04-05 231030](https://github.com/user-attachments/assets/0812416a-99c2-445e-bd7d-2cfa01d55f66)

![Screenshot 2025-04-05 231159](https://github.com/user-attachments/assets/ba4f6c98-85a1-4b73-a943-940931a0fef8)

![Screenshot 2025-04-05 231305](https://github.com/user-attachments/assets/975e5c85-7bb1-4568-a999-e25fcfaa2d87)

![Screenshot 2025-04-05 231413](https://github.com/user-attachments/assets/6203944a-73ed-4123-80f5-5f5fc5cc66b9)

![Screenshot 2025-04-05 231823](https://github.com/user-attachments/assets/fce6a5ba-55b1-4455-af38-30654f7a8b1c)

![Screenshot 2025-04-05 231956](https://github.com/user-attachments/assets/3f48f675-00be-41f2-b0ba-dfa453f6579c)

![Screenshot 2025-04-05 232233](https://github.com/user-attachments/assets/d64caac2-30d4-4779-ac43-4a1c8428be6c)

![Screenshot 2025-04-05 232413](https://github.com/user-attachments/assets/9eee27dc-51f7-43a9-97b0-c4ea18f5c3ae)

![Screenshot 2025-04-05 232547](https://github.com/user-attachments/assets/8544e1d3-d14a-4713-b689-cb6edc3c3c6c)


![Screenshot 2025-04-05 232801](https://github.com/user-attachments/assets/e87d9f2d-b902-4c54-aabe-8416973b6276)


📚 How to Run
1. Clone the repository
      bash :--- git clone https://github.com/your-username/showroom-management-system.git

2. Open in VS Code or any Java-supported IDE

3. Run Main.java to start the application

4. Make sure showroom.db is in the correct path




## 📂 Project Structure

```bash
Showroom-Management-System/
│
├── src/
│   ├── Main.java
│   ├── panels/
│   │   ├── AddProductPanel.java
│   │   ├── AddCustomerPanel.java
│   │   ├── SellProductPanel.java
│   │   ├── ViewProductPanel.java
│   │   ├── ViewSalesPanel.java
│   │   └── LoginPanel.java
│   └── database/
│       └── DBConnection.java
│
├── resources/
│   └── icons, images, styling files
│
├── database/
│   └── showroom.db
│
└── README.md

```

🙏 Acknowledgment

Developed By :- Rameshwar D Patil


📃 License

This project is open-source and free to use for educational purposes



