import React, { Component } from "react";
import { Grid, Row, Col, Table } from "react-bootstrap";
import { Link } from 'react-router-dom'

import Card from "components/Card/Card.jsx";
import { StatsCard } from "components/StatsCard/StatsCard.jsx";

class ListDesign extends Component {
  constructor(props) {
    debugger
      super(props);
      this.state = {
          labels: ["Id", "Fechan de publicación", "Ver diseño original", "Ver diseño procesado"],
          designs: [],
          idEnterprise: props.match.params.urlEnterprise.split('-')[1],
      };
  }
  componentDidMount() {
      this.getDesigns();
  }
  getDesigns() {
      const designs = [
          {
              designId: 3,
              creationDate: "2019-08-27 23:11",
              //price: 100000000,
              //picture: "https://hbr.org/resources/images/article_assets/2018/08/R1805D_CHIN.jpg",
          },
          {
            designId: 2,
            creationDate: "2019-08-27 15:55",
            //price: 20000,
            //picture: "https://public-media.interaction-design.org/images/ux-daily/5628f8c6cdb9d.jpg",
          },
          {
            designId: 1,
            creationDate: "2019-08-27 07:15",
            //price: 340000,
            //picture: "https://blog.intercomassets.com/blog/wp-content/uploads/2018/05/Design-leadership-as-a-subversive-activity-.png",
          },
      ];
      this.setState({ designs: designs });
  }
    render() {
        const { labels, designs } = this.state;
        if (!designs || designs.length === 0) {
            return (
                <Card style={{ marginLeft: '10%', marginRight: '10%', marginTop: '5%' }}>
                    
                </Card>
            );
        }
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
                                                  designId,
                                                  creationDate
                                              } = design
                                              return (
                                                  <tr key={designId}>
                                                      <td>{designId}</td>
                                                      <td>{creationDate}</td>
                                                      <td>
                                                          <Link to={`/design/enterprise/${this.props.match.params.urlEnterprise}/design/list/${designId}`}><i className="pe-7s-search text-info"></i></Link>
                                                      </td>
                                                      <td>
                                                          <Link to={`/design/enterprise/${this.props.match.params.urlEnterprise}/design/list/${designId}`}><i className="pe-7s-search text-info"></i></Link>
                                                      </td>
                                                  </tr>
                                              )
                                          })}
                                      </tbody>
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

export default ListDesign;
