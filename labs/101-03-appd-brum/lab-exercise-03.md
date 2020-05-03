![Lab Icon](./assets/images/lab-icon.png) Create a browser application in the Controller
=========================================================================

In this exercise you will need to do the following:

- Access your AppDynamics Controller from your web browser
- Create the Browser Application in the Controller
- Configure the Browser Application

In the example URL below, substitute the IP Address or fully qualified domain name of your Controller VM. 

Example Controller URL for browser:

```
http://IP_OR_FQDN_OF_HOST:8090/controller
```

### **1.** Access the controller login screen from your web browser
You should see the login page of the Controller like the image below.

![Controller Login Screen](./assets/images/03-controller-login.png)

Use the case sensative credentials below to login:

- Username = admin 
- Password = welcome1

<br>

### **2.** Create the Browser Application in the Controller

Use the following steps to create your new browser application.

1. Click on the "User Experience" tab on the top menu
2. Click on the "Browser Apps" option under "User Experience"
3. Clcik on the "Create Browser App" button
4. Select the option to "Create an Application manually"
5. Type in the name = Supercar-Trader-Web
6. Click on the "OK" button

<br>

![BRUM Dash 1](./assets/images/03-brum-dashboard-01.png)

<br>

If you were not automatically taken to the "Browser App Dashboard" for the "Supercar-Trader-Web" application, you can get there by:

1. Clicking on the "Supercar-Trader-Web" application in the list
2. Click on the "Details" button

![BRUM Dash 2](./assets/images/03-brum-dashboard-02.png)

<br>

You should now see the "Browser App Dashboard" for the "Supercar-Trader-Web" application.  

1. Click on the "Configuration" tab on the left menu

![BRUM Dash 3](./assets/images/03-brum-dashboard-03.png)

<br>

You should now see two main options for configuration, "Configure JavaScript Agent" and "Instrumentation".

1. Click on the "Instrumentation" option

![BRUM Dash 4](./assets/images/03-brum-dashboard-04.png)

<br>

Change the default configuration to have the IP Address stored along with the data captured by the browser monitoring agent by following the steps below.

1. Click on the "Settings" option
2. Use the scroll bar on the right to scroll to the bottom of the screen
3. Check the "Store IP Address" check box
4. Click the "Save" button

You can read more about configuring the Controller UI for Browser RUM [here](https://docs.appdynamics.com/display/latest/Configure+the+Controller+UI+for+Browser+RUM)

![BRUM Dash 5](./assets/images/03-brum-dashboard-05.png)

<br>


[Lab setup](../101-00-appd-vm-setup/lab-exercise-01.md) | [1](lab-exercise-01.md), [2](lab-exercise-02.md), 3, [4](lab-exercise-04.md), [5](lab-exercise-05.md), [6](lab-exercise-06.md), [7](lab-exercise-07.md) | [Back](lab-exercise-02.md) | [Next](lab-exercise-04.md)
