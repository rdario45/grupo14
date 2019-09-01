import React, { Component } from "react";
import Modal from 'react-modal';
import { Grid, Row, Col, Table } from "react-bootstrap";

import Card from "components/Card/Card.jsx";
import Button from "components/CustomButton/CustomButton.jsx";

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
            }
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
        const designs = [
            {
                designId: 3,
                creationDate: "2019-08-27 23:11",
                email: '2222@dasdsa.dadas',
                firstName: 'dasd',
                lastName: 'dasd',
                price: 100000000,
                state: 'En Proceso',
                picture: "https://hbr.org/resources/images/article_assets/2018/08/R1805D_CHIN.jpg",
                pictureProcessed: "https://hbr.org/resources/images/article_assets/2018/08/R1805D_CHIN.jpg",
            },
            {
                designId: 2,
                creationDate: "2019-08-27 15:55",
                email: '1111@dasdsa.dadas',
                firstName: 'dasd',
                lastName: 'dasd',
                price: 100000000,
                state: 'En Proceso',
                picture: "https://public-media.interaction-design.org/images/ux-daily/5628f8c6cdb9d.jpg",
                pictureProcessed: "https://hbr.org/resources/images/article_assets/2018/08/R1805D_CHIN.jpg",
            },
            {
                designId: 1,
                creationDate: "2019-08-27 07:15",
                email: '2222@dasdsa.dadas',
                firstName: 'dasd',
                lastName: 'dasd',
                price: 100000000,
                state: 'En Proceso',
                picture: "https://blog.intercomassets.com/blog/wp-content/uploads/2018/05/Design-leadership-as-a-subversive-activity-.png",
                pictureProcessed: "https://hbr.org/resources/images/article_assets/2018/08/R1805D_CHIN.jpg",
            },
        ];
        this.setState({ designs: designs });
    }
    render() {
        const { labels, designs } = this.state;
        if (!designs || designs.length === 0) {
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
                                                    designId,
                                                    creationDate,
                                                    email,
                                                    firstName,
                                                    lastName,
                                                    price,
                                                    state,
                                                    picture,
                                                    pictureProcessed
                                                } = design
                                                return (
                                                    <tr key={designId}>
                                                        <td>{designId}</td>
                                                        <td>{creationDate}</td>
                                                        <td>{email}</td>
                                                        <td>{firstName}</td>
                                                        <td>{lastName}</td>
                                                        <td>{price}</td>
                                                        <td>{state}</td>
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
                                style={{width: '100%', height: '100%'}} />
                        </Col>
                    </Row>
                    <Row>
                        <br/>
                        <br/>
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