package com.hanwool.airvisual.respository.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PollutionKey {
    @PrimaryKey(autoGenerate = true)
    public long pollId;
    @ColumnInfo(name = "Pollution_name")
    public String city;
    public String air_index;
    public String classification;
    public String date_time;

}
