## Development Guide  

### To run development tools, go to dev folder   
__(Available: MongoDB, Redis, Kafka)__

```cmd
cd docs/dev
```
### MongoDB:
1. To start-mongodb:

```cmd
docker compose -f mongodb/docker-compose-mongodb.yml up
```

2. To stop-mongodb:

```cmd
docker compose -f mongodb/docker-compose-mongodb.yml down --remove-orphans
```

> __Optional: MongoDB client__  
> https://www.mongodb.com/docs/compass/current/install/

---

### Redis:
1. To start-redis:

```cmd
docker compose -f docker-compose-redis.yml up
```

2. To access-redis-container:
```cmd
# Check running container
docker ps

# Get redis container id, at-least-3-digit, and open shell
docker exec -it 8f1 sh

# Access redis-cli, by typing
redis-cli
```

3. To stop-redis:

```cmd
docker compose -f docker-compose-redis.yml down --remove-orphans
```


> __Optional: Redis Client, To see and work with redis UI__  
> From: (https://snapcraft.io/install/another-redis-desktop-manager/ubuntu)

---

### Kafka  
1. To start-kafka:

```cmd
docker compose -f docker-compose-kafka.yml up
```

### To check-kafka-started:

```cmd
docker compose -f docker-compose-kafka.yml logs kafka | grep -i 'started'
```

2. To access-kafka-container:
```cmd
# Check running container
docker ps

# Get Kafka container id, at-least-3-digit, and open shell
docker exec -it 8f1 sh
```

3. To run-kafka-commands inside shell:

```cmd
bin/kafka-console-consumer --topic candidate-events --from-beginning --bootstrap-server localhost:9092
```
> e.g reading candidate-events. (remove .sh when running cmd)  
> See: (https://kafka.apache.org/quickstart)

4. To stop-kafka:

```cmd
docker compose -f docker-compose-kafka.yml down --remove-orphans
```

---

### Build Project:

```cmd
mvn clean install -DskipTests
```

---


## Working with Project

### Go to root directory
```cmd
swa-proj3/
```

### Services

1. To create image only:
```cmd
docker compose build
```

3. To run services
```cmd
docker compose up
```
