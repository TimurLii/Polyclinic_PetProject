# Polyclinic Management System

## Описание
Система для управления данными поликлиники: пациенты, врачи, расписание.

## Требования
- Java 17
- Maven 3.6+
- PostgreSQL 13+

## Установка
1. Клонируйте репозиторий.
2. Настройте базу данных (переменные среды указаны в application.properties).
3. Выполните `mvn spring-boot:run`.

## Примеры API
### Получение данных врача
**GET /api/doctors/{id}**

Ответ:
```json
{
  "id": 1,
  "name": "John Doe"
}