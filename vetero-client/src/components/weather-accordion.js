import React from "react";
import {
  Accordion,
  AccordionItem,
  AccordionItemHeading,
  AccordionItemButton,
  AccordionItemPanel
} from "react-accessible-accordion";
import DeleteLocationButton from "./delete-location-button";
import AddLocation from "./add-location";

class WeatherAccordion extends React.Component {
  constructor(props) {
    super(props);

    this.handleDelete = this.handleDelete.bind(this);
  }

  round(value, precision) {
    const multiplier = Math.pow(10, precision || 0);
    return Math.round(value * multiplier) / multiplier;
  }

  handleDelete(zip) {
    this.props.onLocationDelete(zip);
  }

  render() {
    if (!this.props.show) {
      return <div />;
    } else if (this.props.weatherError.length > 0) {
      return <div>Weather error! {this.props.weatherError}</div>;
    }

    // Sort the locations by city then zip
    let accordionItems = [];

    if (Object.entries(this.props.weather).length > 0) {
      let sortedLocations = this.props.weather.locations.slice(0);
      sortedLocations.sort((a, b) => {
        let aLocationName = a.locationName.toLowerCase();
        let bLocationName = b.locationName.toLowerCase();
        if (a.locationName !== b.locationName) {
          return aLocationName < bLocationName ? -1 : 1;
        }
        return parseInt(a.zip, 10) - parseInt(b.zip, 10);
      });

      accordionItems = sortedLocations.map(location => {
        return (
          <AccordionItem key={location.zip}>
            <AccordionItemHeading>
              <AccordionItemButton>
                <div className="accordion__left">
                  <span className="location-name">{location.locationName}</span>
                  <span className="zip">({location.zip})</span>
                </div>
                <div className="accordion__right">
                  <span className="description">{location.description}</span>
                  <span className="temp degrees">{this.round(location.temp, 0)}</span>
                </div>
              </AccordionItemButton>
            </AccordionItemHeading>
            <AccordionItemPanel>
              <span className="label">Humidity</span>
              <span className="label">Feels Like</span>
              <span className="data">{location.humidity}%</span>
              <span className="data degrees">{this.round(location.feelsLike, 0)}</span>
              <div className="divider" />
              <span className="label">Wind</span>
              <span className="label">Pressure</span>
              <span className="data">
                {location.wind.direction} {this.round(location.wind.speed, 0)} MPH
              </span>
              <span className="data pressure">{this.round(location.pressure, 2)}</span>
              <div className="divider" />
              <div className="button-wrapper">
                <DeleteLocationButton onSubmit={this.handleDelete} zip={location.zip} />
              </div>
            </AccordionItemPanel>
          </AccordionItem>
        );
      });
    }

    accordionItems.push(
      <AccordionItem key={"add-location-accordion-item"} className="accordion__item add-location">
        <AddLocation
          onAddLocation={this.props.onAddLocation}
          addLocationError={this.props.addLocationError}
        />
      </AccordionItem>
    );

    return (
      <Accordion allowMultipleExpanded={true} allowZeroExpanded={true}>
        {accordionItems}
      </Accordion>
    );
  }
}

export default WeatherAccordion;
