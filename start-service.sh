#!/usr/bin/env bash

docker build -t employeeapi:0.0.1-SNAPSHOT ./employeeapi
docker build -t reservationapi:0.0.1-SNAPSHOT ./reservationapi
docker build -t notificationapi:0.0.1-SNAPSHOT ./notificationapi

docker-compose up
