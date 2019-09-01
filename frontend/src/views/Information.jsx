import React, { Component } from "react";
import { Grid, Row, Col } from "react-bootstrap";

import Card from "components/Card/Card.jsx";
import { StatsCard } from "components/StatsCard/StatsCard.jsx";

class Information extends Component {
    render() {
        return (
            <div className="content">
                <Grid fluid>
                    <Row>
                        <Col md={12}>
                            <Card
                                title="Información del sitio web"
                                category="Este sitio web ofrece las siguientes funcionalidades."
                                content={
                                    <div className="content">
                                        <Grid fluid>
                                            <Row>
                                                <Col lg={3} sm={6}>
                                                    <StatsCard
                                                        bigIcon={<i className="pe-7s-culture text-warning" />}
                                                        statsText="Empresas"
                                                        statsValue=""
                                                        statsIcon={<i className="fa fa-check" />}
                                                        statsIconText="Visitar el home y crear una cuenta."
                                                    />
                                                </Col>
                                                <Col lg={3} sm={6}>
                                                    <StatsCard
                                                        bigIcon={<i className="pe-7s-home text-success" />}
                                                        statsText="Proyectos"
                                                        statsValue=""
                                                        statsIcon={<i className="fa fa-check" />}
                                                        statsIconText="Crear, actualizar, corrar y consultar un proyecto."
                                                    />
                                                </Col>
                                                <Col lg={3} sm={6}>
                                                    <StatsCard
                                                        bigIcon={<i className="pe-7s-news-paper text-danger" />}
                                                        statsText="Diseños"
                                                        statsValue=""
                                                        statsIcon={<i className="fa fa-check" />}
                                                        statsIconText="Consultar lista de diseños por proyecto."
                                                    />
                                                </Col>
                                                <Col lg={3} sm={6}>
                                                    <StatsCard
                                                        bigIcon={<i className="pe-7s-search text-info" />}
                                                        statsText="Diseños"
                                                        statsValue=""
                                                        statsIcon={<i className="fa fa-check" />}
                                                        statsIconText="Consultar diseño."
                                                    />
                                                </Col>
                                            </Row>
                                            <Row>
                                                <Col lg={3} sm={6}>
                                                    <StatsCard
                                                        bigIcon={<i className="pe-7s-back-2 text-warning" />}
                                                        statsText="Diseños"
                                                        statsValue=""
                                                        statsIcon={<i className="fa fa-check" />}
                                                        statsIconText="Consultar proyecto, pre-visualizar diseño."
                                                    />
                                                </Col>
                                                <Col lg={3} sm={6}>
                                                    <StatsCard
                                                        bigIcon={<i className="pe-7s-door-lock text-success" />}
                                                        statsText="Diseños"
                                                        statsValue=""
                                                        statsIcon={<i className="fa fa-check" />}
                                                        statsIconText="Almacenar diseños."
                                                    />
                                                </Col>
                                                <Col lg={3} sm={6}>
                                                    <StatsCard
                                                        bigIcon={<i className="pe-7s-next text-danger" />}
                                                        statsText="Diseños"
                                                        statsValue=""
                                                        statsIcon={<i className="fa fa-check" />}
                                                        statsIconText="Procesar diseños."
                                                    />
                                                </Col>
                                                <Col lg={3} sm={6}>
                                                    <StatsCard
                                                        bigIcon={<i className="pe-7s-mail-open-file text-info" />}
                                                        statsText="Diseños"
                                                        statsValue=""
                                                        statsIcon={<i className="fa fa-check" />}
                                                        statsIconText="Enviar mensaje al procesar archivo."
                                                    />
                                                </Col>
                                            </Row>
                                        </Grid>
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