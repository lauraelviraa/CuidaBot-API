package br.com.fiap.resource;

import br.com.fiap.bo.ExameBO;
import br.com.fiap.to.ExameTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/cuida-bot/exames")
public class ExameResource {
    private ExameBO exameBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ExameTO exame) {
        exameBO = new ExameBO();
        ExameTO resultado = exameBO.save(exame);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, @Valid ExameTO exame) {
        exameBO = new ExameBO();
        exame.setId(id);
        ExameTO resultado = exameBO.update(exame);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        exameBO = new ExameBO();
        Response.ResponseBuilder response = null;

        if (exameBO.delete(id)) {
            response = Response.status(204);
        } else {
            response = Response.status(404);
        }
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        exameBO = new ExameBO();
        ExameTO resultado = exameBO.findById(id);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        exameBO = new ExameBO();
        ArrayList<ExameTO> resultado = exameBO.findAll();
        Response.ResponseBuilder response = null;

        if (!resultado.isEmpty()) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }
}
