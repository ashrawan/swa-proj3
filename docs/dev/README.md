### Only Development Ready

### Stay in root directory:

```cmd
swa-proj3/
```

### To start-kafka:

```cmd
docker compose -f docs/dev/docker-compose-kafka.yml up
```

### To check-kafka:

```cmd
docker compose -f docs/dev/docker-compose-kafka.yml logs kafka | grep -i 'started'
```

### To access-kafka-container:
```cmd
# Check running container
docker ps

# Get Kafka container id, at-least-3-digit, and open shell
docker exec -it 8f1 sh
```

### To run-kafka-commands inside shell:

```cmd
bin/kafka-console-consumer --topic candidate-events --from-beginning --bootstrap-server localhost:9092
```
> e.g reading candidate-events. (remove .sh when running cmd)  
> See: (https://kafka.apache.org/quickstart)

### To stop-kafka:

```cmd
docker compose -f docs/dev/docker-compose-kafka.yml down --remove-orphans
```

---

### To build:

```cmd
mvn clean install -DskipTests
```


### To start-all-services:

```cmd
docker compose up
```
