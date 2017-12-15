package org.myapp.event;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class MyListener implements UpdateListener {
  public void update(EventBean[] newEvents, EventBean[] oldEvents) {
    //EventBean event = newEvents[0];
	for(EventBean event : newEvents){
		System.out.println("avg(price)=" + event.get("avg(price)") + " ItemName=" + event.get("itemName"));
	}
  }
}


