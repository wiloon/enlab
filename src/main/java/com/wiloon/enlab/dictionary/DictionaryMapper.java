package com.wiloon.enlab.dictionary;

import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.domain.ECPLog;
import com.wiloon.enlab.domain.LogType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by wiloon on 12/14/14.
 */

public interface DictionaryMapper {
    //@Select("SELECT id,english,chinese,pronunciation,load_count as count FROM tbl_ecp ecp WHERE lower(ecp.english) >= lower('$english$') ORDER BY lower(ecp.english) ASC limit 0, 20")
    @Select("SELECT id,english,chinese,pronunciation,load_count as count FROM tbl_ecp ecp WHERE lower(ecp.english) >= #{english} ORDER BY lower(ecp.english) ASC limit 0, 20")
    List<ECP> selectEnRagne(ECP ecp);

    //selectTop10
    @Select("select distinct tmp.english from (select ecp.english from tbl_log log join tbl_ecp ecp on log.id_word=ecp.id order by log.create_datetime desc limit 0,50) tmp limit 0,15")
    List<ECP> getTop10(String english);

    //selectWordCount
    @Select("select count(1) from tbl_ecp ecp")
    int getWordCount();

    //selectWordCountToday
    @Select("select count(1)		from tbl_ecp ecp where		date_format(ecp.create_datetime,'%Y/%m/%d')=date_format(now(),'%Y/%m/%d')")
    int getWordCountToday();

    //selectWordCountTodayUpdate
    @Select("select		count(1)		from tbl_ecp ecp where		date_format(ecp.update_datetime,'%Y/%m/%d')=date_format(now(),'%Y/%m/%d')")
    int getWordCountTodayUpdate();

    @Select("SELECT id,english,chinese,pronunciation,load_count as count FROM tbl_ecp ecp WHERE ecp.id = #{id}")
    ECP readWordByID(int id);

    @Update("update tbl_ecp set load_count =#{count} where id=#{id}")
    void updateEcpCount(ECP ecp);

    @Update("update tbl_ecp set english =#{english},chinese =#{chinese},pronunciation =#{pronunciation},update_datetime =now() where id=#{id}")
    void updateEcp(ECP ecp);

    //insert log
    @Insert("insert into tbl_log (id_word,log_type,message,create_datetime) values (#{wordId},#{logType},#{message},now())")
    void insertLog(ECPLog log);

    @Insert("insert into tbl_ecp (english,chinese,pronunciation,create_datetime) values (#{english},#{chinese},#{pronunciation},now())")
    void insertWord(ECP ecp);

    @Select("SELECT id,english,chinese,pronunciation,load_count as count FROM tbl_ecp ecp WHERE ecp.english = #{english}")
    ECP getWordByEnglish(String english);
}
