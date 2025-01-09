package notepadingo.model;

public class Note {
    private int id;
    private String noteTitle;
    private String noteContent;

    public Note(String noteTitle, String noteContent) {
        this.noteTitle = noteContent;
        this.noteContent = noteContent;
    }

    public int getId() {
        return id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    @Override
    public String toString() {
        return "Note{id=" + id + ", title='" + noteTitle + "', content='" + noteContent + "'}";
    }
}
