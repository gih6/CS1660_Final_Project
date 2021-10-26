FROM maven:3.5.0-jdk-8 AS build
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
ENV GOOGLE_APPLICATION_CREDENTIALS=gleaming-baton-328900-726dee92d1b7.json
RUN mvn clean install
CMD ["mvn", "exec:java", "-D", "exec.mainClass=mainJFrame"]
