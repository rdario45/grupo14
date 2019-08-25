export function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if ([401, 403].indexOf(response.status) !== -1) {
                this.context.router.history.push("/login");
                return null;
            }
        }
        return data;
    });
}