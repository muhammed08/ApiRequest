//package com.ameen.ApiRequest.configurations;
//
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
//import org.springframework.boot.web.server.Ssl;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class NettyWebServerFactoryPortCustomizer
//        implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {
//
//    @Value("${SSL.KEY_ALIAS}")
//    private String keyAlias;
//
//    @Value("${SSL.PORT}")
//    private String port;
//
//    @Value("${SSL.KEYSTORETYPE}")
//    private String keyStoreType;
//
//    @Value("${SSL.KEYSTORE}")
//    private String keyStore;
//
//    @Override
//    public void customize(NettyReactiveWebServerFactory serverFactory) {
//        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().build();
//
//        // Specify the name of your secret
//        String secretName = "YourSecretName";
//
//        // Create a request to get the secret value
//        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
//
//        // Retrieve the secret value
//        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
//        String secretValue = getSecretValueResult.getSecretString();
//        serverFactory.setPort(Integer.parseInt(port));
//        Ssl ssl = new Ssl();
//        ssl.setEnabled(true);
//        ssl.setKeyAlias(keyAlias);
//        ssl.setKeyStoreType(keyStoreType);
//        ssl.setKeyStore(keyStore);
//        serverFactory.setSsl();
//    }
//}