# CS1660_Final_Project Option 2

## Running Application on Terminal

Source Code can be found: src/main/java/mainJFrame.java


Link to Docker Container: https://hub.docker.com/r/gih6/cs1660new

## Docker File Used to Create Application

```
docker build -t gih6/cs1660project .
```
## Running The Application 

```
docker run --rm -it -e DISPLAY=IPADDRESS:0.0  gih6/cs1660new
```
Please note that this only runs if XLaunch is configured correctly on the local machine. This includes reconfigure it for each new IP address and saving the config file in the same directory you run the docker container.

Below is an image showing the application running on XLaunch: 

![image](https://user-images.githubusercontent.com/54678622/138188212-8fd4b4b1-c10a-4a7c-9021-20b24e8aefe9.png)

## Connecting to GCP

The Connection to GCP will be done by using: com.google.auth.oauth2.GoogleCredentials

This is a package listed from the GCP website and is available in Maven Central Repository. 

To Use the Package I had to update the pom.xml file. 

In the docker file the gloabl varaible GOOGLE_APPLICATION_CREDENTIALS path set to JSON file.

The line in the mainJFrame.java attempts to get the Google Credentials: 

```
 credential = GoogleCredentials.getApplicationDefault();
 ```

Can See From Image that when the application is run the GCP Credentials are read correctly and no error is thrown: 

![image](https://user-images.githubusercontent.com/54678622/138188874-a769617f-142b-468e-a556-bfa035b77d17.png)

## Video of Working

## Code Walk Through 


## Siting Sources

1. Followed Steps found here to get the json key: https://cloud.google.com/docs/authentication/production 
2. Github Repo for gooogle credential API: https://github.com/googleapis/google-cloud-java 
3. Inverted Index Job took notes from this reference: https://github.com/imehrdadmahdavi/map-reduce-inverted-index/blob/master/InvertedIndex.java 
4. Running Hadoop Job got from: https://cloud.google.com/dataproc/docs/samples/dataproc-submit-hadoop-fs-job 
5. Word Count: Taken from class notes 
6. Reference to Docker File: https://learnwell.medium.com/how-to-dockerize-a-java-gui-application-bce560abf62a 


Note: MainJFrame.java was used with NetBeans that auto generates a large portion of the code to create the GUI

