# Overview
## 1: Introducing Kubernetes
## 2: First steps with Docker & Kubernetes
# Core Concepts
## 3: Pods: running containers in Kubernetes
## 4: Replication and other controllers: deploying managed pods
## 5: Services: enabling clients to discover and talk to pods
## 6: Volumes: attaching disk storage to containers
## 7: ConfigMaps & Secrets: configuring applications
### 7.1: Configuring containerized applications
### 7.2: Passing command-line arguments to containers
### 7.3: Setting environment variables for a container
### 7.4: Decoupling configuration with a ConfigMap
### 7.5: Using secrets to pass sensitive data to containers
## 8: Accessing pod metadata and other resources from applications
### 8.1: passing metadata through the downward API
### 8.2: Talking to the kubernetes API server
## 9: Deployments: updating applications declaratively
### 9.1: Updating applications running in pods
### 9.2: Performing an automatic rolling update with a ReplicationController
### 9.3: Using Deployments for updating apps declaratively
## 10: StatefulSets: deploying replicated stateful applications
### 10.1: Replicating stateful pods
### 10.2: Understanding StatefulSets
### 10.3: Using a StatefulSet
### 10.4: Discovering peers in a StatefulSet
### 10.5: Understanding how StatefulSets deal with node failures
# Beyond the Basics
## 11: Understanding Kubernetes internals
## 12: Securing the Kubernetes API server
## 13: Securing cluster nodes and the network
## 14: Managing pods' computational resources
## 15: Automatic scaling of pods and cluster nodes
## 16: Advanced scheduling
## 17: Best practices for developing apps
## 18: Extending Kubernetes



# Definitions
- node = a kubernetes layer of abstraction for a linux server/VM.
- cluster = a collection of nodes.
- container = docker, linux container
- pod = a set of container(s) that always runs together.
- application = one type of pod running in kubernetes cluster.
- service = Load balanced external static IP exposing one application.



# Overview
## 1: Introducing Kubernetes
- problem: dev -> ops workflow is super slow
- solution: microservices, automatic server deployment & lifecycle
	- monolithic app -> microservices
	- scaling microservices
	- deploying microservices and consistent environments
	- shifting to Continuous Delivery
- containers
	- use linux namespaces: each process sees its own personal view of the system
	- linux control groups (cgroups): limit amount of the resources process can consume
- kubernetes high level
	- Operating System for a cluster
	- abstracts the cluster and lets you deploy containers; Kubernetes handles where, how it is deployed
	- Kubernetes can optimize how the containers are deployed so cluster is optimally used
- kubernetes cluster high level
	- master node
		- hosts "kubernetes control plane", controls entire kubernetes system
		- control plane
			- has multiple components that manages entire cluster.
			- components can all be on master node, or split (and duplicated) across multiple nodes
			- components
				- kubernetes API server = control plane components' comms
				- Scheduler = schedules the apps (assigns worker nodes to application for deployment)
				- Controller Manager = does cluster-level functions
				- etcd = reliable distributed data store; stores cluster configuration data
	- worker node
		- run applications you deploy
		- components
			- Docker (container runtime) = run your containers
			- Kubelet = talks to API server, manages containers on this node
			- Kubernetes Service Proxy (kube-proxy) = load balances network traffic between application components
- run an application in Kubernetes
	- schedule puts the containers onto worker nodes based on:
		- resources needed per container group
		- unallocated resources on each node currently
	- kubelet tells Container Runtime (docker) to pull & run which container images
- continuous uptime
	- kubernetes makes sure cluster is always maintained; you describe end state, kubernetes will get cluster there
	- kubernetes can auto-scale your application via metrics you wish to use
	- using kube-proxy, the container copies you have are all load balanced and accessed externally with a single static IP
	- containers can move around in the cluster depending how kubernetes wants to optimize the cluster
- benefits of using kubernetes
	- simplifying application deployment == lots of automation for managing the cluster
	- optimization of hardware cluster
	- health checking & self-healing == kubernetes handles node, server, application errors gracefully and restarts them automatically
	- automatic scaling == kubernetes can make more copies given more nodes and usage demand
	- simplifying application develpment == same environment creation and running on dev, staging and prod

## 2: First steps with Docker & Kubernetes
- install docker & run hello world container
	- docker tag kubia bakedkookies/kubia
	- docker login
	- docker push bakedkookies/kubia
	- docker run -p 8080:8080 -d bakedkookies/kubia

### 2.2 setup kubernetes cluster
- you can run kubernetes cluster as:
	- local
	- cluster of machines
	- cloud providers' cluster of machines
	- cloud providers' management kubernetes cluster
- local setup
	- minkube = basic tool for setting up single-node cluster for local testing
		- curl -Lo minikube https://storage.googleapis.com/minikube/releases/v0.23.0/minikube-darwin-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/
		- curl -Lo minikube https://storage.googleapis.com/minikube/releases/v1.0.0/minikube-darwin-amd64 && chmod +x minikube && sudo cp minikube /usr/local/bin/ && rm minikube
		- minikube start
		- minikube ssh (ssh into the minikube VM)
	- kubectl = kubernetes CLI client
		- curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/darwin/amd64/kubectl && chmod +x kubectl && sudo mv kubectl /usr/local/bin/
			- breaks in mac OSX: https://github.com/kubernetes/kubernetes/issues/65575
				- only affects kind integration
		- curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.10.5/bin/darwin/amd64/kubectl && chmod +x kubectl && sudo mv kubectl /usr/local/bin/
		- kubectl cluster-info
- google kubernetes engine
	- install gcloud
		- gcloud container clusters create kubia --num-nodes 3 --machine-type f1-micro
	- using it
		- kubectl get nodes = see nodes on cluster
		- kubectl describe node <node name>
	- add an alias so you don't have to type kubectl all the time:
		add to ~/.bashrc: "alias k=kubectl"
- first app
	- deploy your app
		- "kubectl run kubia --image=bakedkookies/kubia --port=8080 --generator=run/v1"
- worker nodes (VM) -> pod -> containers
	- pods = group of containers that are always together
	- if you have single containers, then they would have pods of single containers
	- each pod has its own IP
	- pods are ephemeral (temporary and disappears all the time)
	- use "kubectl get pods" == get the pods and their status
	- use "kubectl describe pod" == get pod information
	- use "kubectl expose rc kubia --type=LoadBalancer --name kubia-http" == create the service object
		rc == ReplicationController
	- use "kubectl get services" == gets the services
	- use "kubectl get svc" == same as above (svc == shorthand for services)
		- use "minikube service kubia-http" if you used minikube to start the service for local testing.
	- curl IP:8080 to get the hello world again!
- summary
	- "kubectl run kubia --image=bakedkookies/kubia --port=8080 --generator=run/v1"
		- create ReplicationController
			- create Pod object for kubia
	- "kubectl expose rc kubia --type=LoadBalancer --name kubia-http"
		- creates a service that has a external static IP that load balances all kubia pods (just 1 right now)
- scaling horizontally
	- "kubectl get replicationcontrollers"
	- "kubectl scale rc kubia --replicas=3"
		- increases kubia to 3 pods
	- "kubectl get rc"
	- "kubectl get pods"
	- "kubectl get pods -o wide" # get the pod IP and pod's node
	- "kubectl describe pod <node name>"
- kubernetes dashboard
	- "kubectl cluster-info | grep dashboard"
	- use IP on browser to get dashboard
	- "minikube dashboard"  if using minikube
- stopping kubernetes pods
	- "kubectl delete pods kubia"
	- "minikube stop"
	- "kind delete cluster"
	- get dashboard username & password: 'gcloud container clusters describe kubia | grep -E "(username|password):"'
- clean restart of minikube:
	- minikube stop
	- minikube delete
	- rm -rf ~/.kube ~/.minikube
- local testing
	- "kubectl port-forward django-5fb5596956-xhkt5 8000:8000"
	- " exec -it django-5fb5596956-xhkt5 /bin/bash"

- using kind locally (https://kind.sigs.k8s.io/)
	- * the loadbalancer service won't work out of the box because it isn't supported.
		- https://stackoverflow.com/questions/44110876/kubernetes-service-external-ip-pending
		- locally you can only use NodePort or Ingress Controller (your solution?)
	- to at least use it in the meantime:
		- kubectl get services
			- note down cluster-IP and PORT for your load balancer services
		- docker exec -it kind-control-plane /bin/bash
		- curl <cluster-IP>:<port>
- minikube
	- "minikube start"
	- "minikube ssh"
	- "minikube service kubia-http"
	- "minikube dashboard"
	- "minikube stop"

- starting up
	minikube start
	eval $(minikube docker-env)

- check definitions
	- docker history <image>
	- kubectl get deploy deploymentname -o yaml --export

- stopping
	minikube stop
	minikube delete
	rm -rf ~/.kube ~/.minikube

# Core Concepts
## 3: Pods: running containers in Kubernetes
- pod = bucket for containers to be deployed on a node
- pods stay inside a node; pods cannot span multiple nodes
- rationale: 1 process = 1 container
	- 1 pod = N containers = N processes = 1 application
- partial isolation inside a pod of containers
	- shared network
	- shared UTS linux namespaces
	- shared IPC namespaces
	- shared PID namespaces? (new feature)
- note that sharing is only inside a pod!
	- no port conflicts between pods
- pod comms = Flat inter-pod network
	- single flat, shared, network-address space
	- no NAT (network access translation) gateways exist
	- ALL pods can see all other pods
	- just treat pods as all on the same LAN network
	- treat each pod as a logical machine that is separate!
- pod design
	- split containers into pods based on how tightly coupled they are
	- containers should be decoupled whenever possible
		- do they need to run together? what if they're on different hosts?
		- do they represent a single whole or independent components?
		- must they be scaled together or separately?
	- split pods allows horizontal scaling independently without affecting other parts of the system

### create, run, stop pods
- get pod's yaml descriptor
	- "kubectl get pod <pod name> -o yaml"
	- "kubectl get pod <pod name> -o json"
- pod definition: main parts
	- metadata
		- name, namespace, labels, info about the pod
	- spec
		- description of pod's contents (containers, volumes, etc.)
	- status
		- current info about the running pod (health, status of containers, internal IP, etc.)
		- runtime only; no need to specify

#### basic YAML descriptor for pod
apiVersion: v1
kind: Pod
metadata:
	name: name-of-pod
spec:
	containers:
	- image: bakedkookies/kubia
		name: kubia
		ports:
		- containerPort: 8080
			protocol: TCP

- specifying ports is informative, not required
	- containers binding ports to 0.0.0.0 will expose them to the pod and other pods
	- specifying ports just makes it easier to work with
- "kubectl explain pods" == explains pod manifest file & its attributes
- "kubectl explain pod.spec" == explain each pod manifest's attributes
- create a pod using the manifest file
	- "kubectl create -f kubia-manual.yaml"
- docker logs <container id> == get container's logs
- "kubectl logs <pod>" == get logs of the pod
	- container logs are auto-rotated daily and every time log file >= 10MB
	- kubectl logs shows log entries from the last rotation
	- only works when pods exist; you'll need centralized logging (later)
- "kubectl logs <pod> -c <container>" == get logs for a container
	- you need this if there's 1+ containers in the pod
- port fowarding
	- easy way to talk to specific pod without a service
	- nice for debugging
	- "kubectl port-forward kubia-manual 8888:8080"
		- forward local machine's port 8888 -> pod's 8080

### labels to organize pods & resources
- lots of pods makes it hard to work with
- use pods to organize them into smaller groups
- label = key-value pair attached to a resource
- used by "label selectors"
- resources can have 1+ labels
- two types of labels:
	- app == which app, component or microservice pod belongs to
	- rel == pod is a stable, beta or canary release

#### specify labels when creating pod
apiVersion: v1
kind: Pod
metadata:
  name: name-of-pod
	...
	labels:
		creation_method: manual
		env: prod
	...
spec:
  containers:
  - image: bakedkookies/kubia
    name: kubia
    ports:
    - containerPort: 8080
      protocol: TCP

- "kubectl create -f kubia-manual-labelled.yaml"
- "kubectl get pods --show-labels"
- "kubectl get pods -L creation_method,env"
	- specify which labels to show based on label-type

#### edit labels on existing pods
- "kubectl label po name-of-pod creation_method=manual"
- "kubectl label po name-of-pod-v2 env=debug --overwrite"
	- modifying already existing label

### perform operations on all pods via labels
- use label-selectors to get multiple resources with labels
- "kubectl get pods -l creation_method=manual"
- "kubectl get pods -l creation_method!=manual"
- "kubectl get pods -l env"
	- get all pods with env label
- "kubectl get pods -l !env"
- "kubectl get pods -l env in (prod, devel)"
- "kubectl get pods -l env notin (prod, devel)"
- "kubectl get pods -l creation_method=manual,env"

### using labels & selectors to constrain pod scheduling
- problem: some pods need specific hardware requirements
	- GPU acceleration
	- powerful networking
	- powerful CPU
	- more memory
	- higher cache
- solution: use node labels & node label selectors
- "kubectl get nodes"
- "kubectl label node minikube gpu=true"
- "kubectl get nodes -L gpu"

#### constraining pod to labelled nodes
apiVersion: v1
kind: Pod
metadata:
  name: name-of-pod
spec:
	nodeSelector:
		gpu: "true"
  containers:
  - image: bakedkookies/kubia
    name: kubia

- scheduling to one specific node
	- every node has a unique label with the key kubernetes.io/hostname -> hostname-of-node

### annotating pods
- annotation = like labels but not used programmatically
	- created by developer
	- description of resource
	- temporary fields
- listed as:
metadata:
	annotations:
		kubernetes.io/created-by: |
			{"key":"value", ...}
- labels are short, annotations can be long (<= 256kb)
- create an annotation:
	- "kubectl annotate pod name-of-pod mycompany.com/someannotation="foo bar""

### using namespaces to group resources into non-overlapping groups
- namespace = non-overlapping separate groups of objects
	- scope for objects
	- different namespaces, different names
- usage
	- multi-tenant environments
	- production, dev, QA, other environments split into different namespaces but same cluster
	- node resources are (obviously) not tied to namespaces
- "kubectl get ns" == get namespaces
- "kubectl get pods --namespace kube-system"

#### create a namespace from YAML file
apiVersion: v1
kind: Namespace
metadata:
	name: custom-namespace

- "kubectl create namespace -f custom-namespace.yaml"
- "kubectl create namespace custom-namespace"
	- namespace without a yaml file
- namespaces must conform to [RFC 1035 (domain names) - dots]
	- contain only letters, digits, dashes, dots (not for namespaces tho)

#### add object to a namespace
- object yaml file
...
metadata:
	namespace: custom-namespace
...

- "kubectl create -f kubia-manual.yaml -n custom-namespace"
- "kubectl create -f kubia-manual.yaml --namespace custom-namespace"

- managing namespace configs
	- if you don't use -n, it uses your context's default
	- change context via "kubectl config"
	- you can add an alias to simplify this:
		- "alias kcd='kubectl config set-context $(kubectl config currentcontext) --namespace"
		- then use "kcd some-namespace"

- namespaces doesn't isolate the pods running on the cluster
	- all pods still have access to all other pods
		- you can add restrictions via the network solution deployed with kubernetes

### stopping and removing pods
- "kubectl delete pods name-of-pod" == terminates pod containers
	- graceful shutdown of 30 seconds wait (SIGTERM) and then SIGKILL otherwise
- "kubectl delete pods pod1 pod2 ..."
- "kubectl delete pods -l creation_method=manual"
	- use label selector to delete pods
- "kubectl delete ns custom-namespace"
	-	deletes custom-namespace and all resources attached to it
- "kubectl delete po --all"
	- deletes all pods in current namespace
- "kubectl delete all --all"
	- deletes all resources in the current namespace
	- some things (like secrets) are kept

## 4: Replication and other controllers: deploying managed pods
- rationale:
	- you want healthy deployments that fix itself
- use replicationControllers & deployments to manage pods
- unmanaged pods (from chapter 3) are done by a cluster node
	- kubernetes monitors and restarts them if they fail
	- but if the node fails, it breaks the pods

### keeping pods healthy
- containers managed by kubelet service
	- if container crashes, it is auto-restarted
- pods managed by ???
- concept of application "health"
	- infinite loop?
	- deadlock?
	- hanging on something?

#### liveness probes
- 3 liveness probes to check a container:
	1. HTTP GET probe
		- probe makes an HTTP GET request to container's IP:port/path
		- probe expects a 2xx or 3xx response
		- otherwise, restart container
	2. TCP Socket probe
		- probe tries to make a TCP Socket conection
		- if connection fails, restart container
	3. Exec probe
		- execute arbitrary command inside the container and check command's exit code
		- if status code == 0 then OK
		- else restart container

#### make a HTTP liveness probe
apiVersion: v1
kind: pod
metadata:
	name: kubia-liveness
spec:
	containers:
	- image: luksa/kubia-unhealthy
		name: kubia
		livenessProbe:
			httpGet:
				path: /
				port: 8080

- "kubectl logs kubia-liveness --previous" == see logs of container that died if your container was restarted
- "kubectl describe pods kubia-liveness"
- there are additional properties:
	Containers:
		kubia:
			Liveness:       http-get http://:8080/ delay=0s timeout=1s period=10s #success=1 #failure=3
- you can define the probe's settings and change these additional properties
	livenessProbe:
		httpGet:
			path: /
			port: 8080
		initialDelaySeconds: 15 # delay=15s
- ALWAYS set a delay to the probe; your app probably hasn't started yet!
	- exit code 137 == process killed by external signal
		128 + 9 (sigkill)
	- exit code 143 == 128 + 15 (sigterm)
- rationale
	- have a URL path just for liveness (/health for example)
	- make sure /health does not need auth
	- you can use that as a return for any errors you haven't caught
	- make sure the liveness is something that restarting the server would help!
		- if you can't make a connection to an external service, restarting is pointless
	- keep probes light
		- easy to do, simple boolean check for liveness
	- don't bother implementing retry loops in your probes
		- you can add failure thresholds before restarting
		- kubernetes also retries requesting the app several times before considering it a failure
		- therefore don't bother with retry loops

### Introducing ReplicationControllers
- pod manager
	- creates pod when it is deleted, for whatever reason
	- can manage multiple copies (replicas) of a pod
	- handles increasing/decreasing number of running pods to meet replicas #
		- if someone creas a pod of the same type manually
		- if someone changes an existing pod's "type" (label selector criteria)
		- if someone increase/decrease the desired # of pods
- 3 essential parts
	1. label selector
		- if changed, makes pods fall out of scope (so if they crash, ReplicationController won't care)
	2. replica count
	3. pod template
		- only affects new pods being created
- ReplicationController features
	- makes sure a pod is always running by starting a new pod if existing one dies
		- replacement has no relation to failed pod!
	- creates replacement replicas for all pods if cluster node fails
	- enables easy horizontal scaling of pods; manual & automatic

#### create a ReplicationController
apiVersion: v1
kind: ReplicationController
metadata:
	name: kubia
spec:
	replicas: 3
	selector:
		app: kubia
	template:
		metadata:
			labels:
				app: kubia
		spec:
			containers:
			- name: kubia
				image: bakedkookies/kubia
				ports:
				- containerPort: 8080
- spec:replicas = number of pods to deploy
- spec:selector = label selector for ReplicationController to manage
	- obviously, set the labels in the template!
	- you can also skip this part, and let kubernetes figure it out with the template
- template = generated pod's manifest yaml definition
- "kubectl create -f kubia-rc.yaml"
- "kubectl get rc"
- "kubectl describe rc kubia"
- rc label selector change => rc scope of managed pods change
	- each pod's metadata.ownerReferences field = rc it belongs to
- try changing the rc label selector:
	- "kubectl label pod kubia-dmdck type=special"
	- "kubectl get pods --show-labels"
	- "kubectl label pod kubia-dmdck app=foo --overwrite"
	- notice that rc creates a new kubia container because # of containers returned via label selector < 3
- in practice
	- change pod labels so remove a pod affected by a bug
	- if you changed a rc's label selector and moved all pods out of scope, the rc would recreate new pods.
- changing the pod template
	- "kubectl edit rc kubia"
		- opens text editor (vi) for you to edit
		- you can change the text editor via : export KUB_EDITOR="/usr/bin/nano" in ~/.bashrc
- horizontal scaling a ReplicationController
	- "kubectl edit kubia" -> change "replicas: #" entry
	- "kubectl scale rc kubia --replicas=10"
		- this is shorthand instead of manually editing the kubia
- delete a ReplicationController
	- when an RC is deleted, it also deletes its pods
	- "kubectl delete rc kubia --cascade=false"
		- delete rc without deleting pods

### Using ReplicaSets instead of ReplicationControllers
- replacement for ReplicationController
	- better label selector
	- supports multiple selected groups in one ReplicaSet (instead of one rc, multiple times w/ different label selectors)
	- generated via Deployment resources (not created manually)
- example definition
apiVersion: apps/v1
kind: ReplicaSet
metadata:
	name: kubia
spec:
	replicas: 3
	selector:
		matchLabels:
			app: kubia
	template:
		metadata:
			labels:
				app: kubia
		spec:
			containers:
			- name: kubia
				image: luksa/kubia
- more expressive label selectors
	- selector:
			matchExpressions:
				- key: app
				operation: In
				values:
					- kubia
	- notes per expression:
		- each expression has: key, operation, values (optional)
		- 4 valid operators:
			- In == must match one of the specified values
			- NotIn == not match any values
			- Exists == pod includes label with the key, ignores value
				- don't use values field
			- DoesNotExist == pod does not have label
		- if you use both matchLabels and matchExpressions, the pod must satisfy all the conditions!
- "kubectl delete rs kubia"

### scaling pods horizontally: Running exactly one pod on each node with DaemonSets
- DaemonSet == run 1 pod per node
	- used for log collectors, daemons listening per node server
	- only 1 pod per node, cannot set replicas
	- use node selectors to decide which nodes need the daemon pod to run
- example
apiVersion: apps/v1beta2
kind: DaemonSet
metadata:
	name: ssd-monitor
spec:
	selector:
		matchLabels:
			app: ssd-monitor
	template:
		metadata:
			labels:
				app: ssd-monitor
		spec:
			nodeSelector:
				disk: ssd
			containers:
			- name: main
				image: luksa/ssd-monitor
- "kubectl create -f chapter-4/ssd-monitor-daemonset.yaml"
- "kubectl get ds"
- "kubectl get po"
- "kubectl get node"
- "kubectl label node minikube disk=ssd"
	- adds daemon
- "kubectl label node minikube disk=hdd --overwrite"
	- deletes the daemon from node out of scope

### System-Level pods: Running pods that perform a single completable task
- Job resource
	- run a task or job once (not a continuous service)
	- once task is done, job is considered complete
	- Job will reschedule task if it ended without completing
	- if task gets error, Job can be configured to restart or not
- example
apiVersion: batch/v1
kind: Job
metadata:
	name: batch-job
spec:
	completions: 5
	parallelism: 2
	template:
		metadata:
			labels:
				app: batch-job
		spec:
			template:
				...
			restartPolicy: OnFailure
			containers:
			- name: main
				image: luksa/batch-job
- restartPolicy should always be either "OnFailure" or "Never" ("Always" is for services)
- completions: number of times to run the job
	- will create one pod sequentially to run the job per completion
- parallelism: number of parallel pods running at the same time


- "kubectl get jobs"
- "kubectl scale job multi-completion-batch-job --replicas 3"
	- shortcut to editing the Job resource's spec.parallelism field
- use the spec.activeDeadlineSeconds property to add a timer to the Job.
	- if pod fails to complete in the given time, it will terminate and mark Job as failed.

### cronjobs: scheduling jobs to run periodically or once in the future
- time based Jobs
- example
apiVersion: batch/v1beta1
kind: Cronjob
metadata:
	name: batch-job-every-fifteen-minutes
spec:
	schedule: "0,15,30,45 * * * *"
	startingDeadlineSeconds: 15
	jobTemplate:
		spec:
			template:
				metadata:
					labels:
						app: periodic-batch-job
					spec:
						restartPolicy: onFailure
						containers:
						- name: main
							image: luksa/batch-job
- startingDeadlineSeconds == optional property. Adds a deadline for when it should run after the scheduled time.
	- if it didn't run after the deadline, it will not run and be marked as failed.
- notes
	- Cronjob has no guarantees over how many jobs are spinned up
	- Cronjob jobs should be idempotent (running them repeatedly shouldn't created unwanted results)
	- make sure next job run performs any miss previous missed run.

## 5: Services: enabling clients to discover and talk to pods
### Introduction
- why?
	- pods are ephemeral
	- kubernetes assigns an IP address to a started pod: IP of pod not known ahead of time
	- horiontal scaling == multiple pods tied to same service
- Service
	- resource to make a single, constant point of entry to a group of pods
	- Clients then have one static IP to deal instead of directly working with the pods
	- can be load balanced behind the scenes and customizable with label selector
- example: kubia-svc.yaml
apiVersion: v1
kind: Service
metadata:
	name: kubia
spec:
	sessionAffinity: ClientIP
	ports:
	- port: 80
		targetPort: 8080
	selector:
		app: kubia
- sessionAffinity:
	- ClientIP == all requests made by certain client to be redirected to same pod
	- None
- "kubectl create -f chapter-5/kubia-svc.yaml"
- "kubectl get svc"
- curl service within the cluster
	- "kubectl exec kubia-pod-name -- curl -s http://cluster-ip
- example 2: multiple ports in a service definition
apiVersion: v1
kind: Service
metadata:
	name: kubia
spec:
	ports:
	- name: http
		port: 80
		targetPort: 8080
	- name: https
		port: 443
		targetPort: 8443
	selector:
		app: kubia
- targetPort = pod's port

- named ports
- pod definition
kind: Pod
spec: 
	containers;
	- name: kubia
		ports:
		- name: http
			containerPort: 8080
		- name: https
			containerPort: 8443
- Service reference
apiVersion: v1
kind: Service
spec:
	ports:
	- name: http
		port: 80
		targetPort: http
	- name: https
		port: 443
		targetPort: https
- benefits
	- abstract the ports, so you can change them without editing every other file
	- consolidating variables
- discovering services
	- kubernetes populates service host:port as environment variables inside pods
		- they get auto-populated when generated
	- "kubectl exec kubia-name env"
- discovering services via DNS
	- add kube-dns pod to support this
	- each pod can configure if it uses the internal DNS server or not via
		- "dnsPolicy" property in pod.spec
	- FQDN == fully qualified domain name
		- <service name>.<namespace>.svc.cluster.local
			- namespace = default (by default)
			- svc.cluster.local suffix by default
	- "kubectl delete po --all" # delete pods, regen it with RC so they have env entries
	- "kubectl exec -it kubia-name bash" # same as docker
	- "curl http://kubia.default.svc.cluster.local"
	- "curl http://kubia.default"
	- "curl http://kubia"
	- "cat /etc/resolv.conf"
	- pinging service IP will always fail because virtual IPs don't actually exist, and only has meaning with the service port.

### Connecting to services living outside the cluster
- service endpoints
- endpoints resource
	- "kubectl describe svc kubia"
		- endpoints described in the service
	- "kubectl get endpoints kubia"
	- services with a selector == auto managed
	- services w/o a selector == manual control over endpoints
- example of service without selector
apiVersion: v1
kind: Service
metadata:
	name: external-service
spec:
	ports:
	- port: 80
- example of manually created endpoint
apiVersion: v1
kind: Endpoints
metadata:
	name: external-service (must match name of service)
subsets:
	- addresses:
		- ip: 11.11.11.11
		- ip: 22.22.22.22
		ports:
		- port: 80
- alias for external service
apiVersion: v1
kind: Service
metadata:
	name: external-service
spec:
	type: ExternalName
	externalName: someapi.somecompany.com
	ports:
	- port: 80
- by defining a FQDN for external services, even if the IP changes, you can still access the external service
- this is actually just a CNAME on the DNS level

### Exposing services to external clients
- 3 ways to expose external endpoint on the service:
1. service type == NodePort
2. service type == LoadBalancer (extension of NodePort)
3. Creating an Ingress resource == DNS
	- see next section
---
1. service type == NodePort
apiVersion: v1
kind: Service
metadata:
	name: kubia-nodeport
spec:
	type: NodePort
	ports:
	- port: 80
		targetPort: 8080
		nodePort: 30123
	selector:
		app: kubia
- "kubectl get svc kubia-nodeport"
- "curl http://127.0.0.1:30123"

2. service type == LoadBalancer (extension of NodePort)
- load balancer provided by cloud provider
	- if load balancer not provided, reverts back to nodePort
	- "kubectl get svc kubia-loadbalancer"
	- note that browsers use keep-alive connections, so even load balanced pods will have clients hit the same client each time
apiVersion: v1
kind: Service
metadata:
	name: kubia-loadbalancer
spec:
	type: LoadBalancer
	ports:
	- port: 80
		targetPort: 8080
	selector:
		app: kubia

- notes on external connections
	- spec.externalTrafficPolicy: Local
		- redirect external traffic only to pods running on the node that received the connection
		- can break things though - no garantee how the pods are split amongst the nodes
	- the client's IP is changed because of Source Network Address Translation (SNAT)
		- you won't be able to see the cient's IP
		- use spec.externalTrafficPolicy: Local so you don't do the SNAT hop and can get the client IP directly


### Exposing services externally through an Ingress resource
- add Ingress add-on to Minikube
	- "minikube addons list"
	- "minikube addons enable ingress"
	- "kubectl get po --all-namespaces"
- ingress?
	- DNS service basically
	- Ingress maps FQNF:ServiceName:port
	- supports multiple entries at once
	- remember, this is external!
- example
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
	name: kubia
spec:
	rules:
	- host: kubia.example.com
		http:
			paths:
			- path: /
				backend:
					serviceName: kubia-nodeport
					servicePort: 80
			- path: /kubia
				backend:
					serviceName: kubia
					servicePort: 80
- notes
	- "kubectl get ingresses"
	- "curl http://kubia.example.com"
	- "curl http://kubia.example.com/kubia"
- using Ingress with TLS (HTTPS)
	- "openssl genrsa -out tls.key 2048"
	- "openssl req -new -x509 -key tls.key -out tls.cert -days 360 -subj /CN=kubia.example.com"
	- "kubectl create secret tls tls-secret --cert=tls.cert --key=tls.key"
	- "kubectl certificate approve <name of the CSR>"
		- kubernetes can sign the certificate via a certificate signing request resource
		- the CSR must be running on the cluster!
- example
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
	name: kubia
spec:
	tls:
	- hosts:
		- kubia.example.com
		secretName: tls-secret
	rules:
	- host: kubia.example.com
		http:
			paths:
			- path: /
				backend:
					serviceName: kubia-nodeport
					servicePort: 80
- "kubectl apply -f kubia-ingress-tls.yaml"
	- updates the Ingress without needing to delete & create again
	- works because there's only one Ingress with the same name?
- "curl -k -v https://kubia.example.com/kubia"
	- notice HTTPS working?

### Signaling when a pod is ready to accept connections
- readiness probes
	- specifies when a pod should start getting traffic
	- useful because usually pods need time to boot up their software and setup
- types
1. Exec probe
2. HTTP GET probe
3. TCP Socket probe
- example template change to ReplicationController?
spec.containers:
- name: kubia
	image: luksa/kubia
	readinessProbe:
		exec:
			command:
			- ls
			- /var/ready
- 0 = success if file exists, non-1 = failure if file does not exist
- add the file to existing pods
	- "kubectl exec kubia-2r1qb -- touch /var/ready"

- notes
	- always define a readiness probe
		- you never want to immediate start sending traffic to a pod that was just created
		- it will cause a lot of errors on the client!
	- don't include pod shutdown logic in readiness probes
		- kubernetes handles pod shutdown: traffic is immediate stopped routing once it starts terminating a pod

### Using a headless service for discovering individual pods
- a service without external IP == returns a list of all IPs of the all selected pods
- example
apiVersion: v1
kind: Service
metadata:
	name: kubia-headless
spec:
	clusterIP: None (makes this service headless)
	ports:
	- port: 80
		targetPort: 8080
	selector:
		app: kubia
- example usage
	- "kubectl run dnsutils --image=tutum/dnsutils --generator=run-pod/v1 --command -- sleep infinity"
		- sample pod without writing YAML manifest
		- sample pod with DNS utils
	- "kubectl exec dnsutils nslookup kubia-headless"
	- "kubectl exec dnsutils nslookup kubia"
- add to service metadata.annotations: service.alpha.kubernetes.io/tolerate-unready-endpoints: "true"
	- discover all pods, even those that are not ready

### troubleshooting
- steps
	- check if service's cluster IP is connectable from inside the cluster (outside should use external IP!)
	- don't try pinging the service = it will never work
	- make sure readiness probe is working and pod is part of the service
	- use "kubectl get endpoints" to ensure endpoints object is part of the service
	- when trying external FQDN, try the cluster IP first to make sure the service/pods exist and work
	- make sure you're trying the external port, not the pod's exposed port
	- try connecting to the pod IP directly to amke sure pod is actually accepting connections
	- if you can't even access your app through the pod's IP, make sure app isn't only binding to localhost.

## 6: Volumes: attaching disk storage to containers
### 6.1: Introducing Volumes
- Volume
	- data storage shared inside a pod between containers
	- used for sharing data between containers
	- can be used to give containers access to node storage, external storage, git repos and more
- Volume types
	- emptyDir
	- gitRepo
	- hostPath
	- nfs
	- gcePersistentDisk/awsElasticBlockStore/azureDisk = cloud provider specific storage
	- cinder/cephfs/iscsi/flocker/glusterfs/quobyte/rbd/flexVolume/vsphereVolume/photonPersistentDisk/scaleIO = other types of network storage
	- configMap/secret/downwardAPI = special types of volumes for exposing certain kubernetes resources/cluster info to the pod
	- persistentVolumeClaim = proper way to provision persistent storage

### 6.2: Using volumes to share data between containers
- emptyDir volume
	- empty directory that you can use to share data between pod's containers
	- lasts as long as the pod (not very long), used for temporary data
	- example fortune app
		/fortune/fortuneloop.sh
			#!/bin/bash
			trap "exit" SIGINT
			mkdir /var/htdocs
			while :
			do
			echo $(date) Writing fortune to /var/htdocs/index.html
			/usr/games/fortune > /var/htdocs/index.html
			sleep 10
			done
		/fortune/Dockerfile
			FROM ubuntu:latest
			RUN apt-get update ; apt-get -y install fortune
			ADD fortuneloop.sh /bin/fortuneloop.sh
			ENTRYPOINT /bin/fortuneloop.sh
		- "docker build -t luksa/fortune ."
		- "docker push luksa/fortune"
		/fortune/fortune-pod.yaml
		apiVersion: v1
		kind: Pod
		metadata:
			name: fortune
		spec:
			containers:
			- image: luksa/fortune
				name: html-generator
				volumeMounts:
				- name: html
					mountPath: /var/htdocs
			- image: nginx:alpine
				name: web-server
				volumeMounts:
				- name: html
					mountPath: /usr/share/nginx/html
					readOnly: true
				ports:
				- containerPort: 80
					protocol: TCP
			volumes:
			- name: html
				emptyDir: {}
			- name: html2
				emptyDir:
					medium: Memory
		- result?
			- html-generator sends output of `fortune` to /var/htdocs/index.html per 10 seconds
			- web-server picks up on /var/htdocs/index.html and respond to requests using it
		- "kubectl port-forward fortune 8080:80"
			- mount local machine 8080 to pod's 80 port
			- "curl http://localhost:8080"
		- using medium: Memory == use memory (ram) instead of disk
- gitRepo
	- emptyDir volume populated with git clone of a repository
	- only works with public (not private) repos
	- does not manually sync the data - you need to delete the pod and re-create it to update it
	- you can also use a sidecar container pattern: a container that only updates a shared volume with `git sync`
	- example
		apiVersion: v1
		kind: Pod
		metadata:
			name: gitrepo-volume-pod
		spec:
			containers:
			- image: nginx:alpine
				name: web-server
				volumeMounts:
				- name: html
					mountPath: /usr/share/nginx/html
					readOnly: true
				ports:
				- containerPort: 80
					protocol: TCP
			volumes:
			- name: html
				gitRepo:
					repository: https://github.com/luksa/kubia-website-example.git
					revision: master
					directory: .
	- gitRepo.directory -> refers to the root directory of the volume
	- sidecar container
		- search for "git sync" on Docker Hub
			- https://hub.docker.com/r/openweb/git-sync
		- use the sync destination to the volume
		- this will make sure the git repo is always synced on the pod
		- works even with private repos, assuming the sidecar container supports it

### 6.3: Accessing files on the worker node's filesystem
- hostPath volume
	- used to get a specific file/directory on the node's filesystem
	- useful for log collectors, node daemons, etc. that need a node's filesystem access
	- somewhat volatile: persists longer than emptyDir (does not die with pods) but not when the node dies
	- also, the data is specific to each node; if your pod changes nodes, it loses access to the existing data!
	- use this only for read/write system files on the node, nothing else!
- examples
	- "kubectl get pod s --namespace kube-system
	- "kubectl describe po fluentd-kubia-4ebc2f1e-9a3e --namespace kube-system"
	- notice the Volumes.varlog<> sections

### 6.4: Using persistent storage
- example using GCE persistent disk
	- "gcloud container clusters list"
	- "gcloud compute disks create --size=1GiB --zone=europe-west1-b mongodb"
	- example
		apiVersion: v1
		kind: Pod
		metadata:
			name: mongodb
		spec:
			volumes:
			- name: mongodb-data
				gcePersistentDisk:
					pdName: mongodb
					fsType: ext4
			containers:
			- image: mongo
				name: mongodb
				volumeMounts:
				- name: mongodb-data
					mountPath: /data/db
				ports:
				- containerPort: 27017
					protocol: TCP
	- for minikube, you can't use GCE Persistent Disk, but mongo-pod-hostpath.yaml
	- "kubectl exec -it mongodb mongo"
		- "use mystore"
		- "db.foo.insert({name:'foo'})"
		- "db.foo.find()"
	- "kubectl delete pod mongodb"
		- "kubectl create -f monogodb-pod-gcepd.yaml"
		- recreate the pod to make sure you know the data is persisted!
		- "kubectl get pods -o wide"
			- see which node is scheduled for the pod
	- using your own NFS volume
		volumes:
		- name: mongodb-data
			nfs:
				server: 1.2.3.4
				path: /some/path

### 6.5: Decoupling pods from the underlying storage technology
- overview
	- PersistentVolume
		- admin creates these
		- PV resource describes the node/storage hardware capabilities
		- registered in kubernetes so it can be managed
	- PersistentVolumeClaim
		- an attempt by the user to get a persistentVolume
		- kubernetes allocates it behind the scenes
		- user can then use the PVC in the pods as a volume
	- steps
		1. admin creates network storage
		2. admin creates PersistentVolume targeting the network storage
		3. user creates PersistentVolumeClaim
		4. user creates pod with a volume referencing the PVC
- PersistentVolume
	- example
		apiVersion: v1
		kind: PersistentVolume
		metadata:
			name: mongodb-pv
		spec:
			capacity:
				storage: 1Gi
			accessModes:
			- ReadWriteOnce
			- ReadOnlyMany
			persistentVolumeReclaimPolicy: Retain
			gcePersistentDisk:
				pdName: mongodb
				fsType: ext4
		- minikube == use mongodb-pv-hostpath.yaml
		- capactity.storage == PV's size
		- persistentVolumeReclaimPolicy == if claim is released, should PersistentVolume be retained?
		- gcePersistentDisk == what type of volume is being used?
	- "kubectl get pv"
- PersistentVolumeClaim
	- example
		apiVersion: v1
		kind: PersistentVolumeClaim
		metadata:
			name: mongodb-pvc
		spec:
			resources:
				requests:
					storage: 1Gi
			accessModes:
			- ReadWriteOnce
			storageClassName: ""
		- storageClassName == used in the dynamic provisioning section
		- "kubectl get pvc"
			- "RWO--ReadWRiteOnce" == single node to mount the volume for read/write
			- "ROX--ReadOnlyMany" == multiple nodes can mount the volume for read
			- "RWX--ReadWriteMany" == multiple nodes can mount the volume for read/write
	- example pod using the PVC
		apiVersion: v1
		kind: Pod
		metadata:
			name: mongodb
		spec:
			containers:
			- image: mongo
				name: mongodb
				volumeMounts:
				- name: mongodb-data
					mountPath: /data/db
				ports:
				- containerPort: 27017
					protocol: TCP
			volumes:
			- name: mongodb-data
				persistentVolumeClaim:
					claimName: mongodb-pvc
- recycling PersistentVolumes
	- "kubectl delete pod mongodb"
	- "kubectl delete pvc mongodb-pvc"
	- released persistentVolumes are "released" status not "Available"
		- this gives the admin a chance to clean it up and decide what to do with the data
		- this is the behaviour if persistentVolumeReclaimPolicy: Retain
		- persistentVolumeReclaimPolicy: Recycle == deletes data, makes volume available again (basic scrub via rm -rf /my-volume/*)
		- persistentVolumeReclaimPolicy: Delete == deletes underlying storage
		- remember, you can edit the PersistentVolume manually if you change your mind!

### 6.6: Dynamic provisioning of PersistentVolumes
- overview
	- tedious still to manually create PersistentVolumes
	- let kubernetes create PersistentVolumes using a template!
	- this way, you can provision the volumes as you need it, depending on the use case!
	- solution: StorageClass
- StorageClass
	- example
		apiVersion: storage.k8s.io/v1
		kind: StorageClass
		metadata:
			name: fast
		provisioner: kubernetes.io/gce-pd
		parameters:
			type: pd-ssd
			zone: europe-west1-b
	- minikube should use storageclass-fast-hostpath.yaml
	- PVC with dynamic provisioning
		apiVersion: v1
		kind: PersistentVolumeClaim
		metadata:
			name: mongodb-pvc
		spec:
			storageClassName: fast
			resources:
				requests:
					storage: 100Mi
			accessModes:
				- ReadWriteOnce
		- if storageClassName is not a valid StorageClass, it will fail with ProvisioningFailed event
		- "kubectl get pvc mongodb-pvc"
		- "kubectl get pv"
		- "gcloud compute disks list"
	- notes
		- admin can now create different types of storage classes with different performance/sizes
		- you can use them by name, so PVC definitions work across multiple clusters (assuming the same naming convention)
- dynamic provisioning without specifying the storage class
	- "kubectl get sc"
		- note that there is a (default) storageClass, and it already existed
	- "kubectl get sc standard -o yaml"
	- example PVC without a storage class defined
		apiVersion: v1
		kind: PersistentVolumeClaim
		metadata:
			name: mongodb-pvc
		spec:
			resources:
				requests:
					storage: 100Mi
			accessModes:
				- ReadWriteOnce
		- storageClassName: "" == use an existing PV -> doesn't match any class -> kubernetes attempts to find a match
		- otherwise, kubernetes would generate a new PV for you!

## 7: ConfigMaps & Secrets: configuring applications
### 7.1: Configuring containerized applications
- problem
	- you might want to define generic containers, pods and resources with slight differences
	- manage the differences to share the same code but configured differently via settings!
	- solution: ConfigMap
- ConfigMap
	- ways to pass config to containers
		- command line arguments
		- custom environment variables for each container
		- mounting configuration files into containers with a special type of container

### 7.2: Passing command-line arguments to containers
- setup
	- docker container taking in arguments via command line
		- ENTRYPOINT = executable used when starting the container
		- CMD = arguments passed to the ENTRYPOINT
		- shell form == running command with bash == /bin/sh -c <ENTRYPOINT> <CMD>
		- exec form == running command directly == <ENTRYPOINT>
	- you'll need to define the ENTRYPOINT bash script to take arguments
		- usually use $1, $2, etc. to do this
	- once you do, add a default value for the argument in the Dockerfile:
		- CMD ["argument1"]
		- CMD ["argument2"]
		- because you're using numbered arguments, there's no name for them. Change that if you want!
	- run the docker image:
		- "docker build -t docker.io/luksa/fortune:args ."
		- "docker push docker.io/luksa/fortune:args"
		- "docker run -it docker.io/luksa/fortune:args"
		- "docker run -it docker.io/luksa/fortune:args 15"
			- override first argument
	- do the same thing in kubernetes (example):
		kind: Pod
		spec:
			containers:
			- image: /some/image
				command: ["/bin/command"]
				args: ["arg1", "arg2", "arg3"]
			- image: luksa/fortune:args
				args:
				- "15"
				- "1"
				- "10"
	- you generally don't need to override command
	- note you can't update command and args fields after the pod is created

### 7.3: Setting environment variables for a container
- using environment variables with containers
	- unlike command line arguments, the environment variables are more flexible
	- it can be changed during runtime (assuming you check for it)
	- docker containers use environment variables on creation
	- this way you can use them directly
- example ENTRYPOINT bash script
	#!/bin/bash
	$INTERVAL
	...
- example pod using environment variable
	kind: Pod
	spec:
		containers:
		- image: luksa/fortune:env
			env:
			- name: INTERVAL
				value: "30"
			- name: SECOND_VAR
				value: "$(INTERVAL)bar"
		...
- remember that kubernetes also auto-exposes env variables for each service in the same namespace
- drawbacks
	- these are still hardcoded
	- best if we can decouple them entirely

### 7.4: Decoupling configuration with a ConfigMap
- ConfigMap
	- resource that is a map containing key/value pairs for configs
	- containers and apps can still consume it their own way (CLI arguments, environment variables, files, etc.)
- creating the configMap
	- "kubectl create configmap fortune-config --from-literal=sleep-interval=25 --from-literal=foo=bar"
	- yaml
		apiVersion: v1
		data:
			sleep-interval: "25"
		metadata:
			name: fortune-config
	- "kubectl create configmap my-config --from-file=config-file.conf"
	- "kubectl create configmap my-config --from-file=custom-key=config-file.conf"
		- config-file.conf(key) : <config-file.conf contents> (value)
		- you can give it a custom key if you want
	- "kubectl create configmap my-config --from-file=/path/to/dir"
		- generates key/value mappings for ALL valid files (checks their filename is valid)
	- combining the options
		- kubectl create configmap my-config
			--from-file=foo.json
			--from-file=bar=bar.conf
			--from-file=config-opts/
			--from-literal=some=thing
- creating configMap entry via environment variables
	- example
		apiVersion: v1
		kind: Pod
		metadata:
			name: i-am-a-pod
		spec:
			containers:
			- image: luksa/fortune:env
				env:
				- name: INTERVAL
					valueFrom:
						configMapKeyRef:
							name: fortune-config
							key: sleep-interval
				- name: INTERVAL-OPTIONAL
					valueFrom:
						configMapKeyRef:
							name: fortune-config
							key: sleep-interval
							optional: true
				envFrom:
				- prefix: CONFIG_
					configMapRef:
						name: my-config-map
				args: ["$(INTERVAL)"]
		- we set the environment variable INTERVAL = fortune-config.sleep-interval (ConfigMap)
		- envFrom == loads all ConfigMap's entries into container as environment variables
			- note that dashes are invalid in a config entry! It will be skipped!
		- creating configMap entry via command line arguments == add args field and use environment variables defined
- configMap volume
	- transform your application to read files from the configMap's volume mount directory
	- "kubectl create configmap fortune-config --from-file=configmap-files"
	- "kubectl get configmap fortune-config -o yaml"
	- example pod
		apiVersion: v1
		kind: Pod
		metadata:
			name: fortune-configmap-volume
		spec:
			containers:
			- image: nginx:alpine
				name: web-server
				volumeMounts:
				- name: config
					mountPath: /etc/nginx/conf.d
					readOnly: true
			volumes:
			- name: config
				configMap:
					name: fortune-config
		- the default nginx config is at /etc/nginx/nginx.conf
			- it automatically pulls config data from /etc/nginx/conf.d
		- "kubectl port-forward fortune-configmap-volume 8080:80 &"
		- "curl -H "Accept-Encoding: gzip" -I localhost:8080"
		- "kubectl exec fortune-configmap-volume -c web-server ls /etc/nginx/conf.d"
			- you should see multiple config files here besides the my-nginx-config.conf
			- it is because the ConfigMap has multiple variables set
	- it is possible to have multiple ConfigMaps in a pod configure different containers, but it feels wrong
	- example of pod with my specific ConfigMap entries
		volumes:
		- name: config
			configMap:
				name: fortune-config
				items:
				- key: my-nginx-config.conf
					path: gzip.conf
	- note that if you use a configMap on a directory path that already exists in the container, it will overshadow it!
		- as in, you can't access or use the directory that came with the directory
		- to mount a file/directory without it shadowing the whole directory: use subpaths
	- example of subpaths
		spec:
			containers:
			- image: some/image
				volumeMounts:
				- name: myvolume
					mountPath: /etc/someconfig.conf
					subPath: myconfig.conf
					defaultMode: "6600"
		- subPath == not mounting entire volume, just the file
		- defaultMode == file permissions
- edit a ConfigMap
	- "kubectl edit configmap fortune-config"
	- "kubectl exec fortune-configmap-volume -c web-server" == check if file was really updated
	- "kubectl exec fortune-configmap-volume -c web-server -- nginx -s reload" == reload settings
	- the files are automatically updated because kubernetes uses symlinks to mount the directory/files
- notes
	- if you mount only a file instead of a volume, it won't be updated!
		- use a ConfigMap volume, which will update as needed
	- remember that immutability is a big deal: it ensures security and stability
		- bypassing this via editing ConfigMaps isn't great
		- if you're okay with pods reloading and refreshing without going down, that's okay
		- but generally it's better to keep ConfigMaps immutable and re-create pods; they're disposable

### 7.5: Using secrets to pass sensitive data to containers
- secrets
	- ensures only nodes that need Secrets data gets that data
	- stored in memory only and never in physical storage
	- Secrets are encrypted
	- decide
		- configMap = generic configuration data
		- secrets = if data is sensistive and needs to be kept under key
	- secrets are base64-encoded
		- so it can store binary data, not only plain-text
		- limited to 1MB
- example
	- "kubectl describe <pod>"
	- "kubectl kubectl get secrets"
	- "kubectl describe secrets"
- secrets
	- secrets are mounted as a volume
	- create
		- "openssl genrsa -out https.key 2048"
		- "openssl req -new -x509 -key https.key -out https.cert -days 3650 -subj /CN=www.kubia-example.com"
		- "echo foo > bar"
			- creates file bar with content "foo"
		- "kubectl create secret generic fortune-https --from-file=https.key --from-file=https.cert --from-file=foo"
	- view
		- "kubectl get secret fortune-https -o yaml" == secret
		- "kubectl get configmap fortune-config -o yaml" == configMap
- stringData
	kind: Secret
	apiVersion: v1
	stringData:
		foo: plain text
	data:
		...
- usage
	- using a secret in a pod
apiVersion: v1
kind: Pod
metadata:
	name: fortune-https
spec:
	containers:
	- image: luksa/fortune:env
		name: html-generator
		env:
		- name: INTERVAL
			valueFrom:
				configMapKeyRef:
					name: fortune-config
					key: sleep-interval
			volumeMounts:
			- name: html
				mountPath: /var/htdocs
	- image: nginx:alpine
		name: web-server
		volumeMounts:
		- name: html
			mountPath: /usr/share/nginx/html
			readOnly: true
		- name: config
			mountPath: /etc/nginx/conf.d
			readOnly: true
		- name: certs
			mountPath: /etc/nginx/certs/
			readOnly: true
		ports:
		- containerPort: 80
		- containerPort: 443
	volumes:
	- name: html
		emptyDir: {}
	- name: config
		configMap:
			name: fortune-config
			items:
			- key: my-nginx-config.conf
				path: https.conf
	- name: certs
		secret:
			secretName: fortune-https
	- testing this image
		- "kubectl port-forward fortune-https 8443:443 &"
		- "curl https://localhost:8443 -k"
		- "curl https://localhost:8443 -k -v"
		- "kubectl exec fortune-https -c web-server -- mount | grep certs"
			- see that the secrets are mounted as a tmpfs volume storage
- environment variables for secrets
	env:
	- name: FOO_SECRET
		valueFrom:
			secretKeyRef:
				name: fortune-https
				key: foo
	- not recommended
		- exposes the secrets in the container
		- easily dumped via error reports (they can dump all environment variables)
- private Docker image Repos
	- create a Secret containg the credentials for the Docker Registry
		- "kubectl create secret docker-registry mydockerhubsecret \
			--docker-username=myusername --docker-password=mypassword \
			--docker-email=my.email@provider.com"
	- reference that secret in the imagePullSecrets field in the pod
		apiVersion: v1
		kind: Pod
		metadata:
		name: private-pod
		spec:
			imagePullSecrets:
			- name: mydockerhubsecret
			containers:
			- image: username/private:tag
				name: main
	- you don't need to do this: use a serviceAccount instead to handle it!

## 8: Accessing pod metadata and other resources from applications
### 8.1: passing metadata through the downward API
- background
	- we can now get variables to the containers via environment variables, arguments and more
		- use secrets, configMaps
	- but these are known variables ahead of time, what about auto-generated variables?
		- pod, node specific data
			- name
			- IP
			- namespace
			- node of the pod
			- service account of the pod
			- CPU & memory requests, limits for each container
			- pod's labels
			- pod's annotations
	- to get this data, we need to pass the resource metedata down to the container!
	- 2 methods using the downward API
		1. environment variables
			apiVersion: v1
			kind: Pod
			metadata:
				name: downward
			spec:
				containers:
				- name: main
					image: busybox
					env:
					- name: POD_NAME
						valueFrom:
							fieldRef:
								fieldPath: metadata.name
					- name: POD_IP
						valueFrom:
							fieldRef:
								fieldPath: status.podIP
					- name: NODE_NAME
						valueFrom:
							fieldRef:
								fieldPath: spec.nodeName
					- name: SERVICE_ACCOUNT
						valueFrom:
							fieldRef:
								resourceFieldRef:
									resource: requests.cpu
									divisor: 1m
		2. downwardAPI volume
			apiVersion: v1
			kind: Pod
			metadata:
				name: downward
				labels:
					foo: bar
				annotations:
					key1: value1
					key2: |
						multi
						line
						value
			spec:
				containers:
				- name: main
					image: busybox
					volumeMounts:
					- name: downward
						mountPath: /etc/downward
				volumes:
				- name: downward
					downwardAPI:
						items:
						- path: "podName"
							fieldRef:
								fieldPath: metadata.name
						- path: "containerCpuRequestMilliCores"
							resourceFieldRef:
								containerName: main
								resource: requests.cpu
								divisor: 1m
	- differences
		- env variables are easier to use but can't be refreshed
		- thus, volumes are better when working with labels & annotations = they update!
	- testing
		- "kubectl exec downward ls -lL /etc/downward"
		- "kubectl exec downward cat /etc/downward/labels"
		- "kubectl exec downward cat /etc/downward/annotations"
		- "curl http://localhost:8001/apis/batch/v1/jobs"
		- "curl http://localhost:8001/apis/batch/v1/namespaces/default/jobs/my-job"

### 8.2: Talking to the kubernetes API server
- Kubernetes REST API
	- used to get data from other pods, nodes, etc. across the cluster
	- "kubectl cluster-info"
		- gives you the IP the REST API is running on
	- "curl https://192.168.99.100:8443 -k"
		- fails; you need to authenticate & authorize
- access via kubectl proxy
	- "kubectl proxy"
	- "curl http://localhost:8001"
	- "curl http://localhost:8001/apis/batch"
- access from a pod
	- need to ensure access
		- find location of the API server
		- make sure you are talking directly to API server, not impersonated
		- auth with the server
	- setup
		apiVersion: v1
		kind: Pod
		metadata:
			name: curl
		spec:
			containers:
			- name: main
				image: tutum/curl
				command: ["sleep", "9999999"]
		- "kubectl exec -it curl bash"
	- find API Server
		- "kubectl get svc"
		- "env | grep KUBERNETES_SERVICE"
		- "curl https://kubernetes"
	- confirm API server is real
		- " ls /var/run/secrets/kubernetes.io/serviceaccount/ "
		- "curl --cacert /var/run/secrets/kubernetes.io/serviceaccount /ca.crt https://kubernetes"
			- certificate works but your account is still unauthorized
			
		- "export CURL_CA_BUNDLE=/var/run/secrets/kubernetes.io/serviceaccount/ca.crt"
			- you can just "curl https://kubernetes" directly without --cacert
	- auth with the server
		- "TOKEN=$(cat /var/run/secrets/kubernetes.io/serviceaccount/token)"
		- " curl -H "Authorization: Bearer $TOKEN" https://kubernetes"
		- "kubectl create clusterrolebinding permissive-binding \
			--clusterrole=cluster-admin \
			--group=system:serviceaccounts"
			- disables role-based access control
			- we enable access for ALL accounts as a workaround until we learn about service accounts
	- make calls
		- "NS=$(cat /var/run/secrets/kubernetes.io/serviceaccount/namespace)"
		- " curl -H "Authorization: Bearer $TOKEN" https://kubernetes/api/v1/namespaces/$NS/pods"
- using the ambassador container pattern
	- run the kubectl proxy command in a separate container
	- use this container to fetch and process the data, sending it to your application containers
	- example
			apiVersion: v1
			kind: Pod
			metadata:
				name: curl-with-ambassador
			spec:
			containers:
			- name: main
				image: tutum/curl
				command: ["sleep", "9999999"]
			- name: ambassador
				image: luksa/kubectl-proxy:1.6.2
		- "kubectl exec -it curl-with-ambassador -c main bash"
		- "curl localhost:8001"
- using the client libraries
	- there's a swagger API framework to generate it
		- on the cluster, use "/swaggerapi"
	- there's also a OpenAPI file for it too
		- on the cluster, use "/swagger.json"

## 9: Deployments: updating applications declaratively
- deploying new versions of pods
	- 

### 9.1: Updating applications running in pods
- manual upgrade of the pods
	- scenario: you have v1 of an application, and want to deploy v2
	- options:
		- delete all existing pods -> start new ones
			- disconnected servers in the between downtime
		- start new ones, wait for them to go up, delete old ones
			- blue-green deployment
				- problem: it is all or nothing kind of deployment!
			- create new pods with a new label
			- change the service's label selector to the new label
			- delete the old pods
### 9.2: Performing an automatic rolling update with a ReplicationController
- rolling deployment
	- use two services for v1 and v2
		- remove one from v1, add one for v2
		- tedious to do manually, so do it with a helper function!
	- example
		apiVersion: v1
		kind: ReplicationController
		metadata:
			name: kubia-v1
		spec:
			replicas: 3
			template:
				metadata:
					name: kubia
					labels:
						app: kubia
				spec:
					containers:
					- image: luksa/kubia:v1
						name: nodejs
		---
		apiVersion: v1
		kind: Service
		metadata:
			name: kubia
		spec:
			type: LoadBalancer
			selector:
				app: kubia
			ports:
			- port: 80
				targetPort: 8080
	- use --- to denote another resource definition in the same file!
- sample app.js
	const http = require('http');
	const os = require('os');
	console.log("Kubia server starting...");
	var handler = function(request, response) {
	console.log("Received request from " + request.connection.remoteAddress);
	response.writeHead(200);
	response.end("This is v1 running in pod " + os.hostname() + "\n");
	};
	var www = http.createServer(handler);
	www.listen(8080);
	- "kubectl get svc kubia"
	- "while true; do curl http://ip; done"
- now update the app to v2
	- set the message to "this is v2 ..."
	- note that you should always docker image's tag!
		- if you don't, the default "imagePullPolicy" = "IfNotPresent" -> does not refresh docker image
		- set "imagePullPolicy" = "Always" to handle this
		- but its better to use a new tag so it always pulls; new tag = new version
		- if you set docker image:latest, this won't matter though
	- "kubectl rolling-update kubia-v1 kubia-v2 --image=luksa/kubia:v2"
	- "kubectl describe rc kubia-v2"
	- "kubectl delete rc --all"
	- what does "rolling-update" do?
		- creates a new ReplicationController for kubia v2
		- adds label: deployment = <hash> to rc-v1 and pods
		- does the scaling slowly one by one
	- what's wrong with rolling-update?
		- it adds the "deployment" label to the pods and RC
		- no way to recover from a bad update
		- kubernetes methodology is to set the end-state. We did an operation instead here!

### 9.3: Using Deployments for updating apps declaratively
- Deployment Overview
	- high level resource for deploying applications and updating them
	- uses RC and RS
	- Deployment -> ReplicaSet -> Pod
- example
	apiVersion: apps/v1beta1
	kind: Deployment
	metadata:
		name: kubia
	spec:
		replicas: 3
		template:
			metadata:
				name: kubia
				labels:
					app: kubia
			spec:
				containers:
				- image: luksa/kubia:v1
					name: nodejs
	- "kubectl create -f kubia-deployment-v1.yaml --record"
		- "--record" == keep revision history via lingering ReplicaSets
	- "kubectl rollout status deployment kubia"
		- do a rollout deployment
	- "kubectl get replicasets"
- updates via the example
	- "kubectl patch deployment kubia -p '{"spec": {"minReadySeconds": 10}}'"
		- slow down deployments so you can see the changes
	- "while true; do curl http://130.211.109.222; done"
		- track the progress of the deployment
	- "kubectl set image deployment kubia nodejs=luksa/kubia:v2"
		- change the nodejs image (luksa/kubia:v1) to (luksa/kubia:v2)
	- commands you can use; for deployments they are all the same
		- kubectl edit == open object's manifest file
		- kubectl patch == modifies properties of an object
			- needs only partial definition; only the parts being changed
		- kubectl apply == modifies properties of an object using a YAML/JSON object; if object does not exist, create it.	
			- needs full object definition
		- kubectl replace
			- replaces the properties of the object with new object definition.
			- needs object to exist in the first place
		- kubectl set image
			- change container iamge defined in a pod, RC template, Deployment, DaemonSet, Job or RS.
		- all these commands change Deployment's specification and triggers rollout process.
		- note that if you use a ConfigMap in the pod template, changing the ConfigMap doesn't trigger an update
			- instead, create a new ConfigMap and modify the pod template to reference the new ConfigMap
- rolling back
	- "kubectl rollout undo deployment kubia"
	- "kubectl rollout history deployment kubia"
	- "kubectl rollout undo deployment kubia --to-revision=1"
		- go back to a specific revision history
		- that's why you need --record!
- rate of the rollout
	- spec.strategy.rollingUpdate.maxSurge
		- default = 25%
		- value = % or integer
		- the number of additional pods on top of desired_replicas you can have during the rollout
	- spec.strategy.rollingUpdate.maxUnavailable
		- default = 25%
		- number of unavailable pods relative to desired replica count
	- result
		- minAvailabe = desired_replica_count - maxUnavailable
		- totalSurge = desired_replica_count + maxSurge
- pause the rollout
	- "kubectl set image deployment kubia nodejs=luksa/kubia:v4"
	- "kubectl rollout pause deployment kubia"
	- "kubectl rollout resume deployment kubia"
	- "kubectl rollout undo deployment kubia"
		- you need to resume the deployment to run the undo
- blocking rollouts of bad versions
	- using minReadySeconds
		- minReadySeconds == number of seconds a newly created pod should be ready before it is considered available
		- if there is an error and the pod lasts 2 seconds, and minReadySeconds = 4, it is never considered available
	- using readiness Probe
		apiVersion: apps/v1beta1
		kind: Deployment
		metadata:
			name: kubia
		spec:
			replicas: 3
			minReadySeconds: 10
			strategy:
				rollingUpdate:
					maxSurge: 1
					maxUnavailable: 0
				type: RollingUpdate
			template:
				metadata:
					name: kubia
					labels:
						app: kubia
				spec:
					containers:
					- image: luksa/kubia:v3
					name: nodejs
					readinessProbe:
						periodSeconds: 1
						httpGet:
							path: /
							port: 8080
		- minReadySeconds: 10
		- maxUnavailable: 0 so deployment replaces pods one by one
		- readiness probe executes every second
		- using a Http Readiness probe
		- result?
			- failing pods during deployment == pods fail readiness == never makes it to deployment
			- pod is not available!
			- and since maxUnavailable = 0 no existing pods will be removed
- failure
	- by default, if rollout fails to make any progress in 10 minutes, deployment rollout is considered a failure
	- Deployment.spec.progressDeadlineSeconds
	- "kubectl describe deploy kubia"

## 10: StatefulSets: deploying replicated stateful applications
### 10.1: Replicating stateful pods
### 10.2: Understanding StatefulSets
### 10.3: Using a StatefulSet
### 10.4: Discovering peers in a StatefulSet
### 10.5: Understanding how StatefulSets deal with node failures

# Beyond the Basics
## 11: Understanding Kubernetes internals
## 12: Securing the Kubernetes API server
## 13: Securing cluster nodes and the network
## 14: Managing pods' computational resources
## 15: Automatic scaling of pods and cluster nodes
## 16: Advanced scheduling
## 17: Best practices for developing apps
## 18: Extending Kubernetes

