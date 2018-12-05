
package be.kdg.prog3.cycling.dto;

import be.kdg.prog3.cycling.model.Rider;
import be.kdg.prog3.cycling.model.Team;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//https://www.programcreek.com/java-api-examples/?code=kdg-ti/programmeren3/programmeren3-master/cycling_hibernate/src/main/java/be/kdg/prog3/cycling/dto/DtoAssembler.java#
@Component
public class DtoAssembler {
    private final ModelMapper mapper;

    public DtoAssembler() {
        this.mapper = new ModelMapper();

        /*
        TODO: This (more elegant) solution doesn't seem to be working:
        this.mapper.addConverter((MappingContext<Rider, RiderDto> context) -> {
            Rider rider = context.getSource();
            RiderDto riderDto = context.getDestination();
            riderDto.setStageWins(rider.getStageResults().size());
            return riderDto;
        });*/

        this.mapper.addMappings(new PropertyMap<Rider, RiderDto>() {
            @Override
            protected void configure() {
                map().setStageWins(source.getStageResults().size());
            }
        });
    }

    public RiderDto toResource(Rider rider) {
        return mapper.map(rider, RiderDto.class);
    }

    public List<RiderDto> toRiderResources(List<? extends Rider> entities) {
        return entities.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }

    public TeamDto toResource(Team team) {
        return mapper.map(team, TeamDto.class);
    }

    public List<TeamDto> toTeamResources(List<? extends Team> entities) {
        return entities.stream()
                .map(this::toResource)
                .collect(Collectors.toList());
    }
}