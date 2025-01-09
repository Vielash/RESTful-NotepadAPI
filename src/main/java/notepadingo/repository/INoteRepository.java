package notepadingo.repository;

import notepadingo.model.Note;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface INoteRepository {

    //Read only
    Note getNoteByTitle(String title);
    Set<String> getAllNoteTitles();
    Collection<Note> getAllNoteObjects();
    //

    //CRUD without read
    boolean addNote(String newTitle, String newContent);
    boolean deleteNoteByTitle(String title);
    boolean updateNoteContent(String checkTitle, String newContent);
    boolean updateNoteTitle(String checkTitle, String newTitle);
    boolean doesNoteExist(String title);
    //








}
