#!/bin/bash

service docker start
sleep 10
docker-compose up
docker-compose stop