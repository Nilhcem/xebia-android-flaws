#include <string.h>
#include <jni.h>

jstring Java_com_example_codingconfessional_utils_DecryptUtils_decrypt(JNIEnv* env, jobject thiz, jstring input) {

    if (input == NULL){
        (*env)->ThrowNew(env, (*env)->FindClass(env, "java/lang/Exception"), "input cannot be null");
    }

    const char *nativeInput = (*env)->GetStringUTFChars(env, input, 0);
    const int nativeInputLength = strlen(nativeInput);
    char *nativeOutput = strdup(nativeInput);
    strcpy((char *) nativeOutput, nativeInput);

    int i;
    for (i = 0; i < nativeInputLength; i++) {
        nativeOutput[i] = nativeInput[nativeInputLength - i - 1] + (i % 2 == 0 ? -1 : +1);
        if (nativeOutput[i] == 31) {
            nativeOutput[i] = 126;
        }
        if (nativeOutput[i] == 127) {
            nativeOutput[i] = 32;
        }
    }

    (*env)->ReleaseStringUTFChars(env, input, nativeInput);
    jstring output = (*env)->NewStringUTF(env, nativeOutput);
    free(nativeOutput);

    return output;
}
