
# Workstation Setup

The following document will guide you towards seting up your workstation to contribute towards FlyCAGE's codebase.

## Installation
### Back-end Developers

#### Install Git

The version control system that FlyCAGE uses is Git (hosted on GitHub). Git is useful for keep track of code history and coordinating the work between several developers. 

1. Follow the installation guide on the Git website: https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

#### Install Maven

FlyCAGE uses Apache Maven to build the deployable artificat. Maven is used for deploying to local and deploying to the test environment on Heroku. 

1. Following the installation guide on the Maven website: https://maven.apache.org/install.html

#### Install IDE

The back-end of FlyCAGE uses the [Spring Framework](https://spring.io/), therefore it is advised to use an IDE with built-in Spring Framework support.

There are two recommended IDE's to use for FlyCAGE back-end development: Spring Tool Suite and Visual Studio Code.

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





