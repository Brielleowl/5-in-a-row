​                                       **Programming Challenge: Five in a Row**

**Project Setup**

Java 11 with SpirngBoot(Gradle 6.6.1)

**Build and Run**

**server:**

gradlew build   

java -jar server/build/libs/server-0.0.1-SNAPSHOT.jar

**client:**

gradlew build   

java -jar client/build/libs/client-0.0.1-SNAPSHOT.jar

**Design**

**Sever side:** 

It implemented by Tomcat. 

Handle the request:

It used REST to handle client's request. The endpoint is on localhost:8080.  It implement most operations about game: dropping the discs, detecting winner, detecting client timeout, taking turns to let user play, etc.

**Client side:**

It implemented by Jersey Client.

It used http request to get game status from server including current board, the winner, which player will play first, etc.



**Test:**
I tried to several ways to test both sever and client but it has some errors when it building. I am still trying to figure it out.

