# 🌱 Spring-assignments – Лабораторные работы по Java Spring Framework

![Spring Boot Logo](https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg)

📌 **Описание:**  
Этот репозиторий содержит мои лабораторные работы по Java Spring Framework, выполненные в рамках курса веб-разработки на Java. Проекты демонстрируют использование **Spring Boot**, **Spring Data JPA**, **REST API** и других технологий Spring.

---

## 🚀 **Содержание репозитория**

🔹 - [Лабораторная 1: Основы Spring Boot: Основные принципы работы с Spring Boot, настройка проекта, запуск встроенного сервера.](lab1_basics/) — **`lab1_basics/`**

🔹 - [Лабораторная 2: Использование Hibernate в Spring Boot: Глубокая работа с ORM и связями между сущностями.](lab2_hibernate/) — **`lab2_hibernate/`**

🔹 - [Лабораторная 3: Разработка и тестировании Spring Boot‑приложения «Sports‑Library» на Spring JDBC.](lab3_JDBC/) — **`lab2_JDBC/`**

---

## 🛠 **Как запустить лабораторные работы?**

### 1. Клонировать репозиторий
```bash
git clone https://github.com/NikitaBytes/Spring-assignments.git
```

### 2. Перейти в нужную папку
Например, для Лабораторной 1:
```bash
cd lab1_basics
```

### 3. Собрать и запустить проект
Используйте Maven для сборки и запуска:
```bash
mvn clean install
mvn spring-boot:run
```

### 4. Открыть приложение
После запуска приложение будет доступно по адресу:  
[**http://localhost:8080**](http://localhost:8080)

---

## 💡 **Дополнительные примечания**

- **JDK**: Убедитесь, что установлена JDK версии 17 (или другая, указанная в проекте).  
- **База данных**: Если используется MySQL, настройте подключение в `application.properties` и запустите базу данных.  
- **Тестирование**: Для запуска интеграционных тестов выполните:  
  ```bash
  mvn clean test
  ```

### Полезные ссылки на документацию:
- [Spring Boot](https://spring.io/projects/spring-boot)  
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)  

---

## 📄 **Дополнительные материалы**

### Подробности в каждой папке
Каждая лабораторная работа содержит собственный файл `README.md` с:  
- Целями задания.  
- Особенностями реализации.  
- Инструкциями по запуску.  
