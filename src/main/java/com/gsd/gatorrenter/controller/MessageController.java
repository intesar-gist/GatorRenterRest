package com.gsd.gatorrenter.controller;

import com.gsd.gatorrenter.authentication.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

/**
 * Created by Intesar on 3/5/2017.
 */
@Path("/message")
@Component
public class MessageController extends BaseController {

    @Autowired
    Authentication authentication;


}
