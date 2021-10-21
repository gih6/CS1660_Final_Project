# CS1660_Final_Project

## Running Application on Terminal

Source Code can be found: src/main/java/mainJFrame.java

Image of it Running: 


Link to Docker Container: https://hub.docker.com/repository/docker/gih6/cs1660project 

## Docker File Used to Create Application

```
docker build -t gih6/cs1660project .
```

```
docker run --rm -it -e DISPLAY=IPADDRESS:0.0 -e GOOGLE_APPLICATION_CREDENTIALS=[FILE.JSON] gih6/cs1660project
```
Please note that this only runs if XLaunch is configured correctly on the local machine. This includes reconfigure it for each new IP address and saving the config file in the same directory you run the docker container.

Below is an image showing the application running on XLaunch: 

![image](https://user-images.githubusercontent.com/54678622/138188212-8fd4b4b1-c10a-4a7c-9021-20b24e8aefe9.png)

Can See From Image that the GCP Credentials are read correctly. 


## Steps to Connect to GCP 

1. Followed Steps found here to get the json key: https://cloud.google.com/docs/authentication/production 

Can see in code would throw an error if cannot connect to correct credentials. Note the JSON file itself is still locally stored on my computer.

