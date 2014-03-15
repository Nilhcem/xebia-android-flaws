LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := decrypt
LOCAL_SRC_FILES := decrypt.c

include $(BUILD_SHARED_LIBRARY)
