package org.myapp.event;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class test {

  public static void main(String[] args) {

     EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
     //group by sample
 //    String expression = "select avg(price),itemName from org.myapp.event.OrderEvent.win:length(5) group by itemName output  snapshot every 4 events";
 //    EPStatement statement = epService.getEPAdministrator().createEPL(expression);

     
    //insert into sample
    String expression = "insert into tmp(price,itemName) select price, itemName from org.myapp.event.OrderEvent where price >= 100";
    EPStatement statement = epService.getEPAdministrator().createEPL(expression);
    
    String expression2 = "select price, itemName from tmp where price <= 150";
    EPStatement statement2 = epService.getEPAdministrator().createEPL(expression2);
  
     
     
//   Configuration config = new Configuration();
//    config.addEventTypeAutoName("org.myapp.event");
//    EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);

//    String epl = "select avg(price) from OrderEvent.win:time(30 sec)";
//    EPStatement statement = epService.getEPAdministrator().createEPL(epl);

      //group by sample
//    MyListener listener = new MyListener();
//    statement.addListener(listener);
    
    MyListener2 listener2 = new MyListener2();
    statement2.addListener(listener2);

    OrderEvent event = new OrderEvent("Tshirt", 74.50);
    OrderEvent event2 = new OrderEvent("skirt", 100);
    OrderEvent event3 = new OrderEvent("skirt", 125);
    OrderEvent event4 = new OrderEvent("Tshirt", 200);
    
    
    epService.getEPRuntime().sendEvent(event);
  
    epService.getEPRuntime().sendEvent(event2);
    epService.getEPRuntime().sendEvent(event3);
    epService.getEPRuntime().sendEvent(event4);
    

  }

}
