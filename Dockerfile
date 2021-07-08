FROM openjdk:8
RUN apt update
RUN apt install curl
RUN curl -L -H "Accept: application/vnd.github.v3+json" -H "Authorization:token " https://api.github.com/repos/luca-coder/progetto_finale/actions/artifacts/73557497/zip --output NRegine.zip
RUN unzip NRegine.zip
CMD ["java", "-jar", "NRegine-1.0.jar"]