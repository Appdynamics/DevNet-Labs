#!/bin/bash
echo "Finding processes for load scripts..."
ps -ef | grep slow-query
sleep 1s
ps -ef | grep session
sleep 1s
ps -ef | grep request-error
sleep 1s
ps -ef | grep search
sleep 1s
echo "Stopping processes for load scripts..."
sudo pkill -f slow-query-01
sleep 1s
sudo pkill -f session
sleep 1s
sudo pkill -f request-error-01
sleep 1s
sudo pkill -f search-
sleep 1s
echo "Checking for processes after stopping load..."
ps -ef | grep slow-query
sleep 1s
ps -ef | grep session
sleep 1s
ps -ef | grep request-error
sleep 1s
ps -ef | grep search
