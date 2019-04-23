[![Build Status](https://travis-ci.org/NabunyaLilian/ConvergeCodeLab.svg?branch=develop)](https://travis-ci.org/NabunyaLilian/ConvergeCodeLab) [![codecov](https://codecov.io/gh/NabunyaLilian/ConvergeCodeLab/branch/develop/graph/badge.svg)](https://codecov.io/gh/NabunyaLilian/ConvergeCodeLab) [![Maintainability](https://api.codeclimate.com/v1/badges/bfb511ffb9102ad9fbf5/maintainability)](https://codeclimate.com/github/NabunyaLilian/ConvergeCodeLab/maintainability)

# CodeLab
- This project is about building an Android app that retrieves a list of java developers in Nairobi using the GitHub API.

## Wireframes
- When creating wireframes for the major screens of the application I decided to use JustInMind tool.

### List View
<img src="wireframe/Screen 1.png" width="330px" alt="screen 1">

### Detail View
<img src="wireframe/Screen 4.png" width="330px" alt="screen 1">

### Share View
<img src="wireframe/Screen 3.png" width="330px" alt="screen 1">

## Reasons for Choosing JustInMind tool
- JustInMind is simple to use, I learnt how to use it within a day.
- It comes with Device-specific templates and features such as Android features.
- Its simulate feature generates a preview of fully interactive prototype with a device frame.
- It has fine-grain controls for adding interactivity to individual elements through the use of events.
- It supports gesture-based interaction.
- It has an option to add animation to individual elements or transition effects to links.

## Getting Started
- These Instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites
- Android Studio
- Jdk Installed
- Fast Machine

## Project setup
This project consists of a single Gradle-based project

It has 3 main directories inside the app/src folder

- /main - The main application
- /androidTest - UI tests for the app with Espresso
- /test - Unit tests for the project

## Running The tests
Espresso tests can be run using:
These are tests that check if the app is running as it is supposed to.
`./gradlew test`

Coding style tests
These tests check if the code does not violate any standards of clean code.
`/gradlew check`

## Building the App
`./gradlew build`

## Fastlane Integration
Fastlane automates the release of the product by uploading the apk to the slack channel.

- Setup environment variables with

`export SLACK_URL="..."`
`export SLACK_API_TOKEN="..."`
`export SLACK_CHANNEL="..."`


### Run With

`fastlane slackbuild`

## Versioning

- This is the first version for CodeLab

## Authors
- Nabunya Lilian



