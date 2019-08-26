import React, { Component } from "react";
import {
    Grid,
    Row,
    Col,
} from "react-bootstrap";

import { Card } from "components/Card/Card.jsx";
import { FormInputs } from "components/FormInputs/FormInputs.jsx";
import Button from "components/CustomButton/CustomButton.jsx";

class CreateEnterprise extends Component {
    render() {
        return (
            <div className="content">  <Grid fluid>
                <Row>
                    <Col md={12}>
                        <Card
                            title="Crear Cuenta de Empresa"
                            content={
                                <form>
                                    <FormInputs
                                        ncols={["col-md-6", "col-md-6"]}
                                        properties={[
                                            {
                                                label: "Nombre de la empresa",
                                                type: "text",
                                                bsClass: "form-control",
                                                placeholder: "Empresa",
                                            },
                                            {
                                                label: "Correo",
                                                type: "email",
                                                bsClass: "form-control",
                                                placeholder: "Correo de la empresa",
                                            },
                                        ]}
                                    />
                                    <FormInputs
                                        ncols={["col-md-6", "col-md-6"]}
                                        properties={[
                                            {
                                                label: "Contraseña",
                                                type: "password",
                                                bsClass: "form-control",
                                                placeholder: "Contraseña de cuenta",
                                            },
                                            {
                                                label: "Confirmación de contraseña",
                                                type: "password",
                                                bsClass: "form-control",
                                                placeholder: "Confirmar Contraseña de cuenta",
                                            },
                                        ]}
                                    />
                                    <Button bsStyle="success" pullRight fill type="submit">
                                        Crear
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

export default CreateEnterprise;
