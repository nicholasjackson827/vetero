import React from "react";

class AddLocation extends React.Component {
  constructor(props) {
    super(props);

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);

    this.state = {
      locationName: ""
    };
  }

  handleChange(event) {
    this.setState({
      locationName: event.target.value
    });
  }

  handleSubmit(event) {
    event.preventDefault();
    this.props.onAddLocation(this.state.locationName);
    this.setState({
      locationName: ""
    });
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit} className="add-location">
        <label>Add Location: </label>
        <input
          type="text"
          value={this.state.locationName}
          onChange={this.handleChange}
          placeholder={"Zip"}
        />
        <span className="add-location-error">{this.props.addLocationError}</span>
      </form>
    );
  }
}

export default AddLocation;
