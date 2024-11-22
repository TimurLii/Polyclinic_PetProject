1. Команда для поднятия контейнера PostgresSQL
   docker run --name postgres-container -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=pass -e POSTGRES_DB=PolyclinicDB -p 5432:5432 -d postgres:latest
2. Команда для проверки, что Контейнер запустился docker ps 
3. После авторизации сделать переброс на страницу пользователя и запретить ему видеть других пользователй 
4. Добавить роли
