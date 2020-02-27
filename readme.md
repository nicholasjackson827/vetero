# Vetero

A simple app for displaying weather.

## Instructions for Running

The only requirement is a semi-recent version of Docker with Docker Compose. I choose to run Docker Desktop on Windows and it works great.

The app uses the [OpenWeather API](https://openweathermap.org/) for its weather data. To run locally, you'll need an API key (it's free!).

Once you get an API key, create a file in the root of this project called `openweather_api_key.txt` and put your key in there. No property or key, just the API key! Here's a one-liner to do it on Linux:

```
$ echo "MY_API_KEY" >> openweather_api_key.txt
```

Then, from the root directory of the project, run:

```
$ docker-compose up --build
```

This will build images with the source of all 3 parts of the application (frontend, backend, and DB).

You may get an odd request from Docker to access your drive to read the file. It'll be required to read the contents of your secrets file.

You will probably want to grab a cup of coffee after running this as it must download all required dependencies.

Once the command has completed and all three sources are up, navigate to [http://localhost:3000](http://localhost:3000) to view the app!

## Instructions for Development

Assuming you don't want to wait for Docker to re-download the internet each time you make a change, I recommend running each part outside of docker and only doing final testing inside the Docker containers. (You could, in theory, mount your local Maven and NPM repos, but that's outside the scope of this README.)

You'll need the following:

- Java 11
- Maven (3.5+)
- NPM (5.0+)
- Docker

### Building/Running the Database

This is controlled by Docker, so to up the database, from the root directory run:

```
$ docker-compose run db
```

and you're good to go!

### Building/Running the Backend

The backend is a standard Spring Boot app that builds a fat JAR and runs on an embeded version of Tomcat. To run locally, run the following from the `vetero-server` directory:

```
$ mvn spring-boot:run
```

This will package the JAR, run the tests, and start the embedded Tomcat instance. Pretty nifty!

### Building/Running the Frontend

The frontend is a standard React app. To get all the nifty features like hot reloading, run the following from the `vereto-client` directory:

```
$ yarn start
```

and navigate to the URL [http://localhost:3000](http://localhost:3000).
