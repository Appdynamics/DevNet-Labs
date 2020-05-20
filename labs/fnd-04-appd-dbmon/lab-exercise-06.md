![Lab Icon](./assets/images/lab-icon.png) Monitor and troubleshoot Database issues - Part 1
=========================================================================

In this exercise you will need to do the following:

- Review the Overall Database and Server Performance Dashboard
- Review the Main Database Dashboard
- Review the Reports in the Database Activity Window


### **1.** Review the Overall Database and Server Performance Dashboard

The Overall Database and Server Performance Dashboard allows you to quickly see the health of each database at a glance.

1. Click on the "Filters" button to explore the options to filter by health, load, time in database or type
2. Click on the "Actions" button to export the data on this window in a .csv formatted file
3. Click on the "View Options" button to turn the spark charts on and off
4. Click on the "View" button to switch between the card and list view
5. Click on the "Sort" dropdown to see the sorting options
6. Click on the "Supercar-MySQL" link to drill into the main database dashboard

![DB Dash 1](./assets/images/06-db-dashboard-01.png)

<br>

### **2.** Review the Main Database Dashboard

The main database dashboard shows you key insights for the database including:

- The health of the server that is running the database
- The total number of calls during the specified time period
- The number of calls for any point in time
- The total time spent executing SQL statements during the specified time period
- The top ten query wait states
- The average number of connections

Explore the features of the dashboard described below.

1. Click on the colored health status circle to see details of the server health
   - Green = server is healthy
   - Yellow = server with warning-level violations
   - Red = server with critical-level violations
2. The database type or vendor will always be seen here
3. Observe the total time spent executing SQL statements during the specified time period
4. Observe the total number of executions during the specified time period
5. Hover over the time series on the chart to see the detail of the recorded metrics
   - Click on the orange circle at the top of the data point to view the time comparison report, which shows query run times and wait states 15 minutes before and 15 minutes after the selected time
6. Left-click and hold down your mouse button while dragging from left to right to highlight a spike seen in the chart
7. Click on the configuration button to exclude unwanted wait states from the top ten
8. Hover over the labels for each wait state to see a more detailed description
9.  Observe the average number of active connections actively running a query during the selected time period

![DB Dash 2](./assets/images/06-db-dashboard-02.png)

<br>

Explore the bottom half of the dashboard by following the steps below.

1. Scroll to the bottom of the dashboard using the scroll bar on the right
2. Observe the CPU metrics during the time period you have selected
3. Observe the Memory metrics during the time period you have selected
4. Observe the Disk IO metrics during the time period you have selected
5. Observe the Network IO metrics during the time period you have selected

You can read more about the Main Database dashboard [here](https://docs.appdynamics.com/display/latest/Database+Dashboard)


![DB Dash 3](./assets/images/06-db-dashboard-03.png)

<br>

### **3.** Review the Reports in the Database Activity Window

There are up to nine different reports available in Database Visibility on the Database Activity Window. The reports available depend on the database platform being monitored.  In this exercise we will review three of the most common reports.

- Wait State Report
- Top Activity Report
- Query Wait State Report


#### Wait State Report

This report displays time-series data on Wait Events (states) within the database. Each distinct wait is color-coded, and the Y-axis displays time in seconds. This report also displays data in a table and highlights the time spent in each wait state for each SQL statement.

The wait states consuming the most time may point to performance bottlenecks. For example, db file sequential reads may be caused by segment header contention on indexes or by disk contention.

![DB Dash 4](./assets/images/06-db-dashboard-04.png)

<br>

#### Top Activity Report

This report displays the top time in database SQL statements in a time-series view. This report also displays data in a table and highlights the time spent in the database for each of 10 top SQL statements.

Use this report to see which SQL statements are using the most database time. This helps to determine the impact of specific SQL statements on overall system performance allowing you to focus your tuning efforts on the statements that have the most impact on database performance.

![DB Dash 5](./assets/images/06-db-dashboard-05.png)

<br>

#### Query Wait State Report

This report displays the wait times for the top (10, 50, 100, 200) queries.  This report also displays data in a table and highlights the time each query is spending in different wait states.  Use the columns to sort the queries by the different wait states.

You can read more about the Reports in the Database Activity Window [here](https://docs.appdynamics.com/display/latest/Database+Activity+Window)

![DB Dash 6](./assets/images/06-db-dashboard-06.png)

<br>

[Lab setup](lab-exercise-00.md) | [1](lab-exercise-01.md), [2](lab-exercise-02.md), [3](lab-exercise-03.md), [4](lab-exercise-04.md), [5](lab-exercise-05.md), 6, [7](lab-exercise-07.md) | [Back](lab-exercise-05.md) | [Next](lab-exercise-07.md)
