APP_STL := c++_shared

APP_CPPFLAGS := -frtti -fexceptions
#APP_CPPFLAGS += -mfloat-abi=softfp -mfpu=neon -std=c++11
APP_CPPFLAGS += -Wno-deprecated -Wno-c++11-narrowing -Wno-logical-op-parentheses

APP_ABI := armeabi-v7a arm64-v8a
APP_OPTIM := release #debug
APP_ALLOW_MISSING_DEPS := true

#make (e=206): The filename or extension is too long.
APP_SHORT_COMMANDS :=true