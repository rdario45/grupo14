import CreateProject from "views/CreateProject";
import DetailsProject from "views/DetailsProject";
import Logout from "views/Logout";
import ListProjects from "views/ListProjects";

const adminRoutes = [
  {
    path: "/project/create",
    name: "Crear Proyectos",
    icon: "pe-7s-note2",
    component: CreateProject,
    layout: "/admin"
  },
  {
    path: "/project/details/:projectId/:isReadonly",
    name: "Editar Proyectos",
    icon: "pe-7s-note2",
    component: DetailsProject,
    layout: "/admin",
    isHidden: true
  },
  {
    path: "/project/list",
    name: "Listado de Proyectos",
    icon: "pe-7s-news-paper",
    component: ListProjects,
    layout: "/admin"
  },
  {
    path: "/user/logout",
    name: "Cerrar Sesión",
    icon: "pe-7s-user",
    component: Logout,
    layout: "/admin"
  },
  
];

export default adminRoutes;
