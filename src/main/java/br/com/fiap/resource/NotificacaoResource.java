package br.com.fiap.resource;

import br.com.fiap.bo.NotificacaoBO;
import br.com.fiap.to.NotificacaoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/cuida-bot/notificacoes")
public class NotificacaoResource {
    private NotificacaoBO notificacaoBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid NotificacaoTO notificacao) {
        notificacaoBO = new NotificacaoBO();
        NotificacaoTO resultado = notificacaoBO.save(notificacao);
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
    public Response update(@PathParam("id") Long id, @Valid NotificacaoTO notificacao) {
        notificacaoBO = new NotificacaoBO();
        notificacao.setId(id);
        NotificacaoTO resultado = notificacaoBO.update(notificacao);
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
        notificacaoBO = new NotificacaoBO();
        Response.ResponseBuilder response = null;

        if (notificacaoBO.delete(id)) {
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
        notificacaoBO = new NotificacaoBO();
        NotificacaoTO resultado = notificacaoBO.findById(id);
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
        notificacaoBO = new NotificacaoBO();
        ArrayList<NotificacaoTO> resultado = notificacaoBO.findAll();
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
