# Simple Appium Test Example
 For local or saucelabs.   
 
 The is a simple mobile app test that can test both a one page login app for iOS and Android

## Appium
http://appium.io/

##Setup Instructions for Development 

https://www.swtestacademy.com/how-to-install-appium-on-mac/

### Overview 

Prequisites : Assumed already installed by user
1. installed homebrew on mac
2. java and JAVA_HOME environment variable set use echo $JAVA_HOME to show
3. maven
4. Node.js https://nodejs.org/en/download/
5. Xcode installed 

Installation   
1. Download and install Android Studio https://developer.android.com/studio/
2. export environment variables put in your ~/.bash_profile or profile of choice

export ANDROID_HOME=/Users/onur/Library/Android/sdk
export PATH=$ANDROID_HOME/platform-tools:$PATH
export PATH=$ANDROID_HOME/tools:$PATH
export PATH=$ANDROID_HOME/tools/bin:$PATH

```bash
% source ~/.bash_profile
Now verify 
% sdkmanager –list
```
3. Appium install
```bash
npm install -g appium #get appium
npm install wd
```
4. Install Carthage
```bash
sudo chown -R $(whoami) /usr/local/share/man/man5 /usr/local/share/man/man7
sudo xcodebuild -license accept
brew install carthage
npm i -g webpack
```
5. Install Appium Desktop
Let’s go this link: https://github.com/appium/appium-desktop/releases/ 
 
6. libimobiledevice
```bash
brew install libimobiledevice
``` 
7. Install simulators in Xcode 
Once installed, Launch Xcode and select Xcode > Preferences > Components to install the simulators that you might want to test against.
8. install authorize-ios
```bash
npm install -g authorize-ios
```
9. Install ios-deploy
```bash
brew install ios-deploy
```
10. Install ideviceinstaller
```bash
sudo xcode-select -r
 
brew install ideviceinstaller
```
11. Install ios_webkit_debug_proxy
```bash
brew install ios-webkit-debug-proxy
```

12. Install Appium Doctor to check installation
```bash
npm install -g appium-doctor
appium-doctor
```

## Configuration and Conventions

**Application Files**
/src/test/resources/android  /ios
The are the application file that will be uploaded for testing

**Capabilitiy files**
/src/test/resources/capabilities
These files are JSON file of capabilities to load for a test. They are prefixed with the naming convention
local- for testing with a local Appium server and saucelabs- for a configuration to test with saucelabs service.   

**Saucelabs Service**
saucelabs.properties contains configuration of items used to setup the properties used by saucelabs to connect.

**General Properties**
config.properties are used for any other properties needed by the application

## Before Running the Tests
We need to make sure the emulators are running for the platform we are using and also the Appium Server

### Appium Server
run it from the app launcher on Mac OS

or from a terminal window
```bash
 appium &
```


### IOS Simulator
```bash
open /Applications/Xcode.app/Contents/Developer/Applications/Simulator.app/
```

### Android Emulator
```bash
$ANDROID_HOME/tools/emulator -list-avds 
Android_Accelerated_Oreo
Nexus_S_API_28

$ANDROID_HOME/tools/emulator -avd Nexus_S_API_28 & << must have this defined in android studio
```
## Running the tests in maven 

1. make sure appium server is running by running from Mac Searchlight
2. Verify Appium launches and select "Start Server X.xx.x"
3. Verify Emualtor is running for IOS/Android
4. Change to project root directory ( where pom.xml is)
5. Choose one of the following to run
 * mvn test -Dplatform=android -Dtestsite=local -Dcapability=capabilities/local-android-nexus-s-api28.json
 * mvn test -Dplatform=android -Dtestsite=saucelabs -Dcapability=capabilities/saucelabs-android-google-pixel.json
 * mvn test -Dplatform=ios -Dtestsite=local  -Dcapability=capabilities/local-ios-iphone11-13.4.json
 * mvn test -Dplatform=ios -Dtestsite=saucelabs -Dcapability=capabilities/saucelabs-ios-iphone.json ( not working yet)

## Running the test in Intellij
 For each configuration you need to define system properties in the configuratio settings
 
 Add somthing like this to the VM arguments  Run menu -> Edit Configurations 
 -Dplatform=android -Dtestsite=local -Dcapability=capabilities/local-android-nexus-s-api28.json
 
 or 
 -Dplatform=android -Dtestsite=saucelabs -Dcapability=capabilities/saucelabs-android-google-pixel.json
 
 or 
 -Dplatform=ios -Dtestsite=local -Dcapability=capabilities/local-ios-iphone11-13.4.json

#### Example python tests
There are also a couple of python tests in this project as as reference also   
test_login_android.py  to run , % pytest test_login_android.py   
test_login_ios.py  to run , % pytest test_login_ios.py   
## Tutorial on Appium use

https://www.swtestacademy.com/appium-tutorial/

####

brew install node      # get node.js   
npm install -g appium  # get appium   
npm install wd         # get appium client   
appium &               # start appium   
node your-appium-test.js

### Desired Capabilities
These are used to specify the capabilities to test against.

**IOS**
src/test/resources/capabilities/local-ios-iphone11-13.4.json
**Android** 
src/test/resources/capabilities/local-android-nexus-s-api28.json

More info    
https://kobiton.com/book/chapter-3-understanding-the-desired-capabilities/

### Loading files up to saucelabs

Show files loaded on sauce storage

```bash
curl -u "wgentry":"access-key-xxxxxxxxxxxxxxx"          "https://saucelabs.com/rest/v1/storage/wgentry"
{"files":[]}
```

Or you can also use the UI to upload an APK or IPA file


Put file uploaded

```bash
curl -u "wgentry":"878132eb-dfaa-XXXXXXXXXXX"          -X POST          -H 'Content-Type: application/octet-stream'          --data-binary src/test/resources/andriod/app-debug.apk          "https://saucelabs.com/rest/v1/storage/wgentry/app-debug.apk?overwrite=true"
```


#### Issue
Connection refused (Connection refused)
Build info: version: '3.141.59', revision: 'e82be7d358', time: '2018-11-14T08:17:03'
System info: host: 'svuslp00099-170.local', ip: 'fe80:0:0:0:822:ab64:2571:71c5%en0', os.name: 'Mac OS X', os.arch: 'x86_64', os.version: '10.15.4', java.version: '1.8.0_212'
Driver info: driver.version: IOSDriver
        at com.softvision.simplemobile.LoginTest.getDriver(LoginTest.java:136)
        at com.softvision.simplemobile.LoginTest.initAndroid(LoginTest.java:116)
        at com.softvision.simplemobile.LoginTest.init(LoginTest.java:71)
Caused by: java.net.ConnectException: Connection refused (Connection refused)
        at com.softvision.simplemobile.LoginTest.getDriver(LoginTest.java:136)
        at com.softvision.simplemobile.LoginTest.initAndroid(LoginTest.java:116)
        at com.softvision.simplemobile.LoginTest.init(LoginTest.java:71)

[ERROR] com.softvision.simplemobile.LoginTest  Time elapsed: 0.53 s  <<< ERROR!
java.lang.NullPointerException
        at com.softvision.simplemobile.LoginTest.afterTest(LoginTest.java:121)

[INFO] 
[INFO] Results:
[INFO] 
[ERROR] Errors: 
[ERROR] com.softvision.simplemobile.LoginTest.null
[ERROR]   Run 1: LoginTest.init:71->initAndroid:116->getDriver:136 » WebDriver Connection refus...
[ERROR]   Run 2: LoginTest.afterTest:121 NullPointer
[INFO] 
[INFO] 
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0

### Fix is 
Start Appium Server

####Issue
[INFO] 
[INFO] Results:
[INFO] 
[ERROR] Errors: 
[ERROR] com.softvision.simplemobile.LoginTest.null
[ERROR]   Run 1: LoginTest.init:71->initAndroid:116->getDriver:136 » SessionNotCreated Unable t...
[ERROR]   Run 2: LoginTest.afterTest:121 NullPointer
[INFO] 

####Fix
Start the emulator

#### Issue
 Stderr: 'adb: failed to install 
 
 #### Fix
 
 https://stackoverflow.com/questions/25274296/adb-install-fails-with-install-failed-test-only
 
 add this line to your ‘gradle.properties’
 
 I have solved the issue by adding the "android:testOnly" property to android manifest's tag.
 
  <application
     .....
     android:testOnly="false"
     
 
 android.injected.testOnly=false
 
 Rebuild the project APK
 
 
 ## SAUCELABS
 
 testObjectKey : include in the deviceCapabilities
 
   https://us1-manul.app.testobject.com/wd/hub