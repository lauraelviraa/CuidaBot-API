package br.com.fiap.resource;

import br.com.fiap.bo.CuidadorBO;
import br.com.fiap.to.CuidadorTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/cuida-bot/cuidadores")
public class CuidadorResource {
    private CuidadorBO cuidadorBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid CuidadorTO cuidador) {
        cuidadorBO = new CuidadorBO();
        CuidadorTO resultado = cuidadorBO.save(cuidador);
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
    public Response update(@PathParam("id") Long id, @Valid CuidadorTO cuidador) {
        cuidadorBO = new CuidadorBO();
        cuidador.setId(id);
        CuidadorTO resultado = cuidadorBO.update(cuidador);
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
        cuidadorBO = new CuidadorBO();
        Response.ResponseBuilder response = null;

        if (cuidadorBO.delete(id)) {
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
        cuidadorBO = new CuidadorBO();
        CuidadorTO resultado = cuidadorBO.findById(id);
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
        cuidadorBO = new CuidadorBO();
        ArrayList<CuidadorTO> resultado = cuidadorBO.findAll();
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
