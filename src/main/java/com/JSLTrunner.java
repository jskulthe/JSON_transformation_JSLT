package com;

import com.schibsted.spt.data.jslt.Parser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schibsted.spt.data.jslt.Expression;

public class JSLTrunner {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		
		long start = System.currentTimeMillis();
		
		String json = "{\"items\": {\"item\": [{\"id\": \"0001\",\"type\": \"donut\"	},	{\"id\": \"1001\",\"type\": \"Regular\"	},	{\"id\": \"1002\",\"type\": \"Chocolate\"	},	{\"id\": \"1003\",\"type\": \"Blueberry\"	},	{\"id\": \"5001\",\"type\": \"None\"	},	{\"id\": \"5002\",\"type\": \"Glazed\"	},	{\"id\": \"5005\",\"type\": \"Sugar\"	},	{\"id\": \"5007\",\"type\": \"Powdered Sugar\"	},	{\"id\": \"5006\",\"type\": \"Chocolate with Sprinkles\"	},	{\"id\": \"5003\",\"type\": \"Chocolate\"	},	{\"id\": \"5004\",\"type\": \"Maple\"	}]	}}";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode input = objectMapper.readTree(json);
		Expression jslt = Parser.compileString("{ for(.items.item)  .id : .type}");
		JsonNode output = null;
		
		for (int i = 0; i < 100; i++) {
			 output = jslt.apply(input);
		}

		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
		System.out.println(sec + " seconds");
		System.out.println("JSLT Demo");
		String opjson = objectMapper.writeValueAsString(output);
		System.out.println(opjson);

	}
}
