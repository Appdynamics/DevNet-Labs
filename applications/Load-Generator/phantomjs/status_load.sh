#!/bin/bash
echo "Finding processes for load scripts currently running..."
ps -ef | grep home-init
sleep 1s
ps -ef | grep slow-query
sleep 1s
ps -ef | grep session-0
sleep 1s
ps -ef | grep request-error
sleep 1s
ps -ef | grep search-0
sleep 1s
ps -ef | grep mem-leak-insurance
sleep 1s
