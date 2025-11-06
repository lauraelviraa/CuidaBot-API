package br.com.fiap.resource;

import br.com.fiap.bo.AgendamentoBO;
import br.com.fiap.to.AgendamentoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/cuida-bot/agendamentos")
public class AgendamentoResource {
    private AgendamentoBO agendamentoBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid AgendamentoTO agendamento) {
        agendamentoBO = new AgendamentoBO();
        AgendamentoTO resultado = agendamentoBO.save(agendamento);
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
    public Response update(@PathParam("id") Long id, @Valid AgendamentoTO agendamento) {
        agendamentoBO = new AgendamentoBO();
        agendamento.setId(id);
        AgendamentoTO resultado = agendamentoBO.update(agendamento);
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
        agendamentoBO = new AgendamentoBO();
        Response.ResponseBuilder response = null;

        if (agendamentoBO.delete(id)) {
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
        agendamentoBO = new AgendamentoBO();
        AgendamentoTO resultado = agendamentoBO.findById(id);
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
        agendamentoBO = new AgendamentoBO();
        ArrayList<AgendamentoTO> resultado = agendamentoBO.findAll();
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
