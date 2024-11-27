# Используем официальный образ OpenJDK для Java 17
FROM openjdk:17-jdk-slim

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем скомпилированный jar-файл в контейнер
COPY target/Polyclinic_PetProject-0.0.1-SNAPSHOT.jar app.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
