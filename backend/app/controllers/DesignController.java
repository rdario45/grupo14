package controllers;

import play.Logger;
import play.mvc.Result;

import static play.mvc.Results.ok;

public class DesignController {


    /**
     * GET
     * @return
     */
    public Result findAllDesigns() {
        return ok();
    }

    /**
     * GET
     * @return
     */
    public Result findDesign(int id) {
        Logger.debug(String.valueOf(id));
        return ok();
    }

    /**
     * POST
     * @return
     */
    public Result createDesign() {
        return ok();
    }


}
