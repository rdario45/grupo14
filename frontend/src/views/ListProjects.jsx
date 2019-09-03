import React, { Component } from "react";
import { Grid, Row, Col, Table } from "react-bootstrap";
import { Link } from 'react-router-dom'

import Card from "components/Card/Card.jsx";

import { ProjectService } from 'services/Project'
const service = new ProjectService();

class ListProjects extends Component {
    constructor(props) {
        super(props);
        this.state = {
            labels: ["Nombre", "Descripción", "Valor estimado a pagar"],
            projects: [],
            companyId: localStorage.getItem('enterpriseId')
        };
        if (!this.state.companyId)
            this.props.history.push('/admin/user/logout');
    }
    componentDidMount() {
        this.getProjects();
    }
    getProjects() {
        service.getAll(this.state.companyId)
            .then(response => {
                if (response.ok)
                    return response.json();
                else
                    this.props.handleClick("Se ha generado un error listando los proyectos.", 'error');
            })
            .then(projects => {
                if (projects && projects.length > 0)
                    this.setState({ projects: projects });
            });
    }
    deleteProject(projectId) {
        if (!window.confirm('¿Estás seguro de eliminar el proyecto?')) return;
        service.delete(projectId)
            .then(response => {
                if (response.ok) {
                    this.props.handleClick("El proyecto se ha eliminado.", 'success');
                    this.getProjects();
                }
                else
                    this.props.handleClick("Se ha generado un error eliminando el proyecto.", 'error');
            });
    }
    render() {
        const { labels, projects } = this.state;
        if (!projects || projects.length === 0) {
            return (
                <p>No hay proyectos asociados a la empresa.</p>
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
                                                <th colSpan={4}>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {projects.map((event, index) => {
                                                const {
                                                    id,
                                                    name,
                                                    description,
                                                    cost } = event
                                                return (
                                                    <tr key={id}>
                                                        <td>{name}</td>
                                                        <td>{description}</td>
                                                        <td>{cost}</td>
                                                        <td>
                                                            <Link to={`/admin/project/design/list/${id}`}><i className="pe-7s-photo text-success"></i></Link>
                                                        </td>
                                                        <td>
                                                            <Link to={`/admin/project/details/${id}/1`}><i className="pe-7s-search text-info"></i></Link>
                                                        </td>
                                                        <td>
                                                            <Link to={`/admin/project/details/${id}/0`}><i className="pe-7s-pen text-warning"></i></Link>
                                                        </td>
                                                        <td>
                                                            <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
                                                                onClick={() => { this.deleteProject(id); }}>
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
