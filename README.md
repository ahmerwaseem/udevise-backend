# udevise-backend

Udevise is an application that allows users to create and take surveys and quizzes. This is the backend component of the Udevise application. 
You can refer to https://github.com/ahmerwaseem/udevise-frontend for the front end code. They are heavily coupled so you will need both setup.
## Getting Started

### Software needed
You will need the following software installed. Please search and install as install from their respective sites:
1. Maven
2. Git
3. Java IDE such as IntelliJ
4. Node.js
5. Npm
6. Mongodb (mongod, mongoshell)


### Initial setup instructions
1. Install the software needed.
2. Clone the back end code into your local environment (https://github.com/ahmerwaseem/udevise-backend)
3. Clone the front end code into a new app folder in src/main (https://github.com/ahmerwaseem/udevise-frontend), your front end code should reside in src/main/app after this point.
4. Setup Auth0 - Auth0 will handle all logins and give us user info and a JWT. Our front end and backend relies on this information in order to work.
   1. Create Account at https://auth0.com and Go to Dashboard
   2. Create an API, make note of the identifier you assign it.
   3. Now create an Application, make note of the client ID and domain in the new application settings.
      1. In your application settings, add callback urls that will be called when the user has been authenticated from Auth0, where they will be redirected to. 
      In local environment this will be http://localhost:3000/callback. 
      Once deployed it should follow this pattern: protocol://yourdomain:port/callback 
      i.e. https://udevise.com/callback. Add port if necessary. Make note of this call back url.
      2. Follwing similar conventions as the step above. In your application settings, add a Logout url. This is where Auth0 will redirect the user when they click logout on your application. Make note of this logout redirect url.
      2. Now let's create a rule in Auth0 so userinfo is given to us in the token provided by Auth0 so we don't have to make an calls from our code to get user info.
         1. Go to you application in the Auth0 dashboard. 
         2. Click Rules
         3. Click Create Rule
         4. Click Empty Rule
         5. Replace the existing code with the following, replace the namespace with your domain, and click Save. Make note of this namespace url, for example below, you would note https://udevise.com/userInfo. This is very important. This is creating a custom claim for us so our backend can have user info.
            ``` 
            function (user, context, callback) {
                var namespace = 'https://udevise.com/'; // note that you cannot use auth0.com, webtask.io and webtask.run as a namespace identifier
                context.accessToken[namespace + "userInfo"] = user;
                callback(null, user, context);
            }
   4. You should now have an Application client ID, your domain name as set up in Auth0, api identifier, callback url, logout url, and a namespace for our custom claim.
   5. Navigate to the .env property files in the src/main/app folder, for the environment you are setting up, input the details into the appropriate variables
        ```
           REACT_APP_DOMAIN= YourAuth0DomainHere
           REACT_APP_CLIENTID= YourClientIdHere
           REACT_APP_CALLBACK= YourCallBackHere
           REACT_APP_API_IDENTIFIER= YourAPIIdentifier
           REACT_APP_LOGOUT = WHereYouWantToGoAfterLogout

   6. Navigate to the src/main/resources, for the environment you are setting up update the auth0 properties:
        ```
            auth0.issuer=YourAuth0DomainHere
            auth0.apiAudience=YourAPIIdentifier
            auth0.userInfo.claim.name=YourNamespaceForCustomClaim

     
### Running in Non Prod Systems
1. In your server vm options settings, set spring.profiles.active to the environment you are running on. This environment should follow the convention you see in src/main/resources property files i.e. application-local or application-prod property, these must map to the environment you specify. In this example, property files will only be used if the environment variable is set to local or prod respectively. i.e. spring.profiles.active=local. You can create as many of these property files as needed.
1. Start your mongo server. Update spring.data.mongodb.uri and spring.data.mongodb.database in application property files as needed in src/main/resources in the respective environment file.
1. Run the backend application. 
2. Navigate to the src/main/app folder. 
   - Run "npm install"
   - Run "npm start"
3. Both apps should be running in their own local servers and be able to communicate with each other.
   - This assumes you are running on port 8080. If you are running the backend java app on any other port, you will have to edit package.json in src/main/app to point to right port.   "proxy": "http://127.0.0.1:8080"
   
### Running in Prod Systems
1. Run "mvn clean package", this will produce a jar. 
2. In your server vm options settings, set spring.profiles.active to prod. 
3. In your server vm options settings, set your mongo details, set mongodb.uri and mongodb.database in application-prod.properties file in src/main/resources to point to your database instances, or hardcode values into spring.data.mongodb.uri and spring.data.mongodb.database;
4. Upload jar to server.
