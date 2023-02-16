package com.a402.audiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Musicmate {
    @Id
    @Column(name = "musicmate_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mate_id")
    private User mateUser;

    @Column(name = "is_mate")
    @ColumnDefault("1")
    private boolean isMate;

    @Column(name = "is_block")
    private boolean isBlock;

    public long getId() {
        return id;
    }
    public User getUser() { return user; }
    public User getMate() {
        return mateUser;
    }
    public boolean isMate() {
        return isMate;
    }
    public void setMate(boolean mate) { isMate = mate;}
    public boolean isBlock() {
        return isBlock;
    }
    public void setBlock(boolean block) { isBlock = block;}

    @Override
    public String toString(){
        return "Musicmate{" +
                "id=" + id +
                ", user=" + user +
                ", mate=" + mateUser +
                ", isMate=" + isMate +
                ", isBlock=" + isBlock+
                '}';
    }
}
