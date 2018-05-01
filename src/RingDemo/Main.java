package RingDemo;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.lang.String;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import platform.Platform;
import platform.Platform.Server;
import core.SDK;

import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

public class Main extends TimerTask {

	public static HashMap<String, String> configMap = new HashMap<String, String>();

	static int i = 0; //
    
	final Logger log;
	private static final org.apache.log4j.Logger logger = Logger.getLogger("RingDemo");
	public static final Main assistLog = new Main(logger);
	public Main(Logger log)
	{
		this.log=log;
	}
	public static void main(String[] args) throws Exception {

		final long startTime = System.currentTimeMillis();
		
		Main main = new Main();
        logger.log(Level.DEBUG, "Call-log start time:"+startTime);
		Timer time = new Timer(); // Timer is necessary to schedule repeated calls at fixed intervals
		time.schedule(main, 5000);

		while(i <= Integer.parseInt(configMap.get("app.counter")+1))
		{
			TimeUnit.SECONDS.sleep(3);
			logger.log(Level.DEBUG, "Call-log start time:"+startTime);
			if (i == Integer.parseInt(configMap.get("app.counter")))
			{

				logger.log(Level.DEBUG, "Application halted");
				logger.log(Level.DEBUG, "Call-log end time:"+System.currentTimeMillis());
				long elapsedTimeNs = System.currentTimeMillis() - startTime;
				logger.log(Level.DEBUG, "Total elapsed time of the app:"+(elapsedTimeNs / 1000.0));
				logger.log(Level.DEBUG, "Total records generated are:"+i);
				System.exit(0);
			}
		}
	}

	String callerID = "";
	String fromNumber = configMap.get("app.fromNumber");
	Platform platform;

	MakeCall ringout;

	SDK sdk;
	String toNumber = configMap.get("app.toNumber");

	public Main() {
		Main.configMap = LoadData.loadData();
		this.sdk = new SDK(configMap.get("app.key"),
				configMap.get("app.secret"), Server.SANDBOX);
		this.platform = sdk.platform();
		platform.login(configMap.get("app.username"),
				configMap.get("app.extension"), configMap.get("app.password"));
		this.ringout = new MakeCall(platform, configMap.get("app.fromNumber"),
				configMap.get("app.toNumber"), configMap.get("app.callerID"),
				Integer.parseInt(configMap.get("app.counter")));
	}

	@Override
	public void run() {
		//j++;
		String ringOutId = null;
		try {
			TimeUnit.SECONDS.sleep(3);
			ringoutID = ringout.createRingOut();
			TimeUnit.SECONDS.sleep(3);
			TimeUnit.SECONDS.sleep(6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

