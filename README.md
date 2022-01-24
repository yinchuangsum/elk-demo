# ELK-DEMO
Trying out implementing ELK to commit log from spring boot to kibana

# Concept
1. Spring boot application will store logs in a file
2. Logstash will look at the file and push new logs to elastic search

## Logstash configuration
- Logstash's configuration is in `pipeline` folder
- The log file folder may required to be changed in the `-volume` part in the `docker-compose.yml` file

# Setup
1. Configure the `src/main/resource/logback.xml` to store log file in desired location
2. In `docker-compose.yml`, the log file directory location are required to be configured
3. Run `docker-compose up` to start the application

# Starting Kibana
- go to `http://localhost:5601` after starting up the docker compose file
