package RingDemo;

import http.APIResponse;

import org.json.JSONObject;
import java.lang.String;

import platform.Platform;

import com.squareup.okhttp.RequestBody;

public class MakeCall {

    static int i = 1;
    String callerID;
    String fromNumber;
    Platform platform;

    String toNumber;

    public MakeCall(Platform platform, String fromNumber, String toNumber,
                    String callerID, int counter) {
        this.platform = platform;
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
        if (callerID == null || callerID.equals(""))
            this.callerID = "";
        else
            this.callerID = callerID;
    }

    JSONObject createJsonNumberPair(String number) {
        JSONObject pair = new JSONObject();
        pair.put("phoneNumber", number);
        return pair;
    }


    // To make a call
    public String createRingOut() throws Exception {

        JSONObject jbody = jRingOutBody();
        RequestBody body = RequestBody.create(
                Platform.ContentTypeSelection.JSON_TYPE_MARKDOWN.value, jbody
                        .toString().getBytes());
        APIResponse response = platform.sendRequest("post", "/restapi/v1.0/account/~/extension/~/ringout", body, null);

        Main.assistLog.log.debug("Calling " + i);
        if (response.ok() == true) {
            JSONObject jResJsonObject = new JSONObject(response.body().string());
            String ringoutID = jResJsonObject.get("id").toString();
            return ringoutID;
        } else {
            throw new Exception("Ringout not working as Expected!!");
        }
    }


    JSONObject jRingOutBody() {

        JSONObject jbody = new JSONObject();
        jbody.put("to", createJsonNumberPair(fromNumber));
        jbody.put("from", createJsonNumberPair(toNumber));
        jbody.put("callerId", createJsonNumberPair(callerID));
        jbody.put("playPrompt", "false");
        return jbody;
    }
}
