#!/bin/sh
if [[ ! -z "$ANDROID_SDK" ]]; then
	export ANDROID_SDK=/Applications/ADT/sdk/;
fi

rm apk/*.apk
if [[ ! -d apk/singles-android.apk ]]; then
	cp ../singles-android/bin/singles-android.apk apk/.
fi
if [[ ! -d apk/singles-android-robo.apk ]]; then
	cp bin/singles-android-robo.apk apk/.
fi

export LAST_COMMIT=`git --git-dir ../singles-android/.git log --oneline -1`
java -jar apk/spoon-runner-1.1.1-jar-with-dependencies.jar \
	--adb-timeout 200 \
	--apk apk/singles-android.apk \
	--output target \
	--test-apk apk/singles-android-robo.apk \
	--sdk $ANDROID_HOME \
	--title "$LAST_COMMIT"
