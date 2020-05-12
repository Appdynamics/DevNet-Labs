![Lab Icon](./assets/images/lab-icon.png) Monitor and troubleshoot Browser App issues - Part 2
=========================================================================

In this exercise you will need to do the following:

- Review the Browser Session you created
- Review the Pages & AJAX Requests Dashboard
- Review the Dashboard for a specific Base Page
- Troublshoot a Browser Snapshot


### **1.** Review the Browser Session you created

You can think of sessions as a time-based context to analyze a user's experience interacting with an application. By examining browser sessions, you can understand how your applications are performing and how users are interacting with them. This enables you to better manage and improve your application, whether that means modifying the UI or optimizing performance on the server side.

Navigate to the Sessions dashboard and find the browser session you created in the last exercise from navigating the pages of the web application. Follow the steps below.

**NOTE:** You may need to wait ten mintues after you hit the last page in the web application to see your browser session show up in the sessions list.

1. Click on the "Sessions" tab on the left menu
2. Check the "IP Address" in the Session Fields list
3. Find the session you created (find it by your IP Address)
4. Click on your session, then click the "View Details" button

![BRUM Dash 1](./assets/images/06-brum-dashboard-01.png)

<br>

Once you find and open the session you created, follow the steps below to explore the diffrent features of the session view.

**NOTE:** Your session may not have a "View Snapshot" link in any of the pages (as seen in step five).  You will find a session that has one to explore later in this exercise.

1. Click on the "Session Summary" link to view the summary data
2. When you click on a page listed on the left, you see the details of that page on the right
3. You can always see the full name of the page you have selected in the left list here
4. Click on a horizontal blue bar in the waterfall view to show the details of that item
5. Some pages may have a link to a correlated snapshot that was captured on the server side
6. Click on the configuration icon to change the columns shown in the pages list

You can read more about the Browser RUM Sessions [here](https://docs.appdynamics.com/display/latest/Browser+RUM+Sessions)

![BRUM Dash 2](./assets/images/06-brum-dashboard-02.png)

<br>

### **2.** Review the Pages & AJAX Requests Dashboard

Navigate to the Pages & AJAX Requests dashboard, review the options there, and open a specific Base Page dashboard by following the steps below.

1. Click on the "Pages & AJAX Requests" tab on the left menu
2. Explore the options on the toolbar
3. Click on the "localhost:8080/supercar-trader/car.do" page
4. Click on the "Details" button to open the Base Page dashboard

![BRUM Dash 3](./assets/images/06-brum-dashboard-03.png)

<br>

### **3.** Review the Dashboard for a specific Base Page

At the top of the Base Page dashboard you will see key performance indicators, End User Response Time, Load, Cache Hits, and Page Views with JS errors across the period selected in the timeframe dropdown from the upper-right side of the Controller UI. Cache Hits indicates a resource fetched from a cache, such as a CDN, rather than from the source.

In the Timing Breakdown section you will see a waterfall graph that displays the average times needed for each aspect of the page load process. For more information on what each of the metrics measures, hover over its name on the left. A popup appears with a definition. For more detailed information, see [Browser RUM Metrics](https://docs.appdynamics.com/display/latest/Browser+RUM+Metrics).

Review the details for the "localhost:8080/supercar-trader/car.do" Base Page by following the steps below.

1. Change the timeframe dropdown to "last 2 hours"
2. Explore the key performance indicators
3. Explore the metrics on the waterfall view
4. Use the vertical scroll bar to move down the page
5. Explore the graphs for all of the KPI Trends

![BRUM Dash 4](./assets/images/06-brum-dashboard-04.png)

![BRUM Dash 5](./assets/images/06-brum-dashboard-05.png)

<br>

### **2.** Troublshoot a Browser Snapshot

Navigate to the Browser Snapshots list dashboard and open a specific Browser Snapshot by following the steps below.

1. Click on the "Browser Snapshots" option
2. Click the "End User Response Time" column header twice to show the largest response times at the top
3. Click on a browser snapshot that has a gray or blue icon in the third column from the left
4. Click on the "Details" button to open the browser snapshot

![BRUM Dash 6](./assets/images/06-brum-dashboard-06.png)

<br>

Once you open the browser snapshot, review the details and find root cause for the large response time by following the steps below.

1. Review the waterfall view to understand where the response time was impacted
2. Notice the extended "Server Time" metric.  Hover over the label for "Server Time" to understand its meaning
3. Click on the server side transaction that was automatically captured and correlated to the browser snapshot
4. Click on the "View Details" button to open the associated server side snapshot

![BRUM Dash 7](./assets/images/06-brum-dashboard-07.png)

<br>

Once you open the correlated server side snapshot, use the steps below to pinpoint the root cause of the performance degredation.

1. You can see that the percentage of transaction time spent in the browser was minimal
2. The timing between the browser and the Web-Portal Tier represents the initial connection from the browser until the full response was returned
3. It's evedant that the JDBC call was taking the most time
4. Click on the "Drill Down" button to look at the code level view inside the Enquiry-Services Tier

![BRUM Dash 8](./assets/images/06-brum-dashboard-08.png)


<br>

Once you open the snapshot segment for the Enquiry-Services Tier, you can see that there were JDBC calls to the database that caused issues with the transaction.

1. Click on the "JDBC" link with the largest time to open the detail panel for the JDBC calls
2. The detail panel for the JDBC exit calls shows the specific query that took most of the time
3. You can see the full SQL statement along with the SQL parameter values


![BRUM Dash 9](./assets/images/06-brum-dashboard-09.png)

<br>

You have now completed this lab!

[Lab setup](lab-exercise-00.md) | [1](lab-exercise-01.md), [2](lab-exercise-02.md), [3](lab-exercise-03.md), [4](lab-exercise-04.md), [5](lab-exercise-05.md), 6 | [Back](lab-exercise-05.md) | Next
