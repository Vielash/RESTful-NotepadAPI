package notepadingo.model;

public class Note {
    private String noteTitle;
    private String noteContent;

    public Note(String noteTitle, String noteContent) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public String getNoteTitle() {
        return noteTitle;
    }


    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    @Override
    public String toString() {
        return "Note{title='" + noteTitle + "', content='" + noteContent + "'}";
    }
}
