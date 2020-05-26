![Lab Icon](assets/images/lab-icon.png) Check the Learning Lab prerequisites
=========================================================================

In this exercise you will complete the following tasks:

- Access your AppDynamics Controller from your web browser.
- Verify transaction load to the application.
- Restart the application and transaction load if needed.

The Controller can be accessed at a URL similar to the following example. Substitute the IP Address or fully qualified domain name of your Controller VM.

Example Controller URL for browser:

```
http://IP_OR_FQDN_OF_HOST:8090/controller
```

Access the controller login screen from your web browser.

![Controller Login Screen](assets/images/02-controller-login.png)

Use the following case-sensitive credentials to login:
- Username: `admin`
- Password: `welcome1`

## Verify transaction load to the application

Check the application flow map:

1. Select the **last 1 hour** time frame.
2. Verify you see the five different Tiers on the flow map.
3. Verify there has been consistent load over the last 1 hour.


![Verify Load 1](assets/images/02-verify-app-load-01.png)

Check the list of business transactions:

1. Click on the **Business Transactions** option on the left menu.
2. Verify you see the eleven business transactions seen below.
3. Verify that they have some number of calls during the last hour.

> *Note:* If you don't see the **Calls** column, you can click on the **View Options** toolbar button to show that column.

![Verify Load 2](assets/images/02-verify-app-load-02.png)

Check the agent status for the Nodes:

1. Click on the **Tiers & Nodes** option on the left menu
2. Click on the **Grid View** button
3. Verify that the **App Agent Status** for each Node is greater than 90% during the last hour

![Verify Load 3](assets/images/02-verify-app-load-03.png)

## Restart the application and transaction load if needed

If any of the checks you performed in the previous steps could not be verified, SSH into your **Application VM** and follow these steps to restart the application and transaction load.

1. Use the following command to stop the running instance of Apache Tomcat.

    ```
    sudo systemctl stop apache-tomcat-7.service
    ```

2. Wait for one minute then use the following command to start Apache Tomcat.

    ```
    sudo systemctl start apache-tomcat-7.service
    ```

3. Wait for two minutes and use the following command to ensure Apache Tomcat is running on port 8080.

    ```
    sudo netstat -tulpn | grep LISTEN
    ```

You should see output similar to the following image showing that port 8080 is in use by Apache Tomcat.

![Restart App 1](assets/images/02-restart-app-and-load-01.png)

Use the following commands to stop the load generation for the application.

```
su centos

cd /opt/appdynamics/DevNet-Labs/applications/Load-Generator/phantomjs

./stop_load.sh
```

You should see output similar to the following image.

![Restart App 2](assets/images/02-restart-app-and-load-02.png)


Use the following commands to start the load generation for the application.

```
cd /opt/appdynamics/DevNet-Labs/applications/Load-Generator/phantomjs

./start_load.sh
```

You should see output similar to the following image.

![Restart App 3](assets/images/02-restart-app-and-load-03.png)

<br>

[Lab setup](lab-exercise-00.md) | [1](lab-exercise-01.md), 2, [3](lab-exercise-03.md), [4](lab-exercise-04.md), [5](lab-exercise-05.md), [6](lab-exercise-06.md), [7](lab-exercise-07.md) | [Back](lab-exercise-01.md) | [Next](lab-exercise-03.md)
