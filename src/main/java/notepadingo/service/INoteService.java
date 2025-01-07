package notepadingo.service;

import notepadingo.model.Note;

import java.util.List;
import java.util.Set;

public interface INoteService {

    //Read only
    Note getNoteByTitle(String title);
    Set<String> getAllNoteTitles();
    List<Note> getAllNotes();
    //

    //CRUD without read
    boolean addNote(String title, String content);
    boolean updateNoteContent(String title, String newContent);
    boolean updateNoteTitle(String oldTitle, String newTitle);
    boolean deleteNoteByTitle(String title);
    boolean contains(String title);
    //

    //Specific Operations
    Note getLongestTitleNote();
    boolean addNoteIfContentValid(String title, String content);
    List<Note> findNotesByKeyword(String keyword);
    //
}
