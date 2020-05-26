![Lab Icon](./assets/images/lab-icon.png) Monitor and troubleshoot Database issues - Part 2
=========================================================================

In this exercise you will need to do the following:

- Review the Queries Dashboard
- Review the details of an expensive query
- Troublshoot an expensive query


### **1.** Review the Queries Dashboard

The Queries window displays the SQL statements and stored procedures that consume the most time in the database. You can compare the query weights to other metrics such as SQL wait times to determine SQL that requires tuning.

1. Click on the "Queries" tab to view the queries window
2. Click on the "Top Queries" dropdown to display the top 5, 10, 100 or 200 queries
3. Click "Filter by Wait States" button to choose wait states to filter the Query list by
4. Check the "Group Similar" box to group together queries with the same syntax
5. Click on the query that shows the largest "Weight (%)" used
6. Click on the "View Query Details" button to drill into the query details

You can read more about the Database Queries dashboard [here](https://docs.appdynamics.com/display/latest/Database+Queries+Window)

![DB Dash 1](assets/images/07-db-dashboard-01.png)

<br>

### **2.** Review the details of an expensive query

Once you have identified the statements on the Database Queries window that are spending the most amount of time in the database, you can dig down deeper for details that can help you tune those SQL statements. The database instance Query Details window displays details about the query selected on the Database Queries window.


1. Resource consumption over time shows the amount of time the query spent in the database using resources, the number of executions, and the amount of CPU time consumed.
2. Wait states are the activities that contribute to the time it takes the database to service the selected SQL statement. The wait states consuming the most time may point to performance bottlenecks.
3. Components Executing Similar Queries displays the Nodes that execute queries similar to this query.
4. Business Transactions Executing Similar Queries displays the Java business transactions that execute queries similar to this query.

![DB Dash 2](assets/images/07-db-dashboard-02.png)

<br>

1. Use the outer scroll bar on the right to scroll down.
2. Clients shows the machines that executed the selected SQL statement and the percentage of the total time required to execute the statement performed by each machine.
3. Query Active in Database shows the schemas that have been accessed by this SQL.
4. Users shows the users that executed this query.
5. Query Hashcode shows the unique ID for the query that allows the database server to more quickly locate this SQL statement in the cache.
6. Query shows you the entire syntax of the selected SQL statement. You can click the pencil icon in the top right corner of the Query card to edit the query name so that it is easy to identify.
7. Click on the "Execution Plan" option to see the query execution plan window.

You can read more about the Database Query Details dashboard [here](https://docs.appdynamics.com/display/latest/Database+Query+Details+Window)

![DB Dash 3](assets/images/07-db-dashboard-03.png)

<br>

### **2.** Troublshoot an expensive query

<br>


<br>



[Lab setup](lab-exercise-00.md) | [1](lab-exercise-01.md), [2](lab-exercise-02.md), [3](lab-exercise-03.md), [4](lab-exercise-04.md), [5](lab-exercise-05.md), [6](lab-exercise-06.md), 7 | [Back](lab-exercise-06.md) | Next
