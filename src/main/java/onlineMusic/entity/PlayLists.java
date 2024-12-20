package onlineMusic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PlayLists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name = "songs_in_playlist",
            joinColumns =  @JoinColumn(name = "playlists_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> songs = new HashSet();

    public void addSong(Song song){
        this.songs.add(song);
        song.getPlayLists().add(this);
    }

    public void removeSong(Song song){
        this.songs.remove(song);
        song.getPlayLists().remove(this);
    }

}

