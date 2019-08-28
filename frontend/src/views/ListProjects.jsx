import React, { Component } from "react";
import { Grid, Row, Col, Table } from "react-bootstrap";
import { Link } from 'react-router-dom'

import Card from "components/Card/Card.jsx";

class ListProjects extends Component {
    constructor(props) {
        super(props);
        this.state = {
            labels: ["Nombre", "Descripción", "Valor estimado a pagar"],
            projects: []
        };
    }

    componentDidMount() {
        this.getProjects();
    }

    getProjects() {
        const projects = [
            {
                projectId: 1,
                name: "1",
                description: "1",
                cost: "111111",
            },
            {
                projectId: 2,
                name: "2",
                description: "2",
                cost: "22222",
            },
        ];
        this.setState({ projects: projects });
    }

    deleteProject(projectId) {
        if (!window.confirm('¿Estás seguro de eliminar el proyecto?')) return;
        this.props.handleClick("El proyecto se ha eliminado.", 'success');
        this.getProjects();
    }

    render() {
        const { labels, projects } = this.state;
        if (!projects || projects.length === 0) {
            return (
                <Card style={{ marginLeft: '10%', marginRight: '10%', marginTop: '5%' }}>
                    <Card.Header style={{ fontWeight: 'bold' }}>Proyectos.</Card.Header>
                    <Card.Body>No hay proyectos asociados a la empresa.</Card.Body>
                </Card>
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
                                                <th colSpan={3}>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {projects.map((event, index) => {
                                                const {
                                                    projectId,
                                                    name,
                                                    description,
                                                    cost } = event
                                                return (
                                                    <tr key={projectId}>
                                                        <td>{name}</td>
                                                        <td>{description}</td>
                                                        <td>{cost}</td>
                                                        <td>
                                                            <Link to={`/admin/project/details/${projectId}/1`}><i className="pe-7s-search text-info"></i></Link>
                                                        </td>
                                                        <td>
                                                            <Link to={`/admin/project/details/${projectId}/0`}><i className="pe-7s-pen text-warning"></i></Link>
                                                        </td>
                                                        <td>
                                                            <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
                                                                onClick={() => { this.deleteProject(projectId); }}>
                                                                <i className="pe-7s-close-circle text-danger"></i>
                                                            </span>
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

export default ListProjects;
