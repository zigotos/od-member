package fr.miage.odoru.msclient.clients.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Slot implements Serializable {
    @Field
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateStart;

    @Field
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    // TODO régler formule
    //@Formula("value = date_add(date_start,INTERVAL term_in_minutes MINUTE)")
    private Date dateEnd;

    @Field
    private int termInMinutes;
}