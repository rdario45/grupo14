import React, { Component } from "react";
import Modal from 'react-modal';
import { Grid, Row, Col, Table } from "react-bootstrap";

import PropTypes from 'prop-types';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import TablePagination from '@material-ui/core/TablePagination';
import IconButton from '@material-ui/core/IconButton';
import FirstPageIcon from '@material-ui/icons/FirstPage';
import KeyboardArrowLeft from '@material-ui/icons/KeyboardArrowLeft';
import KeyboardArrowRight from '@material-ui/icons/KeyboardArrowRight';
import LastPageIcon from '@material-ui/icons/LastPage';

import Card from "components/Card/Card.jsx";
import Button from "components/CustomButton/CustomButton.jsx";

import { DesignService } from 'services/Design'
const service = new DesignService();

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

Modal.setAppElement('#root');
class ListDesign extends Component {
    constructor(props) {
        super(props);
        this.state = {
            labels: ["Id", "Fechan de publicación", "Ver diseño original", "Ver diseño procesado"],
            designs: [],
            modals: {
                isOpen: false,
                urlImage: null,
                alt: null
            },
            enterpriseId: props.match.params.urlEnterprise.split('-')[1],
            projectId: props.match.params.projectId,
            totalItems: 0,
            currentPage: 0
        };
        this.openModalImage = this.openModalImage.bind(this);
        this.closeModalImage = this.closeModalImage.bind(this);
        this.handleChangePage = this.handleChangePage.bind(this);
        this.handleChangeRowsPerPage = this.handleChangeRowsPerPage.bind(this);
    }
    componentDidMount() {
        this.getDesigns(1);
    }
    getDesigns(page) {
        service.getAllProcessed(this.state.projectId, page)
            .then(response => {
                if (response.ok)
                    return response.json();
                else
                    this.props.handleClick("Se ha generado un error listando los diseños.", 'error');
            })
            .then(designs => {
                this.setState({ designs: designs.designs });
                this.setState({ totalItems: designs.count });
            });
    }
    openModalImage(designId, fileName, isProcessed) {
      let serviceDownload;
      if (isProcessed)
        serviceDownload = service.downloadProcessed(designId);
      else
        serviceDownload = service.downloadOriginal(designId);
        serviceDownload
            .then(response => {
                if (response.ok)
                    return response.blob();
                else
                    this.props.handleClick("Se ha generado un error descargando la imagen.", 'error');
            })
            .then(blob => {
                if (!blob) {
                    this.props.handleClick("No se ha encontrado la imagen.", 'error');
                    return
                }
                let currentModals = this.state.modals;
                currentModals.isOpen = true;
                currentModals.urlImage = URL.createObjectURL(blob);
                currentModals.alt = fileName;
                this.setState({ modals: currentModals });
            });
    }
    closeModalImage() {
        let currentModals = this.state.modals;
        currentModals.isOpen = false;
        currentModals.urlImage = null;
        currentModals.alt = null;
        this.setState({ modals: currentModals });
    }
    handleChangePage(event, newPage) {
        const page = newPage + 1;
        this.setState({ currentPage: newPage });
        this.getProjects(page);
    }
    handleChangeRowsPerPage() {

    }
    render() {
        const { labels, designs, totalItems, currentPage } = this.state;
        if (!designs || designs.length === 0) {
            return (
                <div className="content">
                    <Grid fluid>
                        <Row>
                            <Col md={1}>
                                <Button bsStyle="success"
                                    fill
                                    onClick={() => {
                                        const urlEnterprise = this.props.match.params.urlEnterprise;
                                        const projectId = this.state.projectId;
                                        this.props.history.push(`/design/enterprise/${urlEnterprise}/design/create/${projectId}`);
                                    }}>Crear diseño</Button>
                            </Col>
                            <Col md={5}></Col>
                            <Col md={6}></Col>
                        </Row>
                        <Row>
                            <br />
                        </Row>
                        <Row>
                            <Col md={12}>
                                <Card
                                    title="Listado de diseños"
                                    ctTableFullWidth
                                    ctTableResponsive
                                    content={
                                        <p>No hay diseños asociados al proyecto de la empresa.</p>
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
                        <Col md={1}>
                            <Button bsStyle="success"
                                fill
                                onClick={() => {
                                    const urlEnterprise = this.props.match.params.urlEnterprise;
                                    const projectId = this.state.projectId;
                                    this.props.history.push(`/design/enterprise/${urlEnterprise}/design/create/${projectId}`);
                                }}>Crear diseño</Button>
                        </Col>
                        <Col md={5}></Col>
                        <Col md={6}></Col>
                    </Row>
                    <Row>
                        <br />
                    </Row>
                    <Row>
                        <Col md={12}>
                            <Card
                                title="Listado de diseños"
                                ctTableFullWidth
                                ctTableResponsive
                                content={
                                    <Table striped hover>
                                        <thead align="center">
                                            <tr>
                                                {labels.map((prop, key) => {
                                                    return <th key={key}>{prop}</th>;
                                                })}
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {designs.map((design, index) => {
                                                const {
                                                    id,
                                                    uploadDate,
                                                    fileName
                                                } = design
                                                return (
                                                    <tr key={id}>
                                                        <td>{id}</td>
                                                        <td>{uploadDate}</td>
                                                        <td>
                                                            <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
                                                                onClick={() => { this.openModalImage(id, fileName); }}>
                                                                <i className="pe-7s-search text-info"></i>
                                                            </span>
                                                        </td>
                                                        <td>
                                                            <span style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
                                                                onClick={() => { this.openModalImage(id, fileName); }}>
                                                                <i className="pe-7s-search text-info"></i>
                                                            </span>
                                                        </td>
                                                    </tr>
                                                )
                                            })}
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <TablePagination
                                                    rowsPerPageOptions={[10]}
                                                    colSpan={4}
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
                <Modal
                    style={{ textAlign: 'center' }}
                    isOpen={this.state.modals.isOpen}
                    contentLabel={this.state.modals.alt}>
                    <Row>
                        <Col md={2}></Col>
                        <Col md={10}>
                            <img src={this.state.modals.urlImage}
                                alt={this.state.modals.alt}
                                style={{ width: '100%', height: '100%' }} />
                        </Col>
                    </Row>
                    <Row>
                        <br />
                        <br />
                    </Row>
                    <Row>
                        <Col md={5}></Col>
                        <Col md={6}></Col>
                        <Col md={1}>
                            <Button bsStyle="success"
                                fill
                                onClick={this.closeModalImage}>Cerrar</Button>
                        </Col>
                    </Row>
                </Modal>
            </div>
        );
    }
}

export default ListDesign;
