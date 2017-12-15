
#Esper 事件类型

##POJO

```
public class Person
{
String name;
int age;

public String getName()
{
return name;
}

public int getAge()
{
return age;
}
}
```

```
import java.util.List;
import java.util.Map;

public class Person
{
String name;
int age;
List<Child> children;
Map<String, Integer> phones;
Address address;

public String getName()
{
return name;
}

public int getAge()
{
return age;
}

public List<Child> getChildren()
{
return children;
}

public Map<String, Integer> getPhones()
{
return phones;
}

public Address getAddress()
{
return address;
}

}

class Child
{
String name;
int gender;
// 省略getter方法
}

class Address
{
String road;
String street;
int houseNo;
// 省略getter方法
}
```
```
// 当Person类型的事件中name为luonanqin时，Esper能得到对应的age,children和address
select age,children,address from Person where name="luonanqin" 

```

##Map

```

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;


public class PersonMap
{
public static void main(String[] args)
{
EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
EPAdministrator admin = epService.getEPAdministrator();

// Person定义
Map<String,Object> person = new HashMap<String,Object>();
person.put("name", String.class);
person.put("age", int.class);
person.put("children", List.class);
person.put("phones", Map.class);

// 注册Person到Esper
admin.getConfiguration().addEventType("Person", person);
}
}
```


##Object Array

```

import java.util.Arrays;
import java.util.Map;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EventType;

public class PersonArray
{

/**
* @param args
*/
public static void main(String[] args)
{
EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
EPAdministrator admin = epService.getEPAdministrator();

// Address定义
String[] addressPropNames = { "road", "street", "houseNo" };
Object[] addressPropTypes = { String.class, String.class, int.class };

// Child定义
String[] childPropNames = { "name", "age" };
Object[] childPropTypes = { String.class, int.class };

// Person定义
String[] personPropNames = { "name", "age", "children", "phones", "address" };
Object[] personPropTypes = { String.class, int.class, "Child[]", Map.class, "Address" };

// 注册Address到Esper
admin.getConfiguration().addEventType("Address", addressPropNames, addressPropTypes);
// 注册Child到Esper
admin.getConfiguration().addEventType("Child", childPropNames, childPropTypes);
// 注册Person到Esper
admin.getConfiguration().addEventType("Person", personPropNames, personPropTypes);

// 新增一个gender属性
admin.getConfiguration().updateObjectArrayEventType("Person", new String[] { "gender" }, new Object[] { int.class });

/** 输出结果：
* Person props: [name, age, children, phones, address, gender]
*/
EventType event = admin.getConfiguration().getEventType("Person");
System.out.println("Person props: " + Arrays.asList(event.getPropertyNames()));
}
}
```
