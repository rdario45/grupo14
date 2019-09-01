import Information from "views/Information";
import CreateEnterprise from "views/CreateEnterprise";
import ListProjectsEnterprise from "views/ListProjectsEnterprise"
import CreateDesign from "views/CreateDesign";
import ListDesign from 'views/ListDesign';

const designRoutes = [
    {
        path: "/enterprise/:urlEnterprise/design/list/:projectId",
        name: "Empresa",
        icon: "pe-7s-users",
        component: ListDesign,
        layout: "/design",
        isHidden: true
    },
    {
        path: "/enterprise/:urlEnterprise/design/create/:projectId",
        name: "Empresa",
        icon: "pe-7s-users",
        component: CreateDesign,
        layout: "/design",
        isHidden: true
    },
    {
        path: "/enterprise/:urlEnterprise/design/list",
        name: "Empresa",
        icon: "pe-7s-users",
        component: ListProjectsEnterprise,
        layout: "/design",
        isHidden: true
    },
];

export default designRoutes;
