package com.example.blog.hue

import com.google.gson.Gson
import org.json.simple.JSONObject
import org.springframework.http.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.io.Reader
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths

@RestController
class HueController {

    //    @PostMapping("/answer.hueoff")
//    fun hueLightsOff(): ResponseVo {
//        println("##### answer.hueoff")
//
//        val rest: RestTemplate = RestTemplate()
//        val headers: HttpHeaders = HttpHeaders()
//        headers.contentType = MediaType.APPLICATION_JSON
//        var json: JSONObject = JSONObject()
//        json.put("on", false)
//
//        val uri: URI = URI.create("http://localhost:8000/api/newdeveloper/lights/2/state")
//        val entity: HttpEntity<JSONObject> = HttpEntity(json, headers)
//        val responseEntity: ResponseEntity<String> = rest.exchange(uri, HttpMethod.PUT, entity, String::class.java)
//        println("##### res: " + responseEntity.body)
//
//        return ResponseVo("OK")
//    }
    @PostMapping("/answer.hue_lights_state")
    fun hueLightsState(@RequestBody json: JSONObject): ResponseVo {
        var output: MutableMap<String, String> = mutableMapOf<String, String>()

        println("##### answer.hue_lights_state - json: " + json.toJSONString())
//        val reader: Reader = Files.newBufferedReader(Paths.get(worklinPath))

        val gson: Gson = Gson()
        val map = gson.fromJson<Map<Any, Any>>(json.toJSONString(), MutableMap::class.java)

        var amap: MutableMap<Any, Any> = map["action"] as MutableMap<Any, Any>
        var bmap = amap.filterKeys { it!!.equals("parameters") }["parameters"] as MutableMap<Any, Any>
        var cmap = bmap.filterKeys { it!!.equals("light_state") }["light_state"] as MutableMap<Any, Any>
        var dmap = cmap.filterKeys { it!!.equals("value") }["value"] as String

        output.put("light_state", dmap)

        //        var cmapId= bmap.filterKeys { it!!.equals("light_id") }["light_id"] as MutableMap<Any, Any>
        //        var dmapId= cmapId.filterKeys { it!!.equals("value") }["value"] as String

        var cmapId = bmap.filterKeys { it!!.equals("light_id") }["light_id"] as? MutableMap<Any, Any>
        var dmapId = ""
        if (cmapId != null) {
            dmapId = cmapId?.filterKeys { it!!.equals("value") }["value"] as String
            output.put("light_id", dmapId)
        }

        var on: Boolean = when (dmap) {
            "켜" -> true
            "꺼" -> false
            else -> false
        }

        try {
            if (dmapId.isNotEmpty()) {
                callLightState(on, dmapId)
            } else {
                //            lights obj의 child 개수만큼 callLightState 호출
                val uri: URI = URI.create("https://nugu-test.herokuapp.com/api/newdeveloper/lights")

                val rest: RestTemplate = RestTemplate()
                val responseEntity: ResponseEntity<String> = rest.getForEntity(uri, String::class.java)

                val gson: Gson = Gson()
                val map = gson.fromJson<Map<Any, Any>>(responseEntity.body, MutableMap::class.java)

//                var amap: MutableMap<Any, Any> = map["lights"] as MutableMap<Any, Any>

//                for (i in 1..amap.size) {
                for (i in 1..map.size) {
                    callLightState(on, i.toString())
                }
            }

            return ResponseVo("2.0","OK", output)
        } catch (e: Exception) {
            println("##### answer.hue_lights_state error - output: " + output.toString())
            println(e.printStackTrace())
//            try {
//                println("##### answer.hue_lights_state retry...")
//                return ResponseVo("2.0","OK", output)
//            } catch (e: Exception) {
            return ResponseVo("2.0","STATE_ERROR", output)
//            }
        }
    }

    private fun callLightState(on: Boolean, id: String) {
        var json: JSONObject = JSONObject()
        json.put("on", on)

        val headers: HttpHeaders = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val entity: HttpEntity<JSONObject> = HttpEntity(json, headers)

        var uriStr = "https://nugu-test.herokuapp.com/api/newdeveloper/lights/LIGHT_ID/state"
        uriStr = uriStr.replace("LIGHT_ID", id)
        val uri: URI = URI.create(uriStr)
        println("##### uriStr: " + uriStr)
        val rest: RestTemplate = RestTemplate()
        val responseEntity: ResponseEntity<String> = rest.exchange(uri, HttpMethod.PUT, entity, String::class.java)
        println("##### res: " + responseEntity.body)
    }
}