FROM princegupta/devimage

ADD ./target/stock-service.jar /app/
ADD startup.sh /app/

CMD ["sh", "/app/startup.sh"]

EXPOSE 8097
