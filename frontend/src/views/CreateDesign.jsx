import React, { Component } from "react";
import {
    Grid,
    Row,
    Col,
} from "react-bootstrap";

import { Card } from "components/Card/Card.jsx";
import { FormInputs } from "components/FormInputs/FormInputs.jsx";
import Button from "components/CustomButton/CustomButton.jsx";
import DragAndDrop from "components/DragAndDrop/DragAndDrop.jsx";

import { DesignService } from 'services/Design'
const service = new DesignService();

class CreateDesign extends Component {
    extensionsAllowed = ["jpg", "jpeg", "bmp", "png"];
    constructor(props) {
        super(props);
        this.state = {
            firstName: "",
            lastName: "",
            email: "",
            price: "",
            file: null,
            idEnterprise: props.match.params.urlEnterprise.split('-')[1],
            projectId: props.match.params.projectId
        };
    }
    assignFile(file) {
        this.state.file = file;
    }
    validateForm() {
        return this.state.firstName.length > 0
            && this.state.lastName.length > 0
            && this.state.email.length > 0
            && this.state.price > 0;
    }
    handleChange = event => {
        const newState = this.state;
        newState[event.target.id] = event.target.value;
        this.setState(newState);
    }
    handleSubmit = event => {
        event.preventDefault();
        const { file, firstName, lastName, email, price, projectId } = this.state;
        if (!file) {
            this.props.handleClick("Debes adjuntar un archivo.", 'error');
            return;
        }
        const extension = file.name.split('.').pop().toLowerCase();
        if (!this.extensionsAllowed.includes(extension)) {
            this.props.handleClick("El archivo no tiene una extensión válida.", 'error');
            return;
        }
        var data = new FormData();
        data.append('picture', file);
        data.append('firstName', firstName);
        data.append('lastName', lastName);
        data.append('email', email);
        data.append('price', price);
        data.append('projectId', projectId);
        service.create(data)
            .then(response => {
                if (response.ok) {
                    this.props.handleClick("Hemos recibido tu diseño y lo estamos procesado para que sea publicado. Tan pronto esto ocurra, te notificaremos por email.", 'success');
                    this.props.history.push(`/design/enterprise/${this.props.match.params.urlEnterprise}/design/list/`);
                }
                else
                    this.props.handleClick("Ha ocurrido un error creando el diseño.", 'error');
            });
    }
    render() {
        return (
            <div className="content">
                <Grid fluid>
                    <Row>
                        <Col md={12}>
                            <Card
                                title="Crear Diseño"
                                content={
                                    <form onSubmit={this.handleSubmit}>
                                        <FormInputs
                                            ncols={["col-md-6", "col-md-6"]}
                                            properties={[
                                                {
                                                    id: "firstName",
                                                    label: "Nombres",
                                                    type: "text",
                                                    bsClass: "form-control",
                                                    placeholder: "Nombre del diseñador",
                                                    value: this.state.firstName,
                                                    onChange: this.handleChange
                                                },
                                                {
                                                    id: "lastName",
                                                    label: "Apellidos",
                                                    type: "text",
                                                    bsClass: "form-control",
                                                    placeholder: "Apellido del diseñador",
                                                    value: this.state.lastName,
                                                    onChange: this.handleChange
                                                },
                                            ]}
                                        />
                                        <FormInputs
                                            ncols={["col-md-6", "col-md-6"]}
                                            properties={[
                                                {
                                                    id: "email",
                                                    label: "Correo",
                                                    type: "email",
                                                    bsClass: "form-control",
                                                    placeholder: "Correo del diseñador",
                                                    value: this.state.email,
                                                    onChange: this.handleChange
                                                },
                                                {
                                                    id: "price",
                                                    label: "Precio solicitado",
                                                    type: "number",
                                                    bsClass: "form-control",
                                                    placeholder: "Precio solicitado del diseño",
                                                    step: "0.01",
                                                    min: "1",
                                                    value: this.state.cost,
                                                    onChange: this.handleChange
                                                },
                                            ]}
                                        />
                                        <Row>
                                            <Col md={12}>
                                                <DragAndDrop assignFile={(file) => { this.assignFile(file) }} />
                                            </Col>
                                        </Row>
                                        <Button bsStyle="default"
                                            fill
                                            onClick={this.props.history.goBack}>Atrás</Button>
                                        <Button
                                            bsStyle="success"
                                            fill
                                            type="submit"
                                            disabled={!this.validateForm()}>Enviar diseño</Button>
                                        <div className="clearfix" />
                                    </form>
                                }
                            />
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}

export default CreateDesign;
