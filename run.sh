#!/usr/bin/env bash

./gradlew installDist
cd build/install/todo-backend-jodd
./bin/todo-backend-jodd
