docker build -t 10.18.30.15:5000/angulartest .
#Remove old container
docker stop testsite
docker rm testsite
docker run -p 8081:80 -d --restart=always --name testsite 10.18.30.15:5000/angulartest