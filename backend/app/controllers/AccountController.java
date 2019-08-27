package controllers;

import play.Logger;
import play.mvc.Result;

import static play.mvc.Results.ok;

public class AccountController {

    /**
     * POST
     * @return
     */
    public Result createAccount() {
        return ok();
    }

    /**
     * GET
     * @return
     */
    public Result existsAccount(String email) {
        Logger.debug(email);
        return ok();
    }
}
