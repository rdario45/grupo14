import { CustomHeader } from 'helper/set-headers'

const header = new CustomHeader();

export class ProjectService {
    getAll(enterpriseId, page){
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/api/projects/company/${enterpriseId}?page=${page}`, requestOptions)
    }
    get(projectId){
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/api/projects/${projectId}`, requestOptions)
    }
    create(project){
        const requestOptions = {
            method: 'POST',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
            body: JSON.stringify(project)
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/api/projects`, requestOptions)
    }
    update(projectId, project){
        const requestOptions = {
            method: 'PUT',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
            body: JSON.stringify(project)
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/api/projects/${projectId}`, requestOptions)
    }
    delete(projectId){
        const requestOptions = {
            method: 'DELETE',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default'
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/api/projects/${projectId}`, requestOptions)
    }
}

