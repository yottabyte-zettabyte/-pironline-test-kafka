package com.pironline.test.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationType {

    INSERT("c", "INSERT"),
    UPDATE("u", "UPDATE"),
    DELETE("d", "DELETE");

    private final String op;
    private final String logType;
}
