# RVAHams Equipment Tracker
---

# ARCHIVED

This repository is now archived. Its functionality is being built into a new, more feature rich application called [SENM](https://senm.app). You can still download this program and use it if you want.

I was a college student when I wrote this. It isn't good. Just use SENM.

---

### Description
RVAHams Equipment Tracker is designed to aid the RVAHams team during Special
Olympics events by tracking loaner equipment as it is distributed. It does not require internet access or connect to any external servers or databases.
The tracker uses a positional list via linked list implementation and a HashMap to track the radios by identifiers.

### License
See the `LICENSE.md` file for important license information.

### Installation

__This application requires Java 8 or later.__

RVAHams Equipment Tracker has two installation options.

* Download the source code and compile on your own system.
* If you have no changes to make, or don't care to read the source code, just download the executable .jar file and run it from the command line.

If you are compiling from source you will need to download the core Log4J
libraries from (http://logging.apache.org/log4j/2.x/download.html). For this
project, you only need the log4j-api-2.x and the log4j-core-2.x jar files.

If you download the .jar file, I suggest that placing it in its own
directory apart from everything else so the user is able to specify file names
without having to worry about absolute paths. The application will create the
files in the same directory as the application is running. For example, placing the .jar file in `/rvahams` and specifying the file names as`radiolist.txt` will result in `/rvahams/radiolist.txt` as the file path name. 
I do not advise to use absolute paths.

### General Use:
Because RVAHams Equipment Tracker is solely a command line program, users will need to be familiar
with running Java applications via command line.

If running from the source code itself, compile it all and run the following
command in the appropriate directory:

`java MainApplication`

To run the executable .jar file, run the following command in the same
directory as `EquipmentTracker.jar`:

`java -jar EquipmentTracker.jar`

The application owner will need to answer the configuration prompts before any other operations can take place.
These configuration prompts are used for file names that are going to be used for both input and output files.

##### Configuration Prompts:
1. `Enter a Radio List file name:` This file holds a list of all identifiers on
the radios and is loaded into the application.
2. `Enter the Snapshot file name:` This file will hold a current snapshot of the
equipment that is loaned out and can be generated at any time by typing 3 in the
user prompt menu.

A note about file names:

1. The Radio List file __MUST__ have an extension and __MUST__ be a plain text
file.
  * Good: radioList.txt -OR- RadioIdentifiers.dat
  * Bad: Radios.doc -OR- radiolist.xls
2. The Snapshot file should __NOT__ have an extension. The application
automatically adds a random identifier and saves it as a .txt file.

##### Logging

The application automatically creates a log file named `EquipmentTracker.log` in
the same directory as the application itself. The log file tracks all actions
in the main application and will be able to show a radio's history over the entire event
. __THE LOG FILE IS OVERWRITTEN EVERY
TIME THE PROGRAM IS RUN.__

##### User Prompts:
```
User Menu:
Hit the enter/return key after you type selection
	1 - Check Out Radio
	2 - Return Radio
	3 - Generate Snapshot File
	4 - Quit Application (Automatically Creates Snapshot)
```

The prompts are straight forward and will ask for the loanee's name and the
radio's identifier, which should be printed on the radio or otherwise
made known to the user. __Names cannot contain spaces.__ I suggest formatting the names as [last][firstInitial][middleInitial].

That is the end of general user interaction. After the user enters the required
information, the user prompts start over again. All required time stamping is done automatically.

---

### Radio List File Requirements

* Each line should contain a single entry with a __unique__ identifier for
each radio.

* Lines that are to be ignored must start with `//`, like a normal Java
comment. It is not possible to make comments on the same line as an identifier.

* Blank lines are ignored.

##### For Example

```
// List of Radios
Radio1
Radio2

Radio Backup

// The following will not do what you want
// The identifier will be the entire line
// "Radio Ultimate // Best radio ever"
Radio Ultimate // Best radio ever
```
### Future Developments

Eventually I hope to have a version that will take a Snapshot file and be able
to load it to continue the process. This will probably require a reformat of the
current Snapshot file into something like JSON or YAML.

After this configuration, prompts will appear for the user to enter in
information as they check out and return radios.
