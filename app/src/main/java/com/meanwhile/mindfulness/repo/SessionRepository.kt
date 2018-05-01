package com.meanwhile.mindfulness.repo

import com.meanwhile.mindfulness.repo.Session


class SessionRepository() {

    /**
     * Retrieve a list of sessions
     */
    fun getSessions() : List<Session>{
        val list = ArrayList<Session>()

        list.add(Session("Introducción"))
        list.add(Session("Session 2"))
        list.add(Session("Session 3"))
        list.add(Session("Session 4"))
        list.add(Session("Session 5"))
        list.add(Session("Session 6"))
        list.add(Session("Session 7"))

        return list;
    }
}
