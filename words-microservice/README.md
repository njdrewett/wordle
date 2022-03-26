#Words microservice

A Spring-boot microservice to retrieve 4,5,6 or 7 letter words from a REST Controller.

##Docker
To deploy to separate docker contains, run maven with the profile "docker" enabled

or: under the words-microservice
Build the image:
_docker build -t drewett/words-microservice ._
To run word microservice image:
_docker run --name words-ms -p 8080:8080 drewett/words-microservice --network="wordle-network" --network-alias="words-ms"_






