package com.epam.courses.jf.intro.demo;

import com.epam.courses.jf.threads.streaams.Artist;

import java.util.HashSet;
import java.util.Set;

public class Concert {

    private Set<Artist> artists;

    public Concert() {
        artists = new HashSet<>();
    }

    public Concert addArtist(Artist artist) {
        artists.add(artist);
        return this;
    }

    public Concert join(Concert concert) {
        artists.addAll(concert.artists);
        return this;
    }

    @Override
    public String toString() {
        return "Concert{ artists=" + artists + '}';
    }
}
