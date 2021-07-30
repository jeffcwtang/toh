docker build -t angulartest .
docker run -p 8081:80 -d --restart=always --name testsite angulartest