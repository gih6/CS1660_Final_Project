FROM maven:3.6.0-jdk-11-slim
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
ENV GOOGLE_APPLICATION_CREDENTIALS=gleaming-baton-328900-726dee92d1b7.json
RUN mvn package
CMD ["mvn", "exec:java", "-D", "exec.mainClass=mainJFrame"]
