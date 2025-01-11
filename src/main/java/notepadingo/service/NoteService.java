package notepadingo.service;

import notepadingo.exception.NoteAlreadyExistException;
import notepadingo.exception.NoteNotFoundException;
import notepadingo.exception.NoteServiceException;
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

        if(noteTitle.length() > 45) {
            throw new IllegalArgumentException("Note's title can not be more than 45 character.");
        }

        if(noteTitle == null || noteTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Note's title can not be empty or null");
        }

        try {
            Note choosenNote = repository.getNoteByTitle(noteTitle);
            if (choosenNote == null) {
                throw new NoteNotFoundException("Note with \"" + noteTitle + "\"title not found.");
            }
            return choosenNote;
        }

        catch (RuntimeException e) {
            throw new NoteServiceException("Unexpected ERROR.",e);
        }
    }

    @Override
    public Set<String> getAllNoteTitles(){
        Set<String> allNoteTitles = repository.getAllNoteTitles();

            return allNoteTitles;
    }

    @Override
    public Collection<Note> getAllNoteObjects(){
        Collection<Note> allNoteObjects = repository.getAllNoteObjects();

        if (allNoteObjects == null) {
            throw new NullPointerException("Here you go you got NULL OBJECT.");
        }
        return allNoteObjects;
    }

    @Override
    public boolean addNote(String newTitle, String newContent) {
        if(newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Note's title can not be empty or null");
        }
        boolean isNoteAdded =  repository.addNote(newTitle,newContent);

        if (!isNoteAdded) {
            throw new NoteAlreadyExistException("This is has been used.");
        }
        return isNoteAdded;
    }

    @Override
    public boolean deleteNoteByTitle(String noteTitle) throws NoteNotFoundException, IllegalArgumentException {
        if (noteTitle == null || noteTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Note's title can not be empty or null");
        }

        try {
            boolean isDeleted = repository.deleteNoteByTitle(noteTitle); // Silme işlemi repository seviyesinde yapılır
            if (!isDeleted) {
                throw new NoteNotFoundException("Note with title '" + noteTitle + "' not found.");
            }
            return isDeleted;
        } catch (ConcurrentModificationException e) {
            throw new NoteServiceException("Error occurred while trying to delete the note. Please try again.", e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting the note.", e);
        }
    }


    @Override
    public boolean updateNoteContent(String checkTitle, String newContent) {
        if (checkTitle == null || checkTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Note's title cannot be empty or null.");
        }

        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("New content cannot be empty or null.");
        }

        try {
            boolean isUpdated = repository.updateNoteContent(checkTitle, newContent);
            if (!isUpdated) {
                throw new NoteNotFoundException("Note with title '" + checkTitle + "' not found.");
            }
            return true;
        } catch (ConcurrentModificationException e) {
            throw new NoteServiceException("A concurrency issue occurred while updating the note.", e);
        } catch (Exception e) {
            throw new NoteServiceException("An unexpected error occurred while updating the note.", e);
        }
    }


    @Override
    public boolean updateNoteTitle(String currentTitle, String newTitle) {
        if (currentTitle == null || currentTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Current title cannot be empty or null.");
        }

        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("New title cannot be empty or null.");
        }

        if (newTitle.length() > 45) {
            throw new IllegalArgumentException("New title cannot exceed 45 characters.");
        }

        try {
            boolean isUpdated = repository.updateNoteTitle(currentTitle, newTitle);
            if (!isUpdated) {
                throw new NoteNotFoundException("Note with title '" + currentTitle + "' not found, or the new title '" + newTitle + "' is already in use.");
            }
            return true;
        } catch (ConcurrentModificationException e) {
            throw new NoteServiceException("A concurrency issue occurred while updating the note title.", e);
        } catch (Exception e) {
            throw new NoteServiceException("WHAT AN ERROR", e);
        }
    }


    @Override
    public boolean doesNoteExist(String noteTitle) {
        if (noteTitle == null || noteTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Note's title can not be empty or null.");
        }

        try {
            return repository.doesNoteExist(noteTitle);
        }
        catch (Exception e) {
            throw new RuntimeException("Unexpected error while checking if note exists.", e);
        }
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
