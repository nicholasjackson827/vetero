import React, { Component } from "react";
import "./App.css";
import UsernamePicker from "./components/username-picker";
import SignupForm from "./components/signup-form";
import WeatherAccordion from "./components/weather-accordion";

class App extends Component {
  constructor(props) {
    super(props);

    this.handleUsernameChange = this.handleUsernameChange.bind(this);
    this.handleUsernameSubmit = this.handleUsernameSubmit.bind(this);

    this.handleSignupChange = this.handleSignupChange.bind(this);
    this.handleSignupSubmit = this.handleSignupSubmit.bind(this);

    this.updateWeather = this.updateWeather.bind(this);
    this.handleUpdateWeatherNow = this.handleUpdateWeatherNow.bind(this);

    this.handleAddLocation = this.handleAddLocation.bind(this);
    this.handleLocationDelete = this.handleLocationDelete.bind(this);

    // I don't love having this method, but it's necessary if we assume that the app
    // is running on port 8080 on the same host. We can't do a relative URL with the port, sadly
    this.baseFetchUrl = `${window.location.protocol}//${window.location.hostname}:8080`;

    this.state = {
      username: "",
      usernameError: "",
      signupUsername: "",
      signupError: "",
      showSignup: true,
      showWeather: false,
      user: {},
      weather: {},
      weatherError: "",
      addLocationError: ""
    };
  }

  async updateWeather(forceUpdate = false) {
    let url = `${this.baseFetchUrl}/api/weather/?userId=${this.state.user.id}&forceUpdate=${forceUpdate}`;
    let response = await fetch(url);
    let json = await response.json();

    if ("error" in json) {
      this.setState({
        weatherError: json.error,
        showWeather: true
      });
      return;
    }

    this.setState({
      weather: json,
      weatherError: "",
      showWeather: true
    });
  }

  handleUsernameChange(username) {
    this.setState({
      username: username,
      usernameError: ""
    });
  }

  async handleUsernameSubmit(event) {
    let url = `${this.baseFetchUrl}/api/users/?username=${this.state.username}`;
    let response = await fetch(url);
    let json = await response.json();

    if ("error" in json) {
      this.setState({
        usernameError: json.message
      });
      return;
    }
    this.setState({
      showSignup: false,
      showWeather: true,
      user: json
    });

    this.updateWeather(false);
  }

  handleSignupChange(username) {
    this.setState({
      signupUsername: username,
      signupError: ""
    });
  }

  async handleSignupSubmit(event) {
    let url = `${this.baseFetchUrl}/api/users/`;
    let body = {
      username: this.state.signupUsername
    };
    let response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(body)
    });
    let json = await response.json();

    if ("error" in json) {
      this.setState({
        signupError: json.message
      });
      return;
    }

    this.setState({
      showSignup: false,
      showWeather: true,
      user: json,
      username: json.username
    });
  }

  async handleAddLocation(zip) {
    let url = `${this.baseFetchUrl}/api/locations/`;
    let body = {
      userId: this.state.user.id,
      zip: zip
    };
    let response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(body)
    });
    let json = await response.json();

    if ("error" in json) {
      this.setState({
        addLocationError: json.message
      });
    } else {
      this.setState({
        addLocationError: ""
      });
      this.updateWeather(false);
    }
  }

  async handleLocationDelete(zip) {
    let url = `${this.baseFetchUrl}/api/locations/`;
    let body = {
      userId: this.state.user.id,
      zip: zip
    };
    await fetch(url, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(body)
    });

    this.updateWeather(false);
  }

  async handleUpdateWeatherNow() {
    this.updateWeather(true);
  }

  render() {
    const {
      username,
      signupUsername,
      showSignup,
      usernameError,
      signupError,
      weather,
      weatherError,
      showWeather,
      addLocationError
    } = { ...this.state };

    const weatherLocationCount = "locations" in weather ? weather.locations.length : 0;
    const lastUpdated = this.state.weather.lastUpdated;

    return (
      <div className="App">
        <h1>
          Welcome{" "}
          <UsernamePicker
            username={username}
            usernameError={usernameError}
            onChange={this.handleUsernameChange}
            onSubmit={this.handleUsernameSubmit}
          />
          , here's your weather.
        </h1>
        <SignupForm
          username={signupUsername}
          signupError={signupError}
          show={showSignup}
          onChange={this.handleSignupChange}
          onSubmit={this.handleSignupSubmit}
        />
        <WeatherAccordion
          show={showWeather}
          weather={weather}
          weatherError={weatherError}
          onLocationDelete={this.handleLocationDelete}
          onAddLocation={this.handleAddLocation}
          addLocationError={addLocationError}
        />
        {showWeather && weatherLocationCount > 0 && (
          <p className="last-updated">Last updated: {lastUpdated}</p>
        )}
        {showWeather && weatherLocationCount > 0 && (
          <button className="update-weather-now" onClick={this.handleUpdateWeatherNow}>
            Update Weather Now
          </button>
        )}
      </div>
    );
  }
}

export default App;
