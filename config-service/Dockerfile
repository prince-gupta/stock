FROM princegupta/devimage

ADD ./target/config-service.jar /app/
ADD startup.sh /app/

CMD ["sh", "/app/startup.sh"]

EXPOSE 8102
