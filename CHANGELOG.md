# Camel Case Plugin

## Changelog

### Release 3.0.15:
* add '"` switch

### Release 3.0.14:
* add every case action

### Release 3.0.13:
* Update: change config to application

### Release 3.0.12:
* Bugfix: use standard config if com.intellij.openapi.project.Project is null

### Release 3.0.11:
* Bugfix: if only camelCase and snake_case is selected in the options

### Release 3.0.10:
* Single word bugfix (Foo => FOO => foo)
* Pascal Case with space bugfix (foo foo => Foo Foo)

### Release 3.0.9:
* New Conversion added (Camel Case)

### Release 3.0.7:
* Support any text field and editor

### Release 3.0.6:
* Fixed snake case bug
* Need IntelliJ Platform version higher than 191.4212.41

### Release 3.0.5:
* Fix for string in lower case

### Release 3.0.4:
* Select whole string with dashes

### Release 3.0.3:
* Fixed conversion with special char

### Release 3.0.2:
* Fixed deprecated function

### Release 3.0.1:
* Restore multiple caret mode (see #2)

### Release 3.0:
* Allow to use plugin in dialogs (like refactor/rename)

### Release 2.1:
* Introduce "space case" and allow to change conversion order in configuration.

### Release 2.0:
* Introduce config panel to switch off certain transformations (Preferences / Editor / Camel Case)

### Release 1.6:
* Bugfix: double underscore (thanks to John)

### Release 1.5:
* added hyphen-separated-notation

### Release 1.4:
* optimized multiple caret support
* idea 14.1 is now required, please use release 1.2 for older IDEs

### Release 1.3:
* multiple selections support

### Release 1.2:
* Compiled for J2SE 6.0

### Release 1.1:
* Added undo functionality