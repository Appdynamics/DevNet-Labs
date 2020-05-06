#!/bin/bash
echo "Finding processes for load scripts..."
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
echo "Stopping processes for load scripts..."
sudo pkill -f phantomjs
sleep 1s
sudo pkill -f home-init-01
sleep 1s
sudo pkill -f slow-query-01
sleep 1s
sudo pkill -f session-0
sleep 1s
sudo pkill -f request-error-01
sleep 1s
sudo pkill -f search-0
sleep 1s
sudo pkill -f mem-leak-insurance
sleep 1s
echo "Checking for processes after stopping load..."
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
