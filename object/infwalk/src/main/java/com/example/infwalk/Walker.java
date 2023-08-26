package com.example.infwalk;

import java.util.ArrayList;
import java.util.List;

public class Walker {

    private int walkerId;
    private String nickname;
    private List<Lecture> lectures = new ArrayList<>();

    public Walker(final int walkerId, final String nickname) {
        this.walkerId = walkerId;
        this.nickname = nickname;
    }

    public void makeLecture(Lecture lecture) {
        lectures.add(lecture);
    }

    public int getWalkerId() {
        return walkerId;
    }
}
