import React, { Component } from "react";
import {
    Grid,
    Row,
    Col,
} from "react-bootstrap";

import { Card } from "components/Card/Card.jsx";
import { FormInputs } from "components/FormInputs/FormInputs.jsx";
import Button from "components/CustomButton/CustomButton.jsx";

import { AccountService } from 'services/Account'
const service = new AccountService();

class LoginEnterprise extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: ""
        };
    }
    validateForm() {
        return this.state.email.length > 0
            && this.state.password.length > 0;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    handleSubmit = event => {
        event.preventDefault();
        service.login(this.state)
            .then(response => {
                if (response.ok) {
                    //localStorage.setItem('token', token.token);
                    localStorage.setItem('enterpriseId', 1);
                    this.props.history.push('/admin/project/create');
                }
                else if (response.status === 400)
                    this.props.handleClick("Credenciales incorrectas.", 'error');
                else
                    this.props.handleClick("Ha ocurrido un error iniciando sesión.", 'error');
            });
    }
    render() {
        return (
            <div className="content">
                <Grid fluid>
                    <Row>
                        <Col md={4}></Col>
                        <Col md={4}>
                            <Card
                                title="Iniciar Sesión"
                                content={
                                    <form onSubmit={this.handleSubmit}>
                                        <FormInputs
                                            ncols={["col-md-12"]}
                                            properties={[
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
                                            ncols={["col-md-12"]}
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
                                            ]}
                                        />
                                        <Button
                                            bsStyle="success"
                                            fill
                                            type="submit"
                                            disabled={!this.validateForm()}>Iniciar Sesión</Button>
                                        <div className="clearfix" />
                                    </form>
                                }
                            />
                        </Col>
                        <Col md={4}></Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}

export default LoginEnterprise;
