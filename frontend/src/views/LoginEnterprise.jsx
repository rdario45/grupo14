import React, { Component } from "react";
import {
    Grid,
    Row,
    Col,
} from "react-bootstrap";

import { Card } from "components/Card/Card.jsx";
import { FormInputs } from "components/FormInputs/FormInputs.jsx";
import Button from "components/CustomButton/CustomButton.jsx";

class LoginEnterprise extends Component {
    render() {
        return (
            <div className="content">
            <Grid fluid>
                <Row>
                    <Col md={4}>
                        <Card
                            title="Iniciar Sesión"
                            content={
                                <form>
                                    <FormInputs
                                        ncols={["col-md-12"]}
                                        properties={[
                                            {
                                                label: "Correo",
                                                type: "email",
                                                bsClass: "form-control",
                                                placeholder: "Correo de la empresa",
                                            },
                                        ]}
                                    />
                                    <FormInputs
                                        ncols={["col-md-12"]}
                                        properties={[
                                            {
                                                label: "Contraseña",
                                                type: "password",
                                                bsClass: "form-control",
                                                placeholder: "Contraseña de cuenta",
                                            },
                                        ]}
                                    />
                                    <Button bsStyle="success" pullRight fill type="submit">
                                        Iniciar Sesión
                                    </Button>
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

export default LoginEnterprise;
