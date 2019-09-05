import { CustomHeader } from 'helper/set-headers'

const header = new CustomHeader();

export class DesignService {
    getAll(projectId) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/designs/project/${projectId}`, requestOptions)
    }
    getAllProcessed(projectId) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/designs/project/${projectId}/status/AVAILABLE`, requestOptions)
    }
    get(designId) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/designs/${designId}`, requestOptions)
    }
    downloadOriginal(designId) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/designs/${designId}/download`, requestOptions)
    }
    downloadProcessed(designId) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/designs/${designId}/download-resized`, requestOptions)
    }
    create(designs) {
        const requestOptions = {
            method: 'POST',
            headers: new Headers(),
            //headers: header.getAuthorization(),
            cache: 'default',
            body: designs
        };
        return fetch(`${process.env.REACT_APP_BASE_API}/designs`, requestOptions)
    }
}

