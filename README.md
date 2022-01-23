# ELK-DEMO
Trying out implementing ELK to commit log from spring boot to kibana

# ELK setup
ELK stack will be set up using docker compose

## Logstash configuration
- Logstash's configuration is in `pipeline` folder
- The log file folder may required to be changed in the `-volume` part in the `docker-compose.yml` file