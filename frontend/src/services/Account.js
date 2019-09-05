import { CustomHeader } from 'helper/set-headers'

const header = new CustomHeader();

export class AccountService {
    login(account){
        const requestOptions = {
            method: 'POST',
            headers: header.getWithoutAuthorization(),
            cache: 'default',
            body: JSON.stringify(account)
        };
        return fetch(`/api/login`, requestOptions)
    }
    logout() {
        const requestOptions = {
            method: 'POST',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
            body: JSON.stringify({})
        };
        return fetch(`/api/logout`, requestOptions)
    }
}

