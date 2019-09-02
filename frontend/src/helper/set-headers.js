export class CustomHeader {
    getAuthorization() {
        var headers = this.getWithoutAuthorization();
        headers.append('Authorization', `${localStorage.getItem('token')}`);
        return headers;
    }
    getWithoutAuthorization() {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return headers;
    }
}