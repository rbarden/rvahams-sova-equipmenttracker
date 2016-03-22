# RVAHams Equipment Tracker
---
### Description
This equipment tracker is designed to aid the RVAHams team during the Special
Olympics event by tracking the loaner equipment as it is loaned out. It does
not connect to any external servers or databases so it can be used without any
Internet access. The tracker uses a positional list (via linked list
implementation) and a HashMap to track the radios and their identifiers.

### License
See the `LICENSE.md` file for important license information.

### Installation

__This application requires Java 8 or later.__

There are two options for installation of the Equipment Tracker.

* Download the source code and compile on your own system.
* If you have no changes to make, or don't care to read the source code
, just download the executable .jar file and run it from the command line.

If you download the .jar file, it is suggested that you place it in it's own
directory apart from everything else, that way you can specify file names
without having to worry about absolute paths. The application will create the
files in the same directory as the application is running. For example, if you
place the .jar file in `/rvahams`, then specifying the file names a la
`radiolist.txt`, will locate them at `/rvahams/radiolist.txt`. It is not advised
to use absolute paths and if you need to, do so at your own risk.

### General Use:
Because it is solely a command line program, the user will have to be familiar
with running a Java application via their command line of choice.

If you are using the source code itself, it is assumed you know something about
java compilation and running it. Simply compile it all and run the following 
command in the appropriate directory: 

`java MainApplication`

To run the executable .jar file, run the following command in the same 
directory as `EquipmentTracker.jar`:

`java -jar EquipmentTracker.jar`

The configuration prompts are to be answered by the owner of the application before
any other operations can and should take place. These configuration prompts are
used for file names that are going to be used for both input and output files.

##### Configuration Prompts:
1. `Enter a Radio List file name:` This file holds a list of all identifiers on
the radios and is loaded into the application.
2. `Enter the Snapshot file name:` This file will hold a current snapshot of the
equipment that is loaned out and can be generated at any time by typing 3 in the
user prompt menu.

A note about file names:

1. The Radio List file __MUST__ have an extension and __MUST__ be a plain text file.
  * Good: radioList.txt -OR- RadioIdentifiers.dat
  * Bad: Radios.doc -OR- radiolist.xls
2. The Snapshot file should __NOT__ have an extension. The application automatically
adds a random identifier and saves it as a .txt file.

There will be a version, eventually, that will take a Snapshot file and be able
to load it to continue the process. This will probably require a reformat of the
current Snapshot file into something like JSON or YAML.

After this configuration, prompts will appear for the user to enter in
information as they check out and return radios.

##### Logging

The application automatically creates a log file named `EquipmentTracker.log` in
the same directory as the application itself. This log file tracks all actions
in the main application, and will be able to show a radio's history if it was checked
out and returned multiple times. __THE LOG FILE IS OVERWRITTEN EVERY TIME THE 
PROGRAM IS RUN.__

##### User Prompts:
```
User Menu:
Hit the enter/return key after you type selection
	1 - Check Out Radio
	2 - Return Radio
	3 - Generate Snapshot File
	4 - Quit Application (Automatically Creates Snapshot)
```

These are straight forward and will prompt for their name and their
radio's identifier which should be printed on the radio or otherwise
made known to the user. __Names can only be one word long.__ The suggested
format is [last][firstInitial][middleInitial].

That is it for general user interaction. When the user enters the required
information the user prompts start over again. The application takes care of
all required time stamping.

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
