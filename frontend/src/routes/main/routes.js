import Information from "views/Information.jsx";
import CreateEnterprise from "views/CreateEnterprise.jsx";

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
];

export default mainRoutes;