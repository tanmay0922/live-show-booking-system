# 🎭 Live Show Booking System

A Java-based command-line application to manage **live show bookings** for users and organizers — complete with slot management, waitlisting, cancellations, and trending show tracking.  
Built with **clean code architecture**, extensible service design, and in-memory data structures (no DB).

---

## 🚀 Features

### 🎟️ For Organizers:
- Register new live shows with specific **genres** (e.g., Comedy, Singing, Theatre, Tech).
- Onboard 1-hour time slots (9 AM – 9 PM) with configurable **capacity** per slot.
- Ensure **non-overlapping** time slots for any single show.

### 👤 For Users:
- Book tickets for a specific show at a specific time slot with a number of persons.
- One user can book **multiple shows in a day**, but not in the same time slot.
- A single booking is **all-or-nothing** (no partial person booking allowed).
- Cancel any existing booking using **booking ID**.
- View all available shows filtered by **genre**, sorted by **start time** (ranking strategy).
- Get added to a **waitlist** when slots are full. If someone cancels, first-in-waitlist gets the booking.


---

## 🧑‍💻 Tech Stack

| Layer              | Technology        |
|--------------------|------------------|
| Programming        | Java 17          |
| Architecture       | Modular OOP, Clean Code, SOLID |
| Build Tool         | Maven            |
| Data Persistence   | In-Memory HashMaps & Lists |
| IDE                | Visual Studio Code |
| Version Control    | Git + GitHub     |

---

## 📁 Project Structure

demo/

├── src/

│ └── main/

│ └── java/com/example/demo/

│ ├── constant/ # Enums (Genre, TimeSlot)

│ ├── model/ # Data Models (Booking, LiveShow, ShowSlot)

│ ├── repository/ # In-memory data repositories

│ ├── service/ # Business logic services

│ └── Driver.java # Entry point for command-line interface



---

## 🛠️ How to Run

### 🧾 Prerequisites:
- Java 17 installed
- Maven installed
- Git (optional)
- IDE like **VS Code** or IntelliJ

### 📦 Setup & Run:
```bash
# Step 1: Clone the repo
git clone https://github.com/tanmay0922/live-show-booking-system.git
cd live-show-booking-system

# Step 2: Compile using Maven
cd demo
mvn clean install

# Step 3: Run the application
java -cp target/demo-1.0-SNAPSHOT.jar com.example.demo.Driver

Sample Commands to Try
registerShow: TMKOC -> Comedy  
onboardShowSlots: TMKOC 09:00-10:00 3, 12:00-13:00 2, 15:00-16:00 5  
bookTicket: (Alice, TMKOC, 09:00, 2)  
cancelBookingId: 1000  
showAvailByGenre: Comedy  

⚙️ Core Logic & Design
LiveShow: Contains genre and list of show slots.

ShowSlot: Represents a time block with start, end, capacity, bookings and waitlist.

Booking: Unique ID assigned for each valid ticket.

BookingService: Manages booking, cancellations, and waitlist logic.

SearchService: Filters shows by genre and ranks by strategy (start time).

Repositories: Lightweight in-memory structures to simulate DBs.

Driver.java: Accepts and parses commands from users in CLI.

❗ Overlapping time slots within a show are prevented. Users are blocked from double-booking in the same time.


👤 Author
Tanmay Upadhyay



