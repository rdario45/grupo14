import { CustomHeader } from 'helper/set-headers'

const header = new CustomHeader();

export class ProjectService {
    getAll(enterpriseId){
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/projects/company/${enterpriseId}`, requestOptions)
    }
    get(projectId){
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/projects/${projectId}`, requestOptions)
    }
    create(project){
        const requestOptions = {
            method: 'POST',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
            body: JSON.stringify(project)
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/projects`, requestOptions)
    }
    update(projectId, project){
        const requestOptions = {
            method: 'PUT',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
            body: JSON.stringify(project)
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/projects/${projectId}`, requestOptions)
    }
    delete(projectId){
        const requestOptions = {
            method: 'DELETE',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default'
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/projects/${projectId}`, requestOptions)
    }
}

