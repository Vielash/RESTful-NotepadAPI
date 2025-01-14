package notepadingo.service;

import jakarta.ejb.Singleton;
import jakarta.enterprise.context.ApplicationScoped;
import notepadingo.exception.NoteAlreadyExistException;
import notepadingo.exception.NoteNotFoundException;
import notepadingo.exception.NoteServiceException;
import notepadingo.model.Note;
import notepadingo.repository.NoteRepository;

import java.util.*;

import static notepadingo.repository.NoteRepository.titleToNoteMap;

@ApplicationScoped
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
                throw new NoteNotFoundException("Note with \"" + noteTitle + "\" title not found.");
            }
            return choosenNote;
        }

        catch (RuntimeException e) {
            throw new NoteServiceException("Unexpected ERROR.",e);
        }
    }

    @Override
    public Map<String,String> getAllNotes() {
        return repository.getAllNotes();
    }

    @Override
    public LinkedHashSet<String> getAllNoteTitles(){
        LinkedHashSet<String> allNoteTitles = repository.getAllNoteTitles();

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
             repository.updateNoteTitle(currentTitle, newTitle);
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
        TreeSet<String> sortedByLenghtSet = new TreeSet<>(Comparator.comparingInt(String::length).reversed());
        sortedByLenghtSet.addAll(repository.getAllNoteTitles());

        String firstTitle = null;

        Iterator<String> iterator = sortedByLenghtSet.iterator();
        if(iterator.hasNext()) {
             firstTitle = iterator.next();
        }

        if(firstTitle == null) {
            throw new IllegalArgumentException("There isn't any note in here");
        }
        else
            return repository.getNoteByTitle(firstTitle);
    }

    @Override
    public List<Note> findNotesByKeyword(String keyword) {
        List<Note> result = new ArrayList<>();
        for (Note note : titleToNoteMap.values()) {
            if (note.getNoteTitle().contains(keyword) || note.getNoteContent().contains(keyword)) {
                result.add(note);
            }
        }
        return result;
    }
}
