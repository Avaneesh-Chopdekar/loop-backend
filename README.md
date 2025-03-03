# **Loop Chat App - Backend (Spring Boot)**

This is the **backend** of a real-time chat application built with **Spring Boot** and **WebSockets**. It enables **instant messaging** and **group chats**.

## 🚀 **Tech Stack**

- **Spring Boot** (REST APIs, WebSockets)
- **STOMP** (WebSocket Protocol)
- **MongoDB** (Database)
- **Lombok** (Reduces boilerplate code)
- **Docker** (Optional - Containerized deployment)

---

## 📌 **Features**
 
✅ **Real-Time Messaging** (WebSocket for instant updates)  
✅ **One-on-One & Group Chats**  
✅ **Message Storage in MongoDB**  
✅ **Docker Support for Deployment and Local Setup**

---

## 🛠️ **Installation Steps (Without Docker)**

### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/Avaneesh-Chopdekar/loop-backend.git
cd loop-backend
```

### **2️⃣ Configure the Database**
- Install **MongoDB** and create a database:

- Update `application.yaml` with your DB credentials:
```yaml
spring:
  data:
    mongodb:
      uri: ${MONGODB_URI:mongodb://localhost:27017/loop_db}
```

### **3️⃣ Install Dependencies & Run**
**Run using Maven**:
```sh
mvn clean install
mvn spring-boot:run
```

---

## 🐳 **Installation with Docker**

### **1️⃣ Build & Run with Docker**
```sh
docker build -t loop-backend .
docker run -p 8080:8080 loop-backend
```

### **2️⃣ Using Docker Compose**

- Start the services:
```sh
docker-compose up -d
```

---

## 🔥 **API Endpoints**

### **Messaging**
| Method | Endpoint                  | Description  |
|--------|---------------------------|--------------|
| POST   | `/room`                   | Create Room  |
| GET    | `/room/{roomId}`          | Join Room    |
| POST   | `/room/{roomId}/messages` | Get Messages |

### **WebSocket Endpoint**
```ws
ws://localhost:8080/chat
```

---

## 📜 **License**
This project is licensed under the **MIT License**.

---

🔥 **Need Help?**  
Feel free to raise an **issue** or **pull request** in the repo! 🚀
