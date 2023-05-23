
docker, spring boot, kafka shovel by trendyol, kafka, postgres, debezium, go 


https://docs.google.com/document/d/1_8nl7MSc_6lZk9XwYXqdSXEIHn481njg21mza8PL2W4/edit?usp=sharing




outbox
{
"name": "outbox-pattern",
"config": {
"connector.class": "io.debezium.connector.postgresql.PostgresConnector",
"tasks.max": "1",
"database.hostname": "db",
"database.port": "5432",
"database.user": "postgres",
"database.password": "example",
"database.dbname": "Kafka",
"database.server.name": "postgres",
"topic.prefix": "maran",
"table.include.list": "public.outbox",
"tombstones.on.delete": "false",
"table.field.event.payload": "payload",
"skipped.operations": "u,t,d",
"key.converter": "org.apache.kafka.connect.json.JsonConverter",
"key.converter.schemas.enable": "false",
"value.converter.schemas.enable": "false",
"transforms": "outbox",
"transforms.outbox.type": "io.debezium.transforms.outbox.EventRouter",
"transforms.outbox.table.expand.json.payload": "true",
"transforms.outbox.route.topic.replacement":"${routedByValue}",
"value.converter": "org.apache.kafka.connect.json.JsonConverter",
"route.topic.regex":""
}
}











{
"name":"default",
"config":{
"connector.class":"io.debezium.connector.postgresql.PostgresConnector",
"database.hostname":"db",
"database.port":"5432",
"database.user":"postgres",
"database.password":"example",
"topic.prefix": "maran",
"database.dbname":"Kafka",
"database.server.name":"postgres",
"schema.include.list":"public",
"table.whitelist":"public.outbox"
}
}
