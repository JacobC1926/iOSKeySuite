# iOSKeySuite
Tired of manually looking up the decryption keys for your device? With this Java console application, you don't have to!

With iOSKeySuite, you can:

* Search all of the decryption keys for your device
* Get some other information about your device
* Query the build, codename and device ID, while only specifiying one of these
* <i>and all of this without even connecting a USB</i>

# Installation (Easy)

<i> If you want to cd to the directory everytime to execute, choose this method </i>

    sudo apt -y install openjdk-8-jre
    git clone https://github.com/MaliciouZzHD/iOSKeySuite
    cd iOSKeySuite/src
    bash Build.sh
    cd ../

# Installation (Advanced)

<i> If you want to be able to type `ioskey---` from anywhere (aslong as you have root), choose this method </i>

    sudo apt -y install openjdk-8-jre
    git clone https://github.com/MaliciouZzHD/iOSKeySuite
    cd iOSKeyTool/src
    bash Build.sh
    cd ../
    sudo ln -s $(pwd)/iOSKeyTool /sbin/ioskeytool
    sudo ln -s $(pwd)/iOSDeviceQuery /sbin/iosdevicequery
    cd

# Todo
* Add support for both codename and build query
* Secure things up
* Pretty things up (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧
