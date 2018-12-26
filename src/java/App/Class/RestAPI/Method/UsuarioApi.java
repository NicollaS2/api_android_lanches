/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Class.RestAPI.Method;

import App.Class.RestAPI.Config.Json;
import App.Class.Controllers.UsuarioController;
import App.Class.Model.Usuario;
import App.Class.Util.TrueFalseResponse;
import App.Class.error.ApiError;
import App.Class.error.LibError;
import App.Class.error.PiError;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Nicollas
 */
@Path("User")
public class UsuarioApi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public UsuarioApi() {
    }

    @Path("cadastrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirUsuario(String bodyJson) throws IOException {

        try {

            Usuario dados = Json.toObject(bodyJson, Usuario.class);

            int retorno = new UsuarioController().inserirUsuario(dados);
            boolean resposta = false;
            if (retorno != 0) {
                resposta = true;
            }
            return Response.status(200).entity(Json.toJson(new TrueFalseResponse(resposta))).build();

        } catch (PiError error) {
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(Json.toJson(apierror)).build();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "UsuarioApi:inserirUsuario",
                    "api para inserção de dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(Json.toJson(apierror)).build();

        }

    }

    /*@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String bodyJson) throws IOException {
        
        System.out.println(bodyJson);
        Usuario usuario; 
        usuario = Json.toObject(bodyJson, Usuario.class);
        
        System.out.println(usuario.getSenha());
    }*/
}
