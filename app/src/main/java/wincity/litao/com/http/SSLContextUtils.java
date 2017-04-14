package wincity.litao.com.http;


import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import wincity.litao.com.App;

/**
 * Created by guangneng
 * 2016-3-16 16:15:28
 */
public class SSLContextUtils {

    //ais_app.cer 为存储于assets中的api请求证书

    private static String cerName="toku.cer";

    private static SSLContext sslContext;

    public static SSLContext getSSLContext(){
        if (sslContext==null)
            sslContext= getSSlContext(cerName);
        return sslContext;
    }

    /**
     * 上传证书 构建SSL
     * @return
     */
    public static SSLContext getSSlContext(String cerName){
        SSLContext sslContext=null;
        try {
            // 创建SSL
            sslContext=SSLContext.getInstance("TLS");
            InputStream inputStream= App.getInstance().getAssets().open(cerName);

            //创建证书工厂
            CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
            Certificate cer=certificateFactory.generateCertificate(inputStream);

            //获取 keyStore
            KeyStore keyStore= KeyStore.getInstance("PKCS12");
            keyStore.load(null,null);
            keyStore.setCertificateEntry("trust",cer);

            //创建 keyStore 管理工厂
            KeyManagerFactory managerFactory=KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            managerFactory.init(keyStore,null);

            //创建初始信任
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            //ssl 初始化
            sslContext.init(managerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),new SecureRandom());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslContext;
    }
}
