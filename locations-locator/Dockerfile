FROM eclipse-temurin:17

MAINTAINER Joerg Reichert

ENV APP_NAME locations-locator
ENV APP_VERSION 1.0.0-SNAPSHOT
ENV USER_NAME $APP_NAME
ENV USER_HOME /home/$USER_NAME

RUN useradd -ms /bin/bash $USER_NAME

WORKDIR $USER_HOME

COPY build/libs/$APP_NAME-$APP_VERSION.jar ./app.jar

RUN chown -R $USER_NAME $USER_HOME

USER $USER_NAME

VOLUME $USER_HOME/data
VOLUME /tmp

EXPOSE 8082

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
