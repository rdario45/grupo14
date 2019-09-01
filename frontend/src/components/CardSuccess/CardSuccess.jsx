import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

export class SuccessCard extends Component {
    render() {
        return (
            <div className="card card-success" style={{
                background: 'rgba(117,189,209,1);',
                background: '-moz-linear-gradient(left, rgba(117,189,209,1) 0%, rgba(73,165,191,1) 59%, rgba(147,206,222,1) 92%, rgba(147,206,222,1) 100%)',
                background: '-webkit-gradient(left top, right top, color-stop(0%, rgba(117,189,209,1)), color-stop(59%, rgba(73,165,191,1)), color-stop(92%, rgba(147,206,222,1)), color-stop(100%, rgba(147,206,222,1)))',
                background: '-webkit-linear-gradient(left, rgba(117,189,209,1) 0%, rgba(73,165,191,1) 59%, rgba(147,206,222,1) 92%, rgba(147,206,222,1) 100%)',
                background: '-o-linear-gradient(left, rgba(117,189,209,1) 0%, rgba(73,165,191,1) 59%, rgba(147,206,222,1) 92%, rgba(147,206,222,1) 100%)',
                background: '-ms-linear-gradient(left, rgba(117,189,209,1) 0%, rgba(73,165,191,1) 59%, rgba(147,206,222,1) 92%, rgba(147,206,222,1) 100%)',
                background: 'linear-gradient(to right, rgba(117,189,209,1) 0%, rgba(73,165,191,1) 59%, rgba(147,206,222,1) 92%, rgba(147,206,222,1) 100%)'
            }}>
                <div className={"header" + (this.props.hCenter ? " text-center" : "")}>
                    <h3 className="title" style={{ color: 'white' }}>{this.props.title}</h3>
                    <p className="category" style={{ color: 'white' }}>{this.props.category}</p>
                </div>
                <div className="content">
                    <Row>
                        <Col xs={5}>
                            <div className="icon-big text-center icon-warning">
                                {this.props.bigIcon}
                            </div>
                        </Col>
                        <Col xs={7}>
                            <div className="numbers">
                                <p>{this.props.statsText}</p>
                                {this.props.statsValue}
                            </div>
                        </Col>
                    </Row>
                    <div className="footer">
                        <hr />
                        <div className="stats" style={{ color: 'white' }}>
                            {this.props.statsIcon} {this.props.statsIconText}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default SuccessCard;
