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
                                                    Visitar el home y crear una cuenta.
                                            </p>
                                            </blockquote>
                                        </div>
                                        <div className="typo-line">
                                            <p className="category">Característica 2</p>
                                            <blockquote>
                                                <p>
                                                    Crear, actualizar, corrar y consultar un proyecto.
                                            </p>
                                            </blockquote>
                                        </div>
                                        <div className="typo-line">
                                            <p className="category">Característica 3</p>
                                            <blockquote>
                                                <p>
                                                    Consultar lista de diseños por proyecto.
                                            </p>
                                            </blockquote>
                                        </div>
                                        <div className="typo-line">
                                            <p className="category">Característica 4</p>
                                            <blockquote>
                                                <p>
                                                    Consultar diseño.
                                            </p>
                                            </blockquote>
                                        </div>
                                        <div className="typo-line">
                                            <p className="category">Característica 5</p>
                                            <blockquote>
                                                <p>
                                                    Consultar proyecto, pre-visualizar diseño.
                                            </p>
                                            </blockquote>
                                        </div>
                                        <div className="typo-line">
                                            <p className="category">Característica 6</p>
                                            <blockquote>
                                                <p>
                                                    Almacenar diseños.
                                            </p>
                                            </blockquote>
                                        </div>
                                        <div className="typo-line">
                                            <p className="category">Característica 7</p>
                                            <blockquote>
                                                <p>
                                                    Procesar diseños.
                                            </p>
                                            </blockquote>
                                        </div>
                                        <div className="typo-line">
                                            <p className="category">Característica 8</p>
                                            <blockquote>
                                                <p>
                                                    Enviar mensaje al procesar archivo.
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