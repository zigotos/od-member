package fr.miage.odoru.msclient.clients.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.miage.odoru.msclient.clients.models.Slot;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto implements Serializable {
    private Long id;
    private String title;
    private int level;
    private Slot slot;
    private Long teacher;
    private String locality;
    private boolean presence;
    private double result;
}
