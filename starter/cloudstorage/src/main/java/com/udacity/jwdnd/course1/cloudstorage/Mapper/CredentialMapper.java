package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getAllCredentials(Integer userid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid)"
            + " VALUES(#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    Integer insert(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password}"
            + " WHERE credentialid = #{credentialid} AND userid = #{userid}")
    Integer update(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid} AND userid = #{userid}")
    Integer delete(Integer credentialid, Integer userid);
}
