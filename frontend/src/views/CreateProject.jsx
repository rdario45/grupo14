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

import { ProjectService } from 'services/Project'
const service = new ProjectService();

class CreateProject extends Component {
    constructor(props) {
        super(props);
        this.state = {
            companyId: localStorage.getItem('enterpriseId'),
            name: "",
            description: "",
            cost: "",
        };
        if (!this.state.companyId)
            this.props.history.push('/admin/user/logout');
    }
    validateForm() {
        return this.state.name.length > 0
            && this.state.description.length > 0
            && this.state.cost > 0;
    }
    handleChange = event => {
        const newState = this.state;
        newState[event.target.id] = event.target.value;
        this.setState(newState);
    }
    handleSubmit = event => {
        event.preventDefault();
        service.create(this.state)
            .then(response => {
                if (response.ok) {
                    this.props.handleClick("Se ha creado el proyecto.", 'success');
                    this.props.history.push('/admin/project/list');
                }
                else
                    this.props.handleClick("Se ha generado un error creando el proyecto.", 'error');
            });
    }
    render() {
        return (
            <div className="content">  <Grid fluid>
                <Row>
                    <Col md={12}>
                        <Card
                            title="Crear Proyecto"
                            content={
                                <form onSubmit={this.handleSubmit}>
                                    <FormInputs
                                        ncols={["col-md-6", "col-md-6"]}
                                        properties={[
                                            {
                                                id: "name",
                                                label: "Nombre",
                                                type: "text",
                                                bsClass: "form-control",
                                                placeholder: "Nombre del proyecto",
                                                value: this.state.name,
                                                onChange: this.handleChange
                                            },
                                            {
                                                id: "cost",
                                                label: "Valor",
                                                type: "number",
                                                bsClass: "form-control",
                                                placeholder: "Valor estimado a pagar",
                                                step: "0.01",
                                                min: "1",
                                                value: this.state.cost,
                                                onChange: this.handleChange
                                            },
                                        ]}
                                    />
                                    <Row>
                                        <Col md={12}>
                                            <FormGroup>
                                                <ControlLabel>Descripción</ControlLabel>
                                                <FormControl
                                                    id="description"
                                                    rows="5"
                                                    componentClass="textarea"
                                                    bsClass="form-control"
                                                    placeholder="Descripción del proyecto"
                                                    value={this.state.description}
                                                    onChange={this.handleChange}
                                                />
                                            </FormGroup>
                                        </Col>
                                    </Row>
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

export default CreateProject;