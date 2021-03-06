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

## Video of Application Running

https://pitt-my.sharepoint.com/:v:/g/personal/gih6_pitt_edu/EcKBTAgcvatCrDd3PfCUngwBBNqQ9orLoX4BFmW4UDPqCg?e=Z7Xf3E 

If the above link doesn't work try: https://drive.google.com/file/d/1R6dOHo03BvVGftJQbvpLQw0tNyBPMGg1/view?usp=sharing


## Video of Code Walk Through 

https://pitt-my.sharepoint.com/:v:/g/personal/gih6_pitt_edu/EeDHVKxLvrJNorKbu2R7HS0B5RsqhkfpG0t5X_ioP2xM3Q?e=BewOkj

If this doesn't work then try this: 

https://drive.google.com/file/d/1pnKbBxZcNV3PbiqotIwYXSqYWRcFuPU1/view?usp=sharing 



Note:
Inverted Index was used to create inverted indicies
WordCount was used to create top N frequencies, when this count was downloaded into the actual program the program than sorted the list from max to least occurances.
This solution ran 2 hadoop fs jobs. 

## Siting Sources

1. Followed Steps found here to get the json key: https://cloud.google.com/docs/authentication/production 
2. Github Repo for gooogle credential API: https://github.com/googleapis/google-cloud-java 
3. Inverted Index Job took notes from this reference: https://github.com/imehrdadmahdavi/map-reduce-inverted-index/blob/master/InvertedIndex.java 
4. Inverted Index: v\https://www.fatalerrors.org/a/mapreduce-programming-document-inverted-index.html
5. Running Hadoop Job got from: https://cloud.google.com/dataproc/docs/samples/dataproc-submit-hadoop-fs-job 
6. Word Count: Taken from class notes 
7. Reference to Docker File: https://learnwell.medium.com/how-to-dockerize-a-java-gui-application-bce560abf62a 


Note: MainJFrame.java was used with NetBeans that auto generates a large portion of the code to create the GUI

