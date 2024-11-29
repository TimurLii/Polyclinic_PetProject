# Polyclinic Management System

## Описание

Система для управления данными поликлиники: пациенты, врачи, расписание.
Основные возможности приложения:

- Создание врачей, пациентов, записей на приём
- Удаление врачей, пациентов, записей на приём
- Вывод все врачей, пациентов, записей на приём

В приложение использована база данных H2.
Написаны следующие файлы миграции:

- Создание таблиц. [V1__Create_Table.sql](src/main/resources/db/migration/V1__Create_Table.sql)
- Вставка данных в таблицу
  patients. [V2__Insert_patient_data.sql](src/main/resources/db/migration/V2__Insert_patient_data.sql)
- Создание таблицы
  doctors. [V3__Create_table_doctors.sql](src/main/resources/db/migration/V3__Create_table_doctors.sql)
- Вставка данных в таблицу
  doctors. [V4__Insert_doctor_data.sql](src/main/resources/db/migration/V4__Insert_doctor_data.sql)
- Вставка данных в таблицу
  appointment_time. [V5__Insert_appointment.sql](src/main/resources/db/migration/V5__Insert_appointment.sql)
- Вставка данных в таблицу
  bookings. [V6__insert_bookings.sql](src/main/resources/db/migration/V6__insert_bookings.sql)
- Вставка данных в таблицы doctor_roles и
  patient_roles. [V7__insert_roles.sql](src/main/resources/db/migration/V7__insert_roles.sql)

В приложение реализована авторизация пользователей с разными правами доступа,
кодирование паролей.
Приложение создано таким образом, что у всех пациентов сразу предустановлена роль USER. У всех
докторов предустановлена роль ADMIN.
Пользователи с ролью ADMIN могут переходить по любым адресам, для которых есть представление, и для
которых нет.

## Требования

- Docker
- JDK
- Среда разработки

## Запуск (Вариант 1)

1. Скачать репозиторий.
2. В папке со скаченным репозиторием открыть терминал.
3. Ввести команду **docker build -t polyclinic-app .** создание Docker-образа из Dockerfile.
4. Ввести команду **docker run -p 8080:8080 --name polyclinic-container polyclinic-app** создание и
   запуск контейнера.
5. В браузере перейти по адресу <u>http://localhost:8080</u>.

## Запуск (Вариант 2)

1. Скачать репозиторий.
2. Открыть в среде разработки проект.
3. Запустить проект.
4. В браузере перейти по адресу <u>http://localhost:8080</u>.

## Примеры API

### Получение данных всех врачей 

<u>http://localhost:8080/doctors </u>.

Ответ
[1.png](image/1.png)

### Получение данных по одному врачу
<u>http://localhost:8080/doctors/1 </u>.

Ответ
[2.png](image/2.png)

Так же подобные методы реализованы для остальных сущностей.

## Доступные страницы
- Для детального ознакомления перейдите по ссылке <u>http://localhost:8080/swagger-ui/index.html </u>.

- Для полного ознакомления перейдите по ссылку <u>http://localhost:8080/swagger-ui/index.html </u>.

- Главной страницей является страница авторизации <u>http://localhost:8080/login </u>

- Страница регистрации нового пользователя <u>http://localhost:8080/registration </u>

- Все остальные страницы доступны исключительно только после авторизации. После авторизации происходит редирект на страницу с записями данного пользователя.

- Страница создания новой записи <u>http://localhost:8080/appointment/create </u>. 
На данной странице происходит выбор врача, даты и времени.
Если выбранный врач, дата или время уже заняты 
происходит редирект на страницу <u>http://localhost:8080/appointment/createAgain <u/>
На которой снова предлагается выбрать, то же самое.
Для создания новой записи информация о пользователе, которая нужна для создания новой записи берётся
из Spring Security.
Doctor не может создать новую запись. 