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
            labels: ["Id", "Fechan de publicación", 'Correo', 'Nombres', 'Apellidos', 'Precio', 'Estado', "Descargar diseño original", "Descargar diseño procesado", "Previsiualizar diseño procesado"],
            designs: [],
            modals: {
                isOpen: false,
                urlImage: null,
                alt: null
            },
            projectId: props.match.params.projectId
        };
        this.openModalImage = this.openModalImage.bind(this);
        this.closeModalImage = this.closeModalImage.bind(this);
    }
    componentDidMount() {
        this.getDesigns();
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
    downloadOriginal(designId, fileName) {
        service.downloadOriginal(designId)
            .then(response => {
                if (response.ok)
                    return response.blob();
                else
                    this.props.handleClick("Se ha generado un error descargando la imagen.", 'error');
            })
            .then(blob => {
                if (!blob) {
                    this.props.handleClick("No se ha encontrado la imagen.", 'error');
                    return
                }
                const url = window.URL.createObjectURL(new Blob([blob]));
                const link = document.createElement('a');
                link.href = url;
                console.log(url);
                link.setAttribute('download', fileName);
                document.body.appendChild(link);
                link.click();
                link.parentNode.removeChild(link);
            });
    }
    downloadProcessed(designId, fileName) {
        service.downloadProcessed(designId)
            .then(response => {
                if (response.ok)
                    return response.blob();
                else
                    this.props.handleClick("Se ha generado un error descargando la imagen.", 'error');
            })
            .then(blob => {
                if (!blob) {
                    this.props.handleClick("No se ha encontrado la imagen.", 'error');
                    return
                }
                const url = window.URL.createObjectURL(new Blob([blob]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', fileName);
                document.body.appendChild(link);
                link.click();
                link.parentNode.removeChild(link);
            });
    }
    openModalImage(designId, fileName) {
        service.downloadProcessed(designId)
            .then(response => {
                if (response.ok)
                    return response.blob();
                else
                    this.props.handleClick("Se ha generado un error descargando la imagen.", 'error');
            })
            .then(blob => {
                if (!blob) {
                    this.props.handleClick("No se ha encontrado la imagen.", 'error');
                    return
                }
                let currentModals = this.state.modals;
                currentModals.isOpen = true;
                currentModals.urlImage = URL.createObjectURL(blob);
                currentModals.alt = fileName;
                this.setState({ modals: currentModals });
            });
    }
    closeModalImage() {
        let currentModals = this.state.modals;
        currentModals.isOpen = false;
        currentModals.urlImage = null;
        currentModals.alt = null;
        this.setState({ modals: currentModals });
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
                                                    fileName
                                                } = design
                                                const isAvailable = designStatus === 'AVAILABLE'
                                                return (
                                                    <tr key={id}>
                                                        <td>{id}</td>
                                                        <td>{uploadDate}</td>
                                                        <td>{email}</td>
                                                        <td>{firstName}</td>
                                                        <td>{lastName}</td>
                                                        <td>{price}</td>
                                                        <td>{isAvailable ? 'Disponible' : 'En proceso'}</td>
                                                        <td>
                                                            <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
                                                                onClick={() => { this.downloadOriginal(id, fileName); }}>
                                                                <i className="pe-7s-cloud-download"></i>
                                                            </span>
                                                        </td>
                                                        <td>
                                                            {isAvailable &&
                                                                <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
                                                                    onClick={() => { this.downloadProcessed(id, fileName); }}>
                                                                    <i className="pe-7s-cloud-download"></i>
                                                                </span>}
                                                        </td>
                                                        <td>
                                                            {isAvailable &&
                                                                <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
                                                                    onClick={() => { this.openModalImage(id, fileName); }}>
                                                                    <i className="pe-7s-search text-info"></i>
                                                                </span>}
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
                    contentLabel={this.state.modals.alt}>
                    <Row>
                        <Col md={2}></Col>
                        <Col md={10}>
                            <img src={this.state.modals.urlImage}
                                alt={this.state.modals.alt}
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
                                onClick={this.closeModalImage}>Cerrar</Button>
                        </Col>
                    </Row>
                </Modal>
            </div>
        );
    }
}
export default ListDesignEnterprise;