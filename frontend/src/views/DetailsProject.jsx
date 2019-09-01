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

class DetailsProject extends Component {
    constructor(props) {
        super(props);
        this.state = {
            projectId: props.match.params.projectId,
            name: "",
            description: "",
            cost: "",
            isReadonly: props.match.params.isReadonly === "1"
        };
    }
    componentDidMount() {
        this.getProject();
    }
    getProject() {
        const newState = this.state;
        newState.name = "adsad";
        newState.description = "tetertr";
        newState.cost = 10000.55;
        this.setState(newState);
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
        const { name, description, cost } = this.state;
        this.props.handleClick("Se ha actualizado el proyecto.", 'success');
        this.props.history.push('/admin/project/list');
    }
    render() {
        const { isReadonly } = this.state;
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
                                                onChange: this.handleChange,
                                                disabled: isReadonly
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
                                                onChange: this.handleChange,
                                                disabled: isReadonly
                                            },
                                        ]}
                                    />
                                    <Row>
                                        <Col md={12}>
                                            <FormGroup controlId="textAreaDescripcion">
                                                <ControlLabel>Descripción</ControlLabel>
                                                <FormControl
                                                    id="description"
                                                    rows="5"
                                                    componentClass="textarea"
                                                    bsClass="form-control"
                                                    placeholder="Descripción del proyecto"
                                                    value={this.state.description}
                                                    onChange={this.handleChange}
                                                    disabled={isReadonly}
                                                />
                                            </FormGroup>
                                        </Col>
                                    </Row>
                                    <Button bsStyle="default"
                                                fill
                                                onClick={this.props.history.goBack}>Atrás</Button>
                                    {!isReadonly && (
                                        <Button
                                            bsStyle="success"
                                            fill
                                            type="submit"
                                            disabled={!this.validateForm()}>Editar</Button>
                                    )}
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

export default DetailsProject;