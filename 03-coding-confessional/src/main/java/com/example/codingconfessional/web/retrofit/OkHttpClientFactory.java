package com.example.codingconfessional.web.retrofit;


import android.content.Context;
import com.squareup.okhttp.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class OkHttpClientFactory {

    private static OkHttpClientFactory sInstance = new OkHttpClientFactory();

    public static OkHttpClientFactory getInstance() {
        return sInstance;
    }

    private OkHttpClientFactory() {
    }

    public OkHttpClient newSecuredHttpClient(Context context, KeystoreConfiguration conf) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setSslSocketFactory(createSSLSocketFactory(context, conf));
        return httpClient;
    }

    private SSLSocketFactory createSSLSocketFactory(Context context, KeystoreConfiguration conf) throws KeyManagementException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException {
        KeyStore trusted = getKeyStore(context, conf);
        TrustManagerFactory tmf = getTrustManagerFactory(trusted);
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, tmf.getTrustManagers(), null);
        return ctx.getSocketFactory();
    }

    private KeyStore getKeyStore(Context context, KeystoreConfiguration conf) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        // Get an instance of the Bouncy Castle KeyStore format
        KeyStore trusted = KeyStore.getInstance("BKS");
        // Get the raw resource, which contains the keystore with your trusted certificates (root and any intermediate certs)
        InputStream in = context.getResources().openRawResource(conf.resource);
        try {
            // Initialize the keystore with the provided trusted certificates. Also provide the password of the keystore
            trusted.load(in, conf.password.toCharArray());
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return trusted;
    }

    private TrustManagerFactory getTrustManagerFactory(KeyStore trusted) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trusted);
        return tmf;
    }
}
