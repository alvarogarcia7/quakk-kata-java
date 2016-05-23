## Quakker Client

This is a Quakker (tm) Client, new trendiest non-concurrent CLI-based social network to date.

### Installing

Download the latest release from [here](https://github.com/alvarogarcia7/quakk-kata-java/releases)

#### Compiling from the sources

 * Download the source code
 * Compile with

```mvn clean package```

 * Find the jar in the ``target`` folder

### Usage

all commands are case sensitive:

  * posting: ``<user name> -> <message>``
  * reading: ``<user name>``
  * following: ``<user name> follows <another user>``
  * wall: ``<user name> wall`
  * stopping the client: ``Stop!``

### Sample

  * posting: ``John -> hello``
  * reading: ``John``
  * following: ``Maria follows John``
  * wall: ``John wall`
  * stopping the client: ``Stop!``

A longer sample:

```
mypc $ java -jar target/quakker-0.0.1-SNAPSHOT-jar-with-dependencies.jar
John
John -> hello
John
hello (3 seconds ago)
John -> hello again
John
hello again (2 seconds ago)
hello (20 seconds ago)
Maria -> Hola mundo!
Maria
Hola mundo! (3 seconds ago)
Maria follows John
Maria
Hola mundo! (11 seconds ago)
Maria wall
Maria - Hola mundo! (16 seconds ago)
John - hello again (31 seconds ago)
John - hello (49 seconds ago)
Stop!
```
