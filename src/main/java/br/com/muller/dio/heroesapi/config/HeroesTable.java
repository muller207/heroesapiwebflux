package br.com.muller.dio.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static br.com.muller.dio.heroesapi.constants.HeroesConstants.ENDPOINT_DYNAMO;
import static br.com.muller.dio.heroesapi.constants.HeroesConstants.REGION_DYNAMO;

@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "Heroes_Table";

        Table table = dynamoDB.createTable(tableName,
                Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
                new ProvisionedThroughput(5l, 5l));
        try {
            table.waitForActive();

        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
