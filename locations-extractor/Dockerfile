FROM develar/java

MAINTAINER Joerg Reichert

ENV APP_NAME locations-extractor
ENV APP_VERSION 1.0.0-SNAPSHOT
ENV USER_NAME $APP_NAME
ENV USER_HOME /home/$USER_NAME

RUN adduser -S $USER_NAME

WORKDIR $USER_HOME

COPY dewac_175m_600.crf.ser.gz .
COPY build/libs/$APP_NAME-$APP_VERSION.jar ./app.jar

RUN chown -R $USER_NAME $USER_HOME

USER $USER_NAME

VOLUME $USER_HOME/data
VOLUME /tmp

EXPOSE 8081

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
