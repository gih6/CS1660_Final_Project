# CS1660_Final_Project Option 2

## Running Application on Terminal

Source Code can be found: src/main/java/mainJFrame.java


Link to Docker Container: https://hub.docker.com/repository/docker/gih6/cs1660project 

## Docker File Used to Create Application

```
docker build -t gih6/cs1660project .
```

```
docker run --rm -it -e DISPLAY=IPADDRESS:0.0  gih6/cs1660project
```
Please note that this only runs if XLaunch is configured correctly on the local machine. This includes reconfigure it for each new IP address and saving the config file in the same directory you run the docker container.

Below is an image showing the application running on XLaunch: 

![image](https://user-images.githubusercontent.com/54678622/138188212-8fd4b4b1-c10a-4a7c-9021-20b24e8aefe9.png)

Can See From Image that the GCP Credentials are read correctly: 

![image](https://user-images.githubusercontent.com/54678622/138188874-a769617f-142b-468e-a556-bfa035b77d17.png)

![image](https://user-images.githubusercontent.com/54678622/138188919-11a54ec4-54c5-405c-b5ad-8c6642f807a3.png)


## Steps to Connect to GCP 

1. Followed Steps found here to get the json key: https://cloud.google.com/docs/authentication/production 
2. Used this as a resource as well: https://www.tabnine.com/code/java/classes/com.google.auth.oauth2.GoogleCredentials 
3. Github Repo for gooogle credential API: https://github.com/googleapis/google-cloud-java 

Reference to Docker File: https://learnwell.medium.com/how-to-dockerize-a-java-gui-application-bce560abf62a 

Note: MainJFrame.java was used with NetBeans that auto generates a large portion of the code to create the GUI

Can see in code would throw an error if cannot connect to correct credentials. Note the JSON file itself is still locally stored on my computer.

