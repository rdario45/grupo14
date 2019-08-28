import React, { Component } from "react";
import {
    Grid,
    Row,
    Col,
} from "react-bootstrap";

import { Card } from "components/Card/Card.jsx";
import { FormInputs } from "components/FormInputs/FormInputs.jsx";
import Button from "components/CustomButton/CustomButton.jsx";

class CreateDesign extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            email: "",
            password: "",
            passwordConfirmation: "",
        };
    }
    validateForm() {
        return this.state.name.length > 0
            && this.state.email.length > 0
            && this.state.password.length > 0
            && this.state.passwordConfirmation.length > 0;
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

        this.props.handleClick("Se ha creado la cuenta de la empresa.", 'success');
        this.props.history.push('/main/login-enterprise');
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

export default CreateDesign;
