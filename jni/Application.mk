APP_STL := gnustl_shared
# -std=gnu++0x
APP_CPPFLAGS := -frtti -fexceptions -mfloat-abi=softfp -mfpu=neon -std=c++11 -Wno-deprecated -Wno-c++11-narrowing
#APP_ABI := armeabi
APP_ABI := armeabi-v7a
APP_OPTIM := debug
APP_ALLOW_MISSING_DEPS=true
