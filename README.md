# Introduction #
The Project is split into 2 essential parts. The Frontend and the Backend.

# Click here to see the project in action #
http://projekt03-env-frontend.pyy6ipv83s.us-east-2.elasticbeanstalk.com/#

# Prerequesites to run the full project on your local machine #
In order to run this project you need a couple of things:
- Java JDK
- Maven
- NodeJS
- npm angular package

# Getting started #
clone this repository to get started.

# How to get the backend to run on localhost. #
1. cd into /projekt03/
2. run "mvn spring-boot:run" in the command line
3. The backend will now be served in your browser on localhost:8080

# How to get the frontend to run on localhost. #
1. cd into /projekt03/src/main/frontend
2. run "ng serve" in the command line
3. The frontend will now be served in your browser on localhost:4200

# Get both to communicate #
Both should now be automaticly communicating with each other. 
If this for some reason does not work check your environment settings in /projekt03/src/main/frontend/environment.ts and change it to fit your needs