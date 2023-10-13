# dog_walker
!NB: u need to create your own application.properties
file for the app to work. it should look sth like: # level can be TRACE, DEBUG, INFO, WARN, ERROR, FATAL, or OFF:
logging.level.root = WARN
logging.level.nl.hva = INFO
logging.level.sql = INFO
logging.level.org.springframework=INFO
logging.level.nl.hva.dogwalker=DEBUG

#server.port=8080
# See http://logback.qos.ch/manual/layouts.html for layout and formatting options:
logging.pattern.console=%d{MM-dd HH:mm} %-5level [%-25logger{0}] %msg\n

spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.sql.init.mode=never


spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=hvacrawler2023@gmail.com
spring.mail.password=your_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.trust=*


