package notepadingo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.json.JsonException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import notepadingo.model.Note;
import notepadingo.service.NoteService;
import org.glassfish.jersey.server.Uri;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Path("notes")
@Produces("application/json")
public class NoteController {
    private final NoteService noteService;

    @Inject
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GET
    @Path("objects")
    public Collection<Note> getAllNoteObjectsAsJSON() {
        System.out.println("in getAllNoteObjectsAsJSON()");
        return noteService.getAllNoteObjects();
    }

    @GET
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Note getNoteByTitle(@PathParam("title") String title) {
        return noteService.getNoteByTitle(title);
    }
    @GET
    @Path("longest")
    public Note getLongestTitle() {
        return noteService.getLongestTitleNote();
    }

    @GET
    @Path("titles")
    public LinkedHashSet<String> getAllTitlesAsJson() {
        return noteService.getAllNoteTitles();
    }

    @GET
    @Path("containing/{title}")
    public boolean doesNoteExist(@PathParam("title") String title){
        return noteService.doesNoteExist(title);
    }

    @GET
    @Path("keyword/{keyword}")
    public List<Note> findNotesByKeyword(@PathParam("keyword") String keyword) { //yanlıs gelmesi halidne cevap döndür ve 404 ver
        return noteService.findNotesByKeyword(keyword);
    }

    @POST
    @Path("/{title}/{content}")
    public Response createNoteByParameter(@PathParam("title")  String noteTitle,
                                          @PathParam("content") String noteContent,
                                          @Context UriInfo uriInfo) {

        Note noteObject = new Note(noteTitle, noteContent);
        try {
            if (noteService.addNote(noteTitle, noteContent)) {

                String noteUri = uriInfo.getBaseUri() + "notes/" + noteTitle;
                URI location = new URI(noteUri);

                String jsonResponse = "{ \"uri\": \"" + location.toString() + "\" }";

                return Response.created(location).entity(jsonResponse).build();
            }

        }catch (URISyntaxException e) {
            throw new WebApplicationException("Not good URI", e, Response.Status.BAD_REQUEST);
        }

            return Response.status(Response.Status.BAD_REQUEST).entity("unable to create").build();
    }
    @PUT
    @Path("/{title}/{content}")
    public Response replaceNoteContent(@PathParam("title") String title,
                                @PathParam("content") String content,
                                @Context UriInfo uriInfo) throws JsonProcessingException {
        try {
            if(noteService.updateNoteContent(title, content)) {

                String noteUri = uriInfo.getBaseUri() + "notes/" + title;
                URI location = new URI(noteUri);

                Map<String,String> responseMap = new HashMap<>();
                responseMap.put("uri", location.toString());

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(responseMap);

                return Response.created(location).entity(jsonResponse).build();
            }
        }catch (URISyntaxException e) {
            throw new WebApplicationException("Not good URI ", e, Response.Status.BAD_REQUEST);
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("cant create").build();
    }

    @PATCH
    @Path("/{oldTitle}/{newTitle}")
    public Response updateNoteTitle(@PathParam("oldTitle") String oldTitle,
                                    @PathParam("newTitle") String newTitle,
                                    @Context UriInfo uriInfo) throws JsonProcessingException {
        try {
        if (noteService.updateNoteTitle(oldTitle,newTitle)) {

                String noteUri = uriInfo.getBaseUri() + "notes/" + newTitle;
                URI location = new URI(noteUri);

                // JSON yanıtını oluştur
                Map<String, String> responseMap = new HashMap<>();
                responseMap.put("uri", location.toString());

                // Jackson ile JSON'a dönüştür
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(responseMap);

                return Response.created(location).entity(jsonResponse).build();
            }
        }catch (URISyntaxException e) {
            throw new WebApplicationException("Bad bad URI", e, Response.Status.BAD_REQUEST);
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("cant create").build();
    }

    @DELETE
    @Path("/{title}")
    public Response deleteNote(@PathParam("title") String title, @Context UriInfo uriInfo) {
        if (noteService.deleteNoteByTitle(title)) {
            return Response.status(200).entity("Object has been deleted with that title: " + title).build();
        }
        return Response.status(400).entity("You couldnt delete sorry").build();
    }


}



