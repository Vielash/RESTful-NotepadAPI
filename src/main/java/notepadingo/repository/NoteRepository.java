package notepadingo.repository;

import notepadingo.model.Note;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class NoteRepository implements INoteRepository{

    private static List<Note> noteObjectList = new ArrayList<>();

    private void loopingNoteObjectList() {
        for (Note note : noteObjectList) {

        }
    }


    @Override
    public Note getNoteByTitle(String title) {
        for (Note note : noteObjectList) {
            if (note.getNoteTitle().equals(title)) {
                return note;
            }
        }
        return null;
    }

    @Override
    public Set<String> getAllNoteTitles() {
        Set<String> allNoteTitles = new HashSet<>();

        for(Note note : noteObjectList) {
            allNoteTitles.add(note.getNoteTitle());
        }
        return allNoteTitles;
    }

    @Override
    public List<Note> getAllNoteObjects() {
        return noteObjectList;
    }

    @Override
    public boolean addNote(String newTitle, String newContent) {
        if(!noteExist(newTitle)) {
            noteObjectList.add(new Note(newTitle, newContent));
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteNoteByTitle(String title) {
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
    public boolean noteExist(String title) {
        int flag = 0;

        for (Note note : noteObjectList) {
            if (note.getNoteTitle().equals(title))
                flag = 1;
        }
        if(flag == 0)
            return false;
        else
            return true;
        }



}
