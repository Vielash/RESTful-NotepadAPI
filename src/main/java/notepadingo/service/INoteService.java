package notepadingo.service;

import notepadingo.model.Note;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface INoteService {

    //Read only
    Note getNoteByTitle(String noteTitle);
    Set<String> getAllNoteTitles();
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

    //Specific Operations
    Note getLongestTitleNote();
    List<Note> findNotesByKeyword(String keyword);
    //
}
