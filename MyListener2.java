package org.myapp.event;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class MyListener2 implements UpdateListener {
	  public void update(EventBean[] newEvents, EventBean[] oldEvents) {
	    //EventBean event = newEvents[0];
		for(EventBean event : newEvents){
			System.out.println("price=" + event.get("price") + " ItemName=" + event.get("itemName"));
		}
	  }
}