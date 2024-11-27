docker build -t polyclinic-app . создание docker образа 

docker run -p 8080:8080 --name polyclinic-container polyclinic-app  запуск контейнера