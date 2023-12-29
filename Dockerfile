FROM openjdk:11

COPY . .

RUN wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
RUN tar -xvf apache-maven-3.6.3-bin.tar.gz
RUN mv apache-maven-3.6.3 /opt/

ENV MAVEN_HOME /opt/apache-maven-3.6.3
ENV PATH="${MAVEN_HOME}/bin:${PATH}"

RUN mvn -version

CMD mvn test -Dsuite=api