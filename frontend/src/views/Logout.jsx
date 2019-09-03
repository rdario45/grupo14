import React, { Component } from 'react';

import { AccountService } from 'services/Account'
const service = new AccountService();

export default class Logout extends Component {
    constructor(props) {
        super(props);
        service.logout()
            .then(response => {
                if (response.ok) {
                    localStorage.removeItem('token');
                    localStorage.removeItem('enterpriseId');
                    this.props.history.push('/main/login-enterprise');
                }
                else
                    this.props.handleClick("Ha ocurrido un error cerrando sesión.", 'error');
            });
    }

    render() {
        return (
            <div></div>
        );
    }
}
