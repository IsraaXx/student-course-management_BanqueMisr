# 🧠 Student Course Management System - Backend

A basic Spring Boot backend system to manage **students** and the **courses** they register for.

---

## 🚀 Project Overview

This backend project allows you to:

- Create, update, delete and retrieve **Students**
- Create, update, delete and retrieve **Courses**
- Create and manage **Categories**
- Associate courses with categories

It follows a **clean architecture** with the use of:
- DTOs to prevent infinite recursion
- Proper use of Services, Repositories, and Controllers
- Manual mapping between entities and DTOs

---

## 🛠️ Technologies Used

- ✅ Java 17
- ✅ Spring Boot
- ✅ Spring Data JPA
- ✅ MySQL
- ✅ Postman (for testing)
- ✅ RESTful APIs

---

## 📦 Project Structure

```
src
└── main
    ├── java
    │   └── com.sprints.student_course_management
    │       ├── controller       # REST controllers (StudentController, CourseController, CategoryController)
    │       ├── dto              # Data Transfer Objects (StudentDto, CourseDto, CategoryDto)
    │       ├── model            # Entity classes (Student, Course, Category)
    │       ├── repository       # Spring Data JPA Repositories
    │       ├── service          # Business logic layer
    │       └── StudentCourseManagementApplication.java
    └── resources
        └── application.properties
```

---

## 🧱 Requirements Implementation

### ✅ Entity Classes

- `Student`: id, name, email, age
- `Course`: id, name, description
- `Category`: id, name

Each class uses:

- `@Entity`, `@Table` for ORM mapping
- `@Id`, `@GeneratedValue` for primary keys

### ✅ Relationships

- **Student ↔ Course**: Many-to-Many  
  - A student can register for many courses  
  - A course can be registered by many students

- **Course → Category**: Many-to-One  
  - Each course belongs to one category  
  - A category can have many courses

Relationships implemented using JPA annotations:  
`@ManyToMany`, `@ManyToOne`, `@OneToMany`, `@JoinColumn`, `@JoinTable`

### ✅ Repositories

- `StudentRepository`
- `CourseRepository`
- `CategoryRepository`

Each extends `JpaRepository<T, ID>`.

---

## 📤 DTOs (Data Transfer Objects)

To prevent infinite recursion (due to bidirectional relationships), DTOs are used instead of exposing entities.

Created DTOs:

- `StudentDto`
- `CourseDto`
- `CategoryDto`

All mapping is done **manually** in the service layer.

---

## 💼 Services

- All business logic is in the **service** layer.
- Services receive and return **DTOs**.
- Entities are created/updated using values from DTOs.

---

## 🧪 Testing

All API endpoints were tested using **Postman** with various scenarios including:

- Adding a student
- Adding courses with category
- Updating and deleting data

---

## 🗃️ Database

Using **MySQL** as the relational database.  
Update your credentials in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
