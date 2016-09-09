/*
 * Copyright (C) 2016, Nable Communications, Inc.
 * All rights reserved.
 * 
 * This software is confidential and proprietary information
 * of Nable Communications, Inc.
 */
package com.nextinno.doshare.api;

import io.jsonwebtoken.Claims;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rsjung
 *
 */
@RestController
@RequestMapping("/api")
public class APIController {
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "role/{role}", method = RequestMethod.GET)
    public Boolean login(@PathVariable final String role,
            final HttpServletRequest request) throws ServletException {
        final Claims claims = (Claims) request.getAttribute("claims");

        return ((List<String>) claims.get("roles")).contains(role);
    }
}