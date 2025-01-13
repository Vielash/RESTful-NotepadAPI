package notepadingo.repository;

import notepadingo.model.Note;

import java.util.*;

public interface INoteRepository {

    //Read only
    Note getNoteByTitle(String noteTitle);
    LinkedHashSet<String> getAllNoteTitles();
    Collection<Note> getAllNoteObjects();
    Map<String,String> getAllNotes();
    //

    //CRUD without read
    boolean addNote(String newTitle, String newContent);
    boolean deleteNoteByTitle(String noteTitle);
    boolean updateNoteContent(String checkTitle, String newContent);
    boolean updateNoteTitle(String checkTitle, String newTitle);
    boolean doesNoteExist(String noteTitle);
    //








}
