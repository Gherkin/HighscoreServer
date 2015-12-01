package com.github.gherkin;

import com.google.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("")
public class HighscoreResource {
    @Inject
    HighscoreService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Highscore postHighScore(Highscore highscore) {
        if(highscore.getName() == null || highscore.getScore() == null || highscore.getId() == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        service.save(highscore);
        return highscore;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{mapId}")
    public List<Highscore> getTop(@PathParam("mapId") Integer mapId) {
        if(mapId == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        return service.getTop(mapId);
    }
}
