package com.pironline.test.models.cdc;

import com.google.gson.annotations.SerializedName;
import com.pironline.test.models.pojo.Identifiable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Payload<T extends Identifiable> {

    @SerializedName("before")
    private T before;

    @SerializedName("after")
    private T after;

    @SerializedName("op")
    private String op;
}