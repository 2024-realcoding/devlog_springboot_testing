
docker buildx build --platform linux/arm64 --progress=plain --tag realcoding:0.0.1 .
docker tag realcoding:0.0.1 ghcr.io/2024-realcoding/devlog_springboot_testing:latest

docker login ghcr.io -u inspire12
docker push ghcr.io/2024-realcoding/devlog_springboot_testing:latest
