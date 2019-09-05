import React, { Component } from "react";
import { Grid, Row, Col, Table } from "react-bootstrap";
import { Link } from 'react-router-dom'

import Card from "components/Card/Card.jsx";

import { ProjectService } from 'services/Project'
const service = new ProjectService();

class ListProjectsEnterprise extends Component {
    constructor(props) {
        super(props);
        this.state = {
            labels: ["Nombre", "Descripción", "Valor estimado a pagar"],
            projects: [],
            idEnterprise: props.match.params.urlEnterprise.split('-')[1],
        };
    }
    componentDidMount() {
        this.getProjects();
    }
    getProjects() {
        service.getAll(this.state.idEnterprise)
            .then(response => {
                if (response.ok)
                    return response.json();
                else
                    this.props.handleClick("Se ha generado un error listando los proyectos.", 'error');
            })
            .then(projects => {
                this.setState({ projects: projects });
            });
    }
    render() {
        const { labels, projects } = this.state;
        if (!projects || projects.length === 0) {
            return (
                <div className="content">
                    <Grid fluid>
                        <Row>
                            <Col md={12}>
                                <Card
                                    title="Listado de diseños"
                                    ctTableFullWidth
                                    ctTableResponsive
                                    content={
                                        <p>No hay proyectos asociados a la empresa.</p>
                                    }
                                />
                            </Col>
                        </Row>
                        <Row>
                            <Col md={1}>
                                <Button bsStyle="default"
                                    fill
                                    onClick={this.props.history.goBack}>Atrás</Button>
                            </Col>
                            <Col md={5}></Col>
                            <Col md={6}></Col>
                        </Row>
                    </Grid>
                </div>
            );
        }
        return (
            <div className="content">
                <Grid fluid>
                    <Row>
                        <Col md={12}>
                            <Card
                                title="Listado de proyectos"
                                ctTableFullWidth
                                ctTableResponsive
                                content={
                                    <Table striped hover>
                                        <thead align="center">
                                            <tr>
                                                {labels.map((prop, key) => {
                                                    return <th key={key}>{prop}</th>;
                                                })}
                                                <th>Ver diseños</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {projects.map((project, index) => {
                                                const {
                                                    id,
                                                    name,
                                                    description,
                                                    cost } = project
                                                return (
                                                    <tr key={id}>
                                                        <td>{name}</td>
                                                        <td>{description}</td>
                                                        <td>{cost}</td>
                                                        <td>
                                                            <Link to={`/design/enterprise/${this.props.match.params.urlEnterprise}/design/list/${id}`}><i className="pe-7s-search text-info"></i></Link>
                                                        </td>
                                                    </tr>
                                                )
                                            })}
                                        </tbody>
                                    </Table>
                                }
                            />
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}

export default ListProjectsEnterprise;
