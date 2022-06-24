package fr.miage.odoru.msclient.clients;

import fr.miage.odoru.msclient.clients.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("odInvolvement")
public interface InvolvementClient {
    @GetMapping(value="involvements/{idMember}/courses", produces = "application/json")
    public List<CourseDto> getCourses(@PathVariable("idMember") String idMember);

    @GetMapping(value="involvements/{idMember}/competitions", produces = "application/json")
    public List<CourseDto> getCompetitions(@PathVariable("idMember") String idMember);

    @GetMapping(value = "involvements/{idMember}/courses/startPeriod/{dateStart}/endPeriod/{dateEnd}", produces = "application/json")
    public List<CourseDto> getCoursesByPeriod(@PathVariable("idMember") String idMember, @PathVariable("dateStart") String dateStart,
                                              @PathVariable("dateEnd") String dateEnd);

    @GetMapping(value = "involvements/{idMember}/competitions/startPeriod/{dateStart}/endPeriod/{dateEnd}", produces = "application/json")
    public List<CourseDto> getCompetitionsByPeriod(@PathVariable("idMember") String idMember, @PathVariable("dateStart") String dateStart,
                                                   @PathVariable("dateEnd") String dateEnd);
}
