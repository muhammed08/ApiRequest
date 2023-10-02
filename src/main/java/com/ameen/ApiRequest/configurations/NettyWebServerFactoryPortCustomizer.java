package com.ameen.ApiRequest.configurations;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClient;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyWebServerFactoryPortCustomizer
        implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

    @Value("${SSL.KEY_ALIAS}")
    private String keyAlias;

    @Value("${SSL.PORT}")
    private String port;

    @Value("${SSL.KEYSTORETYPE}")
    private String keyStoreType;

    @Value("${SSL.KEYSTORE}")
    private String keyStore;

    @Value("${aws.accessKeyId}")
    private String keyId;

    @Value(("${aws.secretKey}"))
    private String key;

    @Override
    public void customize(NettyReactiveWebServerFactory serverFactory) {

        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(keyId, key));

        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion("us-east-1")
                .withCredentials(awsStaticCredentialsProvider)
                .build();
        //
//        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().withRegion("us-east-1")
//                .build();



        // Specify the name of your secret
        String secretName = "keyStorePassword";

        // Create a request to get the secret value
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);

        // Retrieve the secret value
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        String secretValue = getSecretValueResult.getSecretString();
        serverFactory.setPort(Integer.parseInt(port));
        Ssl ssl = new Ssl();
        ssl.setEnabled(true);
        ssl.setKeyAlias(keyAlias);
        ssl.setKeyStoreType(keyStoreType);
        ssl.setKeyStore(keyStore);
        ssl.setKeyStorePassword(secretValue);
        serverFactory.setSsl(ssl);
    }
}