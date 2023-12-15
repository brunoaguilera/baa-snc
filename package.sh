./mvnw clean install -Dspring.config.location=file:///opt/baa-snc/tomcat/conf/application.properties -Dext.prop.dir=/opt/baa-snc/tomcat/conf -DskipTests=true

/etc/init.d/tomcat9-baa-snc stop
sleep 10
rm -fr /opt/baa-snc/tomcat/latest/webapps/mibi*


echo ""
echo ""
ls -ltrh /opt/baa-snc/tomcat/latest/webapps/

echo ""
echo ""
ps -ax | grep -i tomcat
cp target/monitoring-0.0.1.war /opt/baa-snc/tomcat/latest/webapps/baa-snc.war

echo ""
echo ""
ls -ltrh /opt/baa-snc/tomcat/latest/webapps/
/etc/init.d/tomcat9-baa-snc start
sleep 10

echo ""
echo ""
ps -ax | grep -i tomcat

