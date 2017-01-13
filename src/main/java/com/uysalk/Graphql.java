package com.uysalk;

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
            Map<String, Object> variables = new HashMap<>();
            try {
                payload = mapper.readValue(req.body().value(),  Map.class );
            } catch (IOException e) {
                throw new RuntimeException( "Could not parse request body as GraphQL map.",e);
            }
            if (payload.get("variables")!=null){
                variables = (Map<String, Object>)  payload.get("variables");
            }
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
