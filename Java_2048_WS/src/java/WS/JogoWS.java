/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import modelos.GameCommands;
import utils.Arquivo;

@Path("/")
public class JogoWS {

    String filePath = "C:\\logWS\\comando.txt";

    public String RESPONSE_ERROR = "{"+"\"Commands Available"+"\":" + 
            " \n\t\t["+ "\n\t\tUp," 
            + "\n\t\tDown," + "\n\t\tLeft," 
            + "\n\t\tRight," + "\n\t\tRestart," 
            + "\n\t\tUndo," + "\n\t\tRedo," 
            + "\n\t\tBonus" + "\n]}";

    @Context
    private UriInfo context;

    public JogoWS() {
    }

    @GET
    @Produces({"application/json"})
    @Path("withdraw")
    public String withdraw() {
        String content = Arquivo.Read(filePath);
        return content;
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Path("insert")
    public String insert(String content) {

        System.out.println("Content: " + content);

        Gson g = new Gson();
        GameCommands gc = (GameCommands) g.fromJson(content, GameCommands.class);

        if (gc.getOption() == null || gc.getOption().trim().equals("") || !gc.validMove()) {
            throw new WebApplicationException(Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(RESPONSE_ERROR).build());
        }   

        boolean writed = Arquivo.Write(filePath, content);
        System.out.println(writed);

        return content;
    }
}
