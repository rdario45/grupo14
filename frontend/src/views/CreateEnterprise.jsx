import React, { Component } from "react";
import {
    Grid,
    Row,
    Col,
} from "react-bootstrap";

import { Card } from "components/Card/Card.jsx";
import { FormInputs } from "components/FormInputs/FormInputs.jsx";
import Button from "components/CustomButton/CustomButton.jsx";
import { SuccessCard } from "components/CardSuccess/CardSuccess.jsx";

import { EnterpriseService } from 'services/Enterprise'

const service = new EnterpriseService();

class CreateEnterprise extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            email: "",
            password: "",
            passwordConfirmation: "",
            urlEnterprise: null,
        };
    }
    validateForm() {
        return this.state.name.length > 0
            && this.state.email.length > 0
            && this.state.password.length > 0
            && this.state.passwordConfirmation.length > 0
            && this.state.urlEnterprise === null;
    }
    handleChange = event => {
        const newState = this.state;
        newState[event.target.id] = event.target.value;
        this.setState(newState);
    }
    handleSubmit = event => {
        event.preventDefault();
        const { name, email, password, passwordConfirmation } = this.state;
        if (password !== passwordConfirmation) {
            this.props.handleClick("Las contraseñas deben ser iguales.", 'error');
            return;
        }
        if (!email) {
            this.props.handleClick("El email debe ser único.", 'error');
            return;
        }
        service.isUnique(email)
            .then(response => {
                if (response.ok)
                    this.createEnterprise(name, email, password);
                else if (response.status === 404)
                    this.props.handleClick("El correo ya está en uso.", 'error');
                else
                    this.props.handleClick("Ha ocurrido un error creando la cuenta de la empresa.", 'error');
            })
    }
    createEnterprise(name, email, password) {
        service.create({ name, email, password })
            .then(response => {
                if (response.ok) {
                    const partURLEnterprise = 'miempresa-10';
                    this.setState({ urlEnterprise: `${window.location.origin.toString()}/#/design/enterprise/${partURLEnterprise}/design/list` });
                    this.props.handleClick("Se ha creado la cuenta de la empresa.", 'success');
                }
                else
                    this.props.handleClick("Ha ocurrido un error creando la cuenta de la empresa.", 'error');
            });
    }

    render() {
        return (
            <div className="content">
                <Grid fluid>
                    <Row>
                        <Col md={12}>
                            <Card
                                title="Crear Cuenta de Empresa"
                                content={
                                    <form onSubmit={this.handleSubmit}>
                                        {this.state.urlEnterprise !== null && (<SuccessCard
                                            title="URL pública de empresa"
                                            category="Comparte esta URL a tus diseñadores para que puedan publicar sus diseños."
                                            statsIcon={<i className="fa fa-check" />}
                                            statsIconText={this.state.urlEnterprise}
                                        />)}
                                        <FormInputs
                                            ncols={["col-md-6", "col-md-6"]}
                                            properties={[
                                                {
                                                    id: "name",
                                                    label: "Nombre de la empresa",
                                                    type: "text",
                                                    bsClass: "form-control",
                                                    placeholder: "Empresa",
                                                    value: this.state.name,
                                                    onChange: this.handleChange
                                                },
                                                {
                                                    id: "email",
                                                    label: "Correo",
                                                    type: "email",
                                                    bsClass: "form-control",
                                                    placeholder: "Correo de la empresa",
                                                    value: this.state.email,
                                                    onChange: this.handleChange
                                                },
                                            ]}
                                        />
                                        <FormInputs
                                            ncols={["col-md-6", "col-md-6"]}
                                            properties={[
                                                {
                                                    id: "password",
                                                    label: "Contraseña",
                                                    type: "password",
                                                    bsClass: "form-control",
                                                    placeholder: "Contraseña de cuenta",
                                                    value: this.state.password,
                                                    onChange: this.handleChange
                                                },
                                                {
                                                    id: "passwordConfirmation",
                                                    label: "Confirmación de contraseña",
                                                    type: "password",
                                                    bsClass: "form-control",
                                                    placeholder: "Confirmar Contraseña de cuenta",
                                                    value: this.state.passwordConfirmation,
                                                    onChange: this.handleChange
                                                },
                                            ]}
                                        />
                                        <Button
                                            bsStyle="success"
                                            fill
                                            type="submit"
                                            disabled={!this.validateForm()}>Crear</Button>
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

export default CreateEnterprise;
