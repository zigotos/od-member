package fr.miage.odoru.msclient.clients.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Slot implements Serializable {
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateStart;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    // TODO régler formule
    //@Formula("value = date_add(date_start,INTERVAL term_in_minutes MINUTE)")
    private Date dateEnd;

    @Column
    private int termInMinutes;
}