package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId} AND userid = #{userid}")
    File getFile(Integer fileId, Integer userid);

    @Select("SELECT fileId, filename FROM FILES WHERE userid = #{userid}")
    @MapKey("fileId")
    List<File> getFiles(Integer userid);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)"
            + " VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(File file);

    @Select("SELECT EXISTS(SELECT 1 FROM FILES WHERE filename = #{filename} AND userid = #{userid})")
    boolean isFile(String filename, Integer userid);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId} AND userid = #{userid}")
    Integer deleteFile(Integer fileId, Integer userid);
}