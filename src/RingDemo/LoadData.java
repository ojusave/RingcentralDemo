package RingDemo;

import java.util.HashMap;

// Properties required to execute a call


public class LoadData {
	HashMap<String,String> configMap=new HashMap<>();

	public static HashMap<String, String> setData() {
		configMap.put("app.key", "0kfdmVtlRxmXkYuFKCZ-UA");
		configMap.put("app.secret", "Nc2roxI7Qk26gAKRVOEdYgtgV-z6tKR96YHl2MrL7ENw");
		configMap.put("app.username", "+13128589407");
		configMap.put("app.extension", "101");
		configMap.put("app.password", "Ojus-9420");
		configMap.put("app.fromNumber","+13128589407");
		configMap.put("app.toNumber", "+14093380401");

		configMap.put("app.counter","3");
		Main.assistLog.log.debug("Done loading data!");
		return configMap;
	}
}
