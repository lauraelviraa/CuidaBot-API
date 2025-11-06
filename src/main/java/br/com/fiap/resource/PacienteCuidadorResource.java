package br.com.fiap.resource;

import br.com.fiap.bo.PacienteCuidadorBO;
import br.com.fiap.to.PacienteCuidadorTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/cuida-bot/pacientes-cuidadores")
public class PacienteCuidadorResource {
    private PacienteCuidadorBO pacienteCuidadorBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid PacienteCuidadorTO pacienteCuidador) {
        pacienteCuidadorBO = new PacienteCuidadorBO();
        PacienteCuidadorTO resultado = pacienteCuidadorBO.save(pacienteCuidador);
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
    public Response update(@PathParam("id") Long id, @Valid PacienteCuidadorTO pacienteCuidador) {
        pacienteCuidadorBO = new PacienteCuidadorBO();
        pacienteCuidador.setId(id);
        PacienteCuidadorTO resultado = pacienteCuidadorBO.update(pacienteCuidador);
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
        pacienteCuidadorBO = new PacienteCuidadorBO();
        Response.ResponseBuilder response = null;

        if (pacienteCuidadorBO.delete(id)) {
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
        pacienteCuidadorBO = new PacienteCuidadorBO();
        PacienteCuidadorTO resultado = pacienteCuidadorBO.findById(id);
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
        pacienteCuidadorBO = new PacienteCuidadorBO();
        ArrayList<PacienteCuidadorTO> resultado = pacienteCuidadorBO.findAll();
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
