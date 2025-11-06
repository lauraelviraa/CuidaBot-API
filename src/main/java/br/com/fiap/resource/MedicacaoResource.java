package br.com.fiap.resource;

import br.com.fiap.bo.MedicacaoBO;
import br.com.fiap.to.MedicacaoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/cuida-bot/medicacoes")
public class MedicacaoResource {
    private MedicacaoBO medicacaoBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid MedicacaoTO medicacao) {
        medicacaoBO = new MedicacaoBO();
        MedicacaoTO resultado = medicacaoBO.save(medicacao);
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
    public Response update(@PathParam("id") Long id, @Valid MedicacaoTO medicacao) {
        medicacaoBO = new MedicacaoBO();
        medicacao.setId(id);
        MedicacaoTO resultado = medicacaoBO.update(medicacao);
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
        medicacaoBO = new MedicacaoBO();
        Response.ResponseBuilder response = null;

        if (medicacaoBO.delete(id)) {
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
        medicacaoBO = new MedicacaoBO();
        MedicacaoTO resultado = medicacaoBO.findById(id);
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
        medicacaoBO = new MedicacaoBO();
        ArrayList<MedicacaoTO> resultado = medicacaoBO.findAll();
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
