
# Workstation Setup

The following document will guide you towards seting up your workstation to contribute towards FlyCAGE's codebase.

## Installation
### Back-end Developers
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org/install.html)
- [STS](https://spring.io/tools/sts)

#### Install Git

The version control system that FlyCAGE uses is Git (hosted on GitHub). Git is useful for keep track of code history and coordinating the work between several developers. 

1. Follow the installation guide on the Git website: https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

#### Install JDK8
The Java Development Kit provides an environment to build advanced Java applications.

1. Install the correct kit corresponding to your operating system: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

#### Install Maven

FlyCAGE uses Apache Maven to build the deployable artificat. Maven is used for deploying to local and deploying to the test environment on Heroku. 

1. Following the installation guide on the Maven website: https://maven.apache.org/install.html

#### Install IDE

The back-end of FlyCAGE uses the [Spring Framework](https://spring.io/), therefore it is advised to use an IDE with built-in Spring Framework support.

There are two recommended IDE's to use for FlyCAGE back-end development: Spring Tool Suite and Visual Studio Code. There may be other IDE's that have Spring support - feel free to use them.

##### Spring Tool Suite (STS)

STS is an Eclipse-based IDE with built-in Spring Framework features such as deploying within the IDE's integrated environment, Spring DevTools for easier testing, easier addition of Spring libraries to project, etc. 

1. Download an install STS from their website: https://spring.io/tools/sts

##### Visual Studio Code (VS Code)

VS Code is a flexible text-editor for several kinds of development.

1. Install VS Code from their website: https://code.visualstudio.com/docs/setup/setup-overview
2. Install the "Spring Boot Extension Pack" from the "Extensions" tab within VS Code.

### Front End Developers
At the current state of FlyCAGE, front-end developers are not required to install any specialized front-end tools since the front-end of FlyCAGE consists of HTML, CSS, JS, jQuery, and Bootstrap retrieved over CDN. Since the front-end uses back-end template resolution, we do advise you to follow the "Back-end Developers" guide to test the front-end when served.

### All developers
- [Zenhub](https://www.zenhub.com/extension)

#### Install Zenhub Browser Extension

For project management, we use [Zenhub](https://www.zenhub.com/) to organize stories - integrated within GitHub itself. You will need to install ZenHub's browser extension. Only Chrome and Firefox is supported.

1. Install the Zenhub Extension: https://www.zenhub.com/extension

## Retrieving the CodeBase

### Fork the project

You will need to fork the project into your own account. You will be able to add updates to this copy without affecting the main project. 

1. On the [project homepage](https://github.com/CodingBash/FlyCAGE), click "Fork" on the top right.

### Pull the forked project

Next, you will pull the forked project onto your local workstation. 

1. Open Git Bash (installed above) in a preferred directory to contain the project (perhaps in the /Document directory).
2. In the Git Bash terminal, type the commmands to clone the project to the directory:

`git remote add origin <repository-url>` | where `<repository-url>` is the url provided on your forked project homepage.

`git pull origin master`

## Deploy to local workstation

### On Spring Tool Suite (STS)
If you are using STS, you can run the project within the Eclipse environment. NOTE: This will use STS's integrated Maven plugin which may be a different version than your workstation's Maven version.

1. Right click on the project root folder
2. Expand "Run As" to run normally, or "Debug As" to debug the application
3. Click "Spring Boot App"
4. Navigate to `localhost:8082` in preferred browser.

### On Git Bash or another shell.
This is the preferred way of running the application. This is also IDE-agnostic - running through the shell is not impacted by what IDE you use.

1. Open Git Bash (or other shell) in the project root directory.
2. Type in the following command: `mvn spring-boot:run`
3. Navigate to `localhost:8082` in preferred browser.
  
  
## Deploy to Heroku

Currently, only the owner of FlyCAGE has permission to deploy to the Heroku servers on flycage.herokuapp.com. Very soon, deployment to Heroku will be continious through GitHub merges to the master branch.


