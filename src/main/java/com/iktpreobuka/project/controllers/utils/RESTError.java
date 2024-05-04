package com.iktpreobuka.project.controllers.utils;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;

//2.1 kreirati novi paket
//com.iktpreobuka.project.controllers.util
//• u okviru paketa kreirati RESTError klasu koja kao atribute sadrži
//kod greške i poruku greške
//• treba da bude vidljiva od strane svih pogleda

public class RESTError {
    @JsonView(Views.Public.class)
    public Integer code;
    @JsonView(Views.Public.class)
    public String message;

    public RESTError() {
        super();
    }

    public RESTError(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /*
     * getter & setters are NOT necessary to have
     */

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
