package controllers;

import play.Logger;
import play.mvc.Result;

import static play.mvc.Results.ok;

public class ProjectController {

    /**
     * GET
     * @return
     */
    public Result findAllProjects() {
        return ok();
    }

    /**
     * GET
     * @param id
     * @return
     */
    public Result findProject(int id){
        Logger.debug(String.valueOf(id));
        return ok();
    }

    /**
     * POST
     * @return
     */
    public Result createProject() {
        return ok();
    }

    /**
     * PUT
     * @param id
     * @return
     */
    public Result updateProject(int id) {
        Logger.debug(String.valueOf(id));
        return ok();
    }

    /**
     * DELETE
     * @param id
     * @return
     */
    public Result deleteProject(int id) {
        Logger.debug(String.valueOf(id));
        return ok();
    }
}
