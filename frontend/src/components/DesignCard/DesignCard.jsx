import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

export class DesignCard extends Component {
  render() {
    return (
      <div className="card card-stats">
        <div className="content">
          <Row>
            <Col xs={3}>
              <div className="icon-big text-center icon-warning">
                {this.props.bigIcon}
              </div>
            </Col>
            <Col xs={9}>
              <div className="numbers">
                <p>{this.props.name}</p>
                {this.props.cost}
              </div>
            </Col>
          </Row>
          <div className="footer">
            <hr />
            <div className="stats">
              {this.props.statsIcon} {this.props.description}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default DesignCard;
