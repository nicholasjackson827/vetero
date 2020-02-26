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
    let url = `http://localhost:8080/api/weather/?userId=${this.state.user.id}&forceUpdate=${forceUpdate}`;
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
    let url = `http://localhost:8080/api/users/?username=${this.state.username}`;
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
    let url = `http://localhost:8080/api/users/`;
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
      user: json,
      username: json.username
    });
  }

  async handleAddLocation(zip) {
    console.log(`Adding location ${zip}!`);
    let url = `http://localhost:8080/api/locations/`;
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
    console.log("Gotta delete location with zip " + zip + "!");
    let url = `http://localhost:8080/api/locations/`;
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
    const username = this.state.username;
    const signupUsername = this.state.signupUsername;
    const showSignup = this.state.showSignup;
    const usernameError = this.state.usernameError;
    const signupError = this.state.signupError;
    const weather = this.state.weather;
    const weatherError = this.state.weatherError;
    const showWeather = this.state.showWeather;
    const addLocationError = this.state.addLocationError;

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
        {showWeather && <p className="last-updated">Last updated: {lastUpdated}</p>}
        {showWeather && (
          <button className="update-weather-now" onClick={this.handleUpdateWeatherNow}>
            Update Weather Now
          </button>
        )}
      </div>
    );
  }
}

export default App;
