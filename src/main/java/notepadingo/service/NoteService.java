package notepadingo.service;

import notepadingo.exception.NoteNotFoundException;
import notepadingo.model.Note;
import notepadingo.repository.NoteRepository;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Set;


public class NoteService implements INoteService {

    NoteRepository repository = new NoteRepository();


    @Override
    public Note getNoteByTitle(String noteTitle) throws NoteNotFoundException, IllegalArgumentException {
        String titleWithoutGap = noteTitle.trim();
        if(noteTitle.length() > 45) {
            throw new IllegalArgumentException("Note's title can not be more than 45 character.");
        }
        if(noteTitle == null || titleWithoutGap.isEmpty()) {
            throw new IllegalArgumentException("Note's title can not be empty or null");
        }
        try {
            Note choosenNote = repository.getNoteByTitle(noteTitle);
            if (choosenNote == null) {
                throw new NoteNotFoundException("Note with" + noteTitle + "title not found.");
            }
            return choosenNote;
        }
        catch (ConcurrentModificationException e){
            throw new RuntimeException("You got error while accessing the notes.");
        }
        catch (Exception e) {
            throw new RuntimeException("Unexpected ERROR.");
        }
    }

    @Override
    public Set<String> getAllNoteTitles() throws ConcurrentModificationException {
        Set<String> allNoteTitles = repository.getAllNoteTitles();

        try {
            return allNoteTitles;
        }
        catch(ConcurrentModificationException e) {
            throw new RuntimeException("You got error while accessing the notes or notes didnt even created.");
        }
    }

    @Override
    public Collection<Note> getAllNotes() {
        return null;
    }

    @Override
    public boolean addNote(String newTitle, String newContent) {
        return false;
    }

    @Override
    public boolean deleteNoteByTitle(String noteTitle) {
        return false;
    }

    @Override
    public boolean updateNoteContent(String checkTitle, String newContent) {
        return false;
    }

    @Override
    public boolean updateNoteTitle(String checkTitle, String newTitle) {
        return false;
    }

    @Override
    public boolean doesNoteExist(String noteTitle) {
        return false;
    }

    @Override
    public Note getLongestTitleNote() {
        return null;
    }

    @Override
    public boolean addNoteIfContentValid(String noteTitle, String content) {
        return false;
    }

    @Override
    public List<Note> findNotesByKeyword(String keyword) {
        return null;
    }
}
