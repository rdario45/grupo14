export function setHeader() {
    var headers = new Headers();
        headers.append('Authorization', `${localStorage.getItem('token')}`);
        headers.append('Content-Type', 'application/json');
}