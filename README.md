

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
2. Verify Appium laucnes and select "Start Server X.xx.x"
3. Change to project root directory ( where pom.xml is)
3. mvn clean test -Dplatform=ios   
4. mvn clean test -Dplatform=android


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