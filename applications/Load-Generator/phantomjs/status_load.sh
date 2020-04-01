#!/bin/bash
echo "Finding processes for load scripts currently running..."
ps -ef | grep slow-query
sleep 1s
ps -ef | grep session
sleep 1s
ps -ef | grep request-error
sleep 1s
ps -ef | grep search
sleep 1s

