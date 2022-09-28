
## K8s Basic Guide

To start:
```
minikube start
```

Check K8s Dashboard:
```
minikube dashboard
```


> To check "services" and "pods"
> ``` kubectl get services ```   ``` kubectl get pods ``` kubectl get nodes ``` 
> ``` kubectl delete service yourServiceName ```   
> Optional watch command ```  watch -n 1 kubectl get all ```


## Applying yaml files
We can deploy our app into k8s by using manifest files

```
# deploy core file first (will also run required databases)
kubectl create -f k8s/app-cores

# Deploy all services under this directory
kubectl create -f k8s/services

```
> Note: -f takes a directory or a single file

__ERROR While Deploying ? (Couldn't pull docker image):__ Using local docker images:    
In terminal type ``` minikube docker-env ```  
and do as proposed  ``` eval $(minikube -p minikube docker-env) ```  
In same terminal, build docker image again
Then, in same terminal, repeat above steps, e.g kubectl create -f your-file.yaml


## Stopping or removing all deployed services via manifest file
```
kubectl delete -f k8s/db/central-mysqldb.yaml

kubectl delete -f k8s/services
```

### (If using minikube) By default it won't expose the service externally,
```
kubectl get services

mikube service app name
```

### Checking Logs and Descriptions:

Checking Logs:
```
kubectl get pods

kubectl logs pods-name
```

mysql service description:
```
kubectl describe deployment mysql
```




## If stuck, while using command:
```
# For help
kubectl -h 

# to get description about some resource. e.g deployement:  spec
kubectl explain deployment.spec
```


## Scaling the app  
Scale the container to 10 replicas:  
```
kubectl scale --replicas=10 deployment/yourServiceName
```
> To watch pod creation
> ``` kubectl get pods -l app=yourServiceName --watch ```


### Deploying to the cloud  
Everything is Ok. The app worked perfectly on Minikube.
Now, we can try it on Cloud, AWS (EKS), GoogleCloud (GKE) or Azure (AKS)

AWS:  (Its not free !!!. USD ? per hour)
    - USing Amazon Elastic Kubernetes Service (Amazon EKS):
    - Required aws access key (to access service via cmd)
    - Tools: `eksctl`
    - CLuster Creation approximate time (~ 15 minutes)

1. Cluster Creation:  
eksctl create cluster --region=eu-west-2 --name=yourClusterName

2. Deploying the app:  
Get .yaml manifest file in a folder. e.g k8s -> app1.yaml, app2.yaml, mysql.yaml etc...
```
kubectl apply -f k8s

# watch pod being created
kubectl get pods --watch
```

3. Access the app:  
```
kubectl get service yourServiceName
```
Check domain name on EXTERNAL-IP  
> If domain doesn;t work, you may have to wait for couple of minutes
> Until AWS DNS resolution is set.

4. Clean Up the cluster (Stop Hourly bills)  
```
ksctl delete cluster --region=eu-west-2 --name=yourClusterName
```