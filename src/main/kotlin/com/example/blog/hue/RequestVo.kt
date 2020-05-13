package com.example.blog.hue

import org.json.simple.JSONObject

//class RequestVo(var lights_state: String)
class RequestVo(
    var version: String,
    var action: Action,
    var event: Event
)

class Action(
    var actionName: String,
    var parameters: MutableMap<String, MutableMap<String, String>>
)
class Event(
    var type: String
)



