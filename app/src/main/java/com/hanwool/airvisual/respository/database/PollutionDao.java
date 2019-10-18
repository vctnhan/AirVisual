package com.hanwool.airvisual.respository.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;
@Dao
public interface PollutionDao {

    @Insert(onConflict = REPLACE)
    void insertPollution(PollutionKey pollutionKey);
    @Insert(onConflict = IGNORE)
    void insertOrReplacePollution(PollutionKey pollutionKey);

    @Update(onConflict = REPLACE)
    void updatePollution(PollutionKey pollutionKey);

    @Query("DELETE FROM PollutionKey")
    void deleteAll();

    @Query("SELECT * FROM PollutionKey")
    public List<PollutionKey> findAllPollutionSync();
}
