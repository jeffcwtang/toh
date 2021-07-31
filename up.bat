docker build -t angulartest .
#Remove old container
docker stop testsite
docker rm testsite
docker run -p 8081:80 -d --restart=always --name testsite angulartest