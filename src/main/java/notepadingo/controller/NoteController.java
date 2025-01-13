package notepadingo.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import notepadingo.model.Note;
import notepadingo.service.NoteService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Path("{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Note getNoteByTitle(@PathParam("title") String title) {
        return noteService.getNoteByTitle(title);
    }
}



