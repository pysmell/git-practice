package com.example.jacksonpackage;

import com.example.jacksonpackage.entity.AccountBean;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@SuppressWarnings("unchecked")
public class JacksonTest {

    private JsonGenerator jsonGenerator = null;

    private ObjectMapper objectMapper = null;

    private AccountBean bean = null;

    @Test
    public void run() {
        System.out.println("@Test");
    }

    @Before
    public void init() {
        System.out.println("Before");
        bean = new AccountBean();
        bean.setAddress("china");
        bean.setEmail("382394323@qq.com");
        bean.setId(1);
        bean.setName("linqw");

        objectMapper = new ObjectMapper();
        try {
            jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void destory() {
        try {
            if (jsonGenerator != null) {
                jsonGenerator.flush();
            }
            if (!jsonGenerator.isClosed()) {
                jsonGenerator.close();
            }
            jsonGenerator = null;
            objectMapper = null;
            bean = null;
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * java对象转json
     */
    @Test
    public void writeEntityJSON() throws IOException {
        System.out.println("jsonGenerator");
        jsonGenerator.writeObject(bean);
        System.out.println();
        System.out.println("objectMapper");
        //objectMapper 可以有三个参数 ①输出流、file、jsonGenerator②要转换的类③config，转换的规则，指定的Java对象的某些属性进行过滤或转换
        objectMapper.writeValue(System.out, bean);
    }

    /**
     * 将map集合转换为json字符串
     */
    @Test
    public void writeMapJSON() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", bean.getName());
        map.put("account", bean);
        bean = new AccountBean();
        bean.setAddress("china-beijin");
        bean.setEmail("382394323@qq.com");
        map.put("account2",bean);
        System.out.println("jsonGenerator");
        jsonGenerator.writeObject(map);
        System.out.println("");
        System.out.println("objectMapper");
        objectMapper.writeValue(System.out, map);
    }

    /**
     * 将list集合转换为json字符串
     */
    @Test
    public void writeListJSON() throws IOException {
        List<AccountBean> list = new ArrayList<>();
        list.add(bean);

        bean = new AccountBean();
        bean.setId(2);
        bean.setAddress("address2");
        bean.setEmail("email2");
        bean.setName("haha2");
        list.add(bean);
        System.out.println("jsonGenerator");
        jsonGenerator.writeObject(list);
        System.out.println();
        System.out.println("objectMapper");
        //用objectMapper直接返回list转换成的JSON字符串
        System.out.println("1###" + objectMapper.writeValueAsString(list));
        System.out.println("#222");
        //objectMapper list转换成JSON字符串
        objectMapper.writeValue(System.out, list);
    }

    @Test
    public void writeOthersJSON() throws IOException {
        String[] arr = {"a", "b", "c"};
        System.out.println("jsonGenerator");
        String str = "hello world jackson!";
        byte[] bytes = str.getBytes();
        //byte
        jsonGenerator.writeBinary(bytes);
        //boolean
        jsonGenerator.writeBoolean(true);
        //null
        jsonGenerator.writeNull();
        //float
        jsonGenerator.writeNumber(2.2F);
        //char
        jsonGenerator.writeRaw("c");
        //String
        jsonGenerator.writeRaw(str, 5, 10);
        //String,index:5 length:5
        jsonGenerator.writeRaw(str, 5, 5);
        //String
        jsonGenerator.writeString(str);
        jsonGenerator.writeTree(JsonNodeFactory.instance.pojoNode(str));
        System.out.println();

        //object
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectFieldStart("user");
        jsonGenerator.writeStringField("name", "jackson");
        jsonGenerator.writeBooleanField("sex", true);
        jsonGenerator.writeNumberField("age", 22);
        jsonGenerator.writeEndObject();

        jsonGenerator.writeArrayFieldStart("infos");
        jsonGenerator.writeNumber(22);
        jsonGenerator.writeString("this is array");
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();

        AccountBean bean = new AccountBean();
        bean.setAddress("address");
        bean.setEmail("email");
        bean.setId(1);
        bean.setName("haha");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("user", bean);
        jsonGenerator.writeObjectField("infos", arr);
        jsonGenerator.writeEndObject();
    }

    /**
     * JSON转换成JavaBean对象
     */
    @Test
    public void readJsonEntity() throws IOException {
        String json = "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}";
        AccountBean accountBean = objectMapper.readValue(json, AccountBean.class);
        System.out.println(accountBean.getName());
        System.out.println(accountBean);
    }

    /**
     * 将json字符串转换成List<Map>集合
     */
    @Test
    public void readJsonListMap() throws IOException {
        String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
                "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
        List<LinkedHashMap<String, Object>> list = objectMapper.readValue(json, List.class);
        for (int i = 0;i < list.size();i++) {
            Map<String, Object> map = list.get(i);
            Set<String> set = map.keySet();
            for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
                String key = iterator.next();
                System.out.println(key + ":" + map.get(key));
            }
        }
    }

    @Test
    public void readJson2Array() {
        String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
                "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
        try {
            AccountBean[] arr = objectMapper.readValue(json, AccountBean[].class);
            System.out.println(arr.length);
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readJson2Map() {
        String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
                "\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
        try {
            Map<String, Map<String, Object>> maps = objectMapper.readValue(json, Map.class);
            System.out.println(maps.size());
            Set<String> key = maps.keySet();
            Iterator<String> iter = key.iterator();
            while (iter.hasNext()) {
                String field = iter.next();
                System.out.println(field + ":" + maps.get(field));
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@Test
    public void writeObject2Xml() {
        //stax2-api-3.0.2.jar
        System.out.println("XmlMapper");
        XmlMapper xml = new XmlMapper();

        try {
            //javaBean转换成xml
            //xml.writeValue(System.out, bean);
            StringWriter sw = new StringWriter();
            xml.writeValue(sw, bean);
            System.out.println(sw.toString());
            //List转换成xml
            List<AccountBean> list = new ArrayList<AccountBean>();
            list.add(bean);
            list.add(bean);
            System.out.println(xml.writeValueAsString(list));

            //Map转换xml文档
            Map<String, AccountBean> map = new HashMap<String, AccountBean>();
            map.put("A", bean);
            map.put("B", bean);
            System.out.println(xml.writeValueAsString(map));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}





































