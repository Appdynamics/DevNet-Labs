
## <ins>Overview:</ins>

The AppDynamics Fundamentals reservation based Lab provides a developer with an environment to deploy several AppDynamic's agents along with a sample application to understand how AppDynamics provides end-to-end application monitoring and allows you to quickly resolve performance issues before they become business problems.


## <ins>Lab Information:</ins>

The AppDynamics Fundamentals Lab environment consists of two CentOS Linux based VM's.  The first VM hosts the AppDynamics Controller and will be referred to from this point on as the "Controller VM".  The second VM hosts the Supercar-Trader sample application used in the Lab.  The second VM will be the host where you will install the AppDynamics agents and will be referred to from this point on as the "Application VM". A developer can reserve this lab and remotely access to install the AppDynamics agents with the sample application.

After installing the AppDynamics agents with the sample application, you will access the AppDynamics Controller to monitor the application performance and troubleshoot issues in the application that are impacting end user experience.

The topology diagram for the Lab environment can be found [here](https://github.com/Appdynamics/DevNet-Labs/blob/master/labs/lab-sandbox-topology.png).

## <ins>Access Details:</ins>

After your AppDynamics Fundamentals Lab reservation begins, you will receive software VPN information and credentials via email.  After the VPN connection is established, you may access the devices and endpoints in your lab as follows:

- Controller VM
  - Main Controller UI - http://10.10.20.2:8090/controller/
    - credentials [admin/welcome1]
  - Admin Controller UI - http://10.10.20.2:8090/controller/admin.jsp
    - credentials [welcome1]
- Application VM
  - SSH - ```ssh centos@10.10.20.10```
    - credentials [centos/cisco123]
  - Tomcat Manager - http://10.10.20.10:8080/manager/html
    - credentials [admin/welcome1]
  - Sample App - http://10.10.20.10:8080/Supercar-Trader/home.do


## <ins>Learning Track Labs:</ins>

The [AppDynamics Fundamentals](https://developer.cisco.com/learning/tracks/appd-fundamentals) learning track consists of the following Labs.

- [Java Application Monitoring Fundamentals](https://developer.cisco.com/learning/lab/fnd-01-appd-apm-java/step/1)
- [Server Visibility Monitoring Fundamentals](https://developer.cisco.com/learning/lab/fnd-02-appd-svm/step/1)
- [Browser Real User Monitoring Fundamentals](https://developer.cisco.com/learning/lab/fnd-03-appd-brum/step/1)
- [Database Visibility Monitoring Fundamentals](https://developer.cisco.com/learning/lab/fnd-04-appd-dbmon/step/1)

You will need to complete the [Java Application Monitoring Fundamentals](https://developer.cisco.com/learning/lab/fnd-01-appd-apm-java/step/1) lab before you start the other labs in the learning track.  You will need to use the same two VM instances for each lab within the learning track as they build on one another. 

## <ins>Additional Information:</ins>

 - [AppDynamics Concepts](https://docs.appdynamics.com/display/latest/AppDynamics+Concepts)
 - [Application Monitoring](https://docs.appdynamics.com/display/latest/Application+Monitoring)
 - [Server Visibility](https://docs.appdynamics.com/display/latest/Server+Visibility)
 - [End User Monitoring](https://docs.appdynamics.com/display/latest/End+User+Monitoring)
 - [Database Visibility](https://docs.appdynamics.com/display/latest/Database+Visibility)
 - [Sandbox Support](https://communities.cisco.com/community/developer/sandbox)

