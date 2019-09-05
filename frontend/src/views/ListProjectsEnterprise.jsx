import React, { Component } from "react";
import { Grid, Row, Col, Table } from "react-bootstrap";
import { Link } from 'react-router-dom'

import Card from "components/Card/Card.jsx";
import Button from "components/CustomButton/CustomButton.jsx";

import PropTypes from 'prop-types';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import TablePagination from '@material-ui/core/TablePagination';
import IconButton from '@material-ui/core/IconButton';
import FirstPageIcon from '@material-ui/icons/FirstPage';
import KeyboardArrowLeft from '@material-ui/icons/KeyboardArrowLeft';
import KeyboardArrowRight from '@material-ui/icons/KeyboardArrowRight';
import LastPageIcon from '@material-ui/icons/LastPage';

import { ProjectService } from 'services/Project'
const service = new ProjectService();

const useStyles1 = makeStyles(theme => ({
    root: {
        flexShrink: 0,
        color: theme.palette.text.secondary,
        marginLeft: theme.spacing(2.5),
    },
}));
function TablePaginationActions(props) {
    const classes = useStyles1();
    const theme = useTheme();
    const { count, page, rowsPerPage, onChangePage } = props;

    function handleFirstPageButtonClick(event) {
        onChangePage(event, 0);
    }

    function handleBackButtonClick(event) {
        onChangePage(event, page - 1);
    }

    function handleNextButtonClick(event) {
        onChangePage(event, page + 1);
    }

    function handleLastPageButtonClick(event) {
        onChangePage(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
    }
    return (
        <div className={classes.root}>
            <IconButton
                onClick={handleFirstPageButtonClick}
                disabled={page === 0}
                aria-label="Primera página"
            >
                {theme.direction === 'rtl' ? <LastPageIcon /> : <FirstPageIcon />}
            </IconButton>
            <IconButton onClick={handleBackButtonClick} disabled={page === 0} aria-label="Página previa">
                {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
            </IconButton>
            <IconButton
                onClick={handleNextButtonClick}
                disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                aria-label="Siguiente página"
            >
                {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
            </IconButton>
            <IconButton
                onClick={handleLastPageButtonClick}
                disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                aria-label="Última página"
            >
                {theme.direction === 'rtl' ? <FirstPageIcon /> : <LastPageIcon />}
            </IconButton>
        </div>
    );
}
TablePaginationActions.propTypes = {
    count: PropTypes.number.isRequired,
    onChangePage: PropTypes.func.isRequired,
    page: PropTypes.number.isRequired,
    rowsPerPage: PropTypes.number.isRequired,
};

class ListProjectsEnterprise extends Component {
    constructor(props) {
        super(props);
        this.state = {
            labels: ["Nombre", "Descripción", "Valor estimado a pagar"],
            projects: [],
            idEnterprise: props.match.params.urlEnterprise.split('-')[1],
            totalItems: 0,
            currentPage: 0
        };
        this.handleChangePage = this.handleChangePage.bind(this);
        this.handleChangeRowsPerPage = this.handleChangeRowsPerPage.bind(this);
    }
    componentDidMount() {
        this.getProjects(1);
    }
    getProjects(page) {
        service.getAll(this.state.idEnterprise, page)
            .then(response => {
                if (response.ok)
                    return response.json();
                else
                    this.props.handleClick("Se ha generado un error listando los proyectos.", 'error');
            })
            .then(projects => {
                this.setState({ projects: projects });
                this.setState({ totalItems: 36 });
            });
    }
    handleChangePage(event, newPage) {
        const page = newPage + 1;
        this.setState({ currentPage: newPage });
        this.getProjects(page);
    }
    handleChangeRowsPerPage() {

    }
    render() {
        const { labels, projects, totalItems, currentPage } = this.state;
        if (!projects || projects.length === 0) {
            return (
                <div className="content">
                    <Grid fluid>
                        <Row>
                            <Col md={12}>
                                <Card
                                    title="Listado de diseños"
                                    ctTableFullWidth
                                    ctTableResponsive
                                    content={
                                        <p>No hay proyectos asociados a la empresa.</p>
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
                </div>
            );
        }
        return (
            <div className="content">
                <Grid fluid>
                    <Row>
                        <Col md={12}>
                            <Card
                                title="Listado de proyectos"
                                ctTableFullWidth
                                ctTableResponsive
                                content={
                                    <Table striped hover>
                                        <thead align="center">
                                            <tr>
                                                {labels.map((prop, key) => {
                                                    return <th key={key}>{prop}</th>;
                                                })}
                                                <th>Ver diseños</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {projects.map((project) => {
                                                const {
                                                    id,
                                                    name,
                                                    description,
                                                    cost } = project
                                                return (
                                                    <tr key={id}>
                                                        <td>{name}</td>
                                                        <td>{description}</td>
                                                        <td>{cost}</td>
                                                        <td>
                                                            <Link to={`/design/enterprise/${this.props.match.params.urlEnterprise}/design/list/${id}`}><i className="pe-7s-search text-info"></i></Link>
                                                        </td>
                                                    </tr>
                                                )
                                            })}
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <TablePagination
                                                    rowsPerPageOptions={[10]}
                                                    colSpan={5}
                                                    count={totalItems}
                                                    rowsPerPage={10}
                                                    page={currentPage}
                                                    SelectProps={{
                                                        inputProps: { 'aria-label': 'Filas por página' },
                                                        native: true,
                                                    }}
                                                    onChangePage={this.handleChangePage}
                                                    onChangeRowsPerPage={this.handleChangeRowsPerPage}
                                                    ActionsComponent={TablePaginationActions}
                                                />
                                            </tr>
                                        </tfoot>
                                    </Table>
                                }
                            />
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
}

export default ListProjectsEnterprise;
