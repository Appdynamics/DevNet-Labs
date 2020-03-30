#!/bin/bash
nohup ./slow-query-01.sh &> slow-query-01.out &
sleep 3s
nohup ./slow-query-01.sh &> slow-query-02.out &
sleep 3s
nohup ./slow-query-01.sh &> slow-query-03.out &
sleep 3s
nohup ./slow-query-01.sh &> slow-query-04.out &
sleep 3s
nohup ./sessions-01.sh &> sessions-01.out &
sleep 3s
nohup ./sessions-01.sh &> sessions-02.out &
sleep 3s
nohup ./sessions-01.sh &> sessions-03.out &
sleep 3s
nohup ./sessions-01.sh &> sessions-04.out &
sleep 3s
nohup ./search-01.sh &> search-01.out &
sleep 3s
nohup ./request-error-01.sh &> request-error-01.out &
sleep 3s
