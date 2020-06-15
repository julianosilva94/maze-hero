FROM openjdk:14

COPY ./src /app
WORKDIR /app

RUN javac App.java

CMD java App cases/caso7_cohen.txt
