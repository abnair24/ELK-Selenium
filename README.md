# ELK-Selenium

Running Webdriver tests and pushing the results to Elastic search and Kibana.

1.  ELKListener class for configuring the testresults to send to ES

2.  Docker-compose up to bring the containers on localhost 

3.  Trigger the tests 

# Influxdb - Grafana
1. Run docker-compose -f docker-compose-TIG.yml up -d
2. Verify influxdb and grafana are available on localhost:8086 and localhost:3000
3. GraphanaListener for configuring the testresults to send to InfluxDB
