xebia-android-flaws
===================

XKE - Android App Security Flaws Examples

# Build all

```bash
./gradlew clean installDebug
```

# Additional instructions for 03-coding-confessional:

Generate JNI libs for `03-coding-confessional`:

```bash
cd 03-coding-confessional/jni
ndk-build
```

Generate a self-signed certificate:

```bash
openssl genrsa -out privatekey.pem 1024
openssl req -new -key privatekey.pem -out certrequest.csr
openssl x509 -req -in certrequest.csr -signkey privatekey.pem -out certificate.pem -days 1024
```

Launch server:

```bash
cd 03-coding-confessional-server
npm install
sudo node server
```

You can then access it through:

```
https://localhost/latest
```

On your Android device, add a host entry for example.org (your server ip), or modify the `SERVICE_ENDPOINT` variable in the `03-coding-confessional/src/main/java/com/example/codingconfessional/MainApplication.java` file.
Then, launch the application.
