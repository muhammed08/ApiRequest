Storing password in code is not allowed. However we need keystore password to
access the key. Here we make use of the AWS secret manager to store the keys and retrive it using 
aws secret manager client. Currently, the secret manager uses apikey and secret to access
the keystore password. We use springboot configuration to configure the netty server and Value annotation to assign the config values from the properties file

To create aws secret:
aws console -> secret manager -> new secret -> provide plain text secret-> name the secret (should be same as provided in code)-> done

To create aws access key and secret:
aws console -> profile -> security credential -> access keys -> create new access keys