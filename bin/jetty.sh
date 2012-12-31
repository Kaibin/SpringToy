cd ..
export MAVEN_OPTS="-Xmx1024m -Xms512m -XX:MaxPermSize=256m"
mvn -Djetty.port=8080 jetty:run  
