package com.uysalk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uysalk.graphql.Schema;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.jooby.Jooby;
import org.jooby.MediaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by uysal.kara on 22.12.2016.
 */
public class Graphql extends Jooby  {
    private final GraphQL graphql;

    {


        graphql = new GraphQL(Schema.simpsonSchema);


        use("/").produces(MediaType.json).post(req -> {


            Map<String, Object> payload = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();

            try {
                payload = mapper.readValue(req.body().value(), new TypeReference<Map<String, String>>(){});
            } catch (IOException e) {
                throw new RuntimeException( "Could not parse request body as GraphQL map.");
            }
            Map<String, Object> variables = mapper.readValue( payload.get("variables").toString(), new TypeReference<Map<String, Object>>(){});
            //(Map<String, Object>) payload.get("variables");
            ExecutionResult executionResult = graphql.execute(payload.get("query").toString(), null, this, variables);
            Map<String, Object> result = new LinkedHashMap<>();
            if (!executionResult.getErrors().isEmpty()) {
                result.put("ERRORS", executionResult.getErrors());
            }
            result.put("data", executionResult.getData());
            return result;
        });


    }
}
