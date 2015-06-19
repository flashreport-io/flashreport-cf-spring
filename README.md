# flashreport-cf-spring
Sample Cloud Foundry Spring app using a flashreport bound service

## Introduction

The purpose of this app is to showcase the use of the flashreport service in a CloudFoundry environment.

## Prerequisite

To successfully build and deploy this app, you need

- [Java 8 JDK] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven tool] (http://maven.apache.org/index.html)
- [CF cli] (http://docs.cloudfoundry.org/devguide/installcf/install-go-cli.html)

## Let's go!

1 Clone this repository and switch to its directory

1 Build and package the app

    mvn clean package
    
1 Login with CF cli (if necessary) and then switch to the org and space where you want to push the app

    cf login
    cf target -o YOUR-ORG -s YOUR-SPACE
    
1 Push the application

    cf push
    
1 Create a flashreport service using the trial plan

    cf create-service flashreport trial flashreport-trial
    
1 Bind the newly created service to your app

    cf bind-service flashreport-cf-spring flashreport-trial
    
1 Restage the app 

    cf restage flashreport-cf-spring
    
1 Open the url appearing at the end of the staging process in your favourite browser


You can now create and download your first report. 
Be aware that the trial plan only supports a limited number of reports per month.


## More information

Please consult our [documentation] (http://flashreport.io/docs.html) for additional information.



