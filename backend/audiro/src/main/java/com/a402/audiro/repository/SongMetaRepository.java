package com.a402.audiro.repository;

import com.a402.audiro.entity.Song;
import com.a402.audiro.entity.SongMeta;
import com.a402.audiro.entity.Spot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongMetaRepository extends Repository<SongMeta, Long> {

    void save(SongMeta songMeta);

    @Query(value = "select * from SongMeta where SongMeta.song_id = :song_id and SongMeta.spot_id = :spot_id",
            nativeQuery = true)
    SongMeta findBySongIdAndSpotId(@Param("song_id") long SongId, @Param("spot_id")long SpotId);

    @Query(value = "select * from SongMeta join Song on SongMeta.song_id=Song.song_id where SongMeta.spot_id = :spot_id order by gift_cnt DESC limit 10",
            nativeQuery = true)
    List<SongMeta> findBySpotIdByGiftCnt(@Param("spot_id")long SpotId);

    @Query(value = "select * from SongMeta join song on SongMeta.song_id=Song.song_id where SongMeta.spot_id = :spot_id order by update_time DESC limit 10",
            nativeQuery = true)
    List<SongMeta> findBySpotIdByTime(@Param("spot_id")long SpotId);

    @Query(value = "select * from SongMeta join Song on SongMeta.song_id=Song.Song_id where SongMeta.spot_id = :spot_id order by RAND() limit 10",
            nativeQuery = true)
    List<SongMeta> findBySpotIdByRandom(@Param("spot_id")long SpotId);

    SongMeta findBySongAndSpot(Song song, Spot spot);
}
