import React from "react";

class SignupForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = { value: "" };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.props.onChange(event.target.value);
  }

  handleSubmit(event) {
    event.preventDefault();
    this.props.onSubmit(event);
  }

  render() {
    if (this.props.show) {
      return (
        <div>
          <span>Don't have a username? Enter one here: </span>
          <form onSubmit={this.handleSubmit} className="signup-form">
            <input type="text" value={this.props.username} onChange={this.handleChange} />
            <input type="submit" value="Sign up!" />
          </form>
          <p className="signup-error">{this.props.signupError}</p>
        </div>
      );
    } else {
      return <div />;
    }
  }
}

export default SignupForm;
