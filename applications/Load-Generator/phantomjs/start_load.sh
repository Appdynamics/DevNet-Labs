#!/bin/bash

echo "Cleaning up artifacts from previous load..."
rm -f -v *.out
sleep 1s
echo "Starting slow-query-01"
nohup ./slow-query-01.sh &> slow-query-01.out &
sleep 2s
echo "Starting slow-query-02"
nohup ./slow-query-01.sh &> slow-query-02.out &
sleep 2s
echo "Starting slow-query-03"
nohup ./slow-query-01.sh &> slow-query-03.out &
sleep 2s
echo "Starting slow-query-04"
nohup ./slow-query-01.sh &> slow-query-04.out &
sleep 2s
echo "Starting sessions-01"
nohup ./sessions-01.sh &> sessions-01.out &
sleep 2s
echo "Starting sessions-02"
nohup ./sessions-01.sh &> sessions-02.out &
sleep 2s
echo "Starting sessions-03"
nohup ./sessions-01.sh &> sessions-03.out &
sleep 2s
echo "Starting sessions-04"
nohup ./sessions-01.sh &> sessions-04.out &
sleep 2s
echo "Starting search-01"
nohup ./search-01.sh &> search-01.out &
sleep 2s
echo "Starting request-error-01"
nohup ./request-error-01.sh &> request-error-01.out &
sleep 2s
echo "Finished starting load generator scripts"