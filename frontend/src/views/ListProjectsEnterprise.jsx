import React, { Component } from "react";
import { Grid, Row, Col, Table } from "react-bootstrap";
import { Link } from 'react-router-dom'

import Card from "components/Card/Card.jsx";

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
    render() {
        const { labels, projects } = this.state;
        debugger
        if (!projects || projects.length === 0) {
            return (
                <Card style={{ marginLeft: '10%', marginRight: '10%', marginTop: '5%' }}>

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
                                              <th>Ver diseños</th>
                                          </tr>
                                      </thead>
                                      <tbody>
                                        {projects.map((project, index) => {
                                            const {
                                                projectId,
                                                name,
                                                description,
                                                cost } = project
                                            return (
                                                <tr key={projectId}>
                                                    <td>{name}</td>
                                                    <td>{description}</td>
                                                    <td>{cost}</td>
                                                    <td>
                                                        <Link to={`/design/enterprise/${this.props.match.params.urlEnterprise}/design/list/${projectId}`}><i className="pe-7s-search text-info"></i></Link>
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
