package notepadingo.repository;

import notepadingo.model.Note;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//This code has more performance but not clean as second code

public class NoteRepository implements INoteRepository {

    public static Map<String, Note> titleToNoteMap = new ConcurrentHashMap<>();
    private Collection<Note> noteObjectCollection = titleToNoteMap.values();

    static {
        Note note = new Note("First", "Herkese merhabalar");
        titleToNoteMap.put("First", note);

        Note note1 = new Note("Second", "Konichiva all of you");
        titleToNoteMap.put(note1.getNoteTitle(),note1);
    }

    @Override
    public Note getNoteByTitle(String noteTitle) {
        return titleToNoteMap.get(noteTitle);
    }

    @Override
    public Map<String,String> getAllNotes() {
        Map<String,String> titlesAndNotes = new ConcurrentHashMap<>();
        for (Map.Entry<String, Note> entry : titleToNoteMap.entrySet()) {
            String title = entry.getKey();
            String content = entry.getValue().getNoteContent();
            titlesAndNotes.put(title, content);
        }
        return titlesAndNotes;
    }

    @Override
    public LinkedHashSet<String> getAllNoteTitles() {
        return new LinkedHashSet<>(titleToNoteMap.keySet());
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

    public boolean deleteNoteByTitle(String noteTitle) {
        if (titleToNoteMap.containsKey(noteTitle)) {
            titleToNoteMap.remove(noteTitle);
            return true;
        }
        return false;    }

    @Override
    public boolean updateNoteContent(String checkTitle, String newContent) {
        Note choosenNote = getNoteByTitle(checkTitle);
        if (choosenNote != null) {
            titleToNoteMap.remove(checkTitle);
            titleToNoteMap.put(checkTitle, new Note(checkTitle,newContent));
            return true;
        }
        return false;
    }

    @Override
    public boolean updateNoteTitle(String checkTitle, String newTitle) {
        Note choosenNote = getNoteByTitle(checkTitle);
        if (choosenNote != null) {
            choosenNote.setNoteTitle(newTitle);
            titleToNoteMap.put(newTitle, choosenNote);
            titleToNoteMap.remove(checkTitle);

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
