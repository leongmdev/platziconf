package com.example.conf.view.adapter

import model.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}