This is the initial version of the application. I have created a sample springboot api service using spring weblux.
I have created a self signed certificate and used it to make the end point secure.
The ssl certificate can be created using:

keytool -genkeypair -alias mycert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650

Once the key is created, add the necessary properties in application.properties/yml file.

