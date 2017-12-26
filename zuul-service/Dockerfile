FROM princegupta/devimage

ADD ./target/zuul-service.jar /app/
ADD startup.sh /app/

CMD ["sh", "/app/startup.sh"]

EXPOSE 8101
