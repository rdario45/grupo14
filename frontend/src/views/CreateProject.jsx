import React, { Component } from "react";
import {
    Grid,
    Row,
    Col,
    FormGroup,
    ControlLabel,
    FormControl
} from "react-bootstrap";

import { Card } from "components/Card/Card.jsx";
import { FormInputs } from "components/FormInputs/FormInputs.jsx";
import Button from "components/CustomButton/CustomButton.jsx";

class CreateProject extends Component {
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
                                                label: "Nombre",
                                                type: "text",
                                                bsClass: "form-control",
                                                placeholder: "Nombre del proyecto",
                                            },
                                            {
                                                label: "Valor",
                                                type: "number",
                                                bsClass: "form-control",
                                                placeholder: "Valor estimado a pagar",
                                                step: "1"
                                            },
                                        ]}
                                    />
                                    <Row>
                                        <Col md={12}>
                                            <FormGroup controlId="textAreaDescripcion">
                                                <ControlLabel>Descripción</ControlLabel>
                                                <FormControl
                                                    rows="5"
                                                    componentClass="textarea"
                                                    bsClass="form-control"
                                                    placeholder="Descripción del proyecto"
                                                />
                                            </FormGroup>
                                        </Col>
                                    </Row>
                                    <Button bsStyle="success" pullLeft fill type="submit">
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

export default CreateProject;