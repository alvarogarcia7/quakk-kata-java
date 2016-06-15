## Quakker Client

This is a Quakker (tm) Client, the newest, trendiest, non-concurrent CLI-based social network to date.

It has specially been designed for power users working on Windows, Linux and Mac OS X environments.

### Name

The 'quakk' is the onomatopoeia of the duck ([duck calling](https://en.wikipedia.org/wiki/Cross-linguistic_onomatopoeias#Duck_calling)), in the same way as the 'tweet tweet' is the
onomatopoeia of the bird ([bird singing](https://en.wikipedia.org/wiki/Cross-linguistic_onomatopoeias#Bird_singing)).

Any resemblance with the Twitter (tm) network is purely coincidental

### Installing

Download the latest release from [here](https://github.com/alvarogarcia7/quakk-kata-java/releases)

#### Compiling from the sources

 * Download the source code [here](https://github.com/alvarogarcia7/quakk-kata-java)
 * Compile with ```mvn clean package```
 * Find the jar in the ``target`` folder

### Usage

The client interacts with the system via CLI (this is a CLI client, after all).

The client reads the inputOutput you write, showing the results of that command (if any).

Description of the commands (all are case sensitive):

  * posting: ``<user name> -> <message>``
  * reading: ``<user name>``
  * following: ``<user name> follows <another user>``
  * wall: ``<user name> wall``
  * stopping the client: ``Stop!``

To start the client:

  * Open your favorite CLI
  * find the jar file
  * ``java -jar $jar_filename.jar`` #replace ``$jar_filename`` for the right name
  * Quakk quakk
  * ``Stop!`` or Control+C to exit

Note: this client is designed for power users: all commands are assumed to be correct. If you slip and type "Johhn"
instead of "John", the system assumes you wanted to indicate "Johhn" (otherwise, should have typed "John")

### Sample

  * posting: ``John -> hello``
  * reading: ``John``
  * following: ``Maria follows John``
  * wall: ``John wall``
  * stopping the client: ``Stop!``

A longer sample:

```
mypc $ java -jar target/quakker-client.jar
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
