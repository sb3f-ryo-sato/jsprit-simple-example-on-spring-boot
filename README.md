# jsprit-simple-example-on-spring-boot

本リポジトリーは, [jsprit の Simple-Example](https://github.com/graphhopper/jsprit/blob/master/docs/Simple-Example.md)
を, Spring Boot で動作するようにしたものです。


## 動作環境

```shell
$ java -version
openjdk version "11.0.5" 2019-10-15 LTS
OpenJDK Runtime Environment Corretto-11.0.5.10.1 (build 11.0.5+10-LTS)
OpenJDK 64-Bit Server VM Corretto-11.0.5.10.1 (build 11.0.5+10-LTS, mixed mode)
```

```shell
$ javac -version
javac 11.0.5
```

```shell
$ mvn --version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /home/vagrant/.local/apache-maven-3.6.3
Java version: 11.0.5, vendor: Amazon.com Inc., runtime: /home/vagrant/.local/amazon-corretto-11.0.5.10.1-linux-x64
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "3.10.0-1062.4.3.el7.x86_64", arch: "amd64", family: "unix"
```


## サービス起動

```shell
$ mvn clean spring-boot:run
[INFO] Scanning for projects...
[INFO]
[INFO] -----------------------< com.example:myproject >------------------------
[INFO] Building myproject 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ myproject ---
[INFO]
[INFO] >>> spring-boot-maven-plugin:2.2.1.RELEASE:run (default-cli) > test-compile @ myproject >>>
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ myproject ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/vagrant/git/jsprit-simple-example-on-spring-boot/src/main/resources
[INFO] skip non existing resourceDirectory /home/vagrant/git/jsprit-simple-example-on-spring-boot/src/main/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ myproject ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/vagrant/git/jsprit-simple-example-on-spring-boot/target/classes
[INFO]
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ myproject ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/vagrant/git/jsprit-simple-example-on-spring-boot/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ myproject ---
[INFO] No sources to compile
[INFO]
[INFO] <<< spring-boot-maven-plugin:2.2.1.RELEASE:run (default-cli) < test-compile @ myproject <<<
[INFO]
[INFO]
[INFO] --- spring-boot-maven-plugin:2.2.1.RELEASE:run (default-cli) @ myproject ---
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.1.RELEASE)

2019-12-05 14:44:33.262  INFO 2497 --- [           main] Example                                  : Starting Example on localhost.localdomain with PID 2497 (/home/vagrant/git/jsprit-simple-example-on-spring-boot/target/classes started by vagrant in /home/vagrant/git/jsprit-simple-example-on-spring-boot)
2019-12-05 14:44:33.264  INFO 2497 --- [           main] Example                                  : No active profile set, falling back to default profiles: default
2019-12-05 14:44:34.129  INFO 2497 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2019-12-05 14:44:34.139  INFO 2497 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2019-12-05 14:44:34.139  INFO 2497 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.27]
2019-12-05 14:44:34.208  INFO 2497 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2019-12-05 14:44:34.208  INFO 2497 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 914 ms
2019-12-05 14:44:34.620  INFO 2497 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2019-12-05 14:44:34.773  INFO 2497 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2019-12-05 14:44:34.777  INFO 2497 --- [           main] Example                                  : Started Example in 1.932 seconds (JVM running for 2.446)
```


## 実行

サービス起動後, `curl localhost:8080` と実行すると, XML ファイルが返却されます。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<problem xmlns="http://www.w3schools.com"
	 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.w3schools.com vrp_xml_schema.xsd">
  <problemType>
    <fleetSize>INFINITE</fleetSize>
  </problemType>
  <vehicles>
    <vehicle>
      <id>vehicle</id>
      <typeId>vehicleType</typeId>
      <startLocation>
        <id>[x=10.0][y=10.0]</id>
        <coord x="10.0" y="10.0"/>
      </startLocation>
      <endLocation>
        <id>[x=10.0][y=10.0]</id>
        <coord x="10.0" y="10.0"/>
      </endLocation>
      <timeSchedule>
        <start>0.0</start>
        <end>1.7976931348623157E308</end>
      </timeSchedule>
      <returnToDepot>true</returnToDepot>
    </vehicle>
  </vehicles>
  <vehicleTypes>
    <type>
      <id>vehicleType</id>
      <capacity-dimensions>
        <dimension index="0">2</dimension>
      </capacity-dimensions>
      <costs>
        <fixed>0.0</fixed>
        <distance>1.0</distance>
        <time>0.0</time>
        <service>0.0</service>
        <wait>0.0</wait>
      </costs>
    </type>
  </vehicleTypes>
  <services>
    <service id="1" type="service">
      <location>
        <id>[x=5.0][y=7.0]</id>
        <coord x="5.0" y="7.0"/>
      </location>
      <capacity-dimensions>
        <dimension index="0">1</dimension>
      </capacity-dimensions>
      <duration>0.0</duration>
      <timeWindows>
        <timeWindow>
          <start>0.0</start>
          <end>1.7976931348623157E308</end>
        </timeWindow>
      </timeWindows>
    </service>
    <service id="2" type="service">
      <location>
        <id>[x=5.0][y=13.0]</id>
        <coord x="5.0" y="13.0"/>
      </location>
      <capacity-dimensions>
        <dimension index="0">1</dimension>
      </capacity-dimensions>
      <duration>0.0</duration>
      <timeWindows>
        <timeWindow>
          <start>0.0</start>
          <end>1.7976931348623157E308</end>
        </timeWindow>
      </timeWindows>
    </service>
    <service id="3" type="service">
      <location>
        <id>[x=15.0][y=7.0]</id>
        <coord x="15.0" y="7.0"/>
      </location>
      <capacity-dimensions>
        <dimension index="0">1</dimension>
      </capacity-dimensions>
      <duration>0.0</duration>
      <timeWindows>
        <timeWindow>
          <start>0.0</start>
          <end>1.7976931348623157E308</end>
        </timeWindow>
      </timeWindows>
    </service>
    <service id="4" type="service">
      <location>
        <id>[x=15.0][y=13.0]</id>
        <coord x="15.0" y="13.0"/>
      </location>
      <capacity-dimensions>
        <dimension index="0">1</dimension>
      </capacity-dimensions>
      <duration>0.0</duration>
      <timeWindows>
        <timeWindow>
          <start>0.0</start>
          <end>1.7976931348623157E308</end>
        </timeWindow>
      </timeWindows>
    </service>
  </services>
  <solutions>
    <solution>
      <cost>35.3238075793812</cost>
      <routes>
        <route>
          <driverId>noDriver</driverId>
          <vehicleId>vehicle</vehicleId>
          <start>0.0</start>
          <act type="service">
            <serviceId>2</serviceId>
            <arrTime>5.830951894845301</arrTime>
            <endTime>5.830951894845301</endTime>
          </act>
          <act type="service">
            <serviceId>1</serviceId>
            <arrTime>11.8309518948453</arrTime>
            <endTime>11.8309518948453</endTime>
          </act>
          <end>17.6619037896906</end>
        </route>
        <route>
          <driverId>noDriver</driverId>
          <vehicleId>vehicle</vehicleId>
          <start>0.0</start>
          <act type="service">
            <serviceId>4</serviceId>
            <arrTime>5.830951894845301</arrTime>
            <endTime>5.830951894845301</endTime>
          </act>
          <act type="service">
            <serviceId>3</serviceId>
            <arrTime>11.8309518948453</arrTime>
            <endTime>11.8309518948453</endTime>
          </act>
          <end>17.6619037896906</end>
        </route>
      </routes>
    </solution>
    <solution>
      <cost>35.3238075793812</cost>
      <routes>
        <route>
          <driverId>noDriver</driverId>
          <vehicleId>vehicle</vehicleId>
          <start>0.0</start>
          <act type="service">
            <serviceId>2</serviceId>
            <arrTime>5.830951894845301</arrTime>
            <endTime>5.830951894845301</endTime>
          </act>
          <act type="service">
            <serviceId>1</serviceId>
            <arrTime>11.8309518948453</arrTime>
            <endTime>11.8309518948453</endTime>
          </act>
          <end>17.6619037896906</end>
        </route>
        <route>
          <driverId>noDriver</driverId>
          <vehicleId>vehicle</vehicleId>
          <start>0.0</start>
          <act type="service">
            <serviceId>4</serviceId>
            <arrTime>5.830951894845301</arrTime>
            <endTime>5.830951894845301</endTime>
          </act>
          <act type="service">
            <serviceId>3</serviceId>
            <arrTime>11.8309518948453</arrTime>
            <endTime>11.8309518948453</endTime>
          </act>
          <end>17.6619037896906</end>
        </route>
      </routes>
    </solution>
  </solutions>
</problem>
```

どっとはらい。
