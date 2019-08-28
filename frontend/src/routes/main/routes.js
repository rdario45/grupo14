import Information from "views/Information";
import CreateEnterprise from "views/CreateEnterprise";
import LoginEnterprise from "views/LoginEnterprise";

const mainRoutes = [
    {
        path: "/information",
        name: "Información",
        icon: "pe-7s-albums",
        component: Information,
        layout: "/main"
    },
    {
        path: "/create-enterprise",
        name: "Crear Empresa",
        icon: "pe-7s-culture",
        component: CreateEnterprise,
        layout: "/main"
    },
    {
        path: "/login-enterprise",
        name: "Iniciar Sesión",
        icon: "pe-7s-users",
        component: LoginEnterprise,
        layout: "/main"
    },
];

export default mainRoutes;
