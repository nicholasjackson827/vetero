import React from "react";

class UsernamePicker extends React.Component {
  constructor(props) {
    super(props);

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
    return (
      <form onSubmit={this.handleSubmit} className="username-picker">
        <input type="text" value={this.props.username} onChange={this.handleChange} autoFocus />
        <label>username</label>
        <p className="username-error">{this.props.usernameError}</p>
      </form>
    );
  }
}

export default UsernamePicker;
