import React, { Component } from "react";
import { Grid, Row, Col } from "react-bootstrap";

import Card from "components/Card/Card.jsx";

class Information extends Component {
    render() {
        return (
            <div className="content">
                <Grid fluid>
                    <Row>
                        <Col md={12}>
                            <Card
                                title="Información del sitio web"
                                category="Información"
                                content={
                                    <div>

                                        <div className="typo-line">
                                            <p className="category">Característica 1</p>
                                            <blockquote>
                                                <p>
                                                    Lorem ipsum dolor sit amet, consectetuer adipiscing
                                                    elit, sed diam nonummy nibh euismod tincidunt ut
                                                    laoreet dolore magna aliquam erat volutpat. Ut wisi
                                                    enim ad minim veniam.
                                            </p>
                                            </blockquote>
                                        </div>
                                        <div className="typo-line">
                                            <p className="category">Característica 2</p>
                                            <blockquote>
                                                <p>
                                                    Lorem ipsum dolor sit amet, consectetuer adipiscing
                                                    elit, sed diam nonummy nibh euismod tincidunt ut
                                                    laoreet dolore magna aliquam erat volutpat. Ut wisi
                                                    enim ad minim veniam.
                                            </p>
                                            </blockquote>
                                        </div>


                                    </div>
                                }
                            />
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}

export default Information;