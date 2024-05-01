package com.pironline.test.models.pojo;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Company implements Identifiable {

    @SerializedName("id")
    private UUID id;

    @SerializedName("short_name")
    private String shortName;

    @SerializedName("long_name")
    private String longName;

    @SerializedName("description")
    private String description;

    @SerializedName("inn")
    private String inn;
}
