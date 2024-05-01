package com.pironline.test.models.pojo;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee implements Identifiable {

    @SerializedName("id")
    private UUID id;

    @SerializedName("deleted")
    private boolean deleted;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("snils")
    private String snils;

    @SerializedName("birth_date")
    private LocalDate birthDate;

    @SerializedName("gender")
    private String gender;

    @SerializedName("company_id")
    private UUID companyId;

    @SerializedName("title_id")
    private Integer titleId;

    @SerializedName("start_date")
    private LocalDate startDate;

    @SerializedName("leave_date")
    private LocalDate leaveDate;

    @SerializedName("salary_amount")
    private BigDecimal salaryAmount;
}
