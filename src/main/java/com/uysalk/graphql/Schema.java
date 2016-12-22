package com.uysalk.graphql;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by uysal.kara on 22.12.2016.
 */
public class Schema {


    private static GraphQLObjectType getCharacterObjectType (){

        GraphQLObjectType simpsonCharacter = newObject()
                .name("SimpsonCharacter")
                .description("A Simpson character")
                .field(newFieldDefinition()
                        .name("name")
                        .description("The name of the character.")
                        .type(GraphQLString)
                        .dataFetcher (dataFetchingEnvironment -> "Simpson")
                )
                .field(newFieldDefinition()
                        .name("mainCharacter")
                        .description("One of the main Simpson characters?")
                        .type(GraphQLBoolean))
                .build();

        return simpsonCharacter;

    }

    public static GraphQLSchema simpsonSchema = GraphQLSchema.newSchema()
            .query(getCharacterObjectType())
            .build();



}
