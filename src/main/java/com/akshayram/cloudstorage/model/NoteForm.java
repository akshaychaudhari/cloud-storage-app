package com.akshayram.cloudstorage.model;

public class NoteForm {
  private String title;
  private String description;

  /*getter and setters for the NoteForm Type*/

  public String getNoteId() {
    return noteId;
  }

  public void setNoteId(String noteId) {
    this.noteId = noteId;
  }

  private String noteId;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
