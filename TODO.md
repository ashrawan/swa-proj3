
### TODO: Create Basic Structure

- [X] Auth Service
- [X] Job Service
- [X] Candidate Service
- [X] Recruiter Service
- [X] Search Service
- [X] Notification Service
- [X] Application Service

> __8080__: Reserved (Don't Use this)    
> __8081-8099__: Application micro-services    
> __8070-8079__: Application supporting-services  
> __< 8070__: Others  
  
| Service              | Port |
|----------------------|------|
| search-service       | 8081 |
| candidate-service    | 8082 |
| job-service          | 8083 |
| recruiter-service    | 8084 |
| application-service  | 8085 |
| notification-service | 8087 | 
| --------------       | ---- |
| auth-service         | 8070 |


### TODO: Basic functionality for each Services and it's Database

1. Refer to the  Mind Map on [README.md](./README.md)
   - Add features as required
   - Separate Database configuration,( MongoDB, Cassandra, Elastic) ANY. Using localhost is fine for now
2. __Must DO__
   - Architecture/ deployment diagrams & PDF
   - Cloud Deployment (K8S)
   - Scaling Database (MongoDB / Cassandra)
   - Kafka, Caching (redis cache)
   - Testing (service based)

3. __Optional__
- UI  (Angular Or React )
- Tracing (Zipkin), Dashboard (Istio, prometheus & grafana, etc..)
- CI/CD (GitHub Actions, etc...)
- JUnit testing of (Controller and Repository also If possible)


### TODO: Deployment & Testing
- Deployment:
  - __Dockerfile__ for all services and __docker-compose.yml__ to run & test whole system on local
  - __k8s folder__ consisting of k8s config and instruction __k8s-readme__  for deployment to cloud.
- Testing:
  - Include __postman_exported_file__ to show available endpoints of services. So, its easier to work with.
  - Simple instruction "TEST.md" to run and test the overall system.

---  

> __Note: Please, Inform teammates what you are working on:__
> - Either update on Trello: []()
> - Mentioned it on the teams
> - Or mentioned it on this readme [TODO.md](./TODO.md)
