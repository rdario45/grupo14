import React, { Component } from 'react';

export default class Logout extends Component {
    constructor(props) {
        super(props);
        debugger
        localStorage.removeItem('token');
        this.props.history.push('/main/login-enterprise');
    }

    render() {
        return (
            <div></div>
        );
    }
}
