

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

## Running the tests

1. make sure appium server is running by running from Mac Searchlight
2. Verify Appium launches and select "Start Server X.xx.x"
3. Change to project root directory ( where pom.xml is)
3. mvn clean test -Dplatform=ios   
4. mvn clean test -Dplatform=android
5. mvn test -Dplatform=android -Dtestsite=local
6. mvn test -Dplatform=android -Dtestsite=saucelabs
7. mvn test -Dplatform=ios -Dtestsite=local
8. mvn test -Dplatform=ios -Dtestsite=saucelabs

FIX_ME   
Need to have the following running before running tests
1. Appium Server started
2. Android emulator started in background  
    ~/Libary/Android/sdk/tool directory ->  emulator -avd Pixal_3a_API_29
3. Ios Simulator started in background

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