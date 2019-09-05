import { CustomHeader } from 'helper/set-headers'

const header = new CustomHeader();

export class DesignService {
    getAll(projectId, page) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`/api/designs/project/${projectId}?page=${page}`, requestOptions)
    }
    getAllProcessed(projectId, page) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`/api/designs/project/${projectId}/status/AVAILABLE?page=${page}`, requestOptions)
    }
    get(designId) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`/api/designs/${designId}`, requestOptions)
    }
    downloadOriginal(designId) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`/api/designs/${designId}/download`, requestOptions)
    }
    downloadProcessed(designId) {
        const requestOptions = {
            method: 'GET',
            headers: header.getWithoutAuthorization(),
            //headers: header.getAuthorization(),
            cache: 'default',
        };
        return fetch(`/api/designs/${designId}/download-resized`, requestOptions)
    }
    create(designs) {
        const requestOptions = {
            method: 'POST',
            headers: new Headers(),
            //headers: header.getAuthorization(),
            cache: 'default',
            body: designs
        };
        return fetch(`/api/designs`, requestOptions)
    }
}

