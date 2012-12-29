cd ..
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=256M
mvn jetty:run -Djetty.port=8080 
