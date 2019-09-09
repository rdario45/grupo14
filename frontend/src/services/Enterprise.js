import { CustomHeader } from 'helper/set-headers'

const header = new CustomHeader();

export class EnterpriseService {
    isUnique(email){
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            cache: 'default',
        };
        return fetch(`/api/exists-account/${email}`, requestOptions);
    }
    create(enterprise) {
        const requestOptions = {
            method: 'POST',
            headers: header.getWithoutAuthorization(),
            cache: 'default',
            body: JSON.stringify(enterprise)
        };
        return fetch(`/api/create-account`, requestOptions)
    }
}

