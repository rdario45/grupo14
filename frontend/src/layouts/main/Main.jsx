import React, { Component } from "react";
import { Route, Switch, HashRouter as Router } from "react-router-dom";
import NotificationSystem from "react-notification-system";

import AdminNavbar from "components/Navbars/AdminNavbar";
import Footer from "components/Footer/Footer";
import Sidebar from "components/Sidebar/Sidebar";
import FixedPlugin from "components/FixedPlugin/FixedPlugin.jsx";

import { style } from "variables/Variables.jsx";

import routes from "routes/main/routes.js";

import image from "assets/img/sidebar-4.jpg";

class Main extends Component {
    constructor(props) {
        super(props);
        this.state = {
            _notificationSystem: null,
            image: image,
            color: "black",
            hasImage: true,
            fixedClasses: "dropdown"
        };
    }
    getRoutes = routes => {
        return routes.map((prop, key) => {
            if (prop.layout === "/main") {
                return (
                    <Route
                        path={prop.layout + prop.path}
                        render={props => (
                            <prop.component
                                {...props}
                            />
                        )}
                        key={key}
                    />
                );
            } else {
                return null;
            }
        });
    };
    getBrandText = path => {
        for (let i = 0; i < routes.length; i++) {
            if (
                this.props.location.pathname.indexOf(
                    routes[i].layout + routes[i].path
                ) !== -1
            ) {
                return routes[i].name;
            }
        }
        return "Brand";
    };
    handleImageClick = image => {
        this.setState({ image: image });
    };
    handleColorClick = color => {
        this.setState({ color: color });
    };
    handleHasImage = hasImage => {
        this.setState({ hasImage: hasImage });
    };
    handleFixedClick = () => {
        debugger
        if (this.state.fixedClasses === "dropdown") {
            this.setState({ fixedClasses: "dropdown show-dropdown open" });
        } else {
            this.setState({ fixedClasses: "dropdown" });
        }
    };
    componentDidMount() {
        this.setState({ _notificationSystem: this.refs.notificationSystem });
    }
    componentDidUpdate(e) {
        if (
            window.innerWidth < 993 &&
            e.history.location.pathname !== e.location.pathname &&
            document.documentElement.className.indexOf("nav-open") !== -1
        ) {
            document.documentElement.classList.toggle("nav-open");
        }
        if (e.history.action === "PUSH") {
            document.documentElement.scrollTop = 0;
            document.scrollingElement.scrollTop = 0;
            this.refs.mainPanel.scrollTop = 0;
        }
    }
    render() {
        return (
            <div className="wrapper">
                <Sidebar {...this.props} routes={routes} image={this.state.image}
                    color={this.state.color}
                    hasImage={this.state.hasImage} />
                <NotificationSystem ref="notificationSystem" style={style} />
                <div id="main-panel" className="main-panel" ref="mainPanel">
                    <AdminNavbar
                        {...this.props}
                        brandText={this.getBrandText(this.props.location.pathname)}
                    />
                    <Switch history={this.props.history}>{this.getRoutes(routes)}</Switch>
                    <Footer />
                    <FixedPlugin
                        handleImageClick={this.handleImageClick}
                        handleColorClick={this.handleColorClick}
                        handleHasImage={this.handleHasImage}
                        bgColor={this.state["color"]}
                        bgImage={this.state["image"]}
                        mini={this.state["mini"]}
                        handleFixedClick={this.handleFixedClick}
                        fixedClasses={this.state.fixedClasses}
                    />
                </div>
            </div>

        );
    }
}

export default Main;