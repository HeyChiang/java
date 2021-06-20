package com.chiang.elastic.entity;

import lombok.Data;
import org.elasticsearch.common.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "entities")
@Setting(
        sortFields = {"secondField", "firstField"},
        sortModes = {Setting.SortMode.max, Setting.SortMode.min},
        sortOrders = {Setting.SortOrder.desc, Setting.SortOrder.asc},
        sortMissingValues = {Setting.SortMissing._last, Setting.SortMissing._first},
        replicas = 0)
@Data
class Entity {
    @Nullable
    @Id
    private String id;

    @Nullable
    @Field(name = "first_field", type = FieldType.Keyword)
    private String firstField;

    @Nullable
    @Field(name = "second_field", type = FieldType.Keyword)
    private String secondField;


}