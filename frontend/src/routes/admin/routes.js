import CreateProject from "views/CreateProject";
import Logout from "views/Logout";
import ListProjects from "views/ListProjects";

const adminRoutes = [
  {
    path: "/create-project",
    name: "Crear Proyectos",
    icon: "pe-7s-note2",
    component: CreateProject,
    layout: "/admin"
  },
  {
    path: "/list-projects",
    name: "Listado de Proyectos",
    icon: "pe-7s-news-paper",
    component: ListProjects,
    layout: "/admin"
  },
  {
    path: "/close-session",
    name: "Cerrar Sesión",
    icon: "pe-7s-user",
    component: Logout,
    layout: "/admin"
  },
  
];

export default adminRoutes;
