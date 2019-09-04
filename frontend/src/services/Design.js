import { CustomHeader } from 'helper/set-headers'

const header = new CustomHeader();

export class DesignService {
    getAll(projectId){
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/designs/project/${projectId}`, requestOptions)
    }
    get(designId){
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/designs/${designId}`, requestOptions)
    }
    create(designs){
        const requestOptions = {
            method: 'POST',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
            body: JSON.stringify(designs)
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/designs`, requestOptions)
    }
}

