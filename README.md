# spring-file-system

docker-compose up

docker build -t filesys .

docker run --rm -it --name file-system -v "$(pwd)/uploads:/app/uploads" -p 8080:8080 filesys:latest
