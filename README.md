# First And Thirds
Event Management application built with microservies using Quarkus

The application is developed to manage Events that are created by multiple Teams, where Users can be be members of multiple Teams.

## Personas
**Admin :** This is the over all admin in the application, he has full authority and mainly he can create Teams and assign Team Leads  
**Team Lead :** The team lead is has full authority over the Team that he as assigned to, he is responsible to:
  - Create Events.  
  - Change Event Status.
  - Change User Role in the Event.  
  
**User :** Users are the event participants and Team members, any User can:
  - Un/Subscribe to a Team.
  - Register to Event.
  - Change his registeration status.

## Microservices
We have 3 backend microservices built to fulfil those needs and each one has it's own separate DB Schema.  
 1- User-API  
 2- Team-API  
 3- Event-API  
 
 ## Framework
 I selected Quarkus to write the main backend services known for it's fast startup as those microservices are intended to be used as Serverless applications where they will be triggered with Events or normal requests and we need to have a fast startup application that can be spin up quickly by Kubernetes/Openshift.
 
 ## Database
 I am using MySQL/MariaDB, currently in the dev mode, I am using JPA create-drop mode to re-build the schema in addition to some initial data load scripts.
 FlyWay integration is in the roadmap.
 
 ## Prereqs
 1- Create your own MySQL/MariaDB and create 3 Schemas (All can run on the same DB Server)  
 2- Update application.properties file with your DB connection for each service  
 
 ## Roadmap
 - Create FrontEnd ( Mobile version would be prefered )   
 - Add Swagger/OpenAPI  
 - Add Kafka Event Integration  
 - Add Identity Provider Integration  
 - Add Security Certificates  
 - Add Tekton Pipelines Resources  
 - Add Kubernetes resources to the repo  
 
 ## Contribution 
 Contribution is so much welcome in either of the roadmap points or if you have any other suggestion.  
 UI/UX is a needed talent too, so you can contribute with your prototypes.  
 Open an issue with your suggestion.  
 For bugs and code contribution, fork the repo and send a PR.  
