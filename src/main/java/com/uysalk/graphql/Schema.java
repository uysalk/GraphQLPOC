package com.uysalk.graphql;

import com.uysalk.graphql.model.CartoonCharacter;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import java.util.HashMap;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by uysal.kara on 22.12.2016.
 */
public class Schema {



    private static HashMap<String, CartoonCharacter> characters  = new HashMap<String, CartoonCharacter>();
    static{
        characters.put("1", new CartoonCharacter("Bart", false));
        characters.put("2", new CartoonCharacter("Lisa", false));

    }


    private static GraphQLObjectType getCharacterObjectType (){

        return newObject()
                .name("SimpsonCharacter")
                .description("A Simpson character")
                .field(newFieldDefinition()
                        .name("name")
                        .description("The name of the character.")
                        .type(GraphQLString)
                )
                .field(newFieldDefinition()
                        .name("mainCharacter")
                        .description("One of the main Simpson characters?")
                        .type(GraphQLBoolean))
                .build();

    }

    private static GraphQLObjectType queryType = newObject()
            .name("QueryType")
            .field(newFieldDefinition()
                    .name("cartoonCharacter")
                    .type(getCharacterObjectType())
                    .argument(newArgument()
                            .name("id")
                            .description("id of the character")
                            .type(GraphQLString))
                    .dataFetcher(dfe -> { String id = (String) dfe.getArguments().get("id"); return characters.get(id);} ))
            .build();

    public static GraphQLSchema simpsonSchema = GraphQLSchema.newSchema()
            .query(queryType)
            .build();



}
