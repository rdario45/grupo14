import React, { Component } from "react";
import Modal from 'react-modal';
import { Grid, Row, Col, Table } from "react-bootstrap";

import Card from "components/Card/Card.jsx";
import Button from "components/CustomButton/CustomButton.jsx";

import { DesignService } from 'services/Design'
const service = new DesignService();

Modal.setAppElement('#root');
class ListDesignEnterprise extends Component {
    constructor(props) {
        super(props);
        this.state = {
            labels: ["Id", "Fechan de publicación", 'Correo', 'Nombres', 'Apellidos', 'Precio', 'Estado', "Ver diseño original", "Ver diseño procesado"],
            designs: [],
            modals: {
                isOpen: false,
                urlImage: null
            },
            projectId: props.match.params.projectId
        };
        this.handleToggleImage = this.handleToggleImage.bind(this);
    }
    componentDidMount() {
        this.getDesigns();
    }
    handleToggleImage(urlImage) {
        let currentModals = this.state.modals;
        currentModals.isOpen = !currentModals.isOpen;
        currentModals.urlImage = urlImage;
        this.setState({ modals: currentModals });
    }
    getDesigns() {
        service.getAll(this.state.projectId)
            .then(response => {
                if (response.ok)
                    return response.json();
                else
                    this.props.handleClick("Se ha generado un error listando los diseños.", 'error');
            })
            .then(designs => {
                this.setState({ designs: designs });
            });
    }
    render() {
        const { labels, designs } = this.state;
        if (!designs || designs.length === 0) {
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
                                        <p>No hay diseños asociados al proyecto de la empresa.</p>
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
                                title="Listado de diseños de proyecto"
                                ctTableFullWidth
                                ctTableResponsive
                                content={
                                    <Table striped hover>
                                        <thead align="center">
                                            <tr>
                                                {labels.map((prop, key) => {
                                                    return <th key={key}>{prop}</th>;
                                                })}
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {designs.map((design, index) => {
                                                const {
                                                    id,
                                                    uploadDate,
                                                    email,
                                                    firstName,
                                                    lastName,
                                                    price,
                                                    designStatus,
                                                    picture,
                                                    pictureProcessed
                                                } = design
                                                return (
                                                    <tr key={id}>
                                                        <td>{id}</td>
                                                        <td>{uploadDate}</td>
                                                        <td>{email}</td>
                                                        <td>{firstName}</td>
                                                        <td>{lastName}</td>
                                                        <td>{price}</td>
                                                        <td>{designStatus === 'AVAILABLE' ? 'Disponible' : 'En proceso'}</td>
                                                        <td>
                                                            <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
                                                                onClick={() => { this.handleToggleImage(picture); }}>
                                                                <i className="pe-7s-search text-info"></i>
                                                            </span>
                                                        </td>
                                                        <td>
                                                            <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
                                                                onClick={() => { this.handleToggleImage(pictureProcessed); }}>
                                                                <i className="pe-7s-search text-info"></i>
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
                <Modal
                    style={{ textAlign: 'center' }}
                    isOpen={this.state.modals.isOpen}
                    contentLabel="Imagen Original">
                    <Row>
                        <Col md={2}></Col>
                        <Col md={10}>
                            <img src={this.state.modals.urlImage}
                                alt="imageModal"
                                style={{ width: '100%', height: '100%' }} />
                        </Col>
                    </Row>
                    <Row>
                        <br />
                        <br />
                    </Row>
                    <Row>
                        <Col md={5}></Col>
                        <Col md={6}></Col>
                        <Col md={1}>
                            <Button bsStyle="success"
                                fill
                                onClick={this.handleToggleImage}>Cerrar</Button>
                        </Col>
                    </Row>
                </Modal>
            </div>
        );
    }
}
export default ListDesignEnterprise;