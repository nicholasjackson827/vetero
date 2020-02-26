import React, { Component } from "react";
import "./App.css";
import UsernamePicker from "./components/username-picker";
import SignupForm from "./components/signup-form";

class App extends Component {
  constructor(props) {
    super(props);

    this.handleUsernameChange = this.handleUsernameChange.bind(this);
    this.handleUsernameSubmit = this.handleUsernameSubmit.bind(this);

    this.handleSignupChange = this.handleSignupChange.bind(this);
    this.handleSignupSubmit = this.handleSignupSubmit.bind(this);

    this.state = {
      username: "",
      usernameError: "",
      signupUsername: "",
      signupError: "",
      showSignup: true,
      user: {}
    };
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
    } else {
      this.setState({
        showSignup: false,
        user: json
      });
    }
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
    } else {
      this.setState({
        showSignup: false,
        user: json,
        username: json.username
      });
    }
  }

  render() {
    const username = this.state.username;
    const signupUsername = this.state.signupUsername;
    const showSignup = this.state.showSignup;
    const usernameError = this.state.usernameError;
    const signupError = this.state.signupError;

    return (
      <div className="App">
        <div className="App-header">
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
        </div>
      </div>
    );
  }
}

export default App;
