package leetCode;

import java.math.BigDecimal;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

public class JsonAliasObjectMapperProperty {
	/*
	这个注解提供了序列化和反序列化过程中该java属性所对应的名称
	@JsonAlias
	这个注解只只在反序列化时起作用，指定该java属性可以接受的更多名称
	https://www.twblogs.net/a/5d3f98d6bd9eee517422b07c/?lang=zh-cn
	*/

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
	     String a ="{\"NaMe\":\"hello\"}";
	        ObjectMapper objectMapper = new ObjectMapper();
	        Label label = objectMapper.readValue(a, Label.class);
	       Map<String, String> mapResult = objectMapper.convertValue(label, new TypeReference<Map<String, String>>() {
			});
	       System.out.println("Map >>> "+mapResult);
	        
	        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
	        HashMap<String,Object> dataMap = new HashMap<String,Object>();
	        label.amt ="12.1";
	        System.out.println("Amt: "+new BigDecimal(label.amt));
	        dataMap.put("Column1", new BigDecimal(label.amt));
	        list.add(dataMap);
	        System.out.println("List: "+list);
	        String labelString = objectMapper.writeValueAsString(label);
	        System.out.println(labelString);
	        
	        //big decimal adding
	        BigDecimal test = new BigDecimal(0);
	        System.out.println(test);
	        test = test.add(new BigDecimal(30));
	        System.out.println(test);
	        test = test.add(new BigDecimal(45));
	        System.out.println(test);

	}
	
    public static class Label{
    	//反序列化时两个都可用，都没有会报错
        @JsonAlias("NaMe") //这个注解只只在反序列化时起作用
//        @JsonProperty("NaMe")
        public String name;
        public String amt;
        public Label(){
        }
    }

}
