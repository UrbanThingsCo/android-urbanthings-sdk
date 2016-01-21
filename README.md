# UrbanThings Android SDK
_Create Android apps that connect to the UrbanThings API to retrieve smart city data_

This SDK allows you to quickly get up-and-running with an Android-friendly client to access the [Urban Things API / Bristol API](https://portal-bristol.api.urbanthings.i)

## System Requirements
- Android Studio 1.5 or greater
- for supported OSs see [Requirements] (http://developer.android.com/sdk/index.html#Requirements)

## Getting Started
A minimal demonstration app is included to get you up and running. To get hold of the latest version, simply clone this repository and open the resulting folder `android-urbanthings-sdk` within Android Studio 1.5 or greater.

To get the app working, you will need to add in the API key that you received when you registered via the [portal](https://bristol-portal.api.urbanthings.io), by updating file `com.example.urbanthings.network.UrbanThingsApiClient.java`.

Once the API key has been added, you should be able to just press 'Run' (with a suitable device or emulator available) to launch the app.

## Overview
The SDK consists of two libraries:
- `urbanthings`      : contains the data model (POJOs)
- `urbanthings-gson` : adds JSON serialisation support using [Gson](https://github.com/google/gson)

We've divided things up in this way so that if your project uses an alternate JSON serialisation library, you're not forced to include Gson.

## Installation
The demo app includes these libraries as source code. To use the UrbanThings SDK in your own projects, you should instead use the Android Studio gradle dependencies mechanism. Just add the following to the dependencies section of your app's build.gradle file:

If you would like to include Gson serialisation support (this pulls down BOTH libraries):
- `compile 'io.urbanthings:urbanthings-gson:0.9.1’`

*OR* 

If you would prefer to use an alterate serialisation library, just include the data model:
- `compile 'io.urbanthings:urbanthings:0.9.1’`

## The Android Demo App

### Functionality
The Android demo app shows a `ListView` containing a list of all the car parks in Bristol & Bath, United Kingdom. If a row is tapped, a second API request is made to determine the live `ResourceStatus` of the car park, i.e. its current occupancy, with the result shown in a `Snackbar`.

### Implementation
The demo app uses the `android-async-http` library for asynchronous HTTP requests, and includes a helper class `UrbanThingsApiClient` based on example code at https://loopj.com/android-async-http/. 
The app demonstrates calls against endpoints `static/stops` and `rti/resources/status`.

There are many libraries used on Android for asynchronous HTTP; your project might already be using an alternate library such as Volley, OkHttp. You're of course free to use code from this demo app, but please note the app demonstrates the UrbanThings API rather than best practices for asynchronous network calls on Android.


## Future Development
We are soliciting requests from the community for the ongoing development of this SDK; either by:

- *Feature requests* - let us know what you would like to see in the SDK.
- *Pull requests* -  if you wish to fork the SDK and add in functionality, we will gladly consider pull requests to merge these back into the SDK.


_This ReadMe refers to the Urban Things API - see [Urban Things](http://www.urbanthings.co)_
