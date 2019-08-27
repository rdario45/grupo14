import Information from "views/Information";
import CreateEnterprise from "views/CreateEnterprise";
import InformationEnterprise from  "views/InformationEnterprise"

const designRoutes = [
    {
        path: "/enterprise/:urlEnterprise",
        name: "Empresa",
        icon: "pe-7s-users",
        component: InformationEnterprise,
        layout: "/design",
        isHidden: true
    },
];

export default mainRoutes;