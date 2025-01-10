package notepadingo.repository;

import notepadingo.model.Note;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//This code has more performance but not clean as second code

public class NoteRepository implements INoteRepository {

    private static Map<String, Note> titleToNoteMap = new ConcurrentHashMap<>();
    private Collection<Note> noteObjectCollection = titleToNoteMap.values();

    @Override
    public Note getNoteByTitle(String noteTitle) {
        return titleToNoteMap.get(noteTitle);
    }

    @Override
    public Set<String> getAllNoteTitles() throws ConcurrentModificationException {
        return titleToNoteMap.keySet();
    }

    @Override
    public Collection<Note> getAllNoteObjects() {
        return noteObjectCollection;
    }

    @Override
    public boolean addNote(String newTitle, String newContent) {
        if (!titleToNoteMap.containsKey(newTitle)) {
            titleToNoteMap.put(newTitle, new Note(newTitle, newContent));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteNoteByTitle(String noteTitle) {
        return titleToNoteMap.remove(noteTitle) != null;
    }

    @Override
    public boolean updateNoteContent(String checkTitle, String newContent) {
        Note choosenNote = getNoteByTitle(checkTitle);
        if (choosenNote != null) {
            choosenNote.setNoteContent(newContent);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateNoteTitle(String checkTitle, String newTitle) {
        Note choosenNote = getNoteByTitle(checkTitle);
        if (choosenNote != null) {
            titleToNoteMap.remove(checkTitle);

            choosenNote.setNoteTitle(newTitle);
            titleToNoteMap.put(newTitle, choosenNote);
            return true;
        }
        return false;
    }
    @Override
    public boolean doesNoteExist(String noteTitle) {
        return titleToNoteMap.containsKey(noteTitle);
    }
}




//This code is cleaner than first code

//package notepadingo.repository;
//
//import notepadingo.model.Note;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class NoteRepository implements INoteRepository{
//
//    private static List<Note> noteObjectList = new ArrayList<>();
//
//   @Override
//   public Note getNoteByTitle(String noteTitle) {
//    if (doesNoteExist(noteTitle)) {
//        for (Note note : noteObjectList) {
//            if (note.getNoteTitle().equals(noteTitle)) {
//                return note;
//            }
//        }
//    }
//    return null;
// }
//
//    @Override
//    public Set<String> getAllNoteTitles() {
//        Set<String> allNoteTitles = new HashSet<>();
//
//        for(Note note : noteObjectList) {
//            allNoteTitles.add(note.getNoteTitle());
//        }
//        return allNoteTitles;
//    }
//
//    @Override
//    public List<Note> getAllNoteObjects() {
//        return noteObjectList;
//    }
//
//    @Override
//    public boolean addNote(String newTitle, String newContent) {
//        if(!doesNoteExist(newTitle)) {
//            noteObjectList.add(new Note(newTitle, newContent));
//            return true;
//        }
//        else
//            return false;
//    }
//
//    @Override
//    public boolean deleteNoteByTitle(String noteTitle) {
//        if(doesNoteExist(noteTitle)) {
//            noteObjectList.remove(getNoteByTitle(noteTitle));
//            return true;
//        }
//        else
//            return false;
//    }
//
//
//    @Override
//    public boolean updateNoteContent(String checkTitle, String newContent) {
//        if(doesNoteExist(checkTitle)) {
//            getNoteByTitle(checkTitle).setContent(newContent);
//            return true;
//        }
//        else
//            return false;
//    }
//
//    @Override
//    public boolean updateNoteTitle(String checkTitle, String newTitle) {
//        if(doesNoteExist(checkTitle)) {
//            getNoteByTitle(checkTitle).setTitle(newTitle);
//            return true;
//        }
//        else
//            return false;
//    }
//
//    @Override
//    public boolean doesNoteExist(String noteTitle) {
//        for (Note note : noteObjectList) {
//            if (note.getNoteTitle().equals(noteTitle)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//}
