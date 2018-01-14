#!/usr/bin/env bash

./gradlew installDist
cd build/install/
cd $(ls -d */|head -n 1)
./bin/todo-backend-jodd
