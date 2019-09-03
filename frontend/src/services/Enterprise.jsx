import { CustomHeader } from 'helper/set-headers'

const header = new CustomHeader();

export class EnterpriseService {
    create(enterprise) {
        const requestOptions = {
            method: 'POST',
            headers: header.getWithoutAuthorization(),
            cache: 'default',
            body: JSON.stringify(enterprise)
        };
        // fetch(`${process.env.BASE_API}/create-account`, requestOptions)
        return fetch(`http://localhost:9000/create-account`, requestOptions);
    }
}

