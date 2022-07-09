#Wordle Web Applciation

An angular Web Application recreating the popular Worlde game via an Angular Application connecting to a 
Spring microservice.

##Docker
To deploy build to a docker containar, run maven with the profile "docker" enabled

or: under the words-microservice
Build the image:
_docker build -t drewett/wordle-web ._
To run word microservice image:
_docker run --name wordle-web -p 8081:8081 drewett/wordle-web --network="wordle-network" --network-alias="wordle-web"_

## Running locally in develop and Node 
Add the environment variable for production or development:
set NODE_ENV=development

node app/wordle-app.js





