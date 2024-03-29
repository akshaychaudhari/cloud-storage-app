package com.akshayram.cloudstorage.mapper;

import com.akshayram.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

/*Database mapper to perform crud ops on the Notes*/

@Mapper
public interface NoteMapper {
  @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
  Note[] getNotesForUser(Integer userId);

  @Insert(
      "INSERT INTO NOTES (notetitle, notedescription, userid) "
          + "VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
  @Options(useGeneratedKeys = true, keyProperty = "noteId")
  int insert(Note note);

  @Select("SELECT * FROM NOTES")
  Note[] getNoteListings();

  @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
  Note getNote(Integer noteId);

  @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
  void deleteNote(Integer noteId);

  @Update(
      "UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
  void updateNote(Integer noteId, String title, String description);
}
