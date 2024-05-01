package com.pironline.test.models.cdc;

import com.google.gson.annotations.SerializedName;
import com.pironline.test.models.pojo.Identifiable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CdcEvent<T extends Identifiable> {

    @SerializedName("payload")
    private Payload<T> payload;
}


