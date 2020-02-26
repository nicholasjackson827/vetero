import React from "react";

class DeleteLocationButton extends React.Component {
  constructor(props) {
    super(props);

    this.handleClick = this.handleClick.bind(this);

    this.state = {
      numClicks: 0,
      buttonText: "Delete Location"
    };
  }

  handleClick(event) {
    if (this.state.numClicks === 0) {
      this.setState({
        numClicks: this.state.numClicks + 1,
        buttonText: "Really Delete Location?"
      });
    } else {
      this.props.onSubmit(this.props.zip);
    }
  }

  render() {
    return (
      <button
        onClick={this.handleClick}
        className={"delete " + (this.state.numClicks > 0 ? "confirmation" : "")}
      >
        {this.state.buttonText}
      </button>
    );
  }
}

export default DeleteLocationButton;
